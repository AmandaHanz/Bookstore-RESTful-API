package com.bookstore.resource;

import com.bookstore.data.Database;
import com.bookstore.model.Customer;
import com.bookstore.model.Order;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/customers/{customerId}/orders")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class OrderResource {

    @GET
    public List<Order> getOrders(@PathParam("customerId") int customerId) {
        return Database.orders.stream()
                .filter(order -> order.getCustomerId() == customerId)
                .toList();
    }
}
