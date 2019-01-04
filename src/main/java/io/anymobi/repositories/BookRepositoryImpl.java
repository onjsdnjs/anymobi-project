package io.anymobi.repositories;

import io.anymobi.entity.Book;
import org.springframework.stereotype.Repository;

@Repository
public class BookRepositoryImpl extends RepositoryImpl<Book, Long> implements BookRepository {

    public BookRepositoryImpl() {
        super(Book.class);
    }

}
