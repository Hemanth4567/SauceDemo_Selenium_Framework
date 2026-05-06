package com.sauceDemo.utils;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

	private static Properties properties;

	public Properties init_prop()
	{
		if(properties == null)
		{
			properties = new Properties();
			try {
				// This points to the file you just created
				FileInputStream fis = new FileInputStream("src/test/resources/config.properties");
				properties.load(fis);

			}catch(IOException e) {
				e.printStackTrace();
			}
		}
		return properties;
	}

	public String getBrowser()
	{
		return properties.getProperty("browser");
	}

	public String getUrl()
	{
		return properties.getProperty("url");
	}

}
