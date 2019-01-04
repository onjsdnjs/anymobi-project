package io.anymobi.repositories.specifications.author;


import io.anymobi.entity.Author;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDate;

public class FindByAuthor implements Specification<Author> {
    private final Author author;

    public FindByAuthor(Author author) {
        this.author = author;
    }

    @Override
    public Predicate toPredicate(Root<Author> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        return criteriaBuilder.and(
                criteriaBuilder.equal(criteriaBuilder.lower(root.<String>get("firstName")), author.getFirstName().toLowerCase()),
                criteriaBuilder.equal(criteriaBuilder.lower(root.<String>get("lastName")), author.getLastName().toLowerCase()),
                criteriaBuilder.equal(root.<LocalDate>get("birthday"), author.getBirthday())
        );
    }
}
