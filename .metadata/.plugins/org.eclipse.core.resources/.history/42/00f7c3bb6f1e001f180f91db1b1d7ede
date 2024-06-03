package br.com.ewerton.model;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_order")
public class Order implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	private UUID userId;
	private String product;
	private Double price;

	public Order() {

	}

	public Order(UUID id, UUID userId, String product, Double price) {
		super();
		this.id = id;
		this.userId = userId;
		this.product = product;
		this.price = price;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public UUID getUserId() {
		return userId;
	}

	public void setUserId(UUID userId) {
		this.userId = userId;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", userId=" + userId + ", product=" + product + ", price=" + price + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, price, product, userId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		return Objects.equals(id, other.id) && Objects.equals(price, other.price)
				&& Objects.equals(product, other.product) && Objects.equals(userId, other.userId);
	}

}
