package com.tilgungsplan.demo.services;

import com.tilgungsplan.demo.entity.RepaymentDO;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestParam;
import javax.persistence.EntityExistsException;

import java.util.List;
public interface PaymentService {
	public RepaymentDO findById(long id);
	public void saveRepaymentDO(RepaymentDO repaymentDO);
	public long getLastInsertedId();
	public List<RepaymentDO> findall();
	public String getURLDB();
	public List<RepaymentDO> calculatePaymentPlan(@RequestParam double loanValue) throws EntityExistsException;
}
