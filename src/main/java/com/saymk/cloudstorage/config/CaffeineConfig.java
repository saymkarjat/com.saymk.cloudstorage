package com.saymk.cloudstorage.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
@EnableCaching
public class CaffeineConfig {

    @Bean
    public Caffeine<Object, Object> caffeineConfiguration() {
        return Caffeine.newBuilder()
                .initialCapacity(100)                           // начальная емкость
                .maximumSize(500)                               // максимальное количество записей в кэше
                .expireAfterAccess(10, TimeUnit.MINUTES) // время жизни записи
                .weakKeys()                                     // использование слабых ссылок для ключей
                .recordStats();                                 // включение статистики
    }

    @Bean
    public CacheManager cacheManager(Caffeine<Object, Object> caffeine) {
        CaffeineCacheManager caffeineCacheManager = new CaffeineCacheManager("default");
        caffeineCacheManager.setCaffeine(caffeine);
        return caffeineCacheManager;
    }
}
