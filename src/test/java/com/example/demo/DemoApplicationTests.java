package com.example.demo;

import com.example.demo.mapper.DemoMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

	@Autowired
	private DemoMapper demoMapper;

	@Test
	public void testDemoMapper() {
		demoMapper.insert("abcd", "liuyanfu");
	}

}
