package io.anymobi.controller.rest;

import io.anymobi.entity.Author;
import io.anymobi.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "/v1")
public class AuthorRestController {

    @Autowired
    AuthorService authorService;

    @GetMapping(path = "/authors/{id}", produces = {"application/vnd.siren+json"})
    public ResponseEntity<Resource<Author>> getOneById(@PathVariable Long id) {
        Optional<Author> authorOptional = authorService.findById(id);
        if (authorOptional.isPresent()) {
            Resource<Author> authorResource = new Resource<>(authorOptional.get());
            ControllerLinkBuilder allBooks = linkTo(methodOn(this.getClass()).getAll());
            ControllerLinkBuilder self = linkTo(methodOn(this.getClass()).getOneById(id));
            ControllerLinkBuilder createBook = linkTo(methodOn(this.getClass()).create(null));
            ControllerLinkBuilder updateBook = linkTo(methodOn(this.getClass()).update(id, null));
            ControllerLinkBuilder deleteBook = linkTo(methodOn(this.getClass()).delete(id));
            authorResource.add(self.withSelfRel().withType("GET"));
            authorResource.add(allBooks.withRel("all-book").withType("GET").withDeprecation("false"));
            authorResource.add(createBook.withRel("create").withType("POST").withDeprecation("false"));
            authorResource.add(updateBook.withRel("update").withType("UPDATE").withDeprecation("false"));
            authorResource.add(deleteBook.withRel("delete").withType("DELETE").withDeprecation("false"));
            return new ResponseEntity<>(authorResource, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(path = "/authors", produces = {"application/vnd.siren+json"})
    public ResponseEntity<Resources<Resource<Author>>> getAll() {
        List<Author> authors = authorService.findAll();
        List<Resource<Author>> authorResources = new ArrayList<>();
        for (Author author : authors) {
            Resource<Author> authorResource = new Resource<>(author);
            ControllerLinkBuilder allAuthors = linkTo(methodOn(this.getClass()).getAll());
            ControllerLinkBuilder self = linkTo(methodOn(this.getClass()).getOneById(author.getId()));
            authorResource.add(self.withSelfRel());
            authorResource.add(allAuthors.withRel("all-authors"));
            authorResources.add(authorResource);
        }
        return new ResponseEntity<>(new Resources<>(authorResources,
                linkTo(methodOn(BookRestController.class).getAll()).withSelfRel()), HttpStatus.OK);
    }

    @PostMapping(path = "/authors", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Author> create(@RequestBody Author author) {
        System.out.println("hop hey");
        if (authorService.isExist(author)) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        author.setId(null);
        return new ResponseEntity<>(authorService.save(author), HttpStatus.CREATED);
    }

    @PutMapping(path = "/authors/{id}")
    public ResponseEntity<Author> update(@PathVariable Long id, @RequestBody Author author) {

        Optional<Author> currentAuthor = authorService.findById(id);
        if (currentAuthor.isPresent()) {
            currentAuthor.get().setBirthday(author.getBirthday());
            currentAuthor.get().setFirstName(author.getFirstName());
            currentAuthor.get().setLastName(author.getLastName());
            currentAuthor.get().setBooks(author.getBooks());
            return new ResponseEntity<>(authorService.save(currentAuthor.get()), HttpStatus.OK);
        }

        return new ResponseEntity<Author>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(path = "/authors/{id}")
    public ResponseEntity.HeadersBuilder<?> delete(@PathVariable Long id) {
        authorService.deleteById(id);
        return ResponseEntity.noContent();
    }

}
