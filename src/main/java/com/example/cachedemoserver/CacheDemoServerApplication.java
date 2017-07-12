package com.example.cachedemoserver;

import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CacheDemoServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CacheDemoServerApplication.class, args);
	}

	@Bean
	Config config() {
		Config config = new Config();
		config.getGroupConfig().setName("cache-test");
		return config;
	}

	@Bean
	HazelcastInstance hazelcastInstance(Config config) {
		return Hazelcast.newHazelcastInstance(config);
	}
}
