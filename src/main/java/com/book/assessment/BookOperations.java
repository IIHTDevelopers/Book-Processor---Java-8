package com.book.assessment;

public interface BookOperations {
	default double discountPrice(Book book, double discountPercent) {
		return book.getPrice() * (1 - discountPercent / 100);
	}

	static String formatBookDetails(Book book) {
		return String.format("Title: %s, Author: %s, Year Published: %d, Price: %.2f", book.getTitle(),
				book.getAuthor(), book.getYearPublished(), book.getPrice());
	}
}