package de.jomora.imagegallery.backend.services;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.jomora.imagegallery.persistence.Authority;
import de.jomora.imagegallery.persistence.AuthorityRepository;
import de.jomora.imagegallery.persistence.Customer;
import de.jomora.imagegallery.persistence.CustomerRepository;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {
	@Autowired
	private CustomerRepository customerRepo;
	@Autowired
	private AuthorityRepository authorityRepo;
	@Override
	public List<Customer> findAll() {
		return customerRepo.findAll();
	}

	@Override
	public Customer addCustomer(Customer customer) {
		checkNotNull(customer);
		Customer result = customerRepo.save(customer);
		return result;
	}

	@Override
	public Customer findByName(String name) {
		checkNotNull(name);
		Customer customer = customerRepo.findByUserName(name);
		return customer;
	}

	@Override
	public Authority addAuthorityOfCustomer(Authority authority) {
		return authorityRepo.save(authority);
	}

}
