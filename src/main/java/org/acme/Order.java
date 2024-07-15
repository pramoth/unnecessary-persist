package org.acme;

import jakarta.persistence.*;

import java.util.List;


@Table(name = "orders")
@Entity
public class Order  {
    @Id
    private Long id;
    private String name;

    @OneToMany()
    @JoinColumn(name = "orders_id")
    private List<OrderItem> orderItems;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }
}
