package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * 處理人員檔 Staff Entity
 * 對應 staff Table(資料表)
 * 一位人員可以處理多張訂單, 一張訂單也可以多人處理, 形成多對多關聯
 * */

@Getter
@Setter
@Entity // 相當於 @Entity(name = "Staff")
@Table  // 相當於 @Table(name = "staff")
public class Staff {

	@Id
	@Column(comment = "人員編號")
	private Integer id;
	
	@Column(comment = "人員姓名", nullable = false, length = 50)
	private String name;
	
}
