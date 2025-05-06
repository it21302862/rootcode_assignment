package com.assignment.assignment.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue
    private Integer id;

    @NotNull
    private String title;
    @NotNull
    private String author;
    @NotNull
    private int publishedYear;
    @NotNull
    private int availableCopies;

    @OneToMany(mappedBy = "book")
    private List<BorrowRecord> borrowRecords;
}
