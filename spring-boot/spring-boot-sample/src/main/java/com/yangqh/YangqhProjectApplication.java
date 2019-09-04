package com.yangqh;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * @author yangqh
 * @date 2018-07-05
 */
@SpringBootApplication
@EnableMongoRepositories(basePackages = "com.yangqh.nosql.repository")
public class YangqhProjectApplication {

    public static void main(String[] args) {

        SpringApplication.run(YangqhProjectApplication.class, args);

    }

    @Bean
    public TomcatServletWebServerFactory servletContainer() { //springboot2 新变化

        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory() {

            @Override
            protected void postProcessContext(Context context) {

                SecurityConstraint securityConstraint = new SecurityConstraint();
                securityConstraint.setUserConstraint("CONFIDENTIAL");
                SecurityCollection collection = new SecurityCollection();
                collection.addPattern("/*");
                securityConstraint.addCollection(collection);
                context.addConstraint(securityConstraint);
            }
        };
        tomcat.addAdditionalTomcatConnectors(httpConnector());
        return tomcat;
    }

    @Bean
    public Connector httpConnector() {
        Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
        connector.setScheme("http");
        //Connector监听的http的端口号
        connector.setPort(1314);
        connector.setSecure(false);
        //监听到http的端口号后转向到的https的端口号
        connector.setRedirectPort(7443);
        return connector;
    }
}
