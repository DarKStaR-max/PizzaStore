package com.pizzastore.customerservice;

    import org.springframework.boot.SpringApplication;
    import org.springframework.boot.autoconfigure.SpringBootApplication;
    import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
    import org.springframework.cloud.openfeign.EnableFeignClients;
    import springfox.documentation.swagger2.annotations.EnableSwagger2;

    @SpringBootApplication
    @EnableEurekaClient
    @EnableSwagger2
    @EnableFeignClients
    public class CustomerServiceApplication {
      public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
      }
    }