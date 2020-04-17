package kz.iitu.library.repositories;

import kz.iitu.library.models.Genre;
import org.springframework.data.repository.CrudRepository;

public interface GenreRepository extends CrudRepository<Genre, Long> {
    Genre findGenreByName(String name);
}
