package kz.iitu.library.Services;

import kz.iitu.library.models.Genre;
import kz.iitu.library.repositories.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class GenreService {
    private GenreRepository genreRepository;

    @Autowired
    public void setGenreRepository(GenreRepository genreRepository){
        this.genreRepository=genreRepository;
    }

    @Transactional
    public boolean addGenre(Genre genre) {
        if (genreRepository.findGenreByName(genre.getName()) != null)
            return false;
        genreRepository.save(genre);
        return true;

    }
    @Transactional
    public Genre findGenreByName(String name){
        return genreRepository.findGenreByName(name);
    }

    @Transactional
    public void clear(){
        genreRepository.deleteAll();
    }
}
