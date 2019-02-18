package com.wisehub.platform.core.orm.dao;

import com.wisehub.platform.core.orm.entity.EntityModel;

public interface DAO {

  public void create(EntityModel entity);
  public void update(EntityModel entity);
  public void put(String id, EntityModel entity);
  public void delete(EntityModel entity);

  }
