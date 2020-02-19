package hh.swd20.bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import hh.swd20.bookstore.domain.Book;
import hh.swd20.bookstore.domain.BookRepository;

@SpringBootApplication
public class BookstoreApplication {

	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);  // uusi loggeriattribuutti
	
	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	@Bean
	public CommandLineRunner carDemo(BookRepository bookRepository) { 
		return (args) -> {
			log.info("Tallennetaan kirjoja");
			bookRepository.save(new Book(123456789, "978-1-83882-236-1", "Juha Hinkula","Hands-On Full Stack Development with Spring Boot 2 and React", 2019, 1.1));
			bookRepository.save(new Book(123456782, "978-952-220-084-6", "Jukka Harju, Jukka Juslin","Java-ohjelmointi opas ammattimaiseen osaamiseen", 2009, 1.2));	
			
			log.info("fetch all cars");
			for (Book book : bookRepository.findAll()) {
				log.info(book.toString());
			}

		};
	}
}
