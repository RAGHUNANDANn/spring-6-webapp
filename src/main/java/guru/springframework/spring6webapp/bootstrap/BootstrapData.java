package guru.springframework.spring6webapp.bootstrap;

import guru.springframework.spring6webapp.domain.Author;
import guru.springframework.spring6webapp.domain.Book;
import guru.springframework.spring6webapp.domain.Publisher;
import guru.springframework.spring6webapp.repositories.AuthorRepository;
import guru.springframework.spring6webapp.repositories.BookRepository;
import guru.springframework.spring6webapp.repositories.PublishRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublishRepository publishRepository;

    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublishRepository publishRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publishRepository = publishRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Author eric = new Author();
        eric.setFirstName("Eric");
        eric.setLastName("Evans");

        Book ddd = new Book();
        ddd.setTitle("Domain Driven Design");
        ddd.setIsbn("123456");

        Author ericSaved = authorRepository.save(eric);
        Book dddSaved = bookRepository.save(ddd);

        Author rod = new Author();
        rod.setFirstName("Rod");
        rod.setLastName("Johnson");

        Book noEJB = new Book();
        noEJB.setTitle("J2EE Development without EJB");
        noEJB.setIsbn("54757585");

        Author rodSaved = authorRepository.save(rod);
        Book noEJBSaved = bookRepository.save(noEJB);

        ericSaved.getBooks().add(dddSaved);
        rodSaved.getBooks().add(noEJBSaved);
        dddSaved.getAuthors().add(ericSaved);
        noEJBSaved.getAuthors().add(rodSaved);

        //Publisher
        Publisher oreilly = new Publisher();
        oreilly.setPublisherName("ORIELLY");
        oreilly.setState("Karnataka");
        oreilly.setCity("Bangalore");
        oreilly.setZipCode("560064");
        oreilly.setAddress("4th phase yelahanka");

        Publisher oreillySaved = publishRepository.save(oreilly);

        dddSaved.setPublisher(oreillySaved);
        noEJBSaved.setPublisher(oreillySaved);

        authorRepository.save(ericSaved);
        authorRepository.save(rodSaved);
        bookRepository.save(dddSaved);
        bookRepository.save(noEJBSaved);

        System.out.println("In Bootstrap");
        System.out.println("Author count:" + authorRepository.count());
        System.out.println("Book Count:" + bookRepository.count());


        System.out.println("Published count:" + publishRepository.count());


    }
}
