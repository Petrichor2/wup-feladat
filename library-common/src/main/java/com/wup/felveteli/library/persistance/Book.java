package com.wup.felveteli.library.persistance;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Data
@Table
@EqualsAndHashCode
public class Book {

    @Id
    @GeneratedValue
    @Type(type="uuid-char")
    private UUID bookId;

    @ManyToOne(cascade = CascadeType.ALL)
    private Author author;

    @Column(nullable = false)
    private String title;

    @ManyToOne(cascade = CascadeType.ALL)
    private Publisher publisher;

    @Column(nullable = false)
    private Date releaseDate;

    @Column(nullable = false)
    private Date dateOfEntry;

    @Column(nullable = false)
    private int count;
}
