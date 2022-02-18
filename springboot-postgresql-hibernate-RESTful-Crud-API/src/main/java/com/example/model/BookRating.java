package com.example.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "book_rating")
public class BookRating {
	

	private long isbn;
	private String email;
	private Integer rating;
	private Timestamp datestamp;
	private String rating_comment;
	
	
	public BookRating() {
		
	}
	public BookRating(String email, Integer rating, Timestamp datestamp, String rating_comment) {
		this.email = email;
		this.rating = rating;
		this.datestamp = datestamp;
		this.rating_comment = rating_comment;
	}

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long getIsbn() {
		return isbn;
	}
	public void setIsbn(long isbn) {
		this.isbn = isbn;
	}
	
	@Column(name = "email")
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Column(name = "rating")
	public Integer getRating() {
		return rating;
	}
	public void setRating(Integer rating) {
		this.rating = rating;
	}
	
	@Column(name = "datestamp")
	public Timestamp getDatestamp() {
		return datestamp;
	}
	public void setDatestamp(Timestamp datestamp) {
		this.datestamp = datestamp;
	}
	
	@Column(name = "rating_comment")
	public String getRating_comment() {
		return rating_comment;
	}
	public void setRating_comment(String rating_comment) {
		this.rating_comment = rating_comment;
	}

	@Override
	public String toString() {
		return "BookRating [isbn=" + isbn + ", email=" + email + ", rating=" + rating + ", datestamp=" + datestamp
				+ ", rating_comment=" + rating_comment + "]";
	}
}
