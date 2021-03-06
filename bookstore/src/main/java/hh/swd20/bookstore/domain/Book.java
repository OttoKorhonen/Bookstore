package hh.swd20.bookstore.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Book { //luodaan kirja-luokka
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)//luodaan automaattisesti id-numeroita kirjoille
	private Long id; //annetaan kirja-luokalle attribuutit
	private String isbn;
	private String author;
	private String title;
	private int year;
	private double price;
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "categoryid") //id viittaa category id:hen
	private Category category;
	
	public Book() {//luodaan kirja-luokalle konstruktorit sekä getterit ja setterit
		super();
		this.id = null;
		this.isbn = null;
		this.author = null;
		this.title = null;
		this.year = 0;
		this.price = 0;
	}
	
	
	public Book(String isbn, String author, String title, int year, double price, Category category) {
		super();
		this.isbn = isbn;
		this.author = author;
		this.title = title;
		this.year = year;
		this.price = price;
		this.category = category;
	}


	public Book(Long id, String isbn, String author, String title, int year, double price, Category category) {
		super();
		this.id = id;
		this.isbn = isbn;
		this.author = author;
		this.title = title;
		this.year = year;
		this.price = price;
		this.category = category;
	}

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getIsbn() {
		return isbn;
	}


	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}


	public String getAuthor() {
		return author;
	}


	public void setAuthor(String author) {
		this.author = author;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public int getYear() {
		return year;
	}


	public void setYear(int year) {
		this.year = year;
	}


	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}


	public Category getCategory() {
		return category;
	}


	public void setCategory(Category category) {
		this.category = category;
	}


	@Override
	public String toString() {
		if(this.category != null)
			return "Book [id=" + id + ", isbn=" + isbn + ", author=" + author + ", title=" + title + ", year=" + year
				+ ", price=" + price + ", category=" + category + "]";
		else
			return "Book [id=" + id + ", isbn=" + isbn + ", author=" + author + ", title=" + title + ", year=" + year
					+ ", price=" + price + "]";
	}


}