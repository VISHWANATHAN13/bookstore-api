package com.bookstore_api.bookstore.validator;

import com.bookstore_api.bookstore.common.APIError;
import com.bookstore_api.bookstore.entity.Book;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BookValidator {
    public List<APIError> validateCreateBookRequest(Book book) {

        List<APIError> errors = new ArrayList<>();
//        APIError error;

        //name
        if (book.getName() == null) {
            APIError APIError = new APIError("name", "Book name is null");
            errors.add(APIError);
        }

        //
        if (book.getYearOfPublication() == null) {
            APIError APIError = new APIError("year of publication", "year of publication is null");
            errors.add(APIError);
        }

        //book type
        if (book.getBookType() == null) {
            APIError APIError = new APIError("book type", "Book type is null");
            errors.add(APIError);
        }
        System.out.println(errors.toArray().toString());

        return errors;
    }
}
