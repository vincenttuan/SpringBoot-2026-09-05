package com.example.demo.order;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import com.example.demo.model.Staff;
import com.example.demo.repository.StaffRepository;

import jakarta.transaction.Transactional;

@SpringBootTest
public class Add_Staff {
	
	@Autowired
	private StaffRepository staffRepository;
	
	@Test
	@Transactional
	@Commit
	public void add() {
		Staff staff1 = new Staff();
		staff1.setId(98001);
		staff1.setName("王小明");
		
		Staff staff2 = new Staff();
		staff2.setId(98002);
		staff2.setName("林小美");
		
		staffRepository.save(staff1);
		staffRepository.save(staff2);
		
		System.out.println("staff add ok !");
	}
	
}
