package com.HMS.functions;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.HMS.entity.Vehicle;

public class VehicleDetails {
	public VehicleDetails() {
		// TODO Auto-generated constructor stub
	}

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

	public int savevehicle(String curr_id, String vehicle_type, String vehicle_number, String arrival_date,
			String license_number, String reason, String status) {

		// TODO Auto-generated method stub

		Session s = getSession();
		try {
			Transaction tx = s.beginTransaction();
			CriteriaBuilder builder = s.getCriteriaBuilder();
			CriteriaQuery<Vehicle> query = builder.createQuery(Vehicle.class);
			Root<Vehicle> root = query.from(Vehicle.class);
			query.select(root).where(builder.equal(root.get("curr_id"), curr_id));
			Query<Vehicle> q = s.createQuery(query);
			List<Vehicle> results = q.getResultList();
			for (Vehicle c : results) {
				if (c.getStatus().equals("To_Be_approved")) {

					return 1;
				} else if (c.getStatus().equals("Approved")) {
					return 2;
				}
			}
			Vehicle stu = new Vehicle();
			stu.setCurr_id(curr_id);
			stu.setVehicle_type(vehicle_type);
			stu.setVehicle_number(vehicle_number);
			stu.setArrival_date(arrival_date);
			stu.setLicense_number(license_number);
			stu.setReason(reason);
			stu.setStatus(status);
			s.save(stu);
			tx.commit();
		} catch (HibernateException e) {
			System.out.println(e.getMessage());
			System.out.println("error");
		} finally {
			s.close();
		}

		return 0;

	}

}
