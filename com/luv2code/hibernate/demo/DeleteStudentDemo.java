package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class DeleteStudentDemo {

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
			
			// Delete the student
			//System.out.println("Deleting student: " + myStudent);
			//session.delete(myStudent);
			
			// Better way of deleting
			System.out.println("Deleting student id=2");
			session.createQuery("delete from Student where id=2").executeUpdate();
			
			// Commit the transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
		}
		finally {
			factory.close();
		}
	}

}
