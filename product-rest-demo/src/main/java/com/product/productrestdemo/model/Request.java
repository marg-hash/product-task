package com.product.productrestdemo.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "Root")
@XmlRootElement(name = "Root")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class Request {
	
	//@JacksonXmlProperty
	//@JacksonXmlElementWrapper(useWrapping = false)
	@JacksonXmlElementWrapper(localName="RequestDetails")
    @JsonProperty("RequestDetails")
	private RequestDetails requestDetails;
	//@JacksonXmlProperty
	@JacksonXmlElementWrapper(useWrapping = false)
	//@JacksonXmlElementWrapper(localName="Event")
    @JsonProperty("Event")
	private List<Event> events;

	public Request() {
		super();
	}
	@XmlElement(name = "RequestDetails")
	public RequestDetails getRequestDetails() {
		return requestDetails;
	}

	public void setRequestDetails(RequestDetails requestDetails) {
		this.requestDetails = requestDetails;
	}
	
	@XmlElement(name = "Event")
	public List<Event> getEvents() {
		return events;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
	}
	
	@Override
	public String toString() {
		return "Request [requestDetails=" + requestDetails + ", events=" + events + "]";
	}

}
