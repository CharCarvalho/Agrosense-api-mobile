package com.mobileapi.fiap.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mobileapi.fiap.model.ClienteModel;

public interface ClienteRepository extends JpaRepository<ClienteModel, Long>{

}
