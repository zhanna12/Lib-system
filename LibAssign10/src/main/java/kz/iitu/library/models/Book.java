package kz.iitu.library.models;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name="book_author",
            joinColumns = @JoinColumn(name = "book_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "author_id", referencedColumnName = "id"))
    private List<Author> authors;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @JoinColumn(name = "user_id")
    private User user;


    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name="book_genre",
            joinColumns = @JoinColumn(name = "book_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id", referencedColumnName = "id"))
    private List<Genre> genres;

//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public String getTitle() {
//        return title;
//    }
//
//    public List<Author> getAuthors() {
//        return authors;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }
//
//    public User getUser() {
//        return user;
//    }
//
//    public List<Genre> getGenres() {
//        return genres;
//    }
//
//    public void setAuthors(List<Author> authors) {
//        this.authors = authors;
//    }
//
//    public void setGenres(List<Genre> genres) {
//        this.genres = genres;
//    }
}