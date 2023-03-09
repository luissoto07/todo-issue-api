package com.root.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.root.entity.Rol;

public interface RolRepository extends JpaRepository<Rol, Long>{

	public Optional<Rol> findByName(String name);
}
