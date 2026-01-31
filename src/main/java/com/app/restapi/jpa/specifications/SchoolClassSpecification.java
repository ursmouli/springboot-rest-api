package com.app.restapi.jpa.specifications;

import com.app.restapi.jpa.entity.Employee;
import com.app.restapi.jpa.entity.SchoolClass;
import jakarta.persistence.criteria.Predicate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.domain.Specification;

public class SchoolClassSpecification {

    private static final Logger logger = LoggerFactory.getLogger(SchoolClassSpecification.class);

    public static Specification<SchoolClass> hasSearchText(String searchTerm) {
        return ((root, query, cb) -> {
            if (searchTerm == null || searchTerm.isEmpty()) {
                return cb.conjunction();
            }

            String pattern = "%" + searchTerm.toLowerCase().trim() + "%";

            logger.info("Student search pattern '{}'", pattern);

            Predicate nameLike = cb.like(cb.lower(root.get("name").as(String.class)), pattern);
            Predicate academicYearLike = cb.like(cb.lower(root.get("academicYear").as(String.class)), pattern);

            return cb.or(nameLike, academicYearLike);
        });
    }

    public static Specification<SchoolClass> hasSearchField(String searchTerm) {
        return ((root, query, cb) -> {
            if (searchTerm == null || !searchTerm.contains(":")) {
                return cb.conjunction();
            }

            String field = searchTerm.split(":")[0];
            String value = searchTerm.split(":")[1];

            String pattern = "%" + value.toLowerCase().trim() + "%";

            logger.info("SchoolClass search field {} and value '{}'", field, pattern);

            // department and role are supported

            if (field.equalsIgnoreCase("name")) {
                return cb.like(cb.lower(root.get("role").as(String.class)), pattern);
            } else {
                return cb.conjunction();
            }
        });
    }

}
