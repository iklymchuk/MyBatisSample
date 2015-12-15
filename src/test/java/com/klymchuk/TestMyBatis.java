package com.klymchuk;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.klymchuk.domain.Agency;

public class TestMyBatis {
	
	private SqlSession session;

	@Before
	public void setup() throws IOException, SQLException {

	    String resource = "mybatis-config.xml";
	    InputStream inputStream = Resources.getResourceAsStream(resource);
	    SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder()
	            .build(inputStream);
	    
	    session = sqlSessionFactory.openSession();

	    seedData();
	    
	}

	private void seedData() {

	    int createTable = session.update("com.klymchuk.Agency.createTable");

	    int seedAgency = session.update("com.klymchuk.Agency.seedAgency");
	    
	    System.out.println(createTable);
	    System.out.println(seedAgency);
	}
	
	@Test
	public void test() {

	    Agency agency = session
	            .selectOne("com.klymchuk.Agency.selectAgency", 10);

	    System.out.println(agency);
	}
	
	@After
	public void tearDown() {
	    session.close();
	}

}
