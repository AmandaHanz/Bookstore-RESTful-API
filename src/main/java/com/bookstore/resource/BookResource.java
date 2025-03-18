package com.bookstore.resource;

import com.bookstore.data.Database;
import com.bookstore.util.exception.BookNotFoundException;
import com.bookstore.model.Book;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/books")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BookResource {

    @GET
    public List<Book> getAllBooks() {
        return Database.books;
    }

    @GET
    @Path("/{id}")
    public Book getBookById(@PathParam("id") int id) {
        return Database.books.stream()
                .filter(book -> book.getId() == id)
                .findFirst()
                .orElseThrow(() -> new BookNotFoundException("Book with ID " + id + " not found"));
    }

    @POST
    public Response addBook(Book book) {
        book.setId(Database.books.size() + 1);
        Database.books.add(book);
        return Response.status(Response.Status.CREATED).entity(book).build();
    }

    @PUT
    @Path("/{id}")
    public Book updateBook(@PathParam("id") int id, Book updatedBook) {
        for (Book book : Database.books) {
            if (book.getId() == id) {
                book.setTitle(updatedBook.getTitle());
                book.setAuthorId(updatedBook.getAuthorId());
                book.setPrice(updatedBook.getPrice());
                return book;
            }
        }
        throw new BookNotFoundException("Book with ID " + id + " not found");
    }

    @DELETE
    @Path("/{id}")
    public Response deleteBook(@PathParam("id") int id) {
        boolean removed = Database.books.removeIf(book -> book.getId() == id);
        if (!removed) {
            throw new BookNotFoundException("Book with ID " + id + " not found");
        }
        return Response.noContent().build();
    }
}
