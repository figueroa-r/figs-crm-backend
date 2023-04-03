package com.rfigueroa.figscrm.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import com.rfigueroa.figscrm.entity.Category;
import com.rfigueroa.figscrm.entity.Contact;
import com.rfigueroa.figscrm.entity.ContactDetail;
import com.rfigueroa.figscrm.entity.Customer;
import com.rfigueroa.figscrm.entity.Priority;
import com.rfigueroa.figscrm.entity.TicketInteraction;
import com.rfigueroa.figscrm.projections.ContactDetails;
import com.rfigueroa.figscrm.projections.ContactsList;
import com.rfigueroa.figscrm.projections.TicketDetails;
import com.rfigueroa.figscrm.projections.TicketIncludeIds;

@Configuration
public class RestConfiguration implements RepositoryRestConfigurer {
    
    @Override
    public void configureRepositoryRestConfiguration(
        RepositoryRestConfiguration config, CorsRegistry cors) {
            
            config.getProjectionConfiguration()
                        .addProjection(TicketIncludeIds.class)
                        .addProjection(TicketDetails.class)
                        .addProjection(ContactsList.class)
                        .addProjection(ContactDetails.class);


            config.exposeIdsFor(Customer.class);
            config.exposeIdsFor(Contact.class);
            config.exposeIdsFor(Category.class);
            config.exposeIdsFor(Priority.class);
            config.exposeIdsFor(TicketInteraction.class);
            config.exposeIdsFor(ContactDetail.class);
            


            cors.addMapping("/api/**")
                .allowedOrigins("http://localhost:3000", "https://master.d2b1tg1ojgscyw.amplifyapp.com")
                .allowedMethods("*")
                .allowedHeaders("*")
                .maxAge(3600);
        }
}
