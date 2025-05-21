package com.codingtest.librarymanagementsystem.controller;

import com.codingtest.librarymanagementsystem.dto.BookDto;
import com.codingtest.librarymanagementsystem.dto.BorrowerDto;
import com.codingtest.librarymanagementsystem.service.BookService;
import com.codingtest.librarymanagementsystem.service.BorrowerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class LibraryController {
    private final BookService bookService;
    private final BorrowerService borrowerService;

    @PostMapping("/borrowers")
    public ResponseEntity<BorrowerDto> registerBorrower(@RequestBody @Valid BorrowerDto borrower) {
        return new ResponseEntity<>(borrowerService.registerBorrower(borrower), HttpStatus.CREATED);
    }

    @PostMapping("/books")
    public ResponseEntity<BookDto> registerBook(@RequestBody @Valid BookDto book) {
        return new ResponseEntity<>(bookService.registerBook(book), HttpStatus.CREATED);
    }

    @GetMapping("/books")
    public ResponseEntity<List<BookDto>> listBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @PostMapping("/books/{bookId}/borrow/{borrowerId}")
    public ResponseEntity<BookDto> borrowBook(@PathVariable Long bookId, @PathVariable Long borrowerId) {
        return ResponseEntity.ok(bookService.borrowBook(bookId, borrowerId));
    }

    @PostMapping("/books/{bookId}/return")
    public ResponseEntity<BookDto> returnBook(@PathVariable Long bookId) {
        return ResponseEntity.ok(bookService.returnBook(bookId));
    }
}
