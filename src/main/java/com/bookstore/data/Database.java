package com.bookstore.data;

import com.bookstore.model.*;
import java.util.*;

public class Database {

    public static List<Book> books = new ArrayList<>();
    public static List<Author> authors = new ArrayList<>();
    public static List<Customer> customers = new ArrayList<>();
    public static List<Order> orders = new ArrayList<>();

    static {
        // Preloading sample authors
        authors.add(new Author(1, "J.K. Rowling", "Author of Harry Potter series"));
        authors.add(new Author(2, "George Orwell", "Author of 1984 and Animal Farm"));

        // Preloading sample books
        books.add(new Book(1, "Harry Potter and the Sorcerer's Stone", 1, "978-0439708180", 1997, 25.99, 100));
        books.add(new Book(2, "1984", 2, "978-0451524935", 1949, 19.99, 50));

        // Preloading sample customers
        customers.add(new Customer(1, "John Doe", "john@example.com", "password123"));
        customers.add(new Customer(2, "Alice Smith", "alice@example.com", "securepass"));

        // Creating empty carts for customers
        for (Customer customer : customers) {
            customer.setCart(new ArrayList<>());
        }
    }
}
