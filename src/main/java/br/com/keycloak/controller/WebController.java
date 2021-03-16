package br.com.keycloak.controller;

import java.security.Principal;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import br.com.keycloak.domain.Customer;
import br.com.keycloak.repository.CustomerDAO;

@Controller
public class WebController {

    @Autowired
    private CustomerDAO customerDAO;

    @GetMapping(path = "/")
    public String index() {
        return "external";
    }

    @GetMapping(path = "/customers")
    public String clientes(Principal principal, Model model) {
        if(customerDAO.count()==0) {
        	addClientes();
        }
        Iterable<Customer> customers = customerDAO.findAll();
        model.addAttribute("customers", customers);
        model.addAttribute("username", principal.getName());
        return "customers";
    }

    // add customers for demonstration
    public void addClientes() {
    	Random random = new Random();
    	
    	for(int i=0; i <10; i++) {
    		Customer customer = new Customer();
            customer.setAddress(String.format("Rua teste número %s", random.nextInt(100 - 1) + 1));
            customer.setName(String.format("Empresa %s", random.nextInt(100 - 1) + 1));
            customer.setServiceRendered(String.format("Serviço número %s", random.nextInt(100 - 1) + 1));
            customerDAO.save(customer);
    	}
    }
}