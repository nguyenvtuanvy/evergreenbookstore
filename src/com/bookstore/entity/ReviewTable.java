package com.bookstore.entity;
// Generated Mar 11, 2023, 2:05:40 PM by Hibernate Tools 4.3.6.Final

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 * ReviewTable generated by hbm2java
 */
@Entity
@Table(name = "review_table", catalog = "bookstoredb")
@NamedQueries({ 
		@NamedQuery(name = "Review.listall", query = "SELECT r FROM ReviewTable r ORDER BY r.reviewTime DESC"),
		@NamedQuery(name = "Review.countall", query = "SELECT COUNT(r) FROM ReviewTable r"),
		@NamedQuery(name = "Review.mostFavoredBooks", query = "SELECT r.book, COUNT(r.book.bookId) AS ReviewCount, AVG(r.rating) AS AvgRating FROM ReviewTable r " 
		+ "GROUP BY r.book.bookId HAVING AVG(r.rating) >= 4 "
		+ "ORDER BY ReviewCount DESC, AvgRating DESC" ),
		@NamedQuery(name = "Review.findbycutomerandbook", query = "SELECT r FROM ReviewTable r WHERE r.customer.customerId =:customerid AND r.book.bookId =:bookid")
})
public class ReviewTable implements java.io.Serializable {

	private Integer reviewId;
	private Book book;
	private Customer customer;
	private int rating;
	private String headline;
	private String comment;
	private Date reviewTime;

	public ReviewTable() {
	}

	public ReviewTable(Book book, Customer customer, int rating, String headline, Date reviewTime) {
		this.book = book;
		this.customer = customer;
		this.rating = rating;
		this.headline = headline;
		this.reviewTime = reviewTime;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "review_id", unique = true, nullable = false)
	public Integer getReviewId() {
		return this.reviewId;
	}

	public void setReviewId(Integer reviewId) {
		this.reviewId = reviewId;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "book_id", nullable = false)
	public Book getBook() {
		return this.book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "customer_id", nullable = false)
	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Column(name = "rating", nullable = false)
	public int getRating() {
		return this.rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	@Column(name = "headline", nullable = false, length = 128)
	public String getHeadline() {
		return this.headline;
	}

	public void setHeadline(String headline) {
		this.headline = headline;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "review_time", nullable = false, length = 19)
	public Date getReviewTime() {
		return this.reviewTime;
	}

	public void setReviewTime(Date reviewTime) {
		this.reviewTime = reviewTime;
	}

	@Column(name = "comment", nullable = false, length = 500)
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Transient
	public String getStars() {
		String result = "";

		int numberOfstarsOn = (int) rating;

		for (int i = 1; i <= numberOfstarsOn; i++) {
			result += "on,";
		}

		for (int j = numberOfstarsOn + 1; j <= 5; j++) {
			result += "off,";
		}

		return result.substring(0, result.length() - 1);
	}
}