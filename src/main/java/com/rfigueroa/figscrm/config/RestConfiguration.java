package com.rfigueroa.figscrm.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import com.rfigueroa.figscrm.entity.Contact;
import com.rfigueroa.figscrm.entity.Customer;
import com.rfigueroa.figscrm.projections.ContactsList;
import com.rfigueroa.figscrm.projections.TicketIncludeIds;

@Configuration
public class RestConfiguration implements RepositoryRestConfigurer {
    
    @Override
    public void configureRepositoryRestConfiguration(
        RepositoryRestConfiguration config, CorsRegistry cors) {
            
            config.getProjectionConfiguration()
                        .addProjection(TicketIncludeIds.class)
                        .addProjection(ContactsList.class);


            config.exposeIdsFor(Customer.class);
            config.exposeIdsFor(Contact.class);
            


            cors.addMapping("/api/**")
                .allowedOrigins("http://localhost:3000")
                .allowedMethods("*")
                .allowedHeaders("*")
                .maxAge(3600);
        }
}
