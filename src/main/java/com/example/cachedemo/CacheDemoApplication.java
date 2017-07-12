package com.example.cachedemo;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.core.HazelcastInstance;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.AbstractCacheResolver;
import org.springframework.cache.interceptor.CacheOperationInvocationContext;
import org.springframework.cache.interceptor.CacheResolver;
import org.springframework.cache.interceptor.SimpleCacheResolver;
import org.springframework.context.annotation.Bean;

import java.util.Collection;
import java.util.stream.Collectors;

@SpringBootApplication
@EnableCaching
public class CacheDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CacheDemoApplication.class, args);
	}

	@Bean
	ClientConfig clientConfig() {
		ClientConfig clientConfig = new ClientConfig();
		clientConfig.getGroupConfig().setName("gokhan");
		return clientConfig;
	}

	@Bean
	HazelcastInstance hazelcastInstance(ClientConfig clientConfig) {
		return HazelcastClient.newHazelcastClient(clientConfig);
	}

	@Bean
	CacheResolver prefixCacheResolver(CacheManager cacheManager) {
		return new PrefixCacheManager(cacheManager);
	}

	@Bean
	DummyCacheService dummyCacheService() {
		return new DummyCacheService();
	}

	@Bean
	CommandLineRunner commandLineRunner(DummyCacheService dummyCacheService) {
		return (args) ->  {
			System.out.println(dummyCacheService.getCityName(34));
			System.out.println(dummyCacheService.getCityName(34));
			System.out.println(dummyCacheService.getCityName(34));
		};
	}

	private static class PrefixCacheManager extends SimpleCacheResolver {

		@Value("${hazelcast.cache.prefix}")
		private String cachePrefix;

		private PrefixCacheManager(CacheManager cacheManager) {
			super(cacheManager);
		}

		@Override
		protected Collection<String> getCacheNames(CacheOperationInvocationContext<?> context) {
			return super.getCacheNames(context).stream()
					.map(cacheName -> cachePrefix + "." + cacheName)
					.collect(Collectors.toSet());
		}
	}
}
