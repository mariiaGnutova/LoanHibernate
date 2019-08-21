package com.tilgungsplan.demo.services;

import com.tilgungsplan.demo.entity.RepaymentDO;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestParam;
import javax.persistence.EntityExistsException;

import java.time.LocalDateTime;
import java.util.List;
public interface PaymentService {
	RepaymentDO findById(long id);
	RepaymentDO findByDate(LocalDateTime dateTime, long userId);
	void saveRepaymentDO(RepaymentDO repaymentDO);
	long getLastInsertedId();
	List<RepaymentDO> findall();
	String getURLDB();
	List<RepaymentDO> calculatePaymentPlan(@RequestParam double loanValue, @RequestParam LocalDateTime date, long userId) throws EntityExistsException;
	boolean deletOldCulculations();
	long getLastUserId();
	List<RepaymentDO> findallForUser(long userId);
}
