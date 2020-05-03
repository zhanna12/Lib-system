package kz.iitu.library.repositories;

import kz.iitu.library.models.Author;
import kz.iitu.library.models.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {

    List<Book> findAll();
    Book findBookByTitleIgnoreCase(String title);

    Book findBookByAuthorsContaining(Author author);
}
