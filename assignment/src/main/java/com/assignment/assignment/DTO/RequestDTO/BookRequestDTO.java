package com.assignment.assignment.DTO.RequestDTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class BookRequestDTO {

    @NotBlank(message = "Book title is required")
    private String title;
    @NotBlank(message = "Book author is required")
    private String author;

    @Min(value = 0, message = "Published year must be a positive number")
    private int publishedYear;
    @Min(value = 0, message = "Available copies must be zero or more")
    private int availableCopies;
}
