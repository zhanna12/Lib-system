package kz.iitu.library.repos;

import kz.iitu.library.models.Author;
import kz.iitu.library.models.Book;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookRepository extends CrudRepository<Book, Long> {
//    List<Book> findAllByStatus(Status status);
List<Book> findAll();
    Book findBookByTitleIgnoreCase(String title);

    Book findBookByAuthorsContaining(Author author);
}
