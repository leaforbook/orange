package com.leaforbook.orange.config;

import com.leaforbook.orange.util.SnowFlake;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@Slf4j
public class SnowFlakeConfigure {

    @Value("${common.snow.flak.datacenter-id}")
    private long datacenterId;

    @Value("${common.snow.flak.machine-id}")
    private long machineId;

    @Bean
    public SnowFlake getSnowFlake() {
        SnowFlake sf = null;
        try {
            sf = new SnowFlake(datacenterId,machineId);
        } catch (Throwable e) {
            sf = new SnowFlake(0l,0l);
        }

        return sf;
    }

}
