package io.anymobi.services;

import io.anymobi.entity.Book;
import io.anymobi.repositories.RepositoryImpl;
import io.anymobi.repositories.specifications.book.BookSpecification;
import io.anymobi.repositories.specifications.book.FindByNameAndDateSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BookServiceImpl implements BookService {

    @Autowired
    RepositoryImpl<Book,Long> bookRepository;

    @Override
    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> findAll() {

        List<Book> contacts = bookRepository.query(new BookSpecification());
        //new ArrayList<>();//emf.createNamedQuery("Book.findAll", Book.class).getResultList();
        return contacts;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Book> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Book save(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public boolean isExist(Book book) {

        List<Book> books = bookRepository.query(new FindByNameAndDateSpecification(book));
        if (books.isEmpty()) return false;
        return true;
    }

    @Override
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }
}
