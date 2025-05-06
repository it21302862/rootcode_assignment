package com.assignment.assignment.DTO.ResponseDTO;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class BookResponseDTO {
    private Integer id;
    private String title;
    private String author;
    private int publishedYear;
    private int availableCopies;
}
