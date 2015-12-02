package com.myshop.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

/**
 * Mybatis代码生成工具(自动生成实体类, mapper接口, mapper.xml文件)
 * @author Administrator
 *
 */
public class MybatisGeneratorUtil
{
	public static void main(String[] args) throws Exception
	{	
		List<String> warnings = new ArrayList<String>();
	    boolean overwrite = true;
	    File configFile = new File("config/mybatis-generator.xml");
	    ConfigurationParser cp = new ConfigurationParser(warnings);
	    Configuration config = cp.parseConfiguration(configFile);
	    DefaultShellCallback callback = new DefaultShellCallback(overwrite);
	    MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
	    myBatisGenerator.generate(null);
	}
}
