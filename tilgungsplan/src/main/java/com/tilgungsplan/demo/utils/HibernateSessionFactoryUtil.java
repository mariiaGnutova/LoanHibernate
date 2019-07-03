package com.tilgungsplan.demo.utils;


import com.tilgungsplan.demo.entity.RepaymentDO;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.springframework.context.annotation.Bean;

public class HibernateSessionFactoryUtil {
	private static SessionFactory sessionFactory;

	private HibernateSessionFactoryUtil() {}


	public static SessionFactory getSessionFactory() {
		if (sessionFactory == null) {
			try {
				Configuration configuration = new Configuration().configure();

				configuration.addAnnotatedClass(RepaymentDO.class);

				StandardServiceRegistryBuilder builder =
						new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());

				sessionFactory = configuration.buildSessionFactory(builder.build());

			} catch (Exception e) {
				System.out.println("Exception: " + e);
			}
		}
		return sessionFactory;
	}
	public static void catchException(Transaction transObj, HibernateException exObj){
		if(transObj != null){
			transObj.rollback();
		}
		exObj.printStackTrace();
	}

}
