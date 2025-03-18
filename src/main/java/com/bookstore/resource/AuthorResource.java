package com.bookstore.resource;

import com.bookstore.data.Database;
import com.bookstore.exception.exception.AuthorNotFoundException;
import com.bookstore.model.Author;
import com.bookstore.model.Book;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/authors")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthorResource {

    @GET
    public List<Author> getAllAuthors() {
        return Database.authors;
    }

    @GET
    @Path("/{id}")
    public Author getAuthorById(@PathParam("id") int id) {
        return Database.authors.stream()
                .filter(author -> author.getId() == id)
                .findFirst()
                .orElseThrow(() -> new AuthorNotFoundException("Author with ID " + id + " not found"));
    }

    @POST
    public Response addAuthor(Author author) {
        author.setId(Database.authors.size() + 1);
        Database.authors.add(author);
        return Response.status(Response.Status.CREATED).entity(author).build();
    }

    @PUT
    @Path("/{id}")
    public Author updateAuthor(@PathParam("id") int id, Author updatedAuthor) {
        for (Author author : Database.authors) {
            if (author.getId() == id) {
                author.setName(updatedAuthor.getName());
                author.setBiography(updatedAuthor.getBiography());
                return author;
            }
        }
        throw new AuthorNotFoundException("Author with ID " + id + " not found");
    }

    @DELETE
    @Path("/{id}")
    public Response deleteAuthor(@PathParam("id") int id) {
        boolean removed = Database.authors.removeIf(author -> author.getId() == id);
        if (!removed) {
            throw new AuthorNotFoundException("Author with ID " + id + " not found");
        }
        return Response.noContent().build();
    }

    @GET
    @Path("/{id}/books")
    public List<Book> getBooksByAuthor(@PathParam("id") int id) {
        if (Database.authors.stream().noneMatch(author -> author.getId() == id)) {
            throw new AuthorNotFoundException("Author with ID " + id + " not found");
        }
        return Database.books.stream().filter(book -> book.getAuthorId() == id).toList();
    }
}
