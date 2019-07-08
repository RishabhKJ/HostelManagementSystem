package com.HMS.functions;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.HMS.mail.Sendmail;
import com.HMS.entity.Vehicle;

public class VehicleRequests {

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

	public List<Vehicle> get_tobeapproved() {

		Session s = getSession();
		List<Vehicle> results = new ArrayList<Vehicle>();
		try {
			Transaction tx = s.beginTransaction();
			CriteriaBuilder builder = s.getCriteriaBuilder();
			CriteriaQuery<Vehicle> query = builder.createQuery(Vehicle.class);
			Root<Vehicle> root = query.from(Vehicle.class);
			query.select(root).where(builder.equal(root.get("status"), "To_Be_approved"));

			Query<Vehicle> q = s.createQuery(query);
			results = q.getResultList();

			for (Vehicle c : results) {
				System.out.println(c.getCurr_id());
			}

			tx.commit();
			return results;
		} catch (HibernateException e) {
			System.out.println(e.getMessage());
			System.out.println("error");
		} finally {
			s.close();
		}
		return results;

	}

	public void confirm_cooler(String req_id) {
		Session s = getSession();
		try {
			Transaction tx = s.beginTransaction();
			CriteriaBuilder builder = s.getCriteriaBuilder();
			CriteriaUpdate<Vehicle> update = builder.createCriteriaUpdate(Vehicle.class);
			@SuppressWarnings("rawtypes")
			Root e = update.from(Vehicle.class);
			update.set("status", "Approved");
			update.where(builder.equal(e.get("curr_id"), req_id));
			s.createQuery(update).executeUpdate();
			tx.commit();
			String to = req_id + "@daiict.ac.in";
			String sub = "Vehicle Registration Confirmation";
			String msg = "Dear Student, we have approved your vehicle registration.";
			String user = "sri.hms.2019@gmail.com";
			String pass = "hellosri";
			Sendmail.send(to, sub, msg, user, pass);
		} catch (HibernateException e) {
			System.out.println(e.getMessage());
			System.out.println("error");
		} finally {
			s.close();
		}

	}

	public void deny_cooler(String req_id) {
		// TODO Auto-generated method stub
		Session s = getSession();
		try {
			Transaction tx = s.beginTransaction();
			CriteriaBuilder builder = s.getCriteriaBuilder();
			CriteriaUpdate<Vehicle> update = builder.createCriteriaUpdate(Vehicle.class);
			@SuppressWarnings("rawtypes")
			Root e = update.from(Vehicle.class);
			update.set("status", "Rejected");
			update.where(builder.equal(e.get("curr_id"), req_id));
			s.createQuery(update).executeUpdate();
			tx.commit();
			String to = req_id + "@daiict.ac.in";
			String sub = "Vehicle Registration Rejection";
			String msg = "Dear Student, we have rejected your request for vehicle registration.";
			String user = "sri.hms.2019@gmail.com";
			String pass = "hellosri";
			Sendmail.send(to, sub, msg, user, pass);
		} catch (HibernateException e) {
			System.out.println(e.getMessage());
			System.out.println("error");
		} finally {
			s.close();
		}

	}

}
