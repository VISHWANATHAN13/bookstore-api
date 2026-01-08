package com.bookstore_api.bookstore.repository;

import com.bookstore_api.bookstore.entity.BookAuthor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookAuthorRepository extends JpaRepository<BookAuthor, Long> {
    List<BookAuthor> findAllByBookId(Long bookId);
}
