package com.mobileapi.fiap.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mobileapi.fiap.model.VegetaisModel;

public interface VegetaisRepository extends JpaRepository<VegetaisModel, Long> {
	List<VegetaisModel> findByIdCliente(Long idCliente);

}
