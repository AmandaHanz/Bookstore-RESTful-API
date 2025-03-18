package com.bookstore.util.exceptionMapper;

import com.bookstore.util.exception.CartNotFoundException;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.HashMap;
import java.util.Map;

@Provider
public class CartNotFoundExceptionMapper implements ExceptionMapper<CartNotFoundException> {
    @Override
    public Response toResponse(CartNotFoundException exception) {
        Map<String, String> error = new HashMap<>();
        error.put("error", "Cart Not Found");
        error.put("message", exception.getMessage());

        return Response.status(Response.Status.NOT_FOUND)
                .type(MediaType.APPLICATION_JSON)
                .entity(error)
                .build();
    }
}
