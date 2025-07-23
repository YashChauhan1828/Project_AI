package com.Config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.BasicPolymorphicTypeValidator;

import java.time.Duration;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.cache.*;
import org.springframework.data.redis.serializer.*;
import org.springframework.data.redis.serializer.RedisSerializationContext.SerializationPair;

@Configuration
@EnableCaching
public class RedisCacheConfig {

	@Bean
	public RedisCacheConfiguration cacheConfiguration() {
	    ObjectMapper mapper = new ObjectMapper();
	    mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
	    
	    mapper.activateDefaultTyping(
	        BasicPolymorphicTypeValidator.builder()
	            .allowIfSubType(Object.class)
	            .build(),
	        ObjectMapper.DefaultTyping.EVERYTHING  // use EVERYTHING instead of NON_FINAL
	    );

	    GenericJackson2JsonRedisSerializer serializer = new GenericJackson2JsonRedisSerializer(mapper);

	    return RedisCacheConfiguration.defaultCacheConfig()
	        .entryTtl(Duration.ofMinutes(30))
	        .serializeValuesWith(
	            RedisSerializationContext.SerializationPair.fromSerializer(serializer)
	        );
	}
}


