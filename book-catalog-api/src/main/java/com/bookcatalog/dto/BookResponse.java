package com.bookcatalog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookResponse {
    private Long id;
    private String title;
    private String author;
    private String genre;
    private Integer publicationYear;
    private boolean availability;
}
