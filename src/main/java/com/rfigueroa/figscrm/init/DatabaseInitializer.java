package com.rfigueroa.figscrm.init;


import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import com.github.javafaker.Faker;
import com.rfigueroa.figscrm.dao.CustomerRepository;
import com.rfigueroa.figscrm.entity.Category;
import com.rfigueroa.figscrm.entity.Contact;
import com.rfigueroa.figscrm.entity.ContactDetail;
import com.rfigueroa.figscrm.entity.Customer;
import com.rfigueroa.figscrm.entity.Priority;
import com.rfigueroa.figscrm.entity.Ticket;
import com.rfigueroa.figscrm.entity.TicketInteraction;
import com.rfigueroa.figscrm.enums.Variant;




@Component
public class DatabaseInitializer {

    // this bean is used to initialize our DB with java faker values
    private CustomerRepository customerRepository;


    public DatabaseInitializer(CustomerRepository theCustomerRepository) {
        customerRepository = theCustomerRepository;
    }
    
    @PostConstruct
    @Transactional
    public void initialize() {

        Faker faker = new Faker();

        List<Customer> entitiesToSave = new ArrayList<>();

        // generate application settings to be used on tickets...
        List<Category> categories = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            Category category = new Category();

            category.setName(faker.company().industry());
            category.setVariant(Variant.randomVariant());

            categories.add(category);
        }

        List<Priority> priorities = new ArrayList<>();

        Priority low = new Priority();
        low.setName("LOW");
        low.setVariant(Variant.INFO);
        priorities.add(low);

        Priority med = new Priority();
        med.setName("MEDIUM");
        med.setVariant(Variant.SUCCESS);
        priorities.add(med);

        Priority high = new Priority();
        high.setName("HIGH");
        high.setVariant(Variant.WARNING);
        priorities.add(high);

        Priority critical = new Priority();
        critical.setName("CRITICAL");
        critical.setVariant(Variant.ERROR);
        priorities.add(critical);

        // create parent entities first, followed by children entities
        // utilize convenience methods in nested loops to establish relationship

        for (int i = 0; i < 30; i++) {

            Customer customer = new Customer();

            // use Setters to assign fields to faker data
            customer.setAvatarUrl(faker.company().logo());
            customer.setName(faker.company().name());
            customer.setCompanyType(faker.company().industry());
            customer.setAddress1(faker.address().streetAddress());
            customer.setAddress2(faker.address().secondaryAddress());
            customer.setCity(faker.address().city());
            customer.setState(faker.address().stateAbbr());
            customer.setZip(faker.address().zipCode());

            int numOfEntries = faker.number().numberBetween(5, 10);

            for (int j = 0; j < numOfEntries; j++) {
                Contact contact = new Contact();

                contact.setFirstName(faker.name().firstName());
                contact.setLastName(faker.name().lastName());
                contact.setTitle(faker.job().title());
                contact.setDepartment(faker.job().field());
                contact.setActive(faker.bool().bool());
                contact.setAvatarId(faker.number().numberBetween(1, 25));

                ContactDetail phone = new ContactDetail();
                phone.setContactType("Phone");
                phone.setContactDetail(faker.phoneNumber().cellPhone());

                ContactDetail email = new ContactDetail();
                email.setContactType("Email");
                email.setContactDetail(faker.internet().emailAddress(contact.getFirstName() + '-' + contact.getLastName()));

                contact.addContactDetail(phone);
                contact.addContactDetail(email);


                customer.addContact(contact);

            }

            for (int j = 0; j < numOfEntries; j++) {
                Ticket ticket = new Ticket();

                ticket.setIsOpen(faker.bool().bool());
                ticket.setCreationDate(faker.date()
                                            .past(1095, TimeUnit.DAYS)
                                            .toInstant()
                                            .atZone(ZoneId.systemDefault())
                                            .toLocalDate());
                ticket.setTicketNotes(faker.lorem().paragraph(3));
                ticket.setPrimaryContact(customer.getContacts().get(j));
                ticket.setCategory(categories.get(faker.number().numberBetween(0, 5)));
                ticket.setPriority(priorities.get(faker.number().numberBetween(0, 4)));


                for (int k = 0; k < 5; k++) {
                    TicketInteraction interaction = new TicketInteraction();

                    interaction.setUser("Guest User");

                    Date past = Date.from(ticket.getCreationDate().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
                    Date now = new Date();
                    interaction.setInteractionDate(faker.date()
                                                        .between(past, now));
                    
                    interaction.setInteractionDetails(faker.lorem().sentence());

                    ticket.addInteraction(interaction);
                }

                customer.addTicket(ticket);

            }


            entitiesToSave.add(customer);
        }

        customerRepository.saveAll(entitiesToSave);
    }
    
}
