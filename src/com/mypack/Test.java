package com.mypack;

import java.io.InputStream;
import java.sql.Connection;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.session.TransactionIsolationLevel;

public class Test
{
	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception
	{
		String resource = "SqlMapConfig.xml";
		InputStream in = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);
		
		
		SqlSession sqlSession = sqlSessionFactory.openSession();
		Student stu = sqlSession.selectOne("test.findStuBySno", "sn1");
		System.out.println(stu.getSNO() + "," + stu.getSNAME() + "," + stu.getSSEX());
		sqlSession.close();
	}
}
