package com.assignment.assignment.DTO.ResponseDTO;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
public class BorrowResponseDTO {
    private String message;
    private Integer id;
    private Integer bookId;
    private String bookTitle;
    private LocalDateTime returnedAt;
    private LocalDateTime borrowedAt;
}
