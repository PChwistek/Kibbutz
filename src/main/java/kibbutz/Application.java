/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kibbutz;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {
    
     private static final Logger log = LoggerFactory.getLogger(Application.class);


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    
     @Bean
    public CommandLineRunner demo(UserRepository repository) {
        return (args) -> {
                // save a couple of customers
                // repository.save(new AddressEntity("Phil", "617 Bellfower Blvd", "PA", "18976"));
             
                // fetch all customers
                /*
                log.info("Customers found with findAll():");
                log.info("-------------------------------");
                for (User user : repository.findAll()) {
                        log.info(user.toString());
                }
                log.info("");

                // fetch an individual customer by ID
                User user = repository.findOne(1L);
                log.info("Customer found with findOne(1L):");
                log.info("--------------------------------");
                log.info(user.toString());
                log.info("");

                // fetch customers by last name
                log.info("Customer found with findByLastName('Bauer'):");
                log.info("--------------------------------------------");
                for (User user : repository.findByUsername("Phil")) {
                        log.info(bauer.toString());
                }
                log.info("");
                
                */
        };
    }

}