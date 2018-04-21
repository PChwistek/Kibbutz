/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kibbutz;

import kibbutz.model.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application extends SpringBootServletInitializer {
    
    // private static final Logger log = LoggerFactory.getLogger(Application.class);
    
    @Autowired
    private PasswordHasher hasher;


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application){
        return application.sources(Application.class);
    }
    
    @Bean
    public CommandLineRunner demo(UserRepository repository) {
        return (args) -> {
                // save login
                User root = new User();
                root.setUsername("pchwis");
                root.setPassword(hasher.hashPassword("123"));
                repository.save(root);
                
                User root2 = new User();
                root2.setUsername("pchwis2");
                root2.setPassword(hasher.hashPassword("123"));
                repository.save(root2);
        };
    }

}