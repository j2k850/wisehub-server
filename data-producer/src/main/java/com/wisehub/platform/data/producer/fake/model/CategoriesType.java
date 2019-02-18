package com.wisehub.platform.data.producer.fake.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.google.common.base.Function;
import com.google.common.collect.Maps;

public enum CategoriesType {

	MORTGAGE("Mortgage",1,1,30000d,60000d,1),
	RENT("Rent",1,2,30.000d,50000d,1),
	PROPERTY_TAXES("Property taxes",1,3,4500d,10500d,1),
	STRATA_FEE("Strata fee",1,4,40000d,300000d,1),
	HOUSE_TENANT("House insurance/tenant insurance",1,5,1500d,6000d,1),
	CONDO_FEE("Condo fee",1,6,2000d,5000d,1),
	UTILITY_BILLS("Utility bills",1,7,13500d,43000d,1),
	LEASE_CAR_LOAN_PAYMENT("Lease / car loan payment",1,8,50000d,90000d,1),
	VEHICLE_INSURANCE("Vehicle insurance",1,9,25000d,40000d,1),
	LIFE_DISABILITY_EXTENDED_HEALTH_INSURANCE("Life / Disability / Extended health",1,10,6000d,24000d,1),
	BANK_FEES("Bank fees",1,11,500d,10000d,1),
	DEBT_PAYMENTS("Debt payments for your debt repayment plan",1,12,3000d,9000d,1),
	HOUSE_INSURANCE("House insurance",2,13,7600d,28000d,1),
	CLOTHING_SHOES("Clothing & shoes",2,14,11.180d,24.390d,3),
	HEALTH_EXPENSES("Health expenses",2,15,9000d,36000d,1),
	VET_BILLS("Vet bills",2,16,15000d,50000d,2),
	GIFTS("Gifts",2,17,3000d,5000d,3),
	VEHICLE_MAINTENANCE("Vehicle maintenance",2,18,4000d,36000d,2),
	GROCERIES("Groceries",3,19,200d,2000d,7),
	PERSONAL_CARE_ITEMS_DRUGSTORE("Personal care items",3,20,400d,2000d,5),
	PUBLIC_TRANSPORTATION_COSTS("public transportation costs",3,21,9000d,12000d,7),
	FUEL_COST("fuel",3,22,3000d,4000d,4),
	PARKING("Parking",3,23,200d,500d,10),
	WORK_LUNCHES_SNACKS("Work lunches & snacks",3,24,2500d,5000d,10),
	EATING_OUT("Eating out",3,25,2500d,10000d,5),
	ENTERTAINMENT("Entertainment",3,26,5000d,20000d,4),
	TOBACCO("Tobacco",3,27,200d,400d,5),
	ALCOHOL("alcohol",3,28,300d,400d,10),
	LOTTERY("Lottery",3,29,200d,600d,4),
	BABYSITTING("Babysitting",3,30,15000d,60000d,1),
	SPORTS("Sports",3,31,5000d,14000d,1),
	RECREATION_OTHER_HOBBIES("Recreation & other Hoobies",3,32,2000d,4000d,3),
	HAIR_CARE_SALON_SERVICES("Hair care / salon services",3,33,500d,4000d,1),
	MAGAZINES_NEWSPAPERS_BOOKS("Magazines / newspapers / books",3,34,100d,300d,5),
	CHILDRENS_LESSONS_AND_ACTIVITIES("Children's lessons and activities",3,35,2000d,5000d,2),
	DAYCARE("Daycare",3,36,10000d,30000d,1), 
	SALARIES("Salaries",1,37,50000d,100000d,1), 
	BENEFIT("Benefit",1,38,10000d,20000d,1), 
	EQUIPMENT_RENTAL("Equipment Rental",1,39,50000d,80000d,1), 
	
	HUMAN_ERROR("Human Error",2,40,9000d,20000d,4), 
	EARLY_PAY_DISCOUNTS("Early pay discounts",2,41,10000d,12000d,3), 
	LABO_BENEFIT_COSTS("Labo benefit costs",3,42,50000d,80000d,2), 
	ADVERTISING("Advertising",2,43,2000d,3000d,4), 
	COMMUNICATIONS("Communications",2,44,10000d,12000d,4),  
	CLEANING_AGENTS("Cleaning Agents",3,45,50000d,80000d,1),
	
	UTILITY_CABLE("cable",3,46,13500d,43000d,1),
	UTILITY_INTERNET("internet",3,47,13500d,43000d,1),
	UTILITY_ELECTRICITY("electricity",3,48,13500d,43000d,1),
	UTILITY_WATER("water",3,49,13500d,43000d,1),
	UNIFORMS("uniforms",3,50,13500d,43000d,1),
	PAPER_PRODUCTS("paper products",3,51,13500d,43000d,1),
	;

	final private String name;
	final private int type;
	final private int value;
	final private double min;
	final private double max;
	final private int times;
	
	public static final List<CategoriesType> ALLOWED_TO_PEOPLE_WITH_CHILD = 
			Collections.unmodifiableList(Arrays.asList(CategoriesType.BABYSITTING, 
					CategoriesType.DAYCARE, 
					CategoriesType.CHILDRENS_LESSONS_AND_ACTIVITIES));

	public static final List<CategoriesType> ALLOWED_TO_PEOPLE_WITH_OWN_HOUSE = 
			Collections.unmodifiableList(Arrays.asList(CategoriesType.HOUSE_TENANT, 
					CategoriesType.MORTGAGE));

	public static final List<CategoriesType> ALLOWED_TO_PEOPLE_WITH_CAR = 
			Collections.unmodifiableList(Arrays.asList(CategoriesType.LEASE_CAR_LOAN_PAYMENT,
														CategoriesType.PARKING,
														CategoriesType.VEHICLE_INSURANCE, 
														CategoriesType.VEHICLE_MAINTENANCE));

	public static final List<CategoriesType> ALLOWED_TO_PEOPLE_WITH_PET = 
			Collections.unmodifiableList(Arrays.asList(CategoriesType.VET_BILLS));



	private CategoriesType(String name, int type, int value, double min, double max, int times) {
		this.name = name;
		this.type = type;
		this.value = value;
		this.min = min;
		this.max = max;
		this.times = times;
	}

	public int getValue() {
		return value;
	}

	public static CategoriesType byValue(int value) {
		return NAME_LOOKUP.get(value);
	}

	public String getName() {
		return name;
	}

	public static CategoriesType byName(String name) {
		return NAME_LOOKUP.get(name);
	}

	public int getType() {
		return type;
	}

	private static final Function<CategoriesType, Integer> NAME_FUNCTION = new Function<CategoriesType, Integer>() {
		@Override
		public Integer apply(CategoriesType type) {
			return type.getValue();
		}
	};

	private static final Map<Integer, CategoriesType> NAME_LOOKUP = Maps.uniqueIndex(Arrays.asList(CategoriesType.class.getEnumConstants()), 
			NAME_FUNCTION);

	public double getMin() {
		return min;
	}

	public double getMax() {
		return max;
	}
	
	public int getTimes() {
		return times;
	}

}
