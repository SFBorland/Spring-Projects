package com.seanborland.service;

import java.util.List;

import com.seanborland.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.seanborland.repository.CustomerRepository;

//** Stereotyping: This annotation serves as a specialization of @Component, allowing for implementation classes to be 
//   auto-detected through classpath scanning. @ComponentScan({"com.pluralsight"}) in appConfig file?
@Service("customerService")
//** @Scope defaults to singleton but this is to show that you can change your scope, i.e, SCOPE.PROTOTYPE(opposite
// of singleton).
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class CustomerServiceImpl implements CustomerService {
    
    @Autowired
    private CustomerRepository customerRepository;
    
    //** Need a no args constructor to use setter injection.
    //public CustomerServiceImpl() {}
    
    //** Component scan will search entire package for a component w/ matching type, if it finds two, it will then match
    //   the param VARIABLE name (not the type), with the name specified by the @Component stereotype used. So if one of
    //   the impl class had @Repository("customerREPO") then param would be ...(CustomerRepository customerREPO) for
    //   that one.
    
    //** Component scan will search entire package for a component w/ matching type "CustomerRepository", which all of
    //   our "...impl" classes have as their super classes. If it finds two with the same type and you try to run the
    //   application, you will get an exception. Spring has no way of deciding which one to use. Now, if in each of the
    //   "...impl" classes you add a name to the Stereotype (i.e. @Respoistory("customerService")) it will match that
    //   name
    //   with the param variable name of the Autowired object. So if the param VARIABLE name (not type) matches the anno
    //   name it will match those. "public CustomerServiceImpl(TYPE: CustomerRepository VARIABLE: customerRepository)"
//	@Autowired 
//	//public CustomerServiceImpl(CustomerRepository customerRepository)
//	public CustomerServiceImpl(CustomerRepository seanRepository)
//	//public CustomerServiceImpl(CustomerRepository desireeRepository)
//	{
//		System.out.println("We are using constructor injection");
//		//this.customerRepository = customerRepository;
//		this.customerRepository = seanRepository;
//		//this.customerRepository = desireeRepository;
//	}
    
    //@Autowired
//	public void setCustomerRepository(CustomerRepository customerRepository)
//	{
//		System.out.println("We are using setter injection");
//		this.customerRepository = customerRepository;
//	}
    
    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }
}
