
package com.HMS.functions;

import org.hibernate.query.Query;

import com.HMS.entity.Student;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class StudentCredentials {
	private static SessionFactory sessionFactory;

	private Session getSession() {
		Session s = null;
		try {
			sessionFactory = new Configuration().configure("com\\HMS\\xml\\hibernate.cfg.xml").buildSessionFactory();
			s = sessionFactory.openSession();
		} catch (HibernateException e) {
			System.out.println(e.getMessage());
		}
		return s;
	}

	public boolean CheckStudent(String stuid, String stupassword) {
		Session s = getSession();
		try {

			Transaction tx = s.beginTransaction();

//          
			CriteriaBuilder builder = s.getCriteriaBuilder();
			CriteriaQuery<Student> query = builder.createQuery(Student.class);
			Root<Student> root = query.from(Student.class);
			query.select(root).where(builder.equal(root.get("stuid"), stuid),
					builder.equal(root.get("stupassword"), stupassword));
			Query<Student> q = s.createQuery(query);

			Student stu = (Student) q.uniqueResult();
			if (stu != null) {
				System.out.println("record found:");
				tx.commit();
				return true;
			} else {
				System.out.println("record not found:");
				tx.commit();
				return false;
			}

		} catch (HibernateException e) {
			System.out.println(e.getMessage());
			System.out.println("error");
		} finally {
			s.close();
		}

		return false;

	}

}