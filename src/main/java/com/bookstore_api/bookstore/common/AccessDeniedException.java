package com.bookstore_api.bookstore.common;

public class AccessDeniedException extends RuntimeException {


    public AccessDeniedException(String message) {
        super(message);
    }
}
