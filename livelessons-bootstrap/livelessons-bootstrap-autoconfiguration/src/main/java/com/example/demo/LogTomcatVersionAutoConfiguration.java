package com.example.demo;

import org.apache.catalina.startup.Tomcat;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
@ConditionalOnClass(value = Tomcat.class)
@ConditionalOnProperty(name = "log-tomcat-version", matchIfMissing = true)
public class LogTomcatVersionAutoConfiguration {
    private static Log logger = LogFactory.getLog(LogTomcatVersionAutoConfiguration.class);

    @PostConstruct
    public void logTomcatVersion() {
        logger.info("\n\n\nTomcat v"
                + Tomcat.class.getPackage().getImplementationVersion() + "\n\n"
        );
    }
}
