package com.book.assessment;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BookProcessor implements BookOperations {

	public static void main(String[] args) {
		List<Book> books = new ArrayList<>();

		// Reading book details from a file
		try {
			List<String> lines = Files.readAllLines(Paths.get("books.txt"));
			for (String line : lines) {
				String[] details = line.split(",");
				if (details.length == 4) {
					String title = details[0];
					String author = details[1];
					int yearPublished = Integer.parseInt(details[2]);
					double price = Double.parseDouble(details[3]);
					books.add(new Book(title, author, yearPublished, price));
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Filtering, sorting, and removing books
		books = books.stream().filter(book -> book.getYearPublished() >= 2000)
				.sorted((b1, b2) -> Double.compare(b1.getPrice(), b2.getPrice())).collect(Collectors.toList());

		books.removeIf(book -> book.getPrice() < 10);

		// Writing book titles to a new file
		List<String> titles = books.stream().map(Book::getTitle).collect(Collectors.toList());

		try {
			Files.write(Paths.get("filtered_books.txt"), titles, StandardOpenOption.CREATE);
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Applying discount and printing book details
		BookProcessor processor = new BookProcessor();
		books.forEach(book -> {
			double discountedPrice = processor.discountPrice(book, 10);
			book.setPrice(discountedPrice);
			System.out.println(BookOperations.formatBookDetails(book));
		});
	}

	@Override
	public double discountPrice(Book book, double discountPercent) {
		return book.getPrice() * (1 - discountPercent / 100);
	}
}
