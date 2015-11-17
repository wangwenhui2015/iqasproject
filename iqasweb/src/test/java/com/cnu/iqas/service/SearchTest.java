package com.cnu.iqas.service;

import java.net.URL;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

@ContextConfiguration(locations = {"/applicationContext.xml"})
public class SearchTest  extends AbstractTestNGSpringContextTests {

	@Test
	public void getFilePath(){
		/*URL path =SearchTest.class.getClassLoader().getResource("english-left3words-distsim.tagger");
		System.out.println("path:"+path.getPath());*/
		
	}
}
