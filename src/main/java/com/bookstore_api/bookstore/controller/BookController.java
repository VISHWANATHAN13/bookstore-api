package com.bookstore_api.bookstore.controller;

import java.util.List;
import java.util.Set;

import com.bookstore_api.bookstore.common.APIResponse;
import com.bookstore_api.bookstore.dto.BookDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.bookstore_api.bookstore.entity.Book;
import com.bookstore_api.bookstore.service.BookService;

//@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/getall")
    public List<Book> getAllBooks(@RequestParam(name = "yearOfPublications", required = false) Set<Integer> yop,
                                  @RequestParam(name = "bookType", required = false) String bookType) {

        return bookService.getBooks(yop, bookType);

    }

    @GetMapping("/get/{id}")
    public BookDTO getBooks(@PathVariable("id") Long bookId, @RequestParam(value = "authorData", required = false) boolean authorData) {

        return bookService.getBookById(bookId, authorData);

    }

    @PostMapping("/create")
    public Book createBook(@RequestBody Book book) {
        return bookService.createBook(book);
    }

    @PostMapping("/createall")
    public List<Book> createBook(@RequestBody List<Book> book) {
        return bookService.saveAll(book);
    }

    @PutMapping("/update")
    public Book updateBook(@RequestBody Book incomingBook) {

        return bookService.updateBook(incomingBook);

    }

    @DeleteMapping(value = "/books/{bookId}")
    public String deleteBookById(Long bookId) {
        return bookService.deleteById(bookId);
    }

    @GetMapping("/raw/books")
    public APIResponse getBooksByRawQuery(@RequestParam(value = "yop") Set<Integer> yop) {

        return bookService.getBooksByRawQuery(yop);

    } @GetMapping("/caughtException")
    public APIResponse getCaughtException(@RequestParam(value = "number") Integer yop) {
        return bookService.getCaughtException(yop);
    }
}
