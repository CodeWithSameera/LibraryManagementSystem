package com.codingtest.librarymanagementsystem.util;

public class LogMessages {
    public static final String BOOK_REGISTERING = "Registering new book with ISBN: {}";
    public static final String BORROWER_REGISTERING = "Registering borrower with email: {}";
    public static final String BOOK_ALREADY_BORROWED = "Book already borrowed";
    public static final String VALIDATION_ERROR="Validation error: {}";
    public static final String RUNTIME_EXCEPTION = "Runtime exception: {}";
    public static final String MALFORMED_JSON="Malformed JSON request: {}";
    public static final String INTERNAL_SERVER_ERROR="Internal server error: {}";
    public static final String UNEXPECTED_ERROR="An unexpected error occurred";
    public static final String MALFORMED_JSON_REQUEST="Malformed JSON request";
    public static final String RETRIEVING_ALL_BOOKS="Retrieving all books";
    public static final String BORROWER_BORROWING="Borrower {} attempting to borrow book {}";
    public static final String BORROWER_RETURNING="Borrower {} returning book {}";
    public static final String BOOK_NOT_FOUND="Book not found";
    public static final String BORROWER_NOT_FOUND="Borrower not found";
    public static final String RETURNING_BOOK="Returning book with ID: {}";
    public static final String BOOK_ALREADY_EXISTS="Title and author must match existing books with same ISBN: {}";
    public static final String NOT_FOUND_ERROR="Resource not found: {}";
    public static final String NO_HANDLER="No handler found for request: {} {}";
}
