package com.bookstore.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bookstore.entity.ReviewTable;

public class ReviewDao extends JpaDAO<ReviewTable> implements GenericDAO<ReviewTable> {

	@Override
	public ReviewTable create(ReviewTable review) {
		review.setReviewTime(new Date());
		return super.create(review);
	}

	@Override
	public ReviewTable update(ReviewTable review) {
		return super.update(review);
	}

	@Override
	public ReviewTable get(Object reviewid) {
		return super.find(ReviewTable.class, reviewid);
	}

	@Override
	public void delete(Object reviewid) {
		super.delete(ReviewTable.class, reviewid);
	}

	@Override
	public List<ReviewTable> listAll() {
		return super.findwithnamequery("Review.listall");
	}

	@Override
	public long count() {
		return super.countwithnamequery("Review.countall");
	}

	public ReviewTable findByCutomerAndBook(Integer customerid,Integer bookid) {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("customerid", customerid);
		parameters.put("bookid", bookid);

		List<ReviewTable> result = super.findwithnamequery("Review.findbycutomerandbook", parameters);

		if (!result.isEmpty()) {
			return result.get(0);
		}		
		return null;
	}
	
	public List<ReviewTable> listMostRecent(){
		return super.findwithnamequery("Review.listall", 0, 3);
	}
}
