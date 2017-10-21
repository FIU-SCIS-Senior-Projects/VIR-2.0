package com.vir.model;

public class Percent {

	private double aws;
	private double hi;
	private double med;
	private double low;
	private double total;

	private Percent() {
	}

	public Percent(Count count) {
		this.aws = (double) count.getAwl() / count.getTotal();
		this.hi = (double) count.getHi() / count.getTotal();
		this.med = (double) count.getMed() / count.getTotal();
		this.low = (double) count.getLow() / count.getTotal();
		this.total = (double) count.getTotal() / count.getTotal();
	}

	public double getAws() {
		return aws;
	}
	public void setAws(double aws) {
		this.aws = aws;
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
}
