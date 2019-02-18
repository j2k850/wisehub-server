package com.wisehub.platform.data.producer.fake.profile;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.wisehub.platform.data.producer.fake.model.CategoriesType;

@Profile(value = {"worker-1","worker-2","worker-3"})
@Component
public class WorkerCustomerProfile implements CustomerProfile {

	@Value("${wisehub.profile.worker.name}")
	private String name;
	@Value("${wisehub.profile.worker.surname}")
	private String surname;
	@Value("${wisehub.profile.worker.city}")
	private String city;
	@Value("${wisehub.profile.worker.postalcode}")
	private String postalcode;
	@Value("${wisehub.profile.worker.region}")
	private String region;
	@Value("${wisehub.profile.worker.country}")
	private String country;
	@Value("${wisehub.profile.worker.phone}")
	private String phone;
	@Value("${wisehub.profile.worker.street}")
	private String street;
	@Value("${wisehub.profile.worker.branch}")
	private String branch;
	@Value("${wisehub.profile.worker.product}")
	private String product;
	@Value("${wisehub.profile.worker.account.status}")
	private String accountStatus;
	@Value("${wisehub.profile.worker.account.type}")
	private String accountType;
	@Value("${wisehub.profile.worker.product.status}")
	private String accountByProductStatus;

	@Value("${wisehub.profile.worker.generate.from.month.number}")
	private int fromMonth;
	@Value("${wisehub.profile.worker.generate.to.month.number}")
	private int toMonth;

	@Value("${wisehub.profile.worker.currency}")
	private String currency;

	@Value("${wisehub.profile.worker.bvn}")
	private Long bnv;

	@Value("${wisehub.profile.worker.accountNumber}")
	private Long accountNumber;

	@Value("${wisehub.profile.worker.salary}")
	private int salaryByMonth;

	@Value("${wisehub.profile.worker.salary.max}")
	private double salaryMax;
	@Value("${wisehub.profile.worker.salary.min}")
	private double salaryMin;

	@Value("${wisehub.profile.worker.age}")
	private int age;
	@Value("${wisehub.profile.worker.house}")
	private boolean hasHouse;
	@Value("${wisehub.profile.worker.child}")
	private boolean hasChild;
	@Value("${wisehub.profile.worker.car}")
	private boolean hasCar;
	@Value("${wisehub.profile.worker.pet}")
	private boolean hasPet;

	@Value("${wisehub.profile.worker.generate.populate}")
	private boolean generatePopulate;

	@Value("${wisehub.profile.worker.generate.static}")
	private boolean generateStatic;
	@Value("${wisehub.profile.worker.generate.csv}")
	private boolean generateCSV;
	
	@Value("${wisehub.profile.worker.folder.csv}")
	private String folderCSV;

	private List<CategoriesType> fixedCostCategories;
	private List<CategoriesType> savingCostCategories;
	private List<CategoriesType> variableCostCategories;

	@PostConstruct
	public void init() {
		fixedCostCategories = new ArrayList<CategoriesType>();
		savingCostCategories = new ArrayList<CategoriesType>();
		variableCostCategories = new ArrayList<CategoriesType>();

		fixedCostCategories.add(CategoriesType.HOUSE_INSURANCE);
		fixedCostCategories.add(CategoriesType.UTILITY_ELECTRICITY);
		fixedCostCategories.add(CategoriesType.UTILITY_WATER);

		savingCostCategories.add(CategoriesType.UTILITY_CABLE);
		savingCostCategories.add(CategoriesType.UTILITY_INTERNET);

		fixedCostCategories.add(CategoriesType.LIFE_DISABILITY_EXTENDED_HEALTH_INSURANCE);
		fixedCostCategories.add(CategoriesType.BANK_FEES);
		fixedCostCategories.add(CategoriesType.DEBT_PAYMENTS);

		if (this.hasHouse) {
			fixedCostCategories.add(CategoriesType.HOUSE_TENANT);
			fixedCostCategories.add(CategoriesType.MORTGAGE);
			savingCostCategories.add(CategoriesType.PROPERTY_TAXES);
		} else {
			fixedCostCategories.add(CategoriesType.RENT);
			fixedCostCategories.add(CategoriesType.STRATA_FEE);
		}

		if (this.hasChild) {
			variableCostCategories.add(CategoriesType.BABYSITTING);
			variableCostCategories.add(CategoriesType.DAYCARE);
			variableCostCategories.add(CategoriesType.CHILDRENS_LESSONS_AND_ACTIVITIES);
		}

		if (this.hasCar) {
			fixedCostCategories.add(CategoriesType.LEASE_CAR_LOAN_PAYMENT);
			fixedCostCategories.add(CategoriesType.VEHICLE_INSURANCE);
			savingCostCategories.add(CategoriesType.FUEL_COST);

			savingCostCategories.add(CategoriesType.VEHICLE_MAINTENANCE);
			savingCostCategories.add(CategoriesType.PARKING);
		} else {
			savingCostCategories.add(CategoriesType.PUBLIC_TRANSPORTATION_COSTS);
		}

		if (this.hasPet) {
			savingCostCategories.add(CategoriesType.VET_BILLS);
		}

		savingCostCategories.add(CategoriesType.CLOTHING_SHOES);
		savingCostCategories.add(CategoriesType.HEALTH_EXPENSES);
		savingCostCategories.add(CategoriesType.GIFTS);

		variableCostCategories.add(CategoriesType.GROCERIES);
		variableCostCategories.add(CategoriesType.PERSONAL_CARE_ITEMS_DRUGSTORE);
		variableCostCategories.add(CategoriesType.CLOTHING_SHOES);
		variableCostCategories.add(CategoriesType.WORK_LUNCHES_SNACKS);

		variableCostCategories.add(CategoriesType.EATING_OUT);
		variableCostCategories.add(CategoriesType.ENTERTAINMENT);
		variableCostCategories.add(CategoriesType.TOBACCO);
		variableCostCategories.add(CategoriesType.ALCOHOL);

		variableCostCategories.add(CategoriesType.LOTTERY);
		variableCostCategories.add(CategoriesType.SPORTS);
		variableCostCategories.add(CategoriesType.RECREATION_OTHER_HOBBIES);

		variableCostCategories.add(CategoriesType.HAIR_CARE_SALON_SERVICES);
		variableCostCategories.add(CategoriesType.MAGAZINES_NEWSPAPERS_BOOKS);

	}

	public double getSalaryMax() {
		return salaryMax;
	}

	public void setSalaryMax(double salaryMax) {
		this.salaryMax = salaryMax;
	}

	public double getSalaryMin() {
		return salaryMin;
	}

	public void setSalaryMin(double salaryMin) {
		this.salaryMin = salaryMin;
	}

	public int getSalaryByMonth() {
		return salaryByMonth;
	}

	public void setSalaryByMonth(int salaryByMonth) {
		this.salaryByMonth = salaryByMonth;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public boolean isHasHouse() {
		return hasHouse;
	}

	public void setHasHouse(boolean hasHouse) {
		this.hasHouse = hasHouse;
	}

	public boolean isHasChild() {
		return hasChild;
	}

	public void setHasChild(boolean hasChild) {
		this.hasChild = hasChild;
	}

	public boolean isHasCar() {
		return hasCar;
	}

	public void setHasCar(boolean hasCar) {
		this.hasCar = hasCar;
	}

	public boolean isHasPet() {
		return hasPet;
	}

	public void setHasPet(boolean hasPet) {
		this.hasPet = hasPet;
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
		return savingCostCategories;
	}

	public void setSavingCostCategories(List<CategoriesType> savingCostCategories) {
		this.savingCostCategories = savingCostCategories;
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
