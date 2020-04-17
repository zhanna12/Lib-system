package kz.iitu.library.repositories;

import kz.iitu.library.models.Author;
import org.springframework.data.repository.CrudRepository;

public interface AuthorRepository extends CrudRepository<Author, Long> {
    Author findAuthorByName(String name);
}
