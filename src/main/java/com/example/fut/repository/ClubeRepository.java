package com.example.fut.repository;

import com.example.fut.model.Clube;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ClubeRepository extends JpaRepository<Clube, UUID> {
}
