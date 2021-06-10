package com.example.demo;

import com.demo.mapper.Accoutmapper;
import com.demo.pojo.Accoutuser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class DemoApplicationTests {
	@Autowired
	Accoutmapper accoutmapper;



	@Test
	void  cheshi(){
		int x=10;

	}

	//foreach 使用方法一  (list 中传输基本数据类型)
	@Test
	void contextLoads() {
		Accoutuser accoutuser=new Accoutuser();

		List<String> strings=new ArrayList<>();
		strings.add("hwxadmin");
		strings.add("z");

		List<Accoutuser> accoutuserList= accoutmapper.foreach(strings);
		for(Accoutuser accoutuser1 : accoutuserList){
			System.out.println(accoutuser1.toString());
		}

	}


	@Test
	void contextLoadstwo() {
		List<Accoutuser> strings=new ArrayList<>();
		Accoutuser accoutuser=new Accoutuser();
		accoutuser.setId("hwxadmin");
		strings.add(accoutuser);

		accoutuser=new Accoutuser();
		accoutuser.setId("z");
		strings.add(accoutuser);

		List<Accoutuser> accoutuserList= accoutmapper.foreachtwo(strings);
		for(Accoutuser accoutuser1 : accoutuserList){
			System.out.println(accoutuser1.toString());
		}

	}

}
