package com.example.fut.repository;

import com.example.fut.model.FederacaoNacional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FederacaoNacionalRepository extends JpaRepository<FederacaoNacional, UUID> {
}
