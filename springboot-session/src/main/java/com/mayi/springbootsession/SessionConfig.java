package com.mayi.springbootsession;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 1800)
public class SessionConfig {

    @Value("${spring.redis.host}")
    String hostname;
    @Value("${spring.redis.port}")
    int port;
    @Value("${spring.redis.password}")
    String password;

    @Bean
    public JedisConnectionFactory connectionFactory(){
        JedisConnectionFactory connectionFactory = new JedisConnectionFactory();
        connectionFactory.setPort(port);
        connectionFactory.setHostName(hostname);
        connectionFactory.setPassword(password);
        return connectionFactory;
    }
}
