package com.tilgungsplan.demo.dataAccessObject;

import com.tilgungsplan.demo.entity.RepaymentDO;

import java.util.List;

public interface RepaymentDAO {

	public RepaymentDO findById(long id);
	public void saveRepaymentDO(RepaymentDO repaymentDO);
	public long getLastInsertedId();
	public List<RepaymentDO> findall();
}
