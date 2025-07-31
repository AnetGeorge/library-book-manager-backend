package com.example.library;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    // Create (POST)
    @PostMapping
    public Book addBook(@RequestBody Book book) {
        return bookRepository.save(book);
    }

    // Read All (GET)
    @GetMapping
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    // Read by ID (GET)
    @GetMapping("/{id}")
public ResponseEntity<?> getBookById(@PathVariable Long id) {
    Optional<Book> book = bookRepository.findById(id);
    if (book.isPresent()) {
        return ResponseEntity.ok(book.get());
    } else {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                             .body("❌ Book not found with ID: " + id);
    }
}


    // Update (PUT)
    @PutMapping("/{id}")
    public Book updateBook(@PathVariable Long id, @RequestBody Book bookDetails) {
        Book book = bookRepository.findById(id).orElseThrow();
        book.setTitle(bookDetails.getTitle());
        book.setAuthor(bookDetails.getAuthor());
        book.setGenre(bookDetails.getGenre());
        book.setPublishedYear(bookDetails.getPublishedYear());
        return bookRepository.save(book);
    }

    // Delete a book
@DeleteMapping("/{id}")
public ResponseEntity<?> deleteBook(@PathVariable Long id) {
    if (bookRepository.existsById(id)) {
        bookRepository.deleteById(id);
        return ResponseEntity.ok("✅ Book deleted with ID: " + id);
    } else {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("❌ Cannot delete. Book not found with ID: " + id);
    }
}

// Filter by author (case-insensitive)
@GetMapping("/author/{author}")
public List<Book> getByAuthor(@PathVariable String author) {
    return bookRepository.findByAuthorIgnoreCase(author);
}

// Filter by genre (case-insensitive)
@GetMapping("/genre/{genre}")
public List<Book> getByGenre(@PathVariable String genre) {
    return bookRepository.findByGenreIgnoreCase(genre);
}



// Books after a specific year
@GetMapping("/year/after/{year}")
public List<Book> getBooksAfterYear(@PathVariable int year) {
return bookRepository.findByPublishedYearGreaterThan(year);
}

// Books before a specific year
@GetMapping("/year/before/{year}")
public List<Book> getBooksBeforeYear(@PathVariable int year) {
return bookRepository.findByPublishedYearLessThan(year);
}

// Books in an exact year
@GetMapping("/year/{year}")
public List<Book> getBooksByYear(@PathVariable int year) {
return bookRepository.findByPublishedYear(year);
}


// Filter by keyword in title (case-insensitive)
@GetMapping("/search/title/{keyword}")
public List<Book> searchTitle(@PathVariable String keyword) {
    return bookRepository.findByTitleContainingIgnoreCase(keyword);
}

// Combined filter using RequestParams (case-insensitive)
@GetMapping("/filter")
public List<Book> filterBooks(
        @RequestParam(required = false) String author,
        @RequestParam(required = false) String genre) {
    if (author != null && genre != null) {
        return bookRepository.findByAuthorAndGenreIgnoreCase(author, genre);
    } else if (author != null) {
        return bookRepository.findByAuthorIgnoreCase(author);
    } else if (genre != null) {
        return bookRepository.findByGenreIgnoreCase(genre);
    } else {
        return bookRepository.findAll();
    }
}
}
