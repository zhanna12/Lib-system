package kz.iitu.library;

import kz.iitu.library.models.*;
import kz.iitu.library.repositories.AuthorRepository;
import kz.iitu.library.repositories.BookRepository;
import kz.iitu.library.repositories.GenreRepository;
import kz.iitu.library.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class LibraryApp {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private AuthorRepository authorRepository;
    private static final Logger log = LoggerFactory.getLogger(LibraryApp.class);
    public static void main(String[] args) {
        SpringApplication.run(LibraryApp.class, args);
    }

    @Bean
    CommandLineRunner runner() {
        return (args) -> {

            User user = new User();
            user.setName("Meruert");
            user.setUserType(UserType.CUSTOMER);
            userRepository.save(user);

            Author author = new Author();
            author.setName("Caren");
            authorRepository.save(author);

            Genre genre1 = new Genre();
            genre1.setName("Psychology");
            genreRepository.save(genre1);

            Genre genre2 = new Genre();
            genre2.setName("Detective");
            genreRepository.save(genre2);

            Book book = new Book();
            List<Author> authors = new ArrayList<>();
            authors.add(author);
            List<Genre> genres = new ArrayList<>();
            genres.add(genre1);
            genres.add(genre2);
            book.setTitle("Personal growth");
            book.setAuthors(authors);
            book.setGenres(genres);
            bookRepository.save(book);

            Book book2 = new Book();
            List<Author> authors2 = new ArrayList<>();
            authors2.add(author);
            List<Genre> genres2 = new ArrayList<>();
            genres2.add(genre1);
            book2.setTitle("Train");
            book2.setAuthors(authors2);
            book2.setGenres(genres2);
            bookRepository.save(book2);

            User user1 = new User();
            List<Book> bookList = new ArrayList<>();
            bookList.add(book2);
            user1.setName("Zhanna");
            user1.setUserType(UserType.CUSTOMER);
            user1.setBooks(bookList);
            userRepository.save(user1);

            User user2 = new User();
            user2.addBook(bookRepository.findById(1L).orElse(null));
            user2.setName("Aruzhan");
            user2.setUserType(UserType.STUDENT);
            userRepository.save(user2);

            log.info("Books");
            log.info("-------------------------------");
            for (Book books : bookRepository.findAll()) {

                log.info(books.toString());
            }
            log.info("");
        };
    }
}
