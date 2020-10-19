package com.wup.felveteli.library.persistance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.Tuple;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Repository
public interface BookRepository extends JpaRepository<Book, UUID> {

    @Query(value = "select b.author, sum(b.count), count(b.title) from Book b group by b.author")
    List<Tuple> getAuthorBookCounts();

    @Query(value = "select b.publisher, sum(b.count), count(b.title) from Book b group by b.publisher")
    List<Tuple> getPublisherCounts();

    @Query(nativeQuery = true, value = "select avg(datediff(CURDATE(),b.release_date)) as diff_in_days from book b")
    int getAvgBookAge();

    @Query(nativeQuery = true, value = "SELECT * FROM book WHERE release_date IN (SELECT MIN(release_date) FROM book union SELECT MAX(release_date) FROM book)")
    List<Book> findLatestOldest();

    @Query(value = "select b.author, sum(b.count) from Book b where b.releaseDate > ?1 group by b.author")
    List<Tuple> booksCountUntil(Date dateUntil);
}
