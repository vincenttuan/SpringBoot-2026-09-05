package com.example.demo.order;

import java.util.List;
import java.util.Optional;

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
		
		// 根據 name 查找
		Optional<Staff> optStuff = staffRepository.findByName("王小明");
		if(optStuff.isPresent()) {
			System.out.print("找到了 => ");
			Staff staff = optStuff.get();
			System.out.println(staff);
		} else {
			System.out.println("查無此人");
		}
		
		
	}
	
}
