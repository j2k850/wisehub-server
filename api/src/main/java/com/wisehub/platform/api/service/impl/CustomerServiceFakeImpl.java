package com.wisehub.platform.api.service.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wisehub.platform.api.domain.model.CustomerDomainModel;
import com.wisehub.platform.api.domain.model.CustomerNotificationDomainModel;
import com.wisehub.platform.api.domain.model.CustomerStatus;
import com.wisehub.platform.api.domain.model.NotificationDomainModel;
import com.wisehub.platform.api.domain.model.NotificationStatus;
import com.wisehub.platform.api.domain.model.ProfitDomainModel;
import com.wisehub.platform.api.dto.DTODashoard;
import com.wisehub.platform.api.service.AccountService;
import com.wisehub.platform.api.service.CustomerService;
import com.wisehub.platform.api.service.UserService;
import com.wisehub.platform.api.view.model.CustomerCLVViewModel;
import com.wisehub.platform.api.view.model.CustomerViewModel;
import com.wisehub.platform.api.view.model.DashboardCustomerProfileViewModel;
import com.wisehub.platform.api.view.model.EngagementViewModel;
import com.wisehub.platform.api.view.model.FeedbackHitViewModel;
import com.wisehub.platform.api.view.model.RevenueViewModel;
import com.wisehub.platform.api.view.model.SaleAccountViewModel;
import com.wisehub.platform.data.model.AccountStatus;
import com.wisehub.platform.data.model.DashboardUser;
import com.wisehub.platform.data.model.UDTAddress;

@Service
public class CustomerServiceFakeImpl implements CustomerService {

	private static final Logger logger = LoggerFactory.getLogger(CustomerServiceFakeImpl.class);

	
	private List<CustomerDomainModel> customers;
	private List<NotificationDomainModel> notifications;

	private String surveys = "Mention what are the missing patterns that are generally observed?:Missing completely at random:Missing at random:Missing that depends on the missing value itself|List out some common problems faced by data analyst?:Common misspelling:Duplicate entries:Missing values:Illegal values:Varying value representations:Identifying |Mention what are the various steps in an analytics project?:Problem definition:Data exploration:Data preparation:Modelling:Validation of data:Implementation and tracking|Explain what is an Outlier?:Univariate:Multivariate|Mention what are the key skills required for Data Analyst?:Database knowledge:Database management:Data blending:Querying|Explain what are the tools used in Big Data?:Hadoop:Hive:Pig:Flume:Mahout:Sqoop|Explain what is Clustering? What are the properties for clustering algorithms?:Hierarchical or flat:Iterative:Hard and soft:Disjunctive|What are some of the statistical methods that are useful for data-analyst?:Bayesian method:Markov process:Sp";

	public static String CUSTOMER_NIGERIAN_NAMES = "Chinaza Halim|Chinedu Ogbonnia|Halim Okpara|Chinwe Ikenna|Chinweuba Uchea|Iweobiegbulam Madue|Zikoranaudodimma Chijioke|Chidiebube Iweobiegbulam|Onyekachi Okeke|Akuchi Akabueze|"
	+ "Chinasa Ozoemena|Halimnye Chinelo|Munachiso Odion|Chinwendu Obea|Chinweike Okparro|Chinwe Nnamdi|Ekene Okeke|Chinweuba Chuks|Akumjeli Tobechukwu|Nkemdilim Chinou|" 
	+ "Chino Uchechee|Enyinnaya Iweobiegbulam|Halimnye Jideofor|Okorie Afamefuna|Akubundu Ijeawele|Oluchi Odion|Beluchi Chydee|Uzochi Uzoma|Buchi Zikoranaudodimma|Uche Chinedu|Zebenjo Akabueze|Chydi Uchechy|Ogbonnia Chioke|Jelaney Uchy|Chizoba Izuchukwu|" 
	+ "Tobechukwu Ugochukwu|Akachi Chidie|Uchechie Chibueze|Uchie Uzochi|Emeka Agu";
	
	@Autowired
	private AccountService accountService;

	@Autowired
	private UserService userService;

	public CustomerServiceFakeImpl() {

	}

	@PostConstruct
	protected void init() throws IOException {
		customers = new ArrayList<>();
		notifications = new ArrayList<>();

		generateFakeData();
	}

	@Override
	public DashboardCustomerProfileViewModel getProfile(DashboardUser currentUser) {

		DashboardCustomerProfileViewModel model = new DashboardCustomerProfileViewModel();
		model.setUser(currentUser);
		LocalDate fromDateFields = LocalDate.of(currentUser.getCreatedAt().getYear(),currentUser.getCreatedAt().getMonth(),currentUser.getCreatedAt().getDate());

		try {

			if (currentUser.getAddresses() == null || currentUser.getAddresses().isEmpty()) {
				currentUser.setAddresses(currentUser.getAddresses());
			}
		
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			fillCurrentWithFakeData(currentUser, fromDateFields);
		}


		// FIXME : Fake data - get from a data source
		model.setClv(new BigDecimal(fromDateFields.getDayOfMonth() * fromDateFields.getDayOfWeek().getValue() * fromDateFields.getDayOfYear() * 17).setScale(2,
				BigDecimal.ROUND_HALF_EVEN));

		model.setSales(accountService.countByStatus(null));
		model.setPendingSales(accountService.countByStatus(AccountStatus.PENDING));

		model.setAccounts(userService.count());

		if (model.getSales().intValue() > 1) {

			BigDecimal revenue = new BigDecimal(
					(model.getSales() * (fromDateFields.getDayOfMonth() + 1)) + (model.getPendingSales() * fromDateFields.getDayOfMonth())).setScale(2,
							BigDecimal.ROUND_HALF_EVEN);
			model.setRevenue(new RevenueViewModel(revenue, "USD"));

			
//			Date firstSale = accountService.findFirstSale();
//			Date lastSale = accountService.findLastSale();

			
			Date saleDate = new Date(fromDateFields.getYear(), fromDateFields.getMonthValue(), fromDateFields.getDayOfMonth(), 16, 58);
			model.setFirstSale(new SaleAccountViewModel(currentUser.getId(),
					new BigDecimal(fromDateFields.getDayOfMonth() + 1).setScale(2, BigDecimal.ROUND_HALF_EVEN), saleDate, "USD"));

			if (model.getSales().intValue() > 2) {
				saleDate = new Date(fromDateFields.getYear(), fromDateFields.getMonthValue(), fromDateFields.getDayOfMonth(), 16, 58);
				model.setLastSale(new SaleAccountViewModel(UUID.randomUUID(),
						new BigDecimal(fromDateFields.getDayOfMonth() + 1).setScale(2, BigDecimal.ROUND_HALF_EVEN), saleDate, "USD"));
			} else {
				model.setLastSale(new SaleAccountViewModel(currentUser.getId(),
						new BigDecimal(fromDateFields.getDayOfMonth() + 1).setScale(2, BigDecimal.ROUND_HALF_EVEN), saleDate, "USD"));
			}

		} else {
			model.setRevenue(new RevenueViewModel(new BigDecimal(0), "USD"));
		}

		Date saleDate = new Date(fromDateFields.getYear(), fromDateFields.getMonthValue(), fromDateFields.getDayOfMonth(), 16, 58);
		model.setFirstEngagement(new EngagementViewModel(UUID.randomUUID(), saleDate));

		saleDate = new Date(fromDateFields.getYear(), fromDateFields.getMonthValue(), fromDateFields.getDayOfMonth(), 16, 58);
		model.setLastEngagement(new EngagementViewModel(UUID.randomUUID(), saleDate));

		return model;
	}

	private void fillCurrentWithFakeData(DashboardUser currentUser, LocalDate fromDateFields) {

		if (currentUser.getAddresses() == null || currentUser.getAddresses().isEmpty()) {
			currentUser.setAddresses(new HashMap<String, UDTAddress>());
			UDTAddress address = new UDTAddress();
			address.setCity("New York");
			address.setCountry("USA");
			address.setPhones(new HashSet<String>());
			address.getPhones().add(
					"001" + String.valueOf(fromDateFields.getDayOfMonth() * fromDateFields.getDayOfWeek().getValue() * fromDateFields.getDayOfYear() * 17));
			address.getPhones().add(
					"2526" + String.valueOf(fromDateFields.getDayOfMonth() * fromDateFields.getDayOfWeek().getValue() * fromDateFields.getDayOfYear() * 2));
			address.setPostalCode("76211");
			address.setRegion("R1");
			address.setStreet("Street " + String.valueOf(fromDateFields.getDayOfMonth()) + "th");
			currentUser.getAddresses().put("Demo", address);
		}
	}

	@Override
	public CustomerViewModel generate(DTODashoard dto) {
		CustomerViewModel customerViewModel = new CustomerViewModel();
		customerViewModel.setFilter((dto == null) ? new DTODashoard().builder() : dto.builder());

		customerViewModel.setCustomersNotified(calcCustomersNotifiedByDTO(dto));
		customerViewModel.setAvgFrequencyOfNotification(calcAvgFrequencyOfNotificationByDTO(dto));
		customerViewModel.setCustomersResponded(calcCustomersRespondedByDTO(dto));

		List<FeedbackHitViewModel> calcTopCustomersFeedbackByDTO = calcTopCustomersFeedbackByDTO(dto);
		Collections.sort(calcTopCustomersFeedbackByDTO, new Comparator<FeedbackHitViewModel>() {
			@Override
			public int compare(FeedbackHitViewModel o1, FeedbackHitViewModel o2) {
				return o1.getHits().compareTo(o2.getHits());
			}
		});

		int topCustomersFeedbackSize = calcTopCustomersFeedbackByDTO.size();
		if (calcTopCustomersFeedbackByDTO.size() > 5) {
			topCustomersFeedbackSize = 5;
		}

		List<FeedbackHitViewModel> subList = calcTopCustomersFeedbackByDTO.subList(0, topCustomersFeedbackSize);
		customerViewModel.setTopCustomersFeedback(subList);

		customerViewModel.setCustomersPending(calcCustomersByDTOAndStatus(dto, CustomerStatus.PENDING));
		customerViewModel.setCustomersNewlyActive(calcCustomersByDTOAndStatus(dto, CustomerStatus.NEW));

		customerViewModel.setAvgCustomerCLV(calcAVGCustomerLVByDTO(dto));
		List<CustomerCLVViewModel> topCustomerLVByDTO = findTopCustomerLVByDTO(dto);

		Collections.sort(topCustomerLVByDTO, new Comparator<CustomerCLVViewModel>() {
			@Override
			public int compare(CustomerCLVViewModel o1, CustomerCLVViewModel o2) {
				return o1.getAmount().compareTo(o2.getAmount());
			}
		});

		int top = topCustomerLVByDTO.size();
		if (topCustomerLVByDTO.size() > 5) {
			top = 5;
		}
		customerViewModel.setTopCustomerCLV(topCustomerLVByDTO.subList(0, top));

		return customerViewModel;
	}

	private List<FeedbackHitViewModel> calcTopCustomersFeedbackByDTO(DTODashoard dto) {
		Map<String, List<CustomerNotificationDomainModel>> map = customers.stream()
				.flatMap(p -> p.getNotifications().stream().filter(n -> n.getReceive().after(dto.getFrom()) && n.getReceive().before(dto.getTo()))
						.map(c -> new AbstractMap.SimpleEntry<>(c.getSelectedAnswer(), c)))
				.collect(Collectors.groupingBy(e -> e.getKey(), Collectors.mapping(Map.Entry::getValue, Collectors.toList())));

		ArrayList<FeedbackHitViewModel> result = map.entrySet().stream().map(c -> new FeedbackHitViewModel(c.getKey(), c.getValue().stream().count()))
				.collect(Collectors.toCollection(ArrayList::new));

		return result;
	}

	private List<CustomerCLVViewModel> calcAVGCustomerLVByDTO(DTODashoard dto) {

		Map<Integer, List<ProfitDomainModel>> map = customers.stream()
				.flatMap(p -> p.getProfits().stream().filter(n -> n.getDate().after(dto.getFrom()) && n.getDate().before(dto.getTo()))
						.map(c -> new AbstractMap.SimpleEntry<>(c.getDate().getMonth(), c)))
				.collect(Collectors.groupingBy(e -> e.getKey(), Collectors.mapping(Map.Entry::getValue, Collectors.toList())));

		ArrayList<CustomerCLVViewModel> result = map.entrySet().stream()
				.map(c -> new CustomerCLVViewModel(months[c.getKey()],
						new BigDecimal(c.getValue().stream().mapToDouble(p -> p.getProfit().doubleValue()).average().getAsDouble()).setScale(2,
								BigDecimal.ROUND_HALF_EVEN)))
				.collect(Collectors.toCollection(ArrayList::new));

		return result;

	}

	private static String[] months = { "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November",
			"December" };

	private List<CustomerCLVViewModel> findTopCustomerLVByDTO(DTODashoard dto) {
		Map<CustomerDomainModel, List<ProfitDomainModel>> map = customers.stream()
				.flatMap(p -> p.getProfits().stream().filter(n -> n.getDate().after(dto.getFrom()) && n.getDate().before(dto.getTo()))
						.map(c -> new AbstractMap.SimpleEntry<>(p, c)))
				.collect(Collectors.groupingBy(e -> e.getKey(), Collectors.mapping(Map.Entry::getValue, Collectors.toList())));

		ArrayList<CustomerCLVViewModel> result = map.entrySet().stream()
				.map(c -> new CustomerCLVViewModel(c.getKey().getId(), c.getKey().getFullname(),
						new BigDecimal(c.getValue().stream().mapToDouble(p -> p.getProfit().doubleValue()).sum()).setScale(2, BigDecimal.ROUND_HALF_EVEN)))
				.collect(Collectors.toCollection(ArrayList::new));

		return result;
	}

	private Long calcCustomersByDTOAndStatus(DTODashoard dto, CustomerStatus status) {
		return customers.stream().filter(c -> status.equals(c.getStatus())).count();
	}

	private Long calcCustomersRespondedByDTO(DTODashoard dto) {
		return customers.stream().mapToLong(c -> c.getNotifications().stream().filter(n -> NotificationStatus.RESPONDED.equals(n.getStatus())).count()).sum();
	}

	private BigDecimal calcAvgFrequencyOfNotificationByDTO(DTODashoard dto) {
		Long amountNotifications = notifications.stream().filter(n -> n.getRelease().after(dto.getFrom()) && n.getRelease().before(dto.getTo())).count();
		Long amountCustomers = customers.stream().filter(n -> n.getCreateAt().after(dto.getFrom()) && n.getCreateAt().before(dto.getTo())).count();

		if(amountCustomers  == 0l){
			return new BigDecimal(0.00d).setScale(2, BigDecimal.ROUND_HALF_EVEN);
		}
		
		double avg = ((double) amountNotifications / (double) amountCustomers);
		return new BigDecimal(avg).setScale(2, BigDecimal.ROUND_HALF_EVEN);
	}

	private Long calcCustomersNotifiedByDTO(DTODashoard dto) {
		return customers.stream()
				.mapToLong(c -> c.getNotifications().stream().filter(n -> n.getReceive().after(dto.getFrom()) && n.getReceive().before(dto.getTo())).count())
				.count();
	}

	private void generateFakeData() {

		LocalDate start = LocalDate.now().withDayOfYear(1);
		LocalDate end = LocalDate.now().plusDays(1);

		Date from = Date.from(start.atStartOfDay(ZoneId.systemDefault()).toInstant());
		Date to = Date.from(end.atStartOfDay(ZoneId.systemDefault()).toInstant());

		for (String sCurrentLine : surveys.split("\\|")) {
			String[] split = sCurrentLine.split(":");
			NotificationDomainModel notification = new NotificationDomainModel();
			notification.setId(UUID.randomUUID().toString());
			Date randomDate = new Date(ThreadLocalRandom.current().nextLong(from.getTime(), to.getTime()));
			notification.setRelease(randomDate);
			notification.setAnswers(new ArrayList<>());
			for (int i = 0; i < split.length; i++) {
				if (i == 0) {
					notification.setQuestion(split[i]);
				} else {
					notification.getAnswers().add(split[i]);
				}
			}
			notifications.add(notification);
		}

		for (String sCurrentLine : CUSTOMER_NIGERIAN_NAMES.split("\\|")) {
			if (sCurrentLine != null && !sCurrentLine.isEmpty()) {
				CustomerDomainModel customer = createCustomer(from, to, sCurrentLine, notifications);
				customers.add(customer);
			}
		}
	}

	private CustomerDomainModel createCustomer(Date from, Date to, String customerName, List<NotificationDomainModel> notifications) {

		Date randomDate = new Date(ThreadLocalRandom.current().nextLong(from.getTime(), to.getTime()));

		CustomerDomainModel customer = new CustomerDomainModel();
		customer.setId(UUID.randomUUID());
		customer.setCreateAt(randomDate);
		customer.setFullname(customerName);

		long highbits = customer.getId().getMostSignificantBits();

		if (highbits % 2 == 0) {
			customer.setStatus(CustomerStatus.ACTIVE);
		} else {
			customer.setStatus(CustomerStatus.NEW);
		}

		if (highbits % 3 == 0) {
			customer.setStatus(CustomerStatus.PENDING);
		}

		customer.setProfits(buildProfits(from, to, customer, randomDate));
		//
		customer.setNotifications(notifications.stream().filter(n -> n.getRelease().after(randomDate))
				.map(n -> new CustomerNotificationDomainModel(UUID.randomUUID().toString(),
						ThreadLocalRandom.current().nextInt(2) == 1 ? NotificationStatus.PENDING : NotificationStatus.RESPONDED,
						new Date(ThreadLocalRandom.current().nextLong(n.getRelease().getTime(), to.getTime())), n.getId(),
						n.getAnswers().get(ThreadLocalRandom.current().nextInt(0, n.getAnswers().size()))))
				.collect(Collectors.toCollection(ArrayList::new)));

		return customer;

	}

	private List<ProfitDomainModel> buildProfits(Date from, Date to, CustomerDomainModel customer, Date randomDate) {
		List<ProfitDomainModel> profits = new ArrayList<>();

		long highbits = customer.getId().getMostSignificantBits();

		int amountProfits = 1;

		if (highbits % 2 == 0) {
			amountProfits = amountProfits + 4;
		}
		if (highbits % 3 == 0) {
			amountProfits = amountProfits + 3;
		}
		if (highbits % 5 == 0) {
			amountProfits = amountProfits + 5;
		}

		for (int i = 0; i < amountProfits; i++) {
			ProfitDomainModel e = new ProfitDomainModel();
			e.setProfit(new BigDecimal(ThreadLocalRandom.current().nextDouble(2000, 5000)).setScale(2, BigDecimal.ROUND_HALF_EVEN));
			e.setDate(new Date(ThreadLocalRandom.current().nextLong(randomDate.getTime(), to.getTime())));
			e.setId(customer.getId());
			profits.add(e);

		}

		return profits;

	}

}
