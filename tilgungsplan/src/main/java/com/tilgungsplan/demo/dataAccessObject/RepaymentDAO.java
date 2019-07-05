package com.tilgungsplan.demo.dataAccessObject;

import com.tilgungsplan.demo.entity.RepaymentDO;

import java.time.LocalDateTime;
import java.util.List;

public interface RepaymentDAO {

	RepaymentDO findById(long id);
	RepaymentDO findByDate(LocalDateTime dateTime);
	void saveRepaymentDO(RepaymentDO repaymentDO);
	long getLastInsertedId();
	List<RepaymentDO> findall();

}
