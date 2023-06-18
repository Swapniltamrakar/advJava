package pojos;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Auther extends BaseEntity{
	@Column(name="First_name",length = 20)
	private String name;
	@Column(name="Last_name",length = 20)
	private String lastName;
	@Column(length = 20)
	private String email;
	@Column(length = 20)
	private String password;
	@OneToMany(mappedBy = "auth",cascade = CascadeType.ALL)
	private List<Book> listBook = new ArrayList<>();
	
	public Auther() {
		super();
	}
	
	
	public Auther(String name, String lastName ,String email, String password) {
		super();
		this.name = name;
		this.lastName = lastName;
		this.email=email;
		this.password=password;
	}


	public List<Book> getListBook() {
		return listBook;
	}


	public void setListBook(List<Book> listBook) {
		this.listBook = listBook;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public void addABook(Book b) {
		listBook.add(b);
		b.setAuth(this);
	}

}
