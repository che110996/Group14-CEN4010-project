package com.example.controller;

import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import com.example.exception.ResourceNotFoundException;
import com.example.model.BookRating;
import com.example.model.BookDetails;
import com.example.repository.BookRatingRepository;

@RestController
@RequestMapping("/api/")
public class BookRatingController {

	
	
	@Autowired
	private BookRatingRepository bookRatingRepository;
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	
	
	@GetMapping("/bookrating")
	@RequestMapping(path = "/bookrating", method = RequestMethod.GET)
	public List<BookRating> getALLBookRating(){
		return this.bookRatingRepository.findAll();
	}
	
	@RequestMapping(path = "/helloworld", method = RequestMethod.GET)
	public String getGreeting() {
		return "Hello World, this is Eren";
	}
	
	@GetMapping("/bookrating/{isbn}")
	public ResponseEntity<List<BookRating>> getALLByID(@PathVariable(value = "isbn")Long isbn){
		return new ResponseEntity<List<BookRating>>(bookRatingRepository.findByIsbn(isbn), HttpStatus.OK);
	}
	/*
	@GetMapping("/test")
	public List<BookRating> getAll(){
		return jdbcTemplate.query("SELECT isbn,book_name, AVG(rating)\r\n"
				+ "FROM\r\n"
				+ "(SELECT book_details.isbn, book_name, rating FROM\r\n"
				+ "book_details\r\n"
				+ "FULL JOIN book_rating\r\n"
				+ "ON book_details.isbn = book_rating.isbn) AS tmp\r\n"
				+ "WHERE rating IS NOT NULL\r\n"
				+ "GROUP BY isbn, book_name\r\n"
				+ "HAVING AVG(rating) > 2", new BeanPropertyRowMapper<BookRating>(BookRating.class));

	}
	
	/*
	// get book ratings api
	/*
	@GetMapping("bookrating")
	public List<BookRating> getALLBookRating(){
		return this.bookRatingRepository.findAll();
	}
	
	
	*/

	
	
	/*
	 // get book ratings by isbn
	@GetMapping("/bookrating/{isbn}")
		public ResponseEntity<BookRating> getBookRatingByIsbn(@PathVariable(value = "isbn") Long isbn) throws ResourceNotFoundException{
			BookRating bookrating = bookRatingRepository.findById(isbn)
					.orElseThrow(() -> new ResourceNotFoundException("ISBN not found for :: " + isbn));
			return ResponseEntity.ok().body(bookrating);
	}
	
	// save rating
	@PostMapping("bookrating")
	public BookRating createBookRating(@RequestBody BookRating bookrating) {
		return this.bookRatingRepository.save(bookrating);
	}
	
	// update rating
	@PutMapping("bookRating/{isbn}")
	public ResponseEntity<BookRating> updateBookRatingByIsbn(@PathVariable(value = "isbn") Long isbn,
			@Valid @RequestBody BookRating bookRatingDetails) throws ResourceNotFoundException{
		
		BookRating bookrating = bookRatingRepository.findById(isbn)
				.orElseThrow(() -> new ResourceNotFoundException("ISBN not found for :: " + isbn));
		bookrating.setEmail(bookRatingDetails.getEmail());
		bookrating.setRating(bookRatingDetails.getRating());
		bookrating.setDatestamp(bookRatingDetails.getDatestamp());
		bookrating.setRating_comment(bookRatingDetails.getRating_comment());
		
		return ResponseEntity.ok(this.bookRatingRepository.save(bookrating));
		
		
	}
	// delete rating
	
	*/
}
