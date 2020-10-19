package com.wup.felveteli.library.dto;

import com.wup.felveteli.library.persistance.Publisher;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class PublisherDto {

    @ApiModelProperty(required = true, example = "George Allen & Unwin")
    private String name;

    @ApiModelProperty(required = true, example = "1914")
    private int dateOfFoundation;

    public Publisher toEntity() {
        Publisher publisher = new Publisher();
        publisher.setName(name);
        publisher.setDateOfFoundation(dateOfFoundation);
        return publisher;
    }
}
