package dao;

import static utils.HiberNateUtils.getFactory;

import org.hibernate.Session;
import org.hibernate.Transaction;

import pojos.Auther;
import pojos.Book;
public class BookDaoImpl implements BookDao {
	String msg;
	Session session = getFactory().getCurrentSession();
	Transaction tx = session.beginTransaction();
	@Override
	public String addABook(Book b, String email) {
		try {
			
			Auther auth=(Auther)session.createQuery("select a from Auther a where email=:em").setParameter("em", email).getSingleResult();
			if(auth!=null) {
				auth.addABook(b);
				session.persist(b);
				tx.commit();
				msg="Book Added";
			}
			
		}catch(RuntimeException r) {
			tx.rollback();
			msg="Book not added";
		}
		return msg;
	}
	
	@Override
	public String removeBook(long bookId) {
		try {
			Book b=session.get(Book.class, bookId);
			if(b!=null) {
				
				session.remove(b);
				tx.commit();
				msg="Book "+b.getBookName()+" Removed";
			}
			
		}catch(RuntimeException r) {
			tx.rollback();
			msg="Book not Removed";
		}
		return msg;
	}

}
