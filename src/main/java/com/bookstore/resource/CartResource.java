package com.bookstore.resource;

import com.bookstore.data.Database;
import com.bookstore.exception.exception.CartNotFoundException;
import com.bookstore.model.CartItem;
import com.bookstore.model.Customer;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/customers/{customerId}/cart")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CartResource {

    @GET
    public List<CartItem> getCart(@PathParam("customerId") int customerId) {
        Customer customer = Database.customers.stream()
                .filter(c -> c.getId() == customerId)
                .findFirst()
                .orElseThrow(() -> new CartNotFoundException("Cart not found for customer " + customerId));

        return customer.getCart();
    }

    @POST
    @Path("/items")
    public Response addItemToCart(@PathParam("customerId") int customerId, CartItem item) {
        Customer customer = Database.customers.stream()
                .filter(c -> c.getId() == customerId)
                .findFirst()
                .orElseThrow(() -> new CartNotFoundException("Cart not found for customer " + customerId));

        customer.getCart().add(item);
        return Response.status(Response.Status.CREATED).entity(item).build();
    }
}
