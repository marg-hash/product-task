package com.product.productrestdemo.model;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlElement;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
//@JacksonXmlRootElement(localName = "product")
public class Product {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("Id")
	private String id;
    @JsonProperty("Type")
	private String type;
    @JsonProperty("Price")
	private String price;
    @JsonProperty("StartDate")
	private Date startDate;
    @JsonProperty("EndDate")
	private Date endDate;

	public Product() {
		super();
		//this.id = UUID.randomUUID().toString();
	}

	@XmlElement(name = "Id")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@XmlElement(name = "Type")
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@XmlElement(name = "Price")
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	@XmlElement(name = "StartDate")
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	@XmlElement(name = "EndDate")
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        return id != null ? id.equals(product.id) : product.id == null;
    }

	@Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
	@Override
	public String toString() {
		return "Products [id=" + id + ", type=" + type + ", price=" + price + ", startDate=" + startDate + ", endDate=" + endDate + "]";
	}
	
}
