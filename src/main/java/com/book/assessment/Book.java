package com.book.assessment;

public class Book {
	private String title;
	private String author;
	private int yearPublished;
	private double price;

	public Book(String title, String author, int yearPublished, double price) {
		this.title = title;
		this.author = author;
		this.yearPublished = yearPublished;
		this.price = price;
	}

	public String getTitle() {
		return title;
	}

	public String getAuthor() {
		return author;
	}

	public int getYearPublished() {
		return yearPublished;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
}
