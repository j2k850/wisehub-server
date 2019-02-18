package com.wisehub.platform.data.producer.fake;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.wisehub.platform.data.producer.fake.model.CategoriesType;
import com.wisehub.platform.data.producer.fake.model.TransactionFakeModel;
import com.wisehub.platform.data.producer.fake.model.TransactionType;
import com.wisehub.platform.data.producer.fake.profile.CustomerProfile;
import com.wisehub.platform.data.producer.fake.profile.WorkerCustomerProfile;

@Profile(value = {"worker-1","worker-2","worker-3"})
@Component
public class WorkerCustomerFakeProducer extends AbstractFakeProducer implements CommandLineRunner {

	private static final Logger LOG = LoggerFactory.getLogger(WorkerCustomerFakeProducer.class);

	@Autowired
	WorkerCustomerProfile workerCustomerProfile;


	@Override
	public CustomerProfile getCustomerProfile() {
		return workerCustomerProfile;
	}


	public List<TransactionFakeModel> generate(LocalDate localDate, double open1ingBalance) {

		List<TransactionFakeModel> txs = new ArrayList<>();
		int salaries = workerCustomerProfile.getSalaryByMonth();
		
		LocalDate end = localDate.plusMonths(1l);
		TransactionFakeModel transactionFakeGen = buildOperation(localDate, null, TransactionType.OPEN, null, open1ingBalance);
		txs.add(transactionFakeGen);
		salaries--;
		int maxOperation = 1;
		
		List<CategoriesType> savingCostCategories1 = new ArrayList<CategoriesType>(workerCustomerProfile.getSavingCostCategories());
		List<CategoriesType> variableCostCategories1 = new ArrayList<CategoriesType>(workerCustomerProfile.getVariableCostCategories());
		List<CategoriesType> categories = new ArrayList<CategoriesType>();
		categories.addAll(savingCostCategories1);
		categories.addAll(variableCostCategories1);
		
		shuffleList(categories);
		
		Map<CategoriesType,Integer> times = new HashMap<>();

		
		txs.addAll(buildFromFixedCost(false, localDate, workerCustomerProfile, transactionFakeGen));
		transactionFakeGen = txs.get(txs.size() -1);
		localDate = transactionFakeGen.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		
		int nextDay = ThreadLocalRandom.current().nextInt(2);
		localDate = localDate.plusDays(nextDay);
		
		WeekFields weekFields = WeekFields.of(Locale.getDefault());
		// TODO: Modify by start & end day of month
		double localBalance = transactionFakeGen.getBalance().doubleValue();
		while (end.isAfter(localDate) && localBalance > 0 && maxOperation < 140) {

			// TODO: two salaries at month
			if (localDate.getDayOfMonth() > 14 && salaries > 0) {

				double saliry = ThreadLocalRandom.current().nextDouble(workerCustomerProfile.getSalaryMin(), workerCustomerProfile.getSalaryMax() + 1);

				TransactionFakeModel tx = buildOperation(localDate, transactionFakeGen, TransactionType.DEPOSIT_INFO, null, saliry);
				if (tx != null && tx.getBalance() != null) {
					localBalance = tx.getBalance().doubleValue();
					txs.add(tx);
					transactionFakeGen = tx;
					maxOperation++;
					salaries--;
				}

			}
			// Expense by day ramdomize;
			int weekNumber = localDate.get(weekFields.weekOfMonth());
			int expenses = 1;
			if (weekNumber < 6) {
				expenses = ThreadLocalRandom.current().nextInt(8 - weekNumber);
			}

			for (int j = 0; j < expenses; j++) {
				int index = ThreadLocalRandom.current().nextInt(categories.size());
				CategoriesType category = categories.get(index);

				if (category != null) {
					double amount = ThreadLocalRandom.current().nextDouble(category.getMin(), category.getMax()) / 2;
					TransactionFakeModel tx  = buildOperation(localDate, transactionFakeGen, TransactionType.WITHDRAW, category, amount);
					if (tx != null && tx.getBalance() != null) {
						localBalance = tx.getBalance().doubleValue();
						txs.add(tx);
						transactionFakeGen = tx;
						if (category.getType() == 3){
							categories.remove(index);
						}else if (!times.containsKey(category)){
							times.put(category, 1);
						}else if (times.get(category).intValue() < category.getTimes() ){
							Integer val = times.get(category);
							val++;
							times.put(category, val);
						}else{
							categories.remove(index);
						}
						maxOperation++;
					}
				}
			}

			localDate = localDate.plusDays(1);

		}

		return txs;

	}


	@Override
	public double getIncome() {
		return ThreadLocalRandom.current().nextDouble(workerCustomerProfile.getSalaryMin(), workerCustomerProfile.getSalaryMax() + 1);
	}


}