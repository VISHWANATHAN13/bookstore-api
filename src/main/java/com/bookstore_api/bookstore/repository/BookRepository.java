package com.bookstore_api.bookstore.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bookstore_api.bookstore.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

//	List<Book> findAllByYearOfPublicationIn(Set<Integer> yop);

	List<Book> findAllByYearOfPublicationInAndBookType(Set<Integer> yop, String bookType);

	/* raw query */
	String query = "SELECT * FROM booktable WHERE year_of_publication IN ?1";// AND bookType = ?2;

	@Query(nativeQuery = true, value = query)
	List<Book> getBooksByRawQuery(Set<Integer> yop);

}
