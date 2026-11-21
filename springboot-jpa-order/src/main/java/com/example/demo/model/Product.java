package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * 商品檔 Product Entity
 * 對應 product Table(資料表)
 * */
@Getter
@Setter
@Entity(name = "Product")
@Table(name = "product")
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(comment = "商品編號")
	private Integer id;
	
	@Column(comment = "商品名稱", nullable = false, length = 50)
	private String name;
	
	@Column(comment = "商品單價（元）", nullable = false)
	private Integer price;
	
	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", price=" + price + "]";
	}
	
}
