package com.product.productrestdemo.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlElement;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;

@Entity
//@JacksonXmlRootElement(localName = "Event")
public class Event {
	
	@Id
    @JsonProperty("Id")
	private String id;
    @JsonProperty("Type")
	private String type;
    @JsonProperty("InsuredId")
	private Long insuredId;
	
	@ManyToOne
	private RequestDetails requestDetails;
	@OneToMany
	@JoinColumn(name = "event_id")
    @JacksonXmlElementWrapper(useWrapping = false)
    @JsonProperty("Product")
	private List<Product> products = new ArrayList<>();
	
	public Event() {
		super();
	}	
	public Event(String id, String type, Long insuredId) {
		super();
		this.id = id;
		this.type = type;
		this.insuredId = insuredId;
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
	@XmlElement(name = "InsuredId")
	public Long getInsuredId() {
		return insuredId;
	}
	public void setInsuredId(Long insuredId) {
		this.insuredId = insuredId;
	}

	
	public RequestDetails getRequestDetails() {
		return requestDetails;
	}

	public void setRequestDetails(RequestDetails requestDetails) {
		this.requestDetails = requestDetails;
	}
	 
	@XmlElement(name = "Product")
	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Event event = (Event) o;

        return id != null ? id.equals(event.id) : event.id == null;
    }
    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
	public String toString() {
		return "Events [id=" + id + ", type=" + type + ", insuredId=" + insuredId + ",products=" + products + "]";
	}
	
}
