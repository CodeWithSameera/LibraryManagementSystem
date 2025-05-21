package com.codingtest.librarymanagementsystem.service;

import com.codingtest.librarymanagementsystem.dto.BookDto;
import com.codingtest.librarymanagementsystem.entity.Book;
import com.codingtest.librarymanagementsystem.entity.Borrower;
import com.codingtest.librarymanagementsystem.repository.BookRepository;
import com.codingtest.librarymanagementsystem.repository.BorrowerRepository;
import com.codingtest.librarymanagementsystem.service.impl.BookServiceImpl;
import com.codingtest.librarymanagementsystem.util.LogMessages;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.anyLong;
import static org.junit.jupiter.api.Assertions.assertNull;

 class BookServiceImplTest {
    @Mock
    private BookRepository bookRepository;

    @Mock
    private BorrowerRepository borrowerRepository;

    @InjectMocks
    private BookServiceImpl bookService;

    private Book book;
    private Borrower borrower;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        borrower = Borrower.builder()
                .id(1L)
                .name("Test Borrower")
                .email("testBorrower@example.com")
                .build();

        book = Book.builder()
                .id(1L)
                .isbn("123-4567890123")
                .title("Sample Book")
                .author("Author A")
                .borrowedBy(null)
                .build();
    }

    @Test
    void registerBook_ShouldSaveBook() {
        when(bookRepository.save(any(Book.class))).thenReturn(book);

        BookDto dto = BookDto.builder()
                .isbn("123-4567890123")
                .title("Sample Book")
                .author("Author A")
                .build();

        BookDto saved = bookService.registerBook(dto);

       assertNotNull(saved);
        assertEquals("Sample Book", saved.getTitle());
        Mockito.verify(bookRepository, times(1)).save(any(Book.class));
    }

    @Test
    void getAllBooks_ShouldReturnList() {
        when(bookRepository.findAll()).thenReturn(List.of(book));

        List<BookDto> books = bookService.getAllBooks();

        assertEquals(1, books.size());
        verify(bookRepository, times(1)).findAll();
    }

    @Test
    void borrowBook_ShouldSetBorrower() {
        // Arrange
        when(bookRepository.findByIdWithLocks(1L)).thenReturn(Optional.of(book));
        when(borrowerRepository.findById(1L)).thenReturn(Optional.of(borrower));
        when(bookRepository.save(any(Book.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        BookDto borrowed = bookService.borrowBook(1L, 1L);

        // Assert
        assertNotNull(borrowed.getBorrowedById());
        assertEquals(1L, borrowed.getBorrowedById());
        verify(bookRepository, times(1)).save(any(Book.class));
    }

    @Test
    void borrowBook_AlreadyBorrowed_ThrowsException() {
        // Arrange
        book.setBorrowedBy(borrower);
        when(bookRepository.findByIdWithLocks(1L)).thenReturn(Optional.of(book));

        // Act & Assert
        Exception exception = assertThrows(IllegalStateException.class, () -> bookService.borrowBook(1L, 1L));
        assertEquals(LogMessages.BOOK_ALREADY_BORROWED, exception.getMessage());

        // Verify no borrower lookup or save occurred
        verify(borrowerRepository, never()).findById(anyLong());
        verify(bookRepository, never()).save(any());
    }

    @Test
    void returnBook_ShouldClearBorrower() {
        book.setBorrowedBy(borrower);
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
        when(bookRepository.save(any(Book.class))).thenAnswer(invocation -> invocation.getArgument(0));

        BookDto returned = bookService.returnBook(1L);

        assertNull(returned.getBorrowedById());
        verify(bookRepository, times(1)).save(any(Book.class));
    }
}
