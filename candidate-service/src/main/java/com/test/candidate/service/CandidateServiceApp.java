package com.test.candidate.service;

import com.test.candidate.persistence.PersistenceConfiguration;
import com.test.candidate.service.remote.IntakeGenerationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;
import org.springframework.remoting.rmi.RmiRegistryFactoryBean;
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

//    @Bean
//    public RmiRegistryFactoryBean startRMIService(){
//        RmiRegistryFactoryBean factoryBean = new RmiRegistryFactoryBean();
//
//        factoryBean.setHost("localhost");
//        factoryBean.setPort(5000);
//
//        return factoryBean;
//    }

//    @Bean
//    public RmiServiceExporter registerService(IntakeGenerationServiceImpl barService) {
//        RmiServiceExporter rmiServiceExporter = new RmiServiceExporter();
//        rmiServiceExporter.setServiceName(IntakeGenerationService.RMI_NAME);
//        rmiServiceExporter.setService(barService);
//        rmiServiceExporter.setServiceInterface(IntakeGenerationService.class);
//        rmiServiceExporter.setRegistryPort(5000);
//        return rmiServiceExporter;
//    }

//    @Bean
//    public IntakeGenerationService createIntakeGenerationServiceLink() {
//        RmiProxyFactoryBean rmiProxyFactoryBean = new RmiProxyFactoryBean();
//        rmiProxyFactoryBean.setServiceUrl("rmi://localhost:5000/" + IntakeGenerationService.RMI_NAME);
//        rmiProxyFactoryBean.setServiceInterface(IntakeGenerationService.class);
//        rmiProxyFactoryBean.afterPropertiesSet();
//        return (IntakeGenerationService) rmiProxyFactoryBean.getObject();
//        return new IntakeGenerationServiceImpl();
//    }
}
