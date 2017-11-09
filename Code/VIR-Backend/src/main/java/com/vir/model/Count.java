package com.vir.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Count {

	private long awl;
	private long hi;
	private long med;
	private long low;
	private long noCategory;
	private long total;

	private Count() {
	}

	public Count(long awl, long hi, long med, long low, long noCategory) {
		this.awl = awl;
		this.hi = hi;
		this.med = med;
		this.low = low;
		this.noCategory = noCategory;
		this.total = this.awl + this.hi + this.med + this.low + this.noCategory;
	}
	
	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

	@Override
	public boolean equals(Object obj) {
		Count that = (Count) obj;
		return EqualsBuilder.reflectionEquals(this, that);
	}
	
	public long getAwl() {
		return awl;
	}
	public void setAwl(long awl) {
		this.awl = awl;
	}
	public long getHi() {
		return hi;
	}
	public void setHi(long hi) {
		this.hi = hi;
	}
	public long getMed() {
		return med;
	}
	public void setMed(long med) {
		this.med = med;
	}
	public long getLow() {
		return low;
	}
	public void setLow(long low) {
		this.low = low;
	}
	public long getNoCategory() {
		return noCategory;
	}
	public void setNoCategory(long noCategory) {
		this.noCategory = noCategory;
	}
	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}
}
