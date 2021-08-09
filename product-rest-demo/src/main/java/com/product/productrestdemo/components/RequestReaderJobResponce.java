package com.product.productrestdemo.components;

import org.springframework.stereotype.Component;

@Component
public class RequestReaderJobResponce {
	private boolean success;
    private String message;

    public RequestReaderJobResponce() {
		super();
	}

	public RequestReaderJobResponce(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

    /*Map<String, Map<String, ProductBean>> productData = new HashMap<>();

	public Map<String, Map<String, ProductBean>> getProductData() {
		return productData;
	}

	public void setProductData(Map<String, Map<String, ProductBean>> productData) {
		this.productData = productData;
	}*/
	
}
