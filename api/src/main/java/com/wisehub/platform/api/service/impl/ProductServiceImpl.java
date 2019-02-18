package com.wisehub.platform.api.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wisehub.platform.api.service.ProductService;
import com.wisehub.platform.api.view.model.ProductRecommendedViewModel;
import com.wisehub.platform.data.model.Product;
import com.wisehub.platform.data.model.dao.ProductRepository;
import com.wisehub.platform.data.model.dao.VersionCrudRepository;

@Service
public class ProductServiceImpl extends AbstractVersionCrudService<Product> implements ProductService {

	private static Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class.getName());

	@Autowired
	ProductRepository productRepository;

	@Override
	protected VersionCrudRepository<Product, UUID> getRepository() {
		return productRepository;
	}

	@Override
	public List<ProductRecommendedViewModel> getRecommendedBy(UUID id) {
		/**Fake Product Recommeded"**/
		long highbits = id.getMostSignificantBits();
		
		List<ProductRecommendedViewModel> list = new ArrayList<>();
		
		if(highbits%2==0){
			list.add(ProductRecommendedViewModel.SAVINGS);
		}
		if(highbits%3==0){
			list.add(ProductRecommendedViewModel.INSURANCE);
		}
		if(highbits%4==0){
			list.add(ProductRecommendedViewModel.INVESTMENT);
		}
		if(highbits%5==0){
			list.add(ProductRecommendedViewModel.LOANS);
		}

		return list;
	}

}
