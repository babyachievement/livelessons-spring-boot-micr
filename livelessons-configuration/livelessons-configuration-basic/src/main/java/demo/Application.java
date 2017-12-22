package demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@SpringBootApplication
@EnableConfigurationProperties
public class Application {
    @Value("${configuration.projectName}")
    void setProjectName(String projectName) {
        System.out.println("Setting project name: " + projectName);
    }

    @Autowired
    void setEnvironment(Environment environment) {
        System.out.println("Setting environment: " + environment.getProperty("configuration.projectName"));
    }

    @Autowired
    void setConfigurationProjectProperties(ConfigurationProjectProperties cp) {
        System.out.println("configurationProjectProperties.projectName: " + cp.getProjectName());
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

    @Component
    @ConfigurationProperties("configuration")
    private class ConfigurationProjectProperties {
        private String projectName;

        public String getProjectName() {
            return projectName;
        }

        public void setProjectName(String projectName) {
            this.projectName = projectName;
        }
    }
}
