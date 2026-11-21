package com.example.demo.model;

import java.lang.annotation.ElementType;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * 訂單主檔
 * */

@Getter
@Setter
@Entity
@Table(name = "`order`") // 因為 order 是 sql 保留字, 所以要用 `` 來跳脫讓 mysql 區別 
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(comment = "訂單編號")
	private Integer id;
	
	/**
	 * 用字串儲存 OrderStatus 的內容
	 * 若 OrderStatus.PENDING_PAYMENT => 在 mysql 就用 "PENDING_PAYMENT" 字串存起來
	 * 若 OrderStatus.PAID            => 在 mysql 就用 "PAID" 字串存起來
	 * 若 OrderStatus.CANCELLED       => 在 mysql 就用 "CANCELLED" 字串存起來
	 * */
	@Enumerated(EnumType.STRING) 
	@Column(comment = "訂單狀態", nullable = false)
	private OrderStatus status = OrderStatus.PENDING_PAYMENT; // 預設值 = PENDING_PAYMENT(待付款)

	@Override
	public String toString() {
		return "Order [id=" + id + ", status=" + status + "]";
	}
	
	// -- 關聯配置 --------------------------------------------------------
	// mappedBy: 由 OrderItem.order 來維護關係
	// CascadeType.PERSIST: 新增訂單時連帶新增明細(聯集新增)
	// orphanRemoval = true: 移除關聯時將明細一併刪除
	@OneToMany(mappedBy = "order", cascade = CascadeType.PERSIST, orphanRemoval = true)
	private List<OrderItem> items = new ArrayList<>();
	
	// 新增入口: 保證雙向同步
	public void addItem(OrderItem item) {
		if(item == null) return;
		this.items.add(item);
		item.setOrder(this);
	}
	
	// 移除入口: : 保證雙向同步
	public void removeItem(OrderItem item) {
		if(item == null) return;
		this.items.remove(item);
		item.setOrder(null);
	}
	
	
}
