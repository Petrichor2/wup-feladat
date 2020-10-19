package com.wup.felveteli.library.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PublisherCountsDto {

    private String publisherName;

    private int bookTitles;

    private int sumCount;
}
