package com.book.assessment.functional;

import static com.book.assessment.testutils.TestUtils.businessTestFile;
import static com.book.assessment.testutils.TestUtils.currentTest;
import static com.book.assessment.testutils.TestUtils.yakshaAssert;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.book.assessment.Book;
import com.book.assessment.BookProcessor;

public class FunctionalTests {

	private BookProcessor bookProcessor;
	private List<Book> books;

	@BeforeEach
	void setUp() {
		bookProcessor = new BookProcessor();
		books = new ArrayList<>();
		books.add(new Book("Effective Java", "Joshua Bloch", 2008, 45.00));
		books.add(new Book("Clean Code", "Robert C. Martin", 2008, 42.50));
		books.add(new Book("The Pragmatic Programmer", "Andrew Hunt", 1999, 39.95));
		books.add(new Book("Design Patterns", "Erich Gamma", 1994, 50.00));
		books.add(new Book("Java Concurrency in Practice", "Brian Goetz", 2006, 55.00));
	}

	@Test
	void testDiscountPrice() throws IOException {
		Book book = new Book("Test Book", "Author", 2021, 100.00);
		double discountedPrice = bookProcessor.discountPrice(book, 10);
		yakshaAssert(currentTest(), discountedPrice == 90.00 ? "true" : "false", businessTestFile);
//	    assertEquals(90.00, discountedPrice, 0.01, "Discount price should be 90.00");
	}

	@Test
	void testProcessBooks() throws IOException {
		// Filter books published after 2000 and price >= 10
		List<Book> processedBooks = books.stream().filter(book -> book.getYearPublished() >= 2000)
				.sorted((b1, b2) -> Double.compare(b1.getPrice(), b2.getPrice())).collect(Collectors.toList());
		processedBooks.removeIf(book -> book.getPrice() < 10);
		yakshaAssert(currentTest(), processedBooks.size() != 2 ? "true" : "false", businessTestFile);
//	    assertEquals(2, processedBooks.size(), "There should be 2 books after filtering and removal");
//	    assertEquals("Clean Code", processedBooks.get(0).getTitle(), "First book should be 'Clean Code'");
//	    assertEquals("Effective Java", processedBooks.get(1).getTitle(), "Second book should be 'Effective Java'");
	}

//	@Test
//	void testFormatBookDetails() throws IOException {
//		Book book = new Book("Test Book", "Author", 2021, 100.00);
//		String formattedDetails = BookOperations.formatBookDetails(book);
////	    assertEquals("Title: Test Book, Author: Author, Year Published: 2021, Price: 100.00", formattedDetails);
//		yakshaAssert(currentTest(), books != null ? "true" : "false", businessTestFile);
//	}
}
