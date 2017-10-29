package com.vir.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Percent {

	private double awl;
	private double hi;
	private double med;
	private double low;
	private double noCategory;
	private double total;

	@SuppressWarnings("unused")
	private Percent() {
	}

	public Percent(Count count) {
		this.awl = (double) count.getAwl() / count.getTotal();
		this.hi = (double) count.getHi() / count.getTotal();
		this.med = (double) count.getMed() / count.getTotal();
		this.low = (double) count.getLow() / count.getTotal();
		this.noCategory = (double) count.getNoCategory() / count.getTotal();
		this.total = (double) count.getTotal() / count.getTotal();
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

	@Override
	public boolean equals(Object obj) {
		Percent that = (Percent) obj;
		return EqualsBuilder.reflectionEquals(this, that);
	}
	
	public double getAwl() {
		return awl;
	}
	public void setAwl(double awl) {
		this.awl = awl;
	}
	public double getHi() {
		return hi;
	}
	public void setHi(double hi) {
		this.hi = hi;
	}
	public double getMed() {
		return med;
	}
	public void setMed(double med) {
		this.med = med;
	}
	public double getLow() {
		return low;
	}
	public void setLow(double low) {
		this.low = low;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public double getNoCategory() {
		return noCategory;
	}
	public void setNoCategory(double noCategory) {
		this.noCategory = noCategory;
	}
}
