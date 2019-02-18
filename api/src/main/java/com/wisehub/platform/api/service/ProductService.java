package com.wisehub.platform.api.service;

import java.util.List;
import java.util.UUID;

import com.wisehub.platform.api.view.model.ProductRecommendedViewModel;
import com.wisehub.platform.data.model.Product;

public interface ProductService extends VersionCrudService<Product,UUID> {

	List<ProductRecommendedViewModel> getRecommendedBy(UUID id);

}
