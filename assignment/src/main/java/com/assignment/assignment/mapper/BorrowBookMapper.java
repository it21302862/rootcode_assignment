package com.assignment.assignment.mapper;

import com.assignment.assignment.DTO.ResponseDTO.BorrowResponseDTO;
import com.assignment.assignment.entity.BorrowRecord;
import org.springframework.stereotype.Component;

@Component
public class BorrowBookMapper {

    public static BorrowResponseDTO toBorrowResponse(BorrowRecord record, String message) {
        BorrowResponseDTO dto = new BorrowResponseDTO();
        dto.setMessage(message);
        dto.setId(record.getId());
        dto.setBookId(record.getBook().getId());
        dto.setBookTitle(record.getBook().getTitle());
        dto.setBorrowedAt(record.getBorrowedAt());
        dto.setReturnedAt(record.getReturnedAt());
        return dto;
    }
}
