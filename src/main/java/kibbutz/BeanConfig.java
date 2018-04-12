/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kibbutz;

import javax.persistence.EntityManagerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.vendor.HibernateJpaSessionFactoryBean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 *
 * @author Phil
 */

@Configuration
@EnableWebMvc
@EnableScheduling
public class BeanConfig {
    
    @Bean
    public PasswordHasher passwordHasher(){
        return new PasswordHasher();
    }
    
    @Bean
    public HibernateJpaSessionFactoryBean sessionFactory(EntityManagerFactory emf) {
         HibernateJpaSessionFactoryBean factory = new HibernateJpaSessionFactoryBean();
         factory.setEntityManagerFactory(emf);
         return factory;
    }
   
    
}
