package com.product.productrestdemo.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlElement;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;

@Entity
public class RequestDetails {
	
	@Id
    @JsonProperty("Id")
	private String id;
    @JsonProperty("AcceptDate")
	private String acceptDate;
    @JsonProperty("SourceCompany")
	private String sourceCompany;
	
	public RequestDetails() {
		super();
	}

	public RequestDetails(String id, String acceptDate, String sourceCompany) {
		super();
		this.id = id;
		this.acceptDate = acceptDate;
		this.sourceCompany = sourceCompany;
	}

	@XmlElement(name = "Id")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@XmlElement(name = "AcceptDate")
	public String getAcceptDate() {
		return acceptDate;
	}

	public void setAcceptDate(String acceptDate) {
		this.acceptDate = acceptDate;
	}

	@XmlElement(name = "SourceCompany")
	public String getSourceCompany() {
		return sourceCompany;
	}

	public void setSourceCompany(String sourceCompany) {
		this.sourceCompany = sourceCompany;
	}

	@OneToMany
	@JoinColumn(name = "request_details_id")
    @JacksonXmlElementWrapper(useWrapping = false)
    @JsonProperty("Event")
	private List<Event> events = new ArrayList<>();
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RequestDetails requestDetail = (RequestDetails) o;

        return id != null ? id.equals(requestDetail.id) : requestDetail.id == null;
    }
    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

	@Override
	public String toString() {
		return "RequestDetails [id=" + id + ", acceptDate=" + acceptDate + ", sourceCompany=" + sourceCompany+ "]";
	}
	
}
