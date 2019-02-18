package com.wisehub.platform.api.dto;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DTODashoard {

	private Integer filter;

	@JsonProperty(value = "from_date")
	private Date from;
	@JsonProperty(value = "to_date")
	private Date to;

	public void setFilter(Integer filter) {
		this.filter = filter;
	}

	public Integer getFilter() {
		return filter;
	}

	public Date getFrom() {
		return from;
	}

	public void setFrom(Date from) {
		this.from = from;
	}

	public Date getTo() {
		return to;
	}

	public void setTo(Date to) {
		this.to = to;
	}

	public DTODashoard builder() {

		LocalDate start;
		LocalDate end;
		switch (this.getFilter()) {
		case 1: // Today
			start = LocalDate.now();
			end = start.plusDays(1);
			break;
		case 2:
			// Week
			start = LocalDate.now().with(DayOfWeek.MONDAY);
			end = start.plusDays(1).plusWeeks(1).minusDays(1);
			break;
		case 3: // Month
			start = LocalDate.now().withDayOfMonth(1);
			end = start.plusDays(1).plusMonths(1).minusDays(1);
			break;
		case 4: // Year
			start = LocalDate.now().withDayOfYear(1);
			end = start.plusDays(1).plusYears(1).minusDays(1);
			break;
		default: // TODAY
			start = LocalDate.now();
			end = start.plusDays(1);
			break;
		}

		
		Date from = Date.from(start.atStartOfDay(ZoneId.systemDefault()).toInstant());
		Date to = Date.from(end.atStartOfDay(ZoneId.systemDefault()).toInstant());


		this.setFrom(from);
		this.setTo(to);

		return this;
	}

}
