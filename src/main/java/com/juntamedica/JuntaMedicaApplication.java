package com.juntamedica;

import net.javacrumbs.shedlock.spring.annotation.EnableSchedulerLock;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EntityScan
@ComponentScan
@EnableFeignClients
@EnableJpaRepositories
@EnableRabbit
@EnableScheduling
@EnableSchedulerLock(defaultLockAtMostFor = "PT30S")
public class JuntaMedicaApplication {

	public static void main(String[] args) {
		SpringApplication.run(JuntaMedicaApplication.class, args);
	}
}
