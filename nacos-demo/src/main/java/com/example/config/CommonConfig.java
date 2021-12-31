package com.example.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author hollly
 * @date 2021/5/3 13:37
 */
@Configuration
@Data
public class CommonConfig {

    @Value("${common.version}")
    private String version;
}
