package kz.iitu.library.Services;

import kz.iitu.library.models.*;
import kz.iitu.library.repositories.BookRepository;
import kz.iitu.library.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public boolean addBook(Book book) {
        if (bookRepository.findBookByTitleIgnoreCase(book.getTitle()) != null) {
            book.setId(Long.MIN_VALUE);
            System.out.println("Error");
            return false;
        }
        bookRepository.save(book);
        return true;
    }

    @Transactional
    public boolean addBookToUser(Long userId, Long bookId) {
        if (bookRepository.findById(bookId).isEmpty()) {
            System.out.println("Data is not right");
            return false;
        } else {
            userRepository.findById(userId).get();
        }
        if (bookRepository.findById(bookId).get().getUser().getId() != userId) {
            System.out.println("User did not requested this book");
            return false;
        }

        Book book = bookRepository.findById(bookId).get();
        book.setUser(userRepository.findById(userId).get());


        bookRepository.save(book);
        return true;
    }

    @Transactional
    public boolean returnBookFromUser(Long userId, Long bookId) {
        if (bookRepository.findById(bookId).isEmpty() || userRepository.findById(userId).get() == null) {
            System.out.println("Error");
            return false;
        }
        if (bookRepository.findById(bookId).get().getUser() != userRepository.findById(userId).get()) {
            System.out.println("Error");
            return false;
        }

        Book book = bookRepository.findById(bookId).get();
        bookRepository.save(book);
        return true;
    }

    @Transactional
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Transactional
    public Book findBookByName(String title) {
        return bookRepository.findBookByTitleIgnoreCase(title);
    }

    @Transactional
    public void save(Book book) {
        bookRepository.save(book);
    }

    @Transactional
    public Book findBookById(Long id) {
        return bookRepository.findById(id).get();
    }

    @Transactional
    public Book findBookByAuthor(Author author) {
        return bookRepository.findBookByAuthorsContaining(author);
    }

    @Transactional
    public void clear() {
        for (Book b : bookRepository.findAll()) {
            b.setGenres(null);
            b.setAuthors(null);
            bookRepository.save(b);
        }
        bookRepository.deleteAll();
    }
}
