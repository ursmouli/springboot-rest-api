package com.app.restapi.jpa.specifications;

import com.app.restapi.jpa.entity.Section;
import jakarta.persistence.criteria.Predicate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.domain.Specification;

public class SectionSpecification {

    private static final Logger logger = LoggerFactory.getLogger(SectionSpecification.class);

    public static Specification<Section> hasSearchText(String searchTerm) {
        return ((root, query, cb) -> {
            if (searchTerm == null || searchTerm.isEmpty()) {
                return cb.conjunction();
            }

            String pattern = "%" + searchTerm.toLowerCase().trim() + "%";

            logger.info("Section search pattern '{}'", pattern);

            Predicate nameLike = cb.like(cb.lower(root.get("name").as(String.class)), pattern);

            return cb.or(nameLike);
        });
    }

    public static Specification<Section> hasSearchField(String searchTerm) {
        return ((root, query, cb) -> {
            if (searchTerm == null || !searchTerm.contains(":")) {
                return cb.conjunction();
            }

            String field = searchTerm.split(":")[0];
            String value = searchTerm.split(":")[1];

            String pattern = "%" + value.toLowerCase().trim() + "%";

            logger.info("Section search field {} and value '{}'", field, pattern);

            // department and role are supported

            if (field.equalsIgnoreCase("name")) {
                return cb.like(cb.lower(root.get("name").as(String.class)), pattern);
            } else {
                return cb.conjunction();
            }
        });
    }
}
