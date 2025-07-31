package com.example.library;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BookRepository extends JpaRepository<Book, Long> {
@Query("SELECT b FROM Book b WHERE LOWER(b.author) = LOWER(:author)")
List<Book> findByAuthorIgnoreCase(@Param("author") String author);

@Query("SELECT b FROM Book b WHERE LOWER(b.genre) = LOWER(:genre)")
List<Book> findByGenreIgnoreCase(@Param("genre") String genre);

@Query("SELECT b FROM Book b WHERE LOWER(b.title) LIKE LOWER(CONCAT('%', :keyword, '%'))")
List<Book> findByTitleContainingIgnoreCase(@Param("keyword") String keyword);

@Query("SELECT b FROM Book b WHERE LOWER(b.author) = LOWER(:author) AND LOWER(b.genre) = LOWER(:genre)")
List<Book> findByAuthorAndGenreIgnoreCase(@Param("author") String author, @Param("genre") String genre);

List<Book> findByPublishedYearGreaterThan(int year);         // after
List<Book> findByPublishedYearLessThan(int year);            // before
List<Book> findByPublishedYear(int year);                    // exact year
  
}
