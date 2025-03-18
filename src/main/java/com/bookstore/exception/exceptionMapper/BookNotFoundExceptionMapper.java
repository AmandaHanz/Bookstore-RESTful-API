package com.bookstore.exception.exceptionMapper;

import com.bookstore.exception.exception.BookNotFoundException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.Map;

@Provider
public class BookNotFoundExceptionMapper implements ExceptionMapper<BookNotFoundException> {
    @Override
    public Response toResponse(BookNotFoundException exception) {
        Map<String, String> error = new HashMap<>();
        error.put("error", "Book Not Found");
        error.put("message", exception.getMessage());

        return Response.status(Response.Status.NOT_FOUND)
                .type(MediaType.APPLICATION_JSON)
                .entity(error)
                .build();
    }
}
