package dao;

import pojos.Auther;
import static utils.HiberNateUtils.getFactory;

import org.hibernate.Session;
import org.hibernate.Transaction;
public class AutherDaoImpl implements AutherDao {

	@Override
	public String addAuther(Auther auth) {
		String msg;
		Session session = getFactory().getCurrentSession();
		Transaction tx= session.beginTransaction();
		
		try {
			
			session.persist(auth);
			tx.commit();
			msg="Auther added";
			
		}catch(RuntimeException r) {
			tx.rollback();
			msg="Auther not Added";
		}
		
		
		return msg;
	}

}
