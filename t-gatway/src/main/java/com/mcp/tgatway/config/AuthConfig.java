package com.mcp.tgatway.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
@ConfigurationProperties(prefix = "getway.auth")
public class AuthConfig {

    /**
     * 白名单
     */
    private List<String> whiteList;

}
