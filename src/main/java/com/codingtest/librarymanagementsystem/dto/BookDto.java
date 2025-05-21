package com.codingtest.librarymanagementsystem.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookDto {
    private Long id;
    @NotBlank(message = "ISBN is mandatory")
    private String isbn;
    @NotBlank(message = "Title is mandatory")
    private String title;
    @NotBlank(message = "Author is mandatory")
    private String author;
    private Long borrowedById;
}
