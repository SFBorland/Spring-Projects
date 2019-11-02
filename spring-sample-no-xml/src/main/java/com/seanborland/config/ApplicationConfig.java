package com.seanborland.config;
//import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
//import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

//import com.pluralsight.repository.CustomerRepository;
//import com.pluralsight.repository.HibernateCustomerRepositoryImpl;
//import com.pluralsight.service.CustomerService;
//import com.pluralsight.service.CustomerServiceImpl;

//** This makes this a config file, similar to xml. It tells Spring to look for any configuration information in 
//   this file.
@Configuration
//** This will tell Spring to look for Annotations in the specified package. Typically you will put multiple 
//   config files in a single config package.
@ComponentScan({"com.seanborland"})
@PropertySource("app.properties")
public class ApplicationConfig {
    //** This takes all the properties in the app.properties class, brings them here and makes them available in the
    //   context for our app to use.
//	@Bean
//	public static PropertySourcesPlaceholderConfigurer getPropertySourcePlaceholserConfigurer()
//	{
//		return new PropertySourcesPlaceholderConfigurer();
//	}
    //** This is where we are implementing an instance of the concrete class but returning the interface.
    //** This Bean is returning the service which is initialized w/ some CustomerRepository object. The class
    //   Application.java's appContext.getBean() method, gets this bean and puts it in a CustomerService object.
    //   Then you can call the classes "findAll()" method.
    
    //** Can also comment this out because we have the @Service Annotation in CustomerServiceImp class.
//	@Bean(name = "customerService")
//	public CustomerService getCustomerService()
//	{
//		CustomerServiceImpl service = new CustomerServiceImpl();		
//		//CustomerServiceImpl service = new CustomerServiceImpl(getCustomerRepository());
//		
//		//***** Would need this if you didn't Autowire customerRepository in CustomerServiceImpl *******//
//		//service.setCustomerRepository(getCustomerRepository());
//		return service;
//	}
    
    //** We were able to get rid of this bean because we Annotated the HibernateCustomerRepositoryImpl w/
    //   "@Repository" it's being discovered w/ the @ComponentScan above.
//	@Bean(name = "customerRepository")
//	public CustomerRepository getCustomerRepository()
//	{
//		return new HibernateCustomerRepositoryImpl();
//	}
}
