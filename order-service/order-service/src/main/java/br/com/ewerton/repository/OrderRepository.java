package br.com.ewerton.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ewerton.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID>{

}
