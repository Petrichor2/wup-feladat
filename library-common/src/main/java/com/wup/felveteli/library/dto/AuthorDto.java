package com.wup.felveteli.library.dto;

import com.wup.felveteli.library.persistance.Author;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel
public class AuthorDto {

    @ApiModelProperty(required = true, example = "J. R. R. Tolkien")
    private String name;

    @ApiModelProperty(required = true, example = "1892-01-03")
    private Date bornAt;

    @ApiModelProperty(required = true, example = "Bloemfontein, Orange Free State")
    private String bornIn;

    public Author toEntity() {
        Author author = new Author();
        author.setName(this.name);
        author.setBornAt(this.bornAt);
        author.setBornIn(this.bornIn);
        return author;
    }

}
