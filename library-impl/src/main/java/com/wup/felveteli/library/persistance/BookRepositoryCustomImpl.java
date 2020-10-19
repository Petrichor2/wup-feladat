package com.wup.felveteli.library.persistance;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class BookRepositoryCustomImpl implements BookRepositoryCustom {

    private final EntityManager entityManager;

    @Override
    public List<Book> findBook(String title, String author, String publisher, Date publishedAt) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Book> cq = cb.createQuery(Book.class);

        Root<Book> book = cq.from(Book.class);
        List<Predicate> predicates = new ArrayList<>();

        Optional.ofNullable(author)
                .map(aut -> cb.equal(book.get("author").get("name"), aut))
                .ifPresent(predicates::add);

        Optional.ofNullable(title)
                .map(ti -> cb.like(book.get("title"), "%" + ti + "%"))
                .ifPresent(predicates::add);

        Optional.ofNullable(publisher)
                .map(pub -> cb.equal(book.get("publisher").get("name"), pub))
                .ifPresent(predicates::add);

        Optional.ofNullable(publishedAt)
                .map(pubAt -> cb.equal(book.get("release_date"), pubAt))
                .ifPresent(predicates::add);

        cq.where(predicates.toArray(new Predicate[0]));
        return entityManager.createQuery(cq).getResultList();
    }
}
