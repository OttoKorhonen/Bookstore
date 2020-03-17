package hh.swd20.bookstore.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import hh.swd20.bookstore.domain.Book;
import hh.swd20.bookstore.domain.BookRepository;
import hh.swd20.bookstore.domain.CategoryRepository;

@Controller // Controller-annotaatio
public class BookController {

	// Spring-alusta luo sovelluksen käynnistyessä BookRepository-rajapintaa
	// toteuttavan luokan olion
	// ja kytkee olion BookController-luokasta luodun olion attribuuttiolioksi
	@Autowired
	BookRepository bookRepository;

	@Autowired
	CategoryRepository categoryRepository;

	// login controller kirja-sovellukseen
	@RequestMapping(value = "/login")
	public String login() {
		return "login";
	}

	@RequestMapping(value = "/editbook/{id}", method = RequestMethod.GET) // haetaan endpointia
	public String editBook(@PathVariable("id") Long Id, Model model) {
		model.addAttribute("book", bookRepository.findById(Id));// käytetään findById-metodia haettaessa
																// bookrepositorystä tiettyä kirjaa Id-tunnuksella
		return "editbook"; // editbook.html palautus
	}

	// Tehdään RESTful metodi hakemaan kaikki kirjat
	@RequestMapping(value = "/books", method = RequestMethod.GET)
	public @ResponseBody List<Book> bookListRest() {
		return (List<Book>) bookRepository.findAll();
	}

	// RESTful palvelu, jolla etsitään kirjaa id:n perusteella
	@RequestMapping(value = "/books/{id}", method = RequestMethod.GET)
	public @ResponseBody Optional<Book> findBookRest(@PathVariable("id") Long id) {
		return bookRepository.findById(id);
	}
	//listaa kirjat
	@RequestMapping(value = "/booklist", method = RequestMethod.GET)
	public String getBooks(Model model) {
		List<Book> books = (List<Book>) bookRepository.findAll();// haetaan tietokannasta kirjat
		model.addAttribute("books", books); // välitetään kirjalista templatelle model-olion avulla
		return "booklist"; // DispatcherServlet saa tämän template-nimen ja kutsuu seuraavaksi
							// booklist.html-templatea
	} // joka prosessoidaan palvelimella

	// tyhjän kirjalomakkeen muodostaminen, uuden kirjan luominen
	@RequestMapping(value = "/newbook", method = RequestMethod.GET)
	public String AddBookForm(Model model) {
		model.addAttribute("book", new Book()); // "tyhjä" kirja-olio
		model.addAttribute("categories", categoryRepository.findAll());// lisättiin haku categoryrepositorysta
		return "bookform"; // bookform.html palautus
	}

	// kirjalomakkeella syötettyjen tietojen vastaanotto ja tallennus
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveBook(@ModelAttribute Book book) {
		// talletetaan yhden kirjan tiedot tietokantaan
		bookRepository.save(book); // save osaa tehdä tarpeen mukaan SQL insertin tai updaten
		return "redirect:/booklist";// /booklist-endpointin kutsu
	}

	// kirjan poisto
	@RequestMapping(value = "/deletebook/{id}", method = RequestMethod.GET)
	public String deleteBook(@PathVariable("id") Long Id,  Model model) {
		bookRepository.deleteById(Id);
		return "redirect:../booklist";
	}
}