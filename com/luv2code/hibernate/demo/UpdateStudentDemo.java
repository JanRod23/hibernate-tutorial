package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class UpdateStudentDemo {

	public static void main(String[] args) {
		
		SessionFactory factory = new Configuration()
								 .configure("hibernate.cfg.xml")
								 .addAnnotatedClass(Student.class)
								 .buildSessionFactory();
		
		Session session = factory.getCurrentSession();
		
		try {
			int studentId = 1;
			
			// Now get a new session and start a transaction
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			// Retrieve student based on the ID (primary key)
			System.out.println("\nGetting student with ID: " + studentId);
			
			Student myStudent = session.get(Student.class, studentId);
			
			System.out.println("Updating student...");
			myStudent.setFirstName("Scooby");
			
			// Commit the transaction
			session.getTransaction().commit();
			
			
			/** New Code **/
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			// Update the email for all students
			System.out.println("Update all student emails to foo@gmail.com");
			session.createQuery("Update Student set email='foo@gmail.com'").executeUpdate();
			
			// Commit the transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
		}
		finally {
			factory.close();
		}
	}

}
