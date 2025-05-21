package com.codingtest.librarymanagementsystem.service.impl;

import com.codingtest.librarymanagementsystem.dto.BookDto;
import com.codingtest.librarymanagementsystem.entity.Book;
import com.codingtest.librarymanagementsystem.repository.BookRepository;
import com.codingtest.librarymanagementsystem.repository.BorrowerRepository;
import com.codingtest.librarymanagementsystem.service.BookService;
import com.codingtest.librarymanagementsystem.util.LogMessages;
import com.codingtest.librarymanagementsystem.util.Mapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {
    private static final Logger logger = LoggerFactory.getLogger(BookServiceImpl.class);
    private final BookRepository bookRepository;
    private final BorrowerRepository borrowerRepository;
    @Override
    public BookDto registerBook(BookDto dto) {
        logger.info(LogMessages.BOOK_REGISTERING, dto.getIsbn());
        List<Book> existingBooks = bookRepository.findByIsbn(dto.getIsbn());

        if (!existingBooks.isEmpty()) {
            Book existing = existingBooks.get(0);
            if (!existing.getTitle().equals(dto.getTitle()) ||
                    !existing.getAuthor().equals(dto.getAuthor())) {
                throw new IllegalArgumentException(LogMessages.BOOK_ALREADY_EXISTS);
            }
        }

        var book = Mapper.toEntity(dto);
        return Mapper.toDto(bookRepository.save(book));
    }

    @Override
    public List<BookDto> getAllBooks() {
        logger.info(LogMessages.RETRIEVING_ALL_BOOKS);
        return bookRepository.findAll().stream().map(Mapper::toDto).toList();
    }

    @Override
    @Transactional
    public BookDto borrowBook(Long bookId, Long borrowerId) {
        logger.info(LogMessages.BORROWER_BORROWING, borrowerId, bookId);
        var book = bookRepository.findByIdWithLocks(bookId).orElseThrow(() -> new RuntimeException(LogMessages.BOOK_NOT_FOUND));
        if (book.getBorrowedBy() != null) throw new IllegalStateException(LogMessages.BOOK_ALREADY_BORROWED);
        var borrower = borrowerRepository.findById(borrowerId).orElseThrow(() -> new RuntimeException(LogMessages.BORROWER_NOT_FOUND));
        book.setBorrowedBy(borrower);
        return Mapper.toDto(bookRepository.save(book));
    }

    @Override
    public BookDto returnBook(Long bookId) {
        logger.info(LogMessages.RETURNING_BOOK, bookId);
        var book = bookRepository.findById(bookId).orElseThrow(() -> new RuntimeException(LogMessages.BOOK_NOT_FOUND));
        book.setBorrowedBy(null);
        return Mapper.toDto(bookRepository.save(book));
    }
}
