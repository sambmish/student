package com.ali.shared;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

	private static  SessionFactory sessionFactory;

	
	

	public static SessionFactory getSessionFactory() {
		if(sessionFactory==null){

			try {
				// Create the SessionFactory from hibernate.cfg.xml
				Configuration configuration = new Configuration().configure();
				StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
						.applySettings(configuration.getProperties());
				sessionFactory = configuration.buildSessionFactory(builder.build());

			} catch (Throwable ex) {
				// Make sure you log the exception, as it might be swallowed
				System.err.println("Initial SessionFactory creation failed." + ex);
				//throw new ExceptionInInitializerError(ex);
			}
		}
		return sessionFactory;
	}
}
