package com.test.java.user.management.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "security")
public class JwtProperties {

    private String secretkey;
    private Long expirationtime;

}
