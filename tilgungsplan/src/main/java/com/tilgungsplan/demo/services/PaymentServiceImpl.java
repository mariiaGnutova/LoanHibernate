package com.tilgungsplan.demo.services;

import com.tilgungsplan.demo.dataAccessObject.RepaymentDAO;
import com.tilgungsplan.demo.dataAccessObject.RepaymentDAOImpl;
import com.tilgungsplan.demo.entity.RepaymentDO;
import com.tilgungsplan.demo.init.DataInit;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.internal.SessionFactoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;


import javax.persistence.EntityExistsException;
import java.util.List;
import java.util.Map;

@Service
public class PaymentServiceImpl implements PaymentService {
	private RepaymentDAO repaymentDAO = new RepaymentDAOImpl();

//	public void setRepaymentDAO(RepaymentDAO repaymentDAO){
//		this.repaymentDAO = repaymentDAO;
//	}

	@Override
	public RepaymentDO findById(long id) {
		if (repaymentDAO.findById(id) == null){
			System.out.println("Payment '" + id + "' can't be found");
		}
		return repaymentDAO.findById(id);
	}

	@Override
	@Transactional
	public void saveRepaymentDO(RepaymentDO repaymentDO) {
		repaymentDAO.saveRepaymentDO(repaymentDO);
	}

	@Override
	@Transactional
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

	@Override
	public List<RepaymentDO> calculatePaymentPlan(@RequestParam double loanValue)  throws EntityExistsException {
		try
		{
			DataInit dataInit = new DataInit();
			dataInit.createPaymentPlan((double)loanValue);
		}
		catch (Exception e)
		{
			throw new EntityExistsException(e.getMessage());
		}
		return repaymentDAO.findall();
	}
}
