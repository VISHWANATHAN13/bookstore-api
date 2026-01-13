package com.bookstore_api.bookstore.service;

import java.util.*;

import com.bookstore_api.bookstore.common.APIResponse;
import com.bookstore_api.bookstore.common.BadRequestException;
import com.bookstore_api.bookstore.common.APIError;
import com.bookstore_api.bookstore.data.BookData;
import com.bookstore_api.bookstore.dto.AuthorDTO;
import com.bookstore_api.bookstore.dto.BookDTO;
import com.bookstore_api.bookstore.entity.Author;
import com.bookstore_api.bookstore.entity.BookAuthor;
import com.bookstore_api.bookstore.repository.BookAuthorRepository;
import com.bookstore_api.bookstore.validator.BookValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore_api.bookstore.entity.Book;
import com.bookstore_api.bookstore.repository.BookRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookAuthorRepository bookAuthorRepository;

    @Autowired
    private BookValidator bookValidator;

    public Book createBook(Book book) {

        // validation
        List<APIError> apiError = bookValidator.validateCreateBookRequest(book);
        if (!apiError.isEmpty()) throw new BadRequestException("bad request", apiError);

        return bookRepository.save(book);
    }

    @Transactional
    public BookDTO getBookById(Long bookId, boolean authorData) throws IllegalArgumentException {

        List<BookAuthor> bookAuthors = null;
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new RuntimeException("Book not found"));

        if (authorData) {
            bookAuthors = bookAuthorRepository.findAllByBookId(bookId);
        }
        BookDTO bookDTO = new BookDTO();

        // set book details
        bookDTO.setId(book.getId());
        bookDTO.setName(book.getName());
        bookDTO.setDescription(book.getDescription());
        bookDTO.setYearOfPublication(book.getYearOfPublication());
        bookDTO.setBookType(book.getBookType());

        // get author details
        List<AuthorDTO> authorDTOList = new ArrayList<>();

        if (bookAuthors != null) {
            for (BookAuthor bookAuthor : bookAuthors) {

                Author author = bookAuthor.getAuthor();

                AuthorDTO authorDTO = new AuthorDTO();
                authorDTO.setId(author.getId());
                authorDTO.setName(author.getName());
                authorDTO.setGender(author.getGender());

                authorDTOList.add(authorDTO);
            }
        }
        //set author details
        bookDTO.setAuthors(authorDTOList);

        return bookDTO;

    }

    public List<Book> getBooks(Set<Integer> yop, String bookType) {
        List<Book> bookList = new ArrayList<>();

        if (yop == null) {

            bookRepository.findAll().forEach(book -> bookList.add(book));
            return bookList;
        }

        return bookRepository.findAllByYearOfPublicationInAndBookType(yop, bookType);

    }

    public List<Book> saveAll(List<Book> book) {

        return bookRepository.saveAll(book);

    }

    public Book updateBook(Book incomingBook) {
        /*
         * hard code the logic here you wanted
         */
        List<Book> allBooks = bookRepository.findAll();

        // Loop through every book to check for matching ID
        for (Book existingBook : allBooks) {
            if (existingBook.getId().equals(incomingBook.getId())) {
                // ID matches → update fields
                existingBook.setBookType(incomingBook.getBookType());
                existingBook.setDescription(incomingBook.getDescription());
                existingBook.setName(incomingBook.getName());
                existingBook.setYearOfPublication(incomingBook.getYearOfPublication());

                // Save updated book and return
                return bookRepository.save(existingBook);
            }
        }

        // ID not found → throw exception or return null/error
        throw new RuntimeException("Book with id " + incomingBook.getId() + " not found");
    }

    public String deleteById(Long bookId) {
        bookRepository.deleteById(bookId);

        return "deleted successfully";
    }

    // raw query - get books
    public APIResponse getBooksByRawQuery(Set<Integer> yop) {

        APIResponse apiResponse = new APIResponse();

//        DB call
        List<Book> bookList = bookRepository.getBooksByRawQuery(yop);

//        Map dataMap = new HashMap();
//        dataMap.put("books", bookList);

//        set data
        BookData bookData = new BookData();
        bookData.setBooks(bookList);

//        set API response
//        apiResponse.setError(300);
        apiResponse.setData(bookData);

        return apiResponse;
    }

    public APIResponse getCaughtException(Integer yop) {

        int result = 100 / yop;
        APIResponse apiResponse = new APIResponse();
        apiResponse.setData(result);
        return apiResponse;
    }
}
