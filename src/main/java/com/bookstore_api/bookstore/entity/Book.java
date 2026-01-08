package com.bookstore_api.bookstore.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "booktable")
public class Book {

	/*
	 * manually setting sequence generator incrementation to 1
	 */
	@Id
	@SequenceGenerator(name = "book_seq", sequenceName = "booktable_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_seq")
	private Long id;
	private String bookType;
	private String description;
	private String name;
	private Integer yearOfPublication;

    @OneToMany(mappedBy = "book")
//    @JsonManagedReference
    private List<BookAuthor> bookAuthors;

    public List<BookAuthor> getBookAuthors() {
        return bookAuthors;
    }

    public void setBookAuthors(List<BookAuthor> bookAuthors) {
        this.bookAuthors = bookAuthors;
    }

    public Book() {
	}

    public Book(Long id, String bookType, String description, String name, Integer yearOfPublication, List<BookAuthor> bookAuthors) {
        this.id = id;
        this.bookType = bookType;
        this.description = description;
        this.name = name;
        this.yearOfPublication = yearOfPublication;
        this.bookAuthors = bookAuthors;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
		return id;
	}

	public String getBookType() {
		return bookType;
	}

	public void setBookType(String bookType) {
		this.bookType = bookType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getYearOfPublication() {
		return yearOfPublication;
	}

	public void setYearOfPublication(Integer yearOfPublication) {
		this.yearOfPublication = yearOfPublication;
	}

}
