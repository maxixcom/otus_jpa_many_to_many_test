package com.example.demo.specification;

import com.example.demo.domain.Category;
import com.example.demo.domain.Item;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Join;
import java.util.Set;

public class ItemSpecification {
    public static Specification<Item> searchByName(String name) {
        return (root, query, criteriaBuilder) -> {
            return criteriaBuilder.like(root.get("name"), "%" + name + "%");
        };
    }

    public static Specification<Item> searchByIds(Set<Long> ids) {
        return (root, query, criteriaBuilder) -> {
            return root.get("id").in(ids);
        };
    }

    public static Specification<Item> searchByCategoryName(String categoryName) {
        return (root, query, criteriaBuilder) -> {
            query.distinct(true);
            Join<Item, Category> categories = root.join("categories");
            return criteriaBuilder.like(categories.get("name"), "%" + categoryName + "%");
        };
    }
}
