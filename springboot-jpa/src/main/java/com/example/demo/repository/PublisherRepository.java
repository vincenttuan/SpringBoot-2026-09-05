package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.book.Publisher;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Integer> {
	
	// 自訂查詢方法(自訂原生 SQL)
	@Query(value="select * from publisher order by id asc", nativeQuery = true)
	List<Publisher> findAll_useSQL();
	
	// 自訂查詢方法(使用內建 PQL)
	@Query(value = "select p from Publisher p order by p.id asc")
	List<Publisher> findAll_useJPQL();
	
}
