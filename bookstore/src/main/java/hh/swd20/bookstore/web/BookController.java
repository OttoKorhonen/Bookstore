package hh.swd20.bookstore.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import hh.swd20.bookstore.domain.Book;

@Controller
public class BookController {

	//tyhjän kirjalomakkeen muodostaminen
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String getIndex(Model model) {
		model.addAttribute("book", new Book());
		return "index";
	}
}
