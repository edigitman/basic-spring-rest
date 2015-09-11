package com.test.candidate.service;

import com.test.candidate.persistence.PersistenceConfiguration;
import com.test.candidate.service.remote.IntakeGenerationService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Import;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;
import org.springframework.remoting.rmi.RmiServiceExporter;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * Created by oleg on 07/08/15.
 */
@SpringBootApplication
@EnableAsync
@Import({PersistenceConfiguration.class})
public class CandidateServiceApp {
    public static void main(String[] args) {
        SpringApplication.run(CandidateServiceApp.class, args);
    }

    public static final int RMI_PORT = 5000;
    public static final String REMOTE_SERVER = "localhost";

    @Bean(name = "myRmiServiceExporter")
    public RmiServiceExporter registerService(IntakeGenerationServiceImpl barService) {
        RmiServiceExporter rmiServiceExporter = new RmiServiceExporter();
        rmiServiceExporter.setServiceName(IntakeGenerationService.RMI_NAME);
        rmiServiceExporter.setService(barService);
        rmiServiceExporter.setServiceInterface(IntakeGenerationService.class);
        rmiServiceExporter.setRegistryPort(RMI_PORT);
        return rmiServiceExporter;
    }

    @Bean(name = "rmiIntakeGenerationService")
    @DependsOn("myRmiServiceExporter")
    public IntakeGenerationService createIntakeGenerationServiceLink() {
        RmiProxyFactoryBean rmiProxyFactoryBean = new RmiProxyFactoryBean();
        rmiProxyFactoryBean.setServiceUrl("rmi://" + REMOTE_SERVER + ":" + RMI_PORT + "/" + IntakeGenerationService.RMI_NAME);
        rmiProxyFactoryBean.setServiceInterface(IntakeGenerationService.class);
        rmiProxyFactoryBean.afterPropertiesSet();
        return (IntakeGenerationService) rmiProxyFactoryBean.getObject();
    }
}
