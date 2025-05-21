package com.codingtest.librarymanagementsystem.service.impl;

import com.codingtest.librarymanagementsystem.dto.BorrowerDto;
import com.codingtest.librarymanagementsystem.repository.BorrowerRepository;
import com.codingtest.librarymanagementsystem.service.BorrowerService;
import com.codingtest.librarymanagementsystem.util.LogMessages;
import com.codingtest.librarymanagementsystem.util.Mapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BorrowerServiceImpl implements BorrowerService {
    private static final Logger logger = LoggerFactory.getLogger(BorrowerServiceImpl.class);

    private final BorrowerRepository borrowerRepository;

    @Override
    public BorrowerDto registerBorrower(BorrowerDto dto) {
        logger.info(LogMessages.BORROWER_REGISTERING, dto.getEmail());
        var borrower = Mapper.toEntity(dto);
        return Mapper.toDto(borrowerRepository.save(borrower));
    }
}
