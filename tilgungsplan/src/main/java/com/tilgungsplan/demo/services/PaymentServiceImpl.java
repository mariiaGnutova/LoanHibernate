package com.tilgungsplan.demo.services;

import com.tilgungsplan.demo.dataAccessObject.RepaymentDAO;
import com.tilgungsplan.demo.dataAccessObject.RepaymentDAOImpl;
import com.tilgungsplan.demo.entity.RepaymentDO;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.internal.SessionFactoryImpl;


import java.util.List;
import java.util.Map;

public class PaymentServiceImpl implements PaymentService {

	private RepaymentDAO repaymentDAO = new RepaymentDAOImpl();
	public PaymentServiceImpl(){}

	@Override
	public double getSumRate() {
		return repaymentDAO.getSumRate();
	}

	@Override
	public double getSumInterest() {
		return repaymentDAO.getSumInterest();
	}

	@Override
	public double getRepayment() {
		return repaymentDAO.getRepayment();
	}

	@Override
	public RepaymentDO findById(long id) {
		if (repaymentDAO.findById(id) == null){
			System.out.println("Payment '" + id + "' can't be found");
		}
		return repaymentDAO.findById(id);
	}

	@Override
	public void saveRepaymentDO(RepaymentDO repaymentDO) {
		repaymentDAO.saveRepaymentDO(repaymentDO);
	}

	@Override
	public long getLastInsertedId() {
		return repaymentDAO.getLastInsertedId();
	}

	@Override
	public List<RepaymentDO> findall() {
		return repaymentDAO.findall();
	}

	@Override
	public String getURLDB() {
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		SessionFactoryImpl sessionFactoryImpl = (SessionFactoryImpl) sessionFactory;
		Map<String, Object> props = sessionFactoryImpl.getProperties();
		String url = props.get("hibernate.connection.url").toString();
		String[] urlArray = url.split(":");
		String db_name = urlArray[urlArray.length - 1];
		return db_name;
	}
}
