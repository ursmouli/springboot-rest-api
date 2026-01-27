package com.app.restapi.jpa.specifications;

import com.app.restapi.jpa.entity.Employee;
import jakarta.persistence.criteria.Predicate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.domain.Specification;

public class EmployeeSpecification {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeSpecification.class);

    public static Specification<Employee> hasSearchText(String searchTerm) {
        return ((root, query, cb) -> {
            if (searchTerm == null || searchTerm.isEmpty()) {
                return cb.conjunction();
            }

            String pattern = "%" + searchTerm.toLowerCase().trim() + "%";

            logger.info("Employee search pattern '{}'", pattern);

            Predicate firstNameLike = cb.like(cb.lower(root.get("firstName").as(String.class)), pattern);
            Predicate lastNameLike = cb.like(cb.lower(root.get("lastName").as(String.class)), pattern);
            Predicate employeeNumber = cb.like(cb.lower(root.get("employeeNumber").as(String.class)), pattern);

            return cb.or(firstNameLike, lastNameLike, employeeNumber);
        });
    }

    public static Specification<Employee> hasSearchField(String searchTerm) {
        return ((root, query, cb) -> {
            if (searchTerm == null || !searchTerm.contains(":")) {
                return cb.conjunction();
            }

            String field = searchTerm.split(":")[0];
            String value = searchTerm.split(":")[1];

            String pattern = "%" + value.toLowerCase().trim() + "%";

            logger.info("Employee search field {} and value '{}'", field, pattern);

            // department and role are supported

            if (field.equalsIgnoreCase("department")) {
                return cb.like(cb.lower(root.join("department").get("name")), pattern);
            } else  if (field.equalsIgnoreCase("role")) {
                return cb.like(cb.lower(root.get("role").as(String.class)), pattern);
            } else {
                return cb.conjunction();
            }
        });
    }
}
