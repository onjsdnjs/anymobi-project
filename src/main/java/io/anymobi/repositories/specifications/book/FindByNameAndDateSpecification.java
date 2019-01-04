package io.anymobi.repositories.specifications.book;


import io.anymobi.entity.Book;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDateTime;

public class FindByNameAndDateSpecification implements Specification<Book> {
    private final Book book;

    public FindByNameAndDateSpecification(Book book) {
        this.book = book;
    }

    @Override
    public Predicate toPredicate(Root<Book> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
//        ParameterExpression<LocalDateTime> date =
//                criteriaBuilder.parameter(LocalDateTime.class, book.getDate().toString());

        return criteriaBuilder.and(
                criteriaBuilder.equal(criteriaBuilder.lower(root.<String>get("name")), book.getName().toLowerCase()),
                criteriaBuilder.equal(root.<LocalDateTime>get("date"), book.getDate()));
    }
}
