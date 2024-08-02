package org.hibernate;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.entity.Student;
import org.hibernate.util.HibernateUtil;


public class Main {

	public static void main(String[] args) {
		 HibernateUtil.getSessionFactory(); // Initialize the SessionFactory
	        
		Student student = new Student("Payal", "Chaudhari", "payal123@gmail.com");
        Student student1 = new Student("Ruchita", "Sonkule", "ruchita5@yahoo.com");
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession();) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student objects
            session.persist(student);
            session.persist(student1);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

        try (Session session = HibernateUtil.getSessionFactory().openSession();) {
        	List<Student> students = session.createQuery("from Student", Student.class).list();
            students.forEach(s -> System.out.println(s.getFirstName()+" "+ s.getLastName()+" "+ s.getEmail()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


	}


