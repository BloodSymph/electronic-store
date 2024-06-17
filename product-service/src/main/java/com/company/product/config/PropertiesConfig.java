package com.company.product.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;



@Setter
@Getter
@Component
@ConfigurationProperties(prefix = "products.service.store")
public class PropertiesConfig {
    private String filePath;
}
