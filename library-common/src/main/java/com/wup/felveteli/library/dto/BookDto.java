package com.wup.felveteli.library.dto;

import com.wup.felveteli.library.persistance.Book;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel("Book Dto")
public class BookDto {
    @ApiModelProperty(required = true, example = "Hobbit")
    private String title;

    @ApiModelProperty(required = true, example = "J. R. R. Tolkien")
    private String author;

    @ApiModelProperty(required = true, example = "1937-09-21")
    private Date publishedAt;

    @ApiModelProperty(required = true, example = "George Allen & Unwin (UK)")
    private String publisher;

    @ApiModelProperty(required = true, example = "2020-10-18")
    private Date dateOfEntry;

    @ApiModelProperty(required = true)
    private int count;

    public static BookDto fromEntity(Book entity) {
        BookDto bookDto = new BookDto();
        bookDto.title = entity.getTitle();
        bookDto.author = entity.getAuthor().getName();
        bookDto.publisher = entity.getPublisher().getName();
        bookDto.publishedAt = entity.getReleaseDate();
        bookDto.dateOfEntry = entity.getDateOfEntry();
        bookDto.count = entity.getCount();
        return bookDto;
    }

}
