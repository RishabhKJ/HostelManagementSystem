package com.HMS.functions;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import com.HMS.entity.*;

public class CoolerDetails {
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

	public int savecooler(String curr_id, String cooler_type, String company, String arrival_date, String depature_date,
			String reason, String status) {
		Session s = getSession();

		try {
			Date date1 = new SimpleDateFormat("MM/dd/yyyy").parse(arrival_date);
			Date date2 = new SimpleDateFormat("MM/dd/yyyy").parse(depature_date);
			if (date1.compareTo(date2) > 0) {

				// When Date d1 > Date d2
				return 3;
			}
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			Transaction tx = s.beginTransaction();
			CriteriaBuilder builder = s.getCriteriaBuilder();
			CriteriaQuery<Cooler> query = builder.createQuery(Cooler.class);
			Root<Cooler> root = query.from(Cooler.class);
			query.select(root).where(builder.equal(root.get("curr_id"), curr_id));
			Query<Cooler> q = s.createQuery(query);
			List<Cooler> results = q.getResultList();
			for (Cooler c : results) {
				if (c.getStatus().equals("To_Be_approved")) {

					return 1;
				} else if (c.getStatus().equals("Approved")) {
					return 2;
				}
			}
			Cooler stu = new Cooler();
			stu.setCurr_id(curr_id);
			stu.setCooler_type(cooler_type);
			stu.setCompany(company);
			stu.setArrival_date(arrival_date);
			stu.setDepature_date(depature_date);
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