package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.stock.Stock;

@Repository
public interface StockRepository extends JpaRepository<Stock, Integer> {
	
	// 自訂方法使用 TSQL
	@Query(value = "select * from fsk001 order by fsk_03 asc", nativeQuery = true)
	public List<Stock> findAll_useTSQL();
	
	// 自訂方法使用 PSQL
	@Query(value = "select s from Stock s order by s.symbol asc")
	public List<Stock> findAll_usePSQL();
}
