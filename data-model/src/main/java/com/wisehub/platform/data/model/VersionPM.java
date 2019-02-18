package com.wisehub.platform.data.model;

import java.util.Date;
import java.util.UUID;

public interface VersionPM {

	public UUID getId();

	public void setId(UUID id);

	public Date getCreatedAt();

	public void setCreatedAt(Date createdAt);

	public Date getUpdatedAt();

	public void setUpdatedAt(Date updatedAt);

}
