package com.zlikun.open;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@SpringBootApplication
@PropertySources({
		@PropertySource("http://conf.zlikun.com/oschina.net.properties")
})
public class OschinaApplication {

	public static void main(String[] args) {
		SpringApplication.run(OschinaApplication.class, args);
	}

}
