package org.docryze.dubboui.config;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author docryze
 */
@Configuration
public class DubboUiConfig {
    @Value("${dubbo.ui.zk_connect_str:}")
    private String zkConnectStr;
    @Value("${spring.application.name}")
    private String applicationName;

    @Bean("curatorFramework")
    public CuratorFramework curatorFramework(){
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework client = CuratorFrameworkFactory.newClient(zkConnectStr, retryPolicy);
        client.start();

        return client;
    }

    @Bean("applicationConfig")
    public ApplicationConfig applicationConfig(RegistryConfig registryConfig){
        ApplicationConfig application = new ApplicationConfig();
        application.setName(applicationName);
        application.setRegistry(registryConfig);
        return application;
    }

    @Bean("registryConfig")
    public RegistryConfig registryConfig(){
        // 连接注册中心配置
        RegistryConfig registry = new RegistryConfig();
        registry.setAddress(zkConnectStr);
        return registry;
    }

}
