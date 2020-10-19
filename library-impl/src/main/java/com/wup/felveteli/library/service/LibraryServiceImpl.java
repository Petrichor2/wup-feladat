package com.wup.felveteli.library.service;

import com.wup.felveteli.library.dto.AuthorDto;
import com.wup.felveteli.library.dto.BookDto;
import com.wup.felveteli.library.model.BookModel;
import com.wup.felveteli.library.persistance.AuthorRepository;
import com.wup.felveteli.library.persistance.Book;
import com.wup.felveteli.library.persistance.BookRepository;
import com.wup.felveteli.library.persistance.PublisherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LibraryServiceImpl implements LibraryService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final PublisherRepository publisherRepository;

    @Override
    public void addBook(BookModel book) {
        bookRepository.save(toEntity(book));
    }

    @Override
    public List<BookDto> findBook(String title, String author, String publisher, Date publishedAt) {
        return bookRepository.findBook(title, author, publisher, publishedAt)
                .stream()
                .map(BookDto::fromEntity)
                .collect(Collectors.toList());
    }


    private Book toEntity(BookModel bookInput) {
        Book entity = new Book();
        entity.setBookId(UUID.randomUUID());
        entity.setTitle(bookInput.getTitle());
        AuthorDto authorDto = bookInput.getAuthor();
        entity.setAuthor(authorRepository.findByNameAndAndBornAt(authorDto.getName(), authorDto.getBornAt()).orElse(authorDto.toEntity()));
        entity.setPublisher(publisherRepository.findByName(bookInput.getPublisher().getName()).orElse(bookInput.getPublisher().toEntity()));
        entity.setReleaseDate(bookInput.getPublishedAt());
        entity.setDateOfEntry(bookInput.getDateOfEntry());
        entity.setCount(bookInput.getCount());
        return entity;
    }
}
