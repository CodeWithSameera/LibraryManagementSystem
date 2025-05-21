package com.codingtest.librarymanagementsystem.service;

import com.codingtest.librarymanagementsystem.dto.BorrowerDto;
import com.codingtest.librarymanagementsystem.entity.Borrower;
import com.codingtest.librarymanagementsystem.repository.BorrowerRepository;
import com.codingtest.librarymanagementsystem.service.impl.BorrowerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

 class BorrowerServiceImplTest {
    @Mock
    private BorrowerRepository borrowerRepository;

    @InjectMocks
    private BorrowerServiceImpl borrowerService;

    private Borrower borrower;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);

        borrower = Borrower.builder()
                .id(1L)
                .name("John Doe")
                .email("john@example.com")
                .build();
    }

    @Test
    void registerBorrower_ShouldSave() {
        when(borrowerRepository.save(any(Borrower.class))).thenReturn(borrower);

        BorrowerDto dto = BorrowerDto.builder()
                .name("John Doe")
                .email("john@example.com")
                .build();

        BorrowerDto saved = borrowerService.registerBorrower(dto);

        assertNotNull(saved);
        assertEquals("John Doe", saved.getName());
        verify(borrowerRepository, times(1)).save(any(Borrower.class));
    }
}
