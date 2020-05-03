package kz.iitu.library.controllers;

import kz.iitu.library.Services.GenreService;
import kz.iitu.library.models.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/genres")
public class GenreController {
    @Autowired
    private GenreService genreService;

    @PostMapping("")
    public void addGenre(@RequestBody Genre genre){
        if(genreService.addGenre(genre)){
            System.out.println(genre + " added");
            return;
        }
        System.out.println("Genre already exist");
    }

    @GetMapping("/{name}")
    public Genre findGenreByName(@PathVariable("name") String name){
        return genreService.findGenreByName(name);
    }

    @DeleteMapping("/del")
    public void clear() {
        genreService.clear();
    }
}
