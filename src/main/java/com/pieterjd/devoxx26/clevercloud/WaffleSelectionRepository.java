package com.pieterjd.devoxx26.clevercloud;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WaffleSelectionRepository extends JpaRepository<WaffleSelection, Long> {
    List<WaffleSelection> findAllByOrderByCreatedAtDesc();
}
