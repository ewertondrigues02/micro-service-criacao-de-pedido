package br.com.ewerton.model;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Representa um pedido no sistema.
 */
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

    /**
     * Construtor padrão.
     */
    public Order() {
    }

    /**
     * Construtor com parâmetros.
     *
     * @param id      Identificador do pedido.
     * @param userId  Identificador do usuário que fez o pedido.
     * @param product Nome do produto.
     * @param price   Preço do pedido.
     */
    public Order(UUID id, UUID userId, String product, Double price) {
        this.id = id;
        this.userId = userId;
        this.product = product;
        this.price = price;
    }

    /**
     * Obtém o identificador do pedido.
     *
     * @return ID do pedido.
     */
    public UUID getId() {
        return id;
    }

    /**
     * Define o identificador do pedido.
     *
     * @param id ID do pedido.
     */
    public void setId(UUID id) {
        this.id = id;
    }

    /**
     * Obtém o identificador do usuário que fez o pedido.
     *
     * @return ID do usuário.
     */
    public UUID getUserId() {
        return userId;
    }

    /**
     * Define o identificador do usuário que fez o pedido.
     *
     * @param userId ID do usuário.
     */
    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    /**
     * Obtém o nome do produto.
     *
     * @return Nome do produto.
     */
    public String getProduct() {
        return product;
    }

    /**
     * Define o nome do produto.
     *
     * @param product Nome do produto.
     */
    public void setProduct(String product) {
        this.product = product;
    }

    /**
     * Obtém o preço do pedido.
     *
     * @return Preço do pedido.
     */
    public Double getPrice() {
        return price;
    }

    /**
     * Define o preço do pedido.
     *
     * @param price Preço do pedido.
     */
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
        if (obj == null || getClass() != obj.getClass())
            return false;
        Order other = (Order) obj;
        return Objects.equals(id, other.id) && Objects.equals(price, other.price)
                && Objects.equals(product, other.product) && Objects.equals(userId, other.userId);
    }
}
