package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Staff;

public interface StaffRepository extends JpaRepository<Staff, Integer> {
	
	// 根據名稱查找員工
	Optional<Staff> findByName(String name);
	
}
