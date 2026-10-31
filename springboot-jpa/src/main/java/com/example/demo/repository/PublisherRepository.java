package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.book.Publisher;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Integer> {
	
	/* 自訂查詢方法(自訂原生 SQL)
	 * 操作對象: 資料表 Table
	 * 查詢欄位: 資料表欄位 Column
	 * 查詢語法: select *
	 * 必須加上 nativeQuery = true 設定
	 */
	@Query(value="select * from publisher order by id asc", nativeQuery = true)
	List<Publisher> findAll_useSQL();
	
	/* 自訂查詢方法(使用內建 JPQL)
	 * 操作對象: Entity
	 * 查詢欄位: Java 屬性
	 * 查詢語法: select p
	 */
	@Query(value = "select p from Publisher p order by p.id asc")
	List<Publisher> findAll_useJPQL();
	
}
