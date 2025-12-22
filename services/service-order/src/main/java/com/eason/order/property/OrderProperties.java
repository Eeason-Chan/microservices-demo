package com.eason.order.property;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@RefreshScope // 关键：必须放在配置属性类上
@ConfigurationProperties(prefix = "order")
@Data
public class OrderProperties {

    String timeout;
}
