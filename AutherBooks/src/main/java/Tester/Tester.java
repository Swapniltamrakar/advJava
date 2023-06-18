package Tester;

import java.util.Scanner;

import dao.AutherDaoImpl;
import dao.BookDaoImpl;
import pojos.Auther;
import pojos.Book;

public class Tester {

	public static void main(String[] args) {
		AutherDaoImpl autherDaoImpl = new AutherDaoImpl();
		int choice;
		boolean flag=true;
		BookDaoImpl bookDaoImpl = new BookDaoImpl();
		try(Scanner sc = new Scanner(System.in))
		{
			while(flag) {
			System.out.println("Enter a choice : \n1.Add an Author\n2.Add a book\n3.Remove a book\n4.Add auther and books\n0.Exit");
			choice=sc.nextInt();
			switch (choice) {
			case 1:
				System.out.println("Enter Author Details : Name,lstname,email,password");
				System.out.println(autherDaoImpl.addAuther(new Auther(sc.next(), sc.next(),sc.next(),sc.next())));
				break;
			case 2:
				
				System.out.println("Enter Book details : BookName,Price,AutherEmail");
				System.out.println(bookDaoImpl.addABook(new Book(sc.next(),sc.nextDouble()),sc.next()));
				break;
			case 3:
				System.out.println("Enter book id to remove");
				System.out.println(bookDaoImpl.removeBook(sc.nextLong()));
				break;
			
			case 4:
				
				System.out.println("Enter AutherDetails :  Name,lstname,email,password");
				Auther a = new Auther(sc.next(), sc.next(), sc.next(), sc.next());
				AutherDaoImpl dao = new AutherDaoImpl();
				
				System.out.println("Enter number of books to add : ");
				int bcount = sc.nextInt();
				for(int i=0;i<bcount;i++) {
					System.out.println("Enter book details : BookName , price");
					Book newBook = new Book(sc.next(), sc.nextDouble());
					a.addABook(newBook);
					}
					dao.addAuther(a);
				break;
			case 0:System.out.println("Thank you");
					flag=false;
					break;
			}}
			
		}catch(Exception e) {e.printStackTrace();}
		

	}

}
