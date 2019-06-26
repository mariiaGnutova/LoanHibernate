package com.tilgungsplan.demo.services;

import com.tilgungsplan.demo.entity.RepaymentDO;

import java.time.LocalDate;
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
}
