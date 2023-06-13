package com.bookstore.entity;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class BookRaingTest {

	@Test
	public void testaveragerating() {
		Book book = new Book();
		Set<ReviewTable> review = new HashSet<>();
		
		ReviewTable review1 = new ReviewTable();
		review1.setRating(5);
		review.add(review1);
		
		ReviewTable review2 = new ReviewTable();
		review2.setRating(4);
		review.add(review2);
		
		ReviewTable review3 = new ReviewTable();
		review3.setRating(3);
		review.add(review3);
		
		book.setReviewTables(review);
		
		float average = book.getAverageRating();
		System.out.println(average);
		assertEquals(4.0, average, 0.0);
	}

	@Test
	public void testratingstart() {
		float averagerating = 4.6f;
		Book book = new Book();
		book.getRatingString(averagerating);
		String ratingstring = book.getRatingString(averagerating);
		System.err.println(ratingstring);
		String expected = "on,on,on,on,half";
		
		assertEquals(expected, ratingstring);
		
		
	}
	
	
}
