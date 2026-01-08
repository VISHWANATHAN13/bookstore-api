package com.bookstore_api.bookstore.service;

import com.bookstore_api.bookstore.common.APIResponse;
import com.bookstore_api.bookstore.common.PaginationMeta;
import com.bookstore_api.bookstore.entity.Author;
import com.bookstore_api.bookstore.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    public APIResponse getAuthors(Pageable pageable) {

        APIResponse apiResponse = new APIResponse();

        // DB call to get authors
        Page<Author> authorPage = authorRepository.findAll(pageable);

        PaginationMeta authorPaginationMeta = new PaginationMeta();
        authorPaginationMeta.createPagination(authorPage);
        apiResponse.setData(authorPage);
        return apiResponse;

    }
}
