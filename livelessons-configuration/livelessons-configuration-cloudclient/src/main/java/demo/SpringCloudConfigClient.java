package demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class SpringCloudConfigClient {
    public static void main(String[] args) {
        SpringApplication.run(SpringCloudConfigClient.class, args);
    }

    @Autowired
    void setEnvironment(Environment environment) {
        System.out.println(environment.getProperty("configuration.projectName"));
    }
}
