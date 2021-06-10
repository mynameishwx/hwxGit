package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;


@SpringBootTest
public class LbbApplicationTests {
	@Autowired
	JdbcTemplate jdbcTemplate;
	@Autowired
	DataSource dataSource;
	@Test
	void contextLoads() {
		Long l = jdbcTemplate.queryForObject("select count(*)  from  hwx_one",Long.class);
		System.out.print("有-》》》》》》"+l);
		System.out.print(dataSource.getClass());
	}
}
