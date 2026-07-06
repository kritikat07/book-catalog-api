package com.bookcatalog.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class BookRequest {

    @NotBlank(message = "Title is required")
    private String title;
    @NotBlank(message = "Author is required")
    private String author;
    private String genre;
    @Min(value = 1000, message = "Publication year seems invalid")
    private Integer publicationYear;

    private boolean availability = true;
}
