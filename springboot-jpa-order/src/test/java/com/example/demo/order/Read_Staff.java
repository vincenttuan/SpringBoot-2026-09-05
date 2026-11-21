package com.example.demo.order;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.model.Staff;
import com.example.demo.repository.StaffRepository;

@SpringBootTest
public class Read_Staff {
	
	@Autowired
	private StaffRepository staffRepository;
	
	
	@Test
	public void read() {
		// 查找全部
		List<Staff> staffs = staffRepository.findAll();
		staffs.forEach(System.out::println);
		
		
	}
	
}
