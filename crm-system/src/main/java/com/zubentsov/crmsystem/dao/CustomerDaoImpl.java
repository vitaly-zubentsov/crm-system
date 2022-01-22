package com.zubentsov.crmsystem.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.zubentsov.crmsystem.entity.Customer;

@Repository
public class CustomerDaoImpl implements CustomerDao {

	// need to inject session factory
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Customer> getCustomers() {

		// get session from session factory
		Session session = sessionFactory.getCurrentSession();

		// set query

		Query<Customer> query = session.createQuery("from Customer order by lastName", Customer.class);

		// get list of customers from session
		List<Customer> customers = query.getResultList();

		return customers;

	}

	@Override
	public void saveOrUpdateCustomer(Customer customer) {

		Session session = sessionFactory.getCurrentSession();

		session.saveOrUpdate(customer);

	}

	@Override
	public Customer getCustomer(int customerId) {

		Session session = sessionFactory.getCurrentSession();

		return session.get(Customer.class, customerId);
	}

	@Override
	public void deleteCustomer(int customerId) {

		Session session = sessionFactory.getCurrentSession();

		Query query = session.createQuery("delete from Customer where id=:customerId");

		query.setParameter("customerId", customerId);

		query.executeUpdate();

	}

	@Override
	public List<Customer> searchCustomers(String searchName) {

		Session currentSession = sessionFactory.getCurrentSession();

		Query<Customer> query = null;

		// only search by name if theSearchName is not empty
		if (searchName != null && searchName.trim().length() > 0) {

			// search for firstName or lastName ... case insensitive
			query = currentSession.createQuery(
					"from Customer where lower(firstName) like :theName or lower(lastName) like :theName",
					Customer.class);
			query.setParameter("theName", "%" + searchName.toLowerCase() + "%");

		} else {
			
			// theSearchName is empty ... so just get all customers
			query = currentSession.createQuery("from Customer", Customer.class);
		}

		// execute query and return the results
		return query.getResultList();

	}

}
