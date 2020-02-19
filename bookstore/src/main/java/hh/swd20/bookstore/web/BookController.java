package hh.swd20.bookstore.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import hh.swd20.bookstore.domain.Book;
import hh.swd20.bookstore.domain.BookRepository;

@Controller
public class BookController {

	 // Spring-alusta luo sovelluksen käynnistyessä BookRepository-rajapintaa toteuttavan luokan olion 
	   // ja kytkee olion BookController-luokasta luodun olion attribuuttiolioksi
	@Autowired
	BookRepository bookrepository;
	//tyhjän kirjalomakkeen muodostaminen
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String getIndex(Model model) {
		model.addAttribute("book", new Book());
		return "index";
	}
	
	@RequestMapping(value = "/books", method = RequestMethod.GET)
	public String getBooks(Model model) {
		List<Book> books = (List<Book>) bookrepository.findAll();//haetaan tietokannasta kirjat
		model.addAttribute("books",books); //välitetään kirjalista templatelle model-olion avulla
		return "booklist"; //DispatcherServlet saa tämän template-nimen ja kutsuu seuraavaksi booklist.html-templatea
	}						//joka prosessoidaan palvelimella
	
	// tyhjän kirjalomakkeen muodostaminen
		@RequestMapping(value = "/newbook", method = RequestMethod.GET)
		public String getNewBookForm(Model model) {
			model.addAttribute("book", new Book()); // "tyhjä" kirja-olio
			return "bookform"; //bookform.html palautus
		}

		// kirjalomakkeella syötettyjen tietojen vastaanotto ja tallennus
		@RequestMapping(value = "/save", method = RequestMethod.POST)
		public String saveBook(@ModelAttribute Book book) {
			// talletetaan yhden kirjan tiedot tietokantaan
			bookrepository.save(book); //save osaa tehdä tarpeen mukaan SQL insertin tai updaten
			return "redirect:/books";// /booklist-endpointin kutsu
		}

		// kirjan poisto
		@RequestMapping(value = "/deletebook/{id}", method = RequestMethod.GET)
		public String deleteBook(@PathVariable("id") Long Id) {
			bookrepository.deleteById(Id);
			return "redirect:../books";
		}
}