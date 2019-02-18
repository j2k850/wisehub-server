package com.wisehub.platform.data.model.dao;

import java.util.UUID;

import com.wisehub.platform.data.model.Product;

public interface ProductRepository extends VersionCrudRepository<Product, UUID> {

}