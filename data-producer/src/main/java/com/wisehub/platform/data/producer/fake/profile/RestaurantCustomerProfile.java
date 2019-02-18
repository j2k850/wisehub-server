package com.wisehub.platform.data.producer.fake.profile;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.wisehub.platform.data.producer.fake.model.CategoriesType;

@Profile(value = "restaurant")
@Component
public class RestaurantCustomerProfile implements CustomerProfile {

	@Value("${wisehub.profile.restaurant.name}")
	private String name;
	@Value("${wisehub.profile.restaurant.surname}")
	private String surname;
	@Value("${wisehub.profile.restaurant.city}")
	private String city;
	@Value("${wisehub.profile.restaurant.postalcode}")
	private String postalcode;
	@Value("${wisehub.profile.restaurant.region}")
	private String region;
	@Value("${wisehub.profile.restaurant.country}")
	private String country;
	@Value("${wisehub.profile.restaurant.phone}")
	private String phone;
	@Value("${wisehub.profile.restaurant.street}")
	private String street;
	@Value("${wisehub.profile.restaurant.branch}")
	private String branch;
	@Value("${wisehub.profile.restaurant.product}")
	private String product;
	@Value("${wisehub.profile.restaurant.account.status}")
	private String accountStatus;
	@Value("${wisehub.profile.restaurant.account.type}")
	private String accountType;
	@Value("${wisehub.profile.restaurant.product.status}")
	private String accountByProductStatus;
	@Value("${wisehub.profile.restaurant.generate.from.month.number}")
	private int fromMonth;
	@Value("${wisehub.profile.restaurant.generate.to.month.number}")
	private int toMonth;
	
	@Value("${wisehub.profile.restaurant.generate.populate}")
	private boolean generatePopulate;
	@Value("${wisehub.profile.restaurant.generate.static}")
	private boolean generateStatic;
	@Value("${wisehub.profile.restaurant.generate.csv}")
	private boolean generateCSV;
	@Value("${wisehub.profile.restaurant.folder.csv}")
	private String folderCSV;

	@Value("${wisehub.profile.restaurant.currency}")
	private String currency;

	@Value("${wisehub.profile.restaurant.bvn}")
	private Long bnv;

	@Value("${wisehub.profile.restaurant.accountNumber}")
	private Long accountNumber;

	@Value("${wisehub.profile.restaurant.sales.max}")
	private double saleMax;

	@Value("${wisehub.profile.restaurant.sales.min}")
	private double saleMin;

	@Value("${wisehub.profile.restaurant.employees}")
	private int employees;

	@Value("${wisehub.profile.restaurant.delivery}")
	private boolean hasDelivery;

	private List<CategoriesType> fixedCostCategories;
	private List<CategoriesType> mixCostCategories;
	private List<CategoriesType> variableCostCategories;

	@PostConstruct
	public void init() {
		fixedCostCategories = new ArrayList<CategoriesType>();
		mixCostCategories = new ArrayList<CategoriesType>();
		variableCostCategories = new ArrayList<CategoriesType>();

		fixedCostCategories.add(CategoriesType.HOUSE_INSURANCE);
		fixedCostCategories.add(CategoriesType.UTILITY_BILLS);

		fixedCostCategories.add(CategoriesType.UTILITY_ELECTRICITY);
		fixedCostCategories.add(CategoriesType.UTILITY_WATER);

		mixCostCategories.add(CategoriesType.UTILITY_CABLE);
		mixCostCategories.add(CategoriesType.UTILITY_INTERNET);
		variableCostCategories.add(CategoriesType.UNIFORMS);
		variableCostCategories.add(CategoriesType.PAPER_PRODUCTS);

		fixedCostCategories.add(CategoriesType.BANK_FEES);
		fixedCostCategories.add(CategoriesType.DEBT_PAYMENTS);
		fixedCostCategories.add(CategoriesType.RENT);
		fixedCostCategories.add(CategoriesType.BENEFIT);
		fixedCostCategories.add(CategoriesType.EQUIPMENT_RENTAL);

		if (this.hasDelivery) {
			fixedCostCategories.add(CategoriesType.LEASE_CAR_LOAN_PAYMENT);
			fixedCostCategories.add(CategoriesType.VEHICLE_INSURANCE);

			mixCostCategories.add(CategoriesType.FUEL_COST);
			mixCostCategories.add(CategoriesType.VEHICLE_MAINTENANCE);
			mixCostCategories.add(CategoriesType.PARKING);
		}

		variableCostCategories.add(CategoriesType.HUMAN_ERROR);
		variableCostCategories.add(CategoriesType.EARLY_PAY_DISCOUNTS);
		variableCostCategories.add(CategoriesType.LABO_BENEFIT_COSTS);

		mixCostCategories.add(CategoriesType.ADVERTISING);
		mixCostCategories.add(CategoriesType.COMMUNICATIONS);
		mixCostCategories.add(CategoriesType.CLEANING_AGENTS);

		mixCostCategories.add(CategoriesType.ENTERTAINMENT);
		mixCostCategories.add(CategoriesType.TOBACCO);
		mixCostCategories.add(CategoriesType.ALCOHOL);
		mixCostCategories.add(CategoriesType.MAGAZINES_NEWSPAPERS_BOOKS);

	}

	public double getSaleMax() {
		return saleMax;
	}

	public void setSaleMax(double salaryMax) {
		this.saleMax = salaryMax;
	}

	public double getSaleMin() {
		return saleMin;
	}

	public void setSaleMin(double salaryMin) {
		this.saleMin = salaryMin;
	}

	public boolean isHasDelivery() {
		return hasDelivery;
	}

	public void setHasDelivery(boolean hasCar) {
		this.hasDelivery = hasCar;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.wisehub.platform.data.producer.fake.profile.CustomerProfile#getBnv()
	 */
	@Override
	public Long getBnv() {
		return bnv;
	}

	public void setBnv(Long bnv) {
		this.bnv = bnv;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.wisehub.platform.data.producer.fake.profile.CustomerProfile#getAccountNumber()
	 */
	@Override
	public Long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(Long accountNumber) {
		this.accountNumber = accountNumber;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.wisehub.platform.data.producer.fake.profile.CustomerProfile#getCurrency()
	 */
	@Override
	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.wisehub.platform.data.producer.fake.profile.CustomerProfile#getFixedCostCategories()
	 */
	@Override
	public List<CategoriesType> getFixedCostCategories() {
		return fixedCostCategories;
	}

	public void setFixedCostCategories(List<CategoriesType> fixedCostCategories) {
		this.fixedCostCategories = fixedCostCategories;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.wisehub.platform.data.producer.fake.profile.CustomerProfile#getSavingCostCategories()
	 */
	@Override
	public List<CategoriesType> getSavingCostCategories() {
		return mixCostCategories;
	}

	public void setSavingCostCategories(List<CategoriesType> savingCostCategories) {
		this.mixCostCategories = savingCostCategories;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.wisehub.platform.data.producer.fake.profile.CustomerProfile#getVariableCostCategories()
	 */
	@Override
	public List<CategoriesType> getVariableCostCategories() {
		return variableCostCategories;
	}

	public void setVariableCostCategories(List<CategoriesType> variableCostCategories) {
		this.variableCostCategories = variableCostCategories;
	}

	public int getEmployees() {
		return this.employees;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPostalcode() {
		return postalcode;
	}

	public void setPostalcode(String postalcode) {
		this.postalcode = postalcode;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	@Override
	public String getAccountStatus() {
		return accountStatus;
	}

	public void setAccountStatus(String accountStatus) {
		this.accountStatus = accountStatus;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	@Override
	public String getAccountType() {
		return accountType;
	}

	public int getFromMonth() {
		return fromMonth;
	}

	public void setFromMonth(int fromMonth) {
		this.fromMonth = fromMonth;
	}

	public int getToMonth() {
		return toMonth;
	}

	public void setToMonth(int toMonth) {
		this.toMonth = toMonth;
	}

	public List<CategoriesType> getMixCostCategories() {
		return mixCostCategories;
	}

	public void setMixCostCategories(List<CategoriesType> mixCostCategories) {
		this.mixCostCategories = mixCostCategories;
	}

	public void setEmployees(int employees) {
		this.employees = employees;
	}

	public void setAccountByProductStatus(String accountByProductStatus) {
		this.accountByProductStatus = accountByProductStatus;
	}

	public String getAccountByProductStatus() {
		return accountByProductStatus;
	}

	@Override
	public String getBaseFolderCSV() {
		return this.folderCSV;
	}

	public void setFolderCS(String folderCSV) {
		this.folderCSV = folderCSV;
	}
	
	public boolean isGenerateCSV() {
		return generateCSV;
	}
	
	public void setGenerateCSV(boolean generate) {
		this.generateCSV = generate;
	}

	public boolean isGenerateStatic() {
		return generateStatic;
	}
	
	public void setGenerateStatic(boolean generateStatic) {
		this.generateStatic = generateStatic;
	}
	
	public boolean isGeneratePopulate() {
		return generatePopulate;
	}

	public void setGeneratePopulate(boolean generatePopulate) {
		this.generatePopulate = generatePopulate;
	}
}

