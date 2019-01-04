package io.anymobi.repositories;

import io.anymobi.entity.Author;
import org.springframework.stereotype.Repository;


@Repository
public class AuthorRepositoryImpl extends RepositoryImpl<Author, Long> implements AuthorRepository {

    public AuthorRepositoryImpl() {
        super(Author.class);
    }
}
