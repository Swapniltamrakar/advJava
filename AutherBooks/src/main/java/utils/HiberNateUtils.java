package utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HiberNateUtils {
   
	private static SessionFactory factory;
	
	public static SessionFactory getFactory() {
		factory = new Configuration().configure().buildSessionFactory();
		return factory;
	}
	
}
