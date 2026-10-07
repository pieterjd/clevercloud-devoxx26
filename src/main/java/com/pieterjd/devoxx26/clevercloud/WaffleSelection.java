package com.pieterjd.devoxx26.clevercloud;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "waffle_selections")
public class WaffleSelection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String topping;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    protected WaffleSelection() {
    }

    public WaffleSelection(String topping) {
        this.topping = topping;
    }

    @PrePersist
    public void prePersist() {
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
    }

    public Long getId() {
        return id;
    }

    public String getTopping() {
        return topping;
    }

    public void setTopping(String topping) {
        this.topping = topping;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
