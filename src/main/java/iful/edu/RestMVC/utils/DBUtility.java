package iful.edu.RestMVC.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class DBUtility {

	public static DriverManagerDataSource getDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		Properties prop = new Properties();
		InputStream inputStream = DBUtility.class.getClassLoader().getResourceAsStream("/config.properties");
		try {
			prop.load(inputStream);
			String driver = prop.getProperty("driver");
			String url = prop.getProperty("url");
			String user = prop.getProperty("user");
			String password = prop.getProperty("password");
			dataSource.setDriverClassName(driver);
			dataSource.setUrl(url);
			dataSource.setUsername(user);
			dataSource.setPassword(password);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return dataSource;
	}
}
