package br.com.ewerton.dto;

import java.util.UUID;

public record OrderCreatedEvent(UUID id, UUID userId, String product, Double price) {

	public UUID id() {
		return id;
	}

	public UUID userId() {
		return userId;
	}

	public String product() {
		return product;
	}

	public Double price() {
		return price;
	}

}
