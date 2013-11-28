/**
 * 
 */
package com.bliferniz.hibernate;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.bliferniz.dto.UserDetails;

public class HibernateTest {

	private static final SessionFactory factory = new Configuration().configure().buildSessionFactory();
	
	public static void main(String[] args) {

		getAndPrintUsers();
		System.out.println("############################################");
		getAndPrintUserNames();
		System.out.println("############################################");
		getAndPrintUserNamesAndIds();
		
	}

	private static void getAndPrintUserNamesAndIds() {
		
		Session session = factory.openSession();
		session.beginTransaction();
		
		Query query = session.createQuery("select new list(userId, userName) from UserDetails");
//		Query query = session.createQuery("select new map(userId, userName) from UserDetails");
		
		List<List<Object>> users = (List<List<Object>>) query.list();
		
		session.getTransaction().commit();
		session.close();

		System.out.println("\nSize of users list: " + users.size());
		System.out.println("=================================");
		
		for(List<Object> usersList : users){
			
			System.out.println();
			
			for(Object user : usersList){
				System.out.print(user + " ");
			}
		}
	}
	
	private static void getAndPrintUserNames() {
		
		Session session = factory.openSession();
		session.beginTransaction();
		
		Query query = session.createQuery("select userName from UserDetails");
		
		List<String> users = (List<String>) query.list();
		
		session.getTransaction().commit();
		session.close();

		System.out.println("\nSize of users list: " + users.size());
		System.out.println("=================================");
		
		for(String user : users){
			System.out.println(user);
		}
	}
	
	private static void getAndPrintUsers() {
		
		Session session = factory.openSession();
		session.beginTransaction();
		
		Query query = session.createQuery("from UserDetails");
		
		//Filters
		query.setFirstResult(5); //omit the first 5 elements
		query.setMaxResults(1); // Max amount of result elements.
		
		List<UserDetails> users = (List<UserDetails>) query.list();
		
		session.getTransaction().commit();
		session.close();

		System.out.println("\nSize of users list: " + users.size());
		System.out.println("=================================");
		
		for(UserDetails user : users){
			System.out.println(user.getUserId() + " : " + user.getUserName());
		}
	}
	
}
