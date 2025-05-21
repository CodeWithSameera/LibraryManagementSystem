package com.codingtest.librarymanagementsystem.util;

import com.codingtest.librarymanagementsystem.dto.BookDto;
import com.codingtest.librarymanagementsystem.dto.BorrowerDto;
import com.codingtest.librarymanagementsystem.entity.Book;
import com.codingtest.librarymanagementsystem.entity.Borrower;

public class Mapper {
    public static BookDto toDto(Book book) {
        return BookDto.builder()
                .id(book.getId())
                .isbn(book.getIsbn())
                .title(book.getTitle())
                .author(book.getAuthor())
                .borrowedById(book.getBorrowedBy() != null ? book.getBorrowedBy().getId() : null)
                .build();
    }

    public static Book toEntity(BookDto dto) {
        return Book.builder()
                .isbn(dto.getIsbn())
                .title(dto.getTitle())
                .author(dto.getAuthor())
                .build();
    }

    public static BorrowerDto toDto(Borrower borrower) {
        return BorrowerDto.builder()
                .id(borrower.getId())
                .name(borrower.getName())
                .email(borrower.getEmail())
                .build();
    }

    public static Borrower toEntity(BorrowerDto dto) {
        return Borrower.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .build();
    }
}
