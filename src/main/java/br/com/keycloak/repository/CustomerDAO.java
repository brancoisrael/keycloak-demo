package br.com.keycloak.repository;


import org.springframework.data.repository.CrudRepository;

import br.com.keycloak.domain.Customer;

public interface CustomerDAO extends CrudRepository<Customer, Long> {

}