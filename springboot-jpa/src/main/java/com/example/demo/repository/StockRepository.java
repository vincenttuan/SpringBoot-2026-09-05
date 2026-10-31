package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

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
	
	
	// 自訂方法使用 TSQL
	@Query(value = "select * from fsk001 where fsk_03 = :symbol", nativeQuery = true)
	public Optional<Stock> findBySymbol_useTSQL(String symbol);
	
	// 自訂方法使用 PSQL
	@Query(value = "select s from Stock s where s.symbol = :symbol")
	public Optional<Stock> findBySymbol_usePSQL(String symbol);
	
	// JPA Query Derivation (方法名稱衍生查詢)
	// 會讓 Spring 自動去尋找 Stock 實體(Entity) 中名稱為 symbol 的屬性
	Optional<Stock> findBySymbol(String symbol);
}
