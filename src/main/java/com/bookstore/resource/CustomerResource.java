package com.bookstore.resource;

import com.bookstore.data.Database;
import com.bookstore.exception.exception.CustomerNotFoundException;
import com.bookstore.model.Customer;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/customers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CustomerResource {

    @GET
    public List<Customer> getCustomers() {
        return Database.customers;
    }

    @GET
    @Path("/{id}")
    public Customer getCustomerById(@PathParam("id") int id) {
        return Database.customers.stream()
                .filter(c -> c.getId() == id)
                .findFirst()
                .orElseThrow(() -> new CustomerNotFoundException("Customer with ID " + id + " not found"));
    }

    @POST
    public Response addCustomer(Customer customer) {
        customer.setId(Database.customers.size() + 1);
        Database.customers.add(customer);
        return Response.status(Response.Status.CREATED).entity(customer).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteCustomer(@PathParam("id") int id) {
        boolean removed = Database.customers.removeIf(c -> c.getId() == id);
        if (!removed) {
            throw new CustomerNotFoundException("Customer with ID " + id + " not found");
        }
        return Response.noContent().build();
    }
}
