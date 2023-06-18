package dao;

import pojos.Book;

public interface BookDao {

	String addABook(Book b,String email);
	String removeBook(long bookId);
}
