package de.jomora.imagegallery.backend.services;

import java.util.List;

import de.jomora.imagegallery.persistence.Authority;
import de.jomora.imagegallery.persistence.Customer;

public interface CustomerService {
	List<Customer> findAll();
	Customer addCustomer(Customer customer);
	Customer findByName(String name);
	Authority addAuthorityOfCustomer(Authority authority);
}
