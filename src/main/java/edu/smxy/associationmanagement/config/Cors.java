package edu.smxy.associationmanagement.config;

import org.springframework.web.filter.*;
import org.springframework.web.cors.*;
import org.springframework.context.annotation.*;

@Configuration
public class Cors
{
    private CorsConfiguration buildConfig() {
        final CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("*");
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.addAllowedMethod("*");
        corsConfiguration.setAllowCredentials(true);
        return corsConfiguration;
    }
    
    @Bean
    public CorsFilter corsFilter() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", this.buildConfig());
        return new CorsFilter((CorsConfigurationSource)source);
    }
}
