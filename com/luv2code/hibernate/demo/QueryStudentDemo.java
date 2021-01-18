package com.luv2code.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class QueryStudentDemo {

	public static void main(String[] args) {
		
		SessionFactory factory = new Configuration()
								 .configure("hibernate.cfg.xml")
								 .addAnnotatedClass(Student.class)
								 .buildSessionFactory();
		
		Session session = factory.getCurrentSession();
		
		try {
			// Start transaction
			session.beginTransaction();
			
			// Query students
			List<Student> theStudents = session.createQuery("from Student").getResultList();
			
			// Display the students
			for (Student tempStudent : theStudents) {
				System.out.println(tempStudent);
			}
			
			// Find students where lastName = "Doe"
			theStudents = session.createQuery("from Student where lastName='Doe'").getResultList();
			
			System.out.println("\n\nStudents with last name of Doe");
			for(Student tempStudent : theStudents) {
				System.out.println(tempStudent);
			}
			
			// Find students where lastName = 'Doe' or firstName = 'Daffy'
			theStudents = session.createQuery("from Student s where s.lastName='Doe' OR s.firstName='Daffy'").getResultList();
			
			System.out.println("\n\nStudents who either have lastname = Doe OR firstname = Daffy");
			for(Student tempStudent : theStudents) {
				System.out.println(tempStudent);
			}
			
			// Find students where email ends with 'luv2code.com'
			theStudents = session.createQuery("from Student s where s.email LIKE '%luv2code.com'").getResultList();
			
			System.out.println("\n\nStudents who have email ending in luv2code.com");
			for(Student tempStudent : theStudents) {
				System.out.println(tempStudent);
			}
			
			// Commit transaction
			session.getTransaction().commit();
		}
		finally {
			factory.close();
		}
	}

}
