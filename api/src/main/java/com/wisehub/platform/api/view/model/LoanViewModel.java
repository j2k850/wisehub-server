package com.wisehub.platform.api.view.model;

import java.math.BigDecimal;

public class LoanViewModel {

	private BigDecimal val;
	private String name;
	
	public BigDecimal getVal() {
		return val;
	}
	public void setVal(BigDecimal val) {
		this.val = val;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
