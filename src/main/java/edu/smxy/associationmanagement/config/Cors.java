package edu.smxy.associationmanagement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * Cors跨域问题解决Config
 */
@Configuration
public class Cors {
    private CorsConfiguration buildConfig() {
        final CorsConfiguration corsConfiguration = new CorsConfiguration();
        // 允许所有Origin
        corsConfiguration.addAllowedOrigin("*");
        // 允许所有Header
        corsConfiguration.addAllowedHeader("*");
        // 允许所有方法
        corsConfiguration.addAllowedMethod("*");
        corsConfiguration.setAllowCredentials(true);
        return corsConfiguration;
    }

    @Bean
    public CorsFilter corsFilter() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", this.buildConfig());
        return new CorsFilter(source);
    }
}
