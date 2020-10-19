package com.wup.felveteli.library.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthorCountsDto {

    private String authorName;

    private int bookTitles;

    private int sumCount;
}
