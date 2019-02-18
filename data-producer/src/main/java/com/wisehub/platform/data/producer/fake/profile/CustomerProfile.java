package com.wisehub.platform.data.producer.fake.profile;

import java.util.List;

import com.wisehub.platform.data.producer.fake.model.CategoriesType;

public interface CustomerProfile {
	
	public boolean isGeneratePopulate();
	
	public boolean isGenerateCSV() ;

	public boolean isGenerateStatic();
		
	Long getBnv();

	Long getAccountNumber();

	String getCurrency();

	List<CategoriesType> getFixedCostCategories();

	List<CategoriesType> getSavingCostCategories();

	List<CategoriesType> getVariableCostCategories();

	public String getName();

	public String getSurname();

	public String getCity();

	public String getPostalcode();

	public String getRegion();

	public String getCountry();

	public String getPhone();

	public String getStreet();

	public String getBranch();

	public String getProduct();

	public String getAccountStatus();

	public String getAccountType();

	public int getFromMonth();

	public int getToMonth();

	public String getAccountByProductStatus();

	public String getBaseFolderCSV();

}