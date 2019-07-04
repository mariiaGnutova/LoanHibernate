package com.tilgungsplan.demo.dataAccessObject;

import com.tilgungsplan.demo.entity.RepaymentDO;
import com.tilgungsplan.demo.utils.HibernateSessionFactoryUtil;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

import java.util.List;

@Repository
public class RepaymentDAOImpl implements RepaymentDAO {



	@Override
	public RepaymentDO findById(long id) {
		Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
		RepaymentDO repaymentDO = session.get(RepaymentDO.class, id);
		session.close();
		return repaymentDO;
	}

	@Override
	public void saveRepaymentDO(RepaymentDO repaymentDO) {
		Session session = null;
		Transaction transObj = null;
		try {
			SessionFactory sessionFactory = HibernateSessionFactoryUtil.getSessionFactory();
			session = sessionFactory.openSession();
			transObj = session.beginTransaction();
			session.save(repaymentDO);
			transObj.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
	}

	@Override
	public long getLastInsertedId() {
		long lastId = 0;
		try {
			Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
			lastId = ((BigInteger) session.createSQLQuery("SELECT LAST_INSERT_ID() FROM LOAN").uniqueResult()).longValue();

			System.out.println("Last Id is: " + lastId);

		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			return lastId;
		}
	}

	@Override
	public List<RepaymentDO> findall() {
		List<RepaymentDO> payments = null;
		Session session = null;
		try {
			session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
	//		payments = (List<RepaymentDO>)session.createQuery("FROM LOAN").list();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<RepaymentDO> criteria = builder.createQuery(RepaymentDO.class);
			criteria.from(RepaymentDO.class);
			payments = session.createQuery(criteria).getResultList();

		} catch (HibernateException e) {
			e.printStackTrace();
			System.out.println(" Problem with find all");
		} finally {
			session.close();
			return payments;
		}
	}

}
