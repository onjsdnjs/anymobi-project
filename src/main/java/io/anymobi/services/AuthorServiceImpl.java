package io.anymobi.services;

import io.anymobi.entity.Author;
import io.anymobi.repositories.AuthorRepository;
import io.anymobi.repositories.specifications.author.FindByAuthor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    AuthorRepository authorRepository;

    @Override
    public Optional<Author> findById(Long id) {
        return authorRepository.findById(id);
    }

    @Override
    public List<Author> findAll() {
        //List<Author> authors = new ArrayList<>();
        //authorRepository.findAll().forEach(authors::add);
        return authorRepository.findAll();
    }

    @Override
    public boolean isExist(Author author) {

        return !authorRepository.query(new FindByAuthor(author)).isEmpty();
    }

    @Override
    public Author save(Author author) {
        return authorRepository.save(author);
    }

    @Override
    public void deleteById(Long id) {
        authorRepository.deleteById(id);
    }

}
