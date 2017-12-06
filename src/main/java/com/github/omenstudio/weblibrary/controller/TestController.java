package com.github.omenstudio.weblibrary.controller;

import com.github.omenstudio.weblibrary.entity.Author;
import com.github.omenstudio.weblibrary.entity.Book;
import com.github.omenstudio.weblibrary.entity.Publisher;
import com.github.omenstudio.weblibrary.repository.AuthorRepository;
import com.github.omenstudio.weblibrary.repository.BookRepository;
import com.github.omenstudio.weblibrary.repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Date;

@RestController
public class TestController {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    PublisherRepository publisherRepository;

    @GetMapping("/iamalive")
    public boolean iAmAlive() {
        return true;
    }

    @GetMapping("/resetdb")
    public void resetDb() {
        // Clear the database
        bookRepository.deleteAll();
        authorRepository.deleteAll();
        publisherRepository.deleteAll();

        // Authors
        Author stephenKing = new Author("Stephen King", new Date(1947, 8, 21), "State Man, USA");
        Author arthurConanDoyle = new Author("Arthur Conan Doyle", new Date(1859, 4, 22), "Edinburg, Great Britain");
        Author andreyCruz = new Author("Andrey Cruz", null, "Moscow, Russia");
        Author malcolmGladwell = new Author("Malcolm Gladwell", new Date(1963, 8, 3), "Warhem, Great Britain");

        // Publishers
        Publisher eksmo = new Publisher("Eksmo", new Date(1991, 0, 0), "Moscow, Russia");
        Publisher mif = new Publisher("MIF: Mann, Ivanov, Ferber", new Date(2005, 0, 0), "Moscow, Russia");
        Publisher alpina = new Publisher("Alpina Publisher", new Date(1998, 0, 0), "Saint-Petersburg, Russia");

        // Books
        Book king1 = new Book("Ono", "It: A Novel",
                "Welcome to Derry, Maine. It’s a small city, a place as hauntingly familiar as " +
                        "your own hometown. Only in Derry the haunting is real.",
                        1979, 13, 239, "978-5-9614-6244-9"
                );
        Book king2 = new Book("Siyanie", "The shining",
                "Jack Torrance’s new job at the Overlook Hotel is the perfect chance for a fresh start. " +
                        "As the off-season caretaker at the atmospheric old hotel",
                        1983, 5, 781, "978-5-699-88634-0"
                );
        Book king3 = new Book("Kladbishe domashnih zhivotnih", "Pet Sematary: A Novel",
                "When Dr. Louis Creed takes a new job and moves his family to the idyllic and rural " +
                        "town of Ludlow, Maine, this new beginning seems too good to be true.",
                        1994, 7, 111, "978-5-699-87785-0"
                );
        Book king4 = new Book("Temnaya bashnya 1: strelok", "The Dark Tower I: The Gunslinger",
                "Roland of Gilead: The Last Gunslinger. He is a haunting figure, " +
                        "a loner on a spellbinding journey into good and evil.",
                        1991, 21, 489, "978-5-9614-6406-1"
                );
        Book doyle1 = new Book("Sherlock Holmes: polnoe sobranie sochineniy", "The Complete Sherlock Holmes",
                "The next elegant edition in the Knickerbocker Classic series is The Complete Sherlock " +
                        "Holmes comprised of 4 full-length novels and 56 short stories featuring the world’s most " +
                        "famous pipe-smoking detective. ",
                        1840, 12, 266, "978-5-91671-563-7"
                );
        Book doyle2 = new Book("Sobaka baskervilley", "The Hound of the Baskervilles",
                "The Hound of the Baskervilles is the third of the four crime novels written " +
                        "by Sir Arthur Conan Doyle featuring the detective Sherlock Holmes.",
                        1850, 17, 431, "978-5-9614-6484-9"
                );
        Book doyle3 = new Book("Sbornik victorianskih strashilok o prizrakah",
                "The Valancourt Book of Victorian Christmas Ghost Stories",
                "During the Victorian era, it became traditional for publishers of newspapers " +
                        "and magazines to print ghost stories during the Christmas season for chilling " +
                        "winter reading by the fireside or candlelight.",
                        1844, 2, 97, "978-5-91671-129-5"
                );
        Book cruz1 = new Book("Ya edu domoy", null,
                null,
                2001, 4, 1024, "978-5-91671-533-0"
        );
        Book cruz2 = new Book("Epoha mertvih: Moskva", null,
                "The next elegant edition in the Knickerbocker Classic series is The Complete Sherlock " +
                        "Great story about zombies in Moscow and postapocalyptics.",
                2005, 3, 2048, "978-5-91671-456-2"
        );
        Book gladwell1 = new Book("Genii i outsideri", "The Complete Sherlock Holmes",
                "The next elegant edition in the Knickerbocker Classic series is The Complete Sherlock " +
                        "Holmes comprised of 4 full-length novels and 56 short stories featuring the world’s most " +
                        "famous pipe-smoking detective. ",
                1998, 2, 302, "978-5-91671-665-8"
        );

        // Link books and authors
        Arrays.stream(new Book[]{king1, king2, king3, king4}).forEach(b -> b.setAuthor(stephenKing));
        Arrays.stream(new Book[]{doyle1, doyle2, doyle3}).forEach(b -> b.setAuthor(arthurConanDoyle));
        Arrays.stream(new Book[]{cruz1, cruz2}).forEach(b -> b.setAuthor(andreyCruz));
        Arrays.stream(new Book[]{gladwell1}).forEach(b -> b.setAuthor(malcolmGladwell));

        // Link books and publishers
        Arrays.stream(new Book[]{cruz1, cruz2, king1, king2,  }).forEach(b -> b.setPublisher(eksmo));
        Arrays.stream(new Book[]{king3, doyle1, doyle3}).forEach(b -> b.setPublisher(mif));
        Arrays.stream(new Book[]{king4, doyle2, gladwell1}).forEach(b -> b.setPublisher(alpina));

        // Save all
        publisherRepository.save(Arrays.asList(eksmo, mif, alpina));
        authorRepository.save(Arrays.asList(stephenKing, arthurConanDoyle, andreyCruz, malcolmGladwell));
        bookRepository.save(Arrays.asList(king1, king2, king3, king4, doyle1, doyle2, doyle3, cruz1, cruz2, gladwell1));
    }


}
