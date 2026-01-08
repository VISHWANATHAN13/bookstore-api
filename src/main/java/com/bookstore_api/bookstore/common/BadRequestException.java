package com.bookstore_api.bookstore.common;

import java.util.List;

public class BadRequestException extends RuntimeException {

    private List<APIError> APIErrors;

    public BadRequestException(String message, List<APIError> APIErrors) {
        super(message);
        this.APIErrors = APIErrors;
    }

    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException(List<APIError> APIErrors) {
        this.APIErrors = APIErrors;
    }

    public void setErrors(List<APIError> APIErrors) {
        this.APIErrors = APIErrors;
    }

    public List<APIError> getErrors() {
        return APIErrors;
    }

}
