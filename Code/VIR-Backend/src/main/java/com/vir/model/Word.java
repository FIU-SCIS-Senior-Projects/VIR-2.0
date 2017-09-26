package com.vir.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Entity representation of a word.
 * 
 * @author Alfredo Lopez
 *
 */
@Entity
public class Word implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String value;

	private String category;

	@Transient
	private String initialValue;

	public Word() {
	}

	public Word(String value, String category, String initialValue) {
		this.value = value;
		this.category = category;
		this.initialValue = initialValue;
	}

	public Word(String initialValue) {
		this(StringUtils.EMPTY, StringUtils.EMPTY, initialValue);
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

	@Override
	public boolean equals(Object obj) {
		Word that = (Word) obj;
		return EqualsBuilder.reflectionEquals(this, that);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getInitialValue() {
		return initialValue;
	}

	public void setInitialValue(String initialValue) {
		this.initialValue = initialValue;
	}
}
