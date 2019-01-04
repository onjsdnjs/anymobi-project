package io.anymobi.controller.rest;

import io.anymobi.entity.Book;
import io.anymobi.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping("/v1")
public class BookRestController {
    @Autowired
    BookService bookService;

    @GetMapping(path = "/books/{id}", produces = {"application/vnd.siren+json"})
    public ResponseEntity<Resource<Book>> getOneById(@PathVariable Long id) {
        Optional<Book> bookOptional = bookService.findById(id);
        if (!bookOptional.isPresent()) {
            System.out.println("not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Resource<Book> bookResource = new Resource<>(bookOptional.get());
        ControllerLinkBuilder allBooks = linkTo(methodOn(this.getClass()).getAll());
        ControllerLinkBuilder self = linkTo(methodOn(this.getClass()).getOneById(id));
        ControllerLinkBuilder createBook = linkTo(methodOn(this.getClass()).create(null));
        ControllerLinkBuilder updateBook = linkTo(methodOn(this.getClass()).update(id, null));
        ControllerLinkBuilder deleteBook = linkTo(methodOn(this.getClass()).delete(id));
        bookResource.add(self.withSelfRel().withType("GET"));
        bookResource.add(allBooks.withRel("all-book").withType("GET").withDeprecation("false"));
        bookResource.add(createBook.withRel("create").withType("POST").withDeprecation("false"));
        bookResource.add(updateBook.withRel("update").withType("UPDATE").withDeprecation("false"));
        bookResource.add(deleteBook.withRel("delete").withType("DELETE").withDeprecation("false"));

        return new ResponseEntity<>(bookResource, HttpStatus.OK);
    }

    @GetMapping(path = "/books", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Resources<Resource<Book>>> getAll() {
        List<Book> books = bookService.findAll();
        List<Resource<Book>> bookResources = new ArrayList<>();
        for (Book book : books) {
            Resource<Book> bookResource = new Resource<>(book);
            ControllerLinkBuilder allBooks = linkTo(methodOn(this.getClass()).getAll());
            ControllerLinkBuilder self = linkTo(methodOn(this.getClass()).getOneById(book.getId()));
            bookResource.add(self.withSelfRel());
            bookResource.add(allBooks.withRel("all-book"));
            bookResources.add(bookResource);
        }
        return new ResponseEntity<>(new Resources<>(bookResources,
                linkTo(methodOn(BookRestController.class).getAll()).withSelfRel()), HttpStatus.OK);
    }

    @PostMapping(path = "/books", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Object> create(@RequestBody Book book) {
        if (bookService.isExist(book)) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        book.setId(null);
        return new ResponseEntity<>(bookService.save(book), HttpStatus.CREATED);
    }

    @PutMapping(path = "/books/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Book> update(@PathVariable Long id, @RequestBody Book book) {
        Optional<Book> currentBook = bookService.findById(id);
        if (currentBook.get() == null) {
            return new ResponseEntity<Book>(HttpStatus.NOT_FOUND);
        }
        currentBook.get().setName(book.getName());
        currentBook.get().setDate(book.getDate());
        return new ResponseEntity<>(bookService.save(currentBook.get()), HttpStatus.OK);
    }

    @DeleteMapping(path = "/books/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        bookService.deleteById(id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    private List<Link> getSingleItemLinks(long id) {

//        return Arrays.asList(linkTo(methodOn(BookRestController.class).getOneById(id)).withSelfRel()
//                        .andAffordance(afford(methodOn(BookRestController.class).update(id, null)))
//                        .andAffordance(afford(methodOn(BookRestController.class).delete(id))),
//                linkTo(methodOn(BookRestController.class).getAll()).withRel("employees"));
        return Collections.emptyList();
    }
}
