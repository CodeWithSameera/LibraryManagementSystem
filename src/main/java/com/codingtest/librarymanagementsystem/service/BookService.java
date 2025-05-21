package com.codingtest.librarymanagementsystem.service;

import com.codingtest.librarymanagementsystem.dto.BookDto;
import com.codingtest.librarymanagementsystem.entity.Borrower;

import java.util.List;

public interface BookService {
     BookDto registerBook(BookDto dto);
     List<BookDto> getAllBooks();
     BookDto borrowBook(Long bookId, Long borrowerId);
     BookDto returnBook(Long bookId);
}
