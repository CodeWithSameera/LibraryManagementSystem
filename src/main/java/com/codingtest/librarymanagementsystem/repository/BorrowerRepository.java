package com.codingtest.librarymanagementsystem.repository;

import com.codingtest.librarymanagementsystem.entity.Borrower;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BorrowerRepository extends JpaRepository<Borrower, Long> {
}
