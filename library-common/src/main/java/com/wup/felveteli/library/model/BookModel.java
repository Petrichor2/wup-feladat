package com.wup.felveteli.library.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.wup.felveteli.library.dto.AuthorDto;
import com.wup.felveteli.library.dto.PublisherDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@Api("Model for Books for upload")
@JsonPropertyOrder(alphabetic = false)
public class BookModel {
    @ApiModelProperty(required = true, example = "Hobbit", name = "Title of the book")
    private String title;

    @ApiModelProperty(required = true, name = "Author of the book")
    private AuthorDto author;

    @ApiModelProperty(required = true, example = "1937-09-21", name = "Publishing date of the book")
    private Date publishedAt;

    @ApiModelProperty(required = true, name = "Publisher company")
    private PublisherDto publisher;

    @ApiModelProperty(required = true, example = "2020-10-18", name = "Entry to the library")
    private Date dateOfEntry;

    @ApiModelProperty(required = true, name = "Number of available issues", example = "10", allowableValues = "range[1, infinity]")
    private int count;
}
