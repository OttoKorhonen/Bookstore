package hh.swd20.bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import hh.swd20.bookstore.domain.Book;
import hh.swd20.bookstore.domain.BookRepository;
import hh.swd20.bookstore.domain.Category;
import hh.swd20.bookstore.domain.CategoryRepository;

@SpringBootApplication
public class BookstoreApplication {

	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);  // tehdään uusi loggeri jokaiselle luokalle
	
	public static void main(String[] args) {//mainista käynnistetään sovellus
		SpringApplication.run(BookstoreApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner bookDemo(BookRepository bookRepository, CategoryRepository categoryRepository) { //luodaan repository, johon tallennetaan muutama esimerkki kirja
		return (args) -> {
			log.info("Tallennetaan kirjoja");
			Category scifi = new Category("Scifi");
			Category comic = new Category("Comic");
			Category nonfiction = new Category("Non-fiction");
			Category horror = new Category("Horror");
			categoryRepository.save(scifi);
			categoryRepository.save(comic);
			categoryRepository.save(nonfiction);
			categoryRepository.save(horror);
			
			
			bookRepository.save(new Book("978-1-83882-236-1", "Juha Hinkula","Hands-On Full Stack Development with Spring Boot 2 and React", 2019, 1.1, nonfiction));
			bookRepository.save(new Book("978-952-220-084-6", "Jukka Harju, Jukka Juslin","Java-ohjelmointi opas ammattimaiseen osaamiseen", 2009, 1.2, nonfiction));	
				
			log.info("Hae kirjoja");
			for (Book book : bookRepository.findAll()) {
				log.info(book.toString());
			}

		};
	}
}
