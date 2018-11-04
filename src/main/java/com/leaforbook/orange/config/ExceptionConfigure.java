package com.leaforbook.orange.config;

import com.leaforbook.orange.util.BasicExceptionHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class ExceptionConfigure {

    @Bean
    public BasicExceptionHandler exceptionHandler() {
        return new BasicExceptionHandler();
    }
}
