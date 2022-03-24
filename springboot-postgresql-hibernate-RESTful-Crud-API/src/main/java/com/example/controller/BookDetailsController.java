package com.example.controller;

import com.example.exception.ResourceNotFoundException;
import com.example.model.BookAvg;
import com.example.model.BookDetails;
import com.example.model.BookRating;
import com.example.repository.BookDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RestController
@RequestMapping("/api/v1/")
public class BookDetailsController {

    @Autowired
    private BookDetailsRepository bookDetailsRepository;

	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
    @RequestMapping (path = "/bookdetails", method = RequestMethod.GET)
    public List<BookDetails> getALLBookDetails() {
        return this.bookDetailsRepository.findAll();
    }

    @GetMapping("/{isbn}")
    public List<BookDetails> getGenreBookDetails(@PathVariable long isbn){

            return this.bookDetailsRepository.findIsbn(isbn);
        }

    //Genre is case sensitive, the first letter must be uppercase!
    @GetMapping("/bookdetails/{genre}")
    public List<BookDetails> getGenreBookDetails(@PathVariable String genre) {

        List<BookDetails> booksOfThatGenre = new ArrayList<>();

        List<BookDetails> allBooks = this.bookDetailsRepository.findAll();

        Iterator<BookDetails> iterator = allBooks.iterator();

        while (iterator.hasNext()) {

            BookDetails currentBook = iterator.next();

            if(currentBook.getGenre().equals(genre)) {
                booksOfThatGenre.add(currentBook);
            }
        }

        return booksOfThatGenre;
    }
    
	@GetMapping("/test1")
	public List<BookAvg> getAll(){
		return jdbcTemplate.query("SELECT isbn,book_name, AVG(rating) AS avg_rating\n"
				+ "FROM\n"
				+ "(SELECT book_details.isbn, book_name, book_rating.rating FROM\n"
				+ "book_details\n"
				+ "FULL JOIN book_rating\n"
				+ "ON book_details.isbn = book_rating.isbn) AS tmp\n"
				+ "WHERE rating IS NOT NULL\n"
				+ "GROUP BY isbn, book_name\n"
				+ "HAVING AVG(rating) > 1", new BeanPropertyRowMapper<BookAvg>(BookAvg.class));
	}

//    @RequestMapping (path = "/genre", method = RequestMethod.GET)
//    public List<BookDetails> getALLBookDetails() {
//        return this.bookDetailsRepository.findAll();
//    }

    @PostMapping("/createbook") //creates book
    public void createBook(@RequestBody BookDetails book) {

        this.bookDetailsRepository.save(book);
        System.out.println(book);
    }
}
