package com.wisehub.platform.data.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@ConfigurationProperties
@ComponentScan({ "com.wisehub.platform.data.producer.*" })
@EnableAutoConfiguration
public class DataProducerApplication {

	private static final Logger log = LoggerFactory.getLogger(DataProducerApplication.class);

	@Bean
	public ExitCodeGenerator exitCodeGenerator() {
		return new ExitCodeGenerator() {
			@Override
			public int getExitCode() {
				return 42;
			}
		};
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	public static void main(String args[]) {
		System.exit(SpringApplication.exit(SpringApplication.run(DataProducerApplication.class, args)));

	}

}
