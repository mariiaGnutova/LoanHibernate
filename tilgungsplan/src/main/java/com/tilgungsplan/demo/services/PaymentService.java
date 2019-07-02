package com.tilgungsplan.demo.services;

import com.tilgungsplan.demo.entity.RepaymentDO;
import org.springframework.web.bind.annotation.RequestParam;
import javax.persistence.EntityExistsException;

import java.util.List;

public interface PaymentService {
	double getSumRate();
	double getSumInterest();
	double getRepayment();
	RepaymentDO findById(long id);
	void saveRepaymentDO(RepaymentDO repaymentDO);
	long getLastInsertedId();
	List<RepaymentDO> findall();
	String getURLDB();
	List<RepaymentDO> calculatePaymentPlan(@RequestParam double loanValue) throws EntityExistsException;
}
