package pojos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Book extends BaseEntity{
	@Column(name="Book_name",length = 30)
	private String bookName;
	private double price;
	@ManyToOne
	@JoinColumn(name="auth_id")
	private Auther auth;
	
	public Book() {
		super();
	}
	
	
	
	
	
	public Book(String bookName, double price) {
		super();
		this.bookName = bookName;
		this.price = price;
	}





	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public Auther getAuth() {
		return auth;
	}
	public void setAuth(Auther auth) {
		this.auth = auth;
	}
	
	
}
