package com.saesig.domain.faq;

import org.springframework.data.jpa.domain.Specification;

public class FaqSpecification {

    public static Specification<Faq> hasContent(String content) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(root.<String>get("content"), "%" + content + "%");
    }

    public static Specification<Faq> hasTitle(String title) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(root.<String>get("title"), "%" + title + "%");
    }

    public static Specification<Faq> isEnabled(Character isEnabled) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("isEnabled"), isEnabled);
    }

    public static Specification<Faq> hasCategory(FaqCategory category) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("category"), category.getValue());
    }
}
