package com.assignment.assignment.DTO.RequestDTO;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class BorrowRequestDTO {
    @NotNull(message = "Please give a book id")
    private Integer bookId;
}
