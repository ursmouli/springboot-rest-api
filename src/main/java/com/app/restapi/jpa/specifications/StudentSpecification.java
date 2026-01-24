package com.app.restapi.jpa.specifications;

import com.app.restapi.jpa.entity.Student;
import jakarta.persistence.criteria.Predicate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.domain.Specification;

public class StudentSpecification {

    private static final Logger logger = LoggerFactory.getLogger(StudentSpecification.class);

    public static Specification<Student> hasSearchText(String searchText) {
        return ((root, query, cb) -> {
            if (searchText == null || searchText.isEmpty()) {
                return cb.conjunction();
            }

            String pattern = "%" + searchText.toLowerCase().trim() + "%";

            logger.info("Student search pattern '{}'", pattern);

            Predicate firstNameLike = cb.like(cb.lower(root.get("firstName").as(String.class)), pattern);
            Predicate lastNameLike = cb.like(cb.lower(root.get("lastName").as(String.class)), pattern);
            Predicate registrationNumberLike = cb.like(cb.lower(root.get("registrationNumber").as(String.class)), pattern);

            return cb.or(firstNameLike, lastNameLike, registrationNumberLike);
        });
    }
}
