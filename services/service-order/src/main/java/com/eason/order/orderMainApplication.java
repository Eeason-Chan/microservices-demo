package com.eason.order;

import com.alibaba.cloud.nacos.NacosConfigManager;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


@EnableFeignClients
@SpringBootApplication
@EnableDiscoveryClient
public class orderMainApplication {
    public static void main(String[] args) {
        SpringApplication.run(orderMainApplication.class,args);
    }

    /*@Bean
    ApplicationRunner runner(NacosConfigManager nacosConfigManager){
        return args -> {
            ConfigService configService = nacosConfigManager.getConfigService();
            configService.addListener("service-order.properties", "DEFAULT_GROUP",
                    new Listener() {
                        @Override
                        public Executor getExecutor() {

                            return Executors.newFixedThreadPool(1);
                        }

                        @Override
                        public void receiveConfigInfo(String s) {
                            System.out.println(s);
                        }
                    });
            System.out.println("启动成功");
        };
    }*/
}
