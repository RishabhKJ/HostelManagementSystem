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

import com.HMS.entity.Room;

public class RoomDetails {

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

	public int getHorMen() {
		Session s = getSession();
		int val = 0;
		try {
			Transaction tx = s.beginTransaction();
			CriteriaBuilder builder = s.getCriteriaBuilder();
			CriteriaQuery<Room> query = builder.createQuery(Room.class);
			Root<Room> root = query.from(Room.class);
			query.select(root).where(builder.equal(root.get("hor"), "men"));
			Query<Room> q = s.createQuery(query);
			List<Room> results = q.getResultList();
			int itemCount = results.size();
			val = itemCount;
			tx.commit();
		} catch (HibernateException e) {
			System.out.println(e.getMessage());
			System.out.println("error");
		} finally {
			s.close();
		}
		return val;
	}

	public int getHorWomen() {
		// TODO Auto-generated method stub
		Session s = getSession();
		int val = 0;
		try {
			Transaction tx = s.beginTransaction();
			CriteriaBuilder builder = s.getCriteriaBuilder();
			CriteriaQuery<Room> query = builder.createQuery(Room.class);
			Root<Room> root = query.from(Room.class);
			query.select(root).where(builder.equal(root.get("hor"), "women"));
			Query<Room> q = s.createQuery(query);
			List<Room> results = q.getResultList();
			int itemCount = results.size();
			val = itemCount;
			tx.commit();
		} catch (HibernateException e) {
			System.out.println(e.getMessage());
			System.out.println("error");
		} finally {
			s.close();
		}
		return val;
	}

	public void addFees(String hor_type_hor, float finalbill) {
		// TODO Auto-generated method stub
		Session s = getSession();
		if (hor_type_hor.equals("hor_men"))
			hor_type_hor = "men";
		else
			hor_type_hor = "women";
		try {
			System.out.println("hor " + hor_type_hor + "   " + finalbill);
			Transaction tx = s.beginTransaction();
			CriteriaBuilder builder = s.getCriteriaBuilder();
			CriteriaUpdate<Room> update = builder.createCriteriaUpdate(Room.class);
			@SuppressWarnings("rawtypes")
			Root e = update.from(Room.class);
			update.set("ele_bill", finalbill);
			update.where(builder.equal(e.get("hor"), hor_type_hor));
			s.createQuery(update).executeUpdate();
			tx.commit();
		} catch (HibernateException e) {
			System.out.println(e.getMessage());
			System.out.println("error");
		} finally {
			s.close();
		}
	}

	public int calculateStudentsWing(String wing_type_wing) {
		// TODO Auto-generated method stub
		Session s = getSession();
		int val = 0;
		try {
			Transaction tx = s.beginTransaction();
			CriteriaBuilder builder = s.getCriteriaBuilder();
			CriteriaQuery<Room> query = builder.createQuery(Room.class);
			Root<Room> root = query.from(Room.class);
			query.select(root).where(builder.equal(root.get("wing"), wing_type_wing));
			Query<Room> q = s.createQuery(query);
			List<Room> results = q.getResultList();
			int itemCount = results.size();
			val = itemCount;
			tx.commit();
		} catch (HibernateException e) {
			System.out.println(e.getMessage());
			System.out.println("error");
		} finally {
			s.close();
		}
		return val;
	}

	public void addFeesWing(String wing_type_wing, float finalbill_wingwise, String floor_type_wing) {
		// TODO Auto-generated method stub
		Session s = getSession();

		if (floor_type_wing.equals("gnd")) {
			floor_type_wing = "0";
		} else if (floor_type_wing.equals("first")) {
			floor_type_wing = "1";
		} else
			floor_type_wing = "2";

		try {

			Transaction tx = s.beginTransaction();
			CriteriaBuilder builder = s.getCriteriaBuilder();
			CriteriaUpdate<Room> update = builder.createCriteriaUpdate(Room.class);
			@SuppressWarnings("rawtypes")
			Root e = update.from(Room.class);
			update.set("ele_bill", finalbill_wingwise);
			update.where(builder.equal(e.get("wing"), wing_type_wing), builder.equal(e.get("floor"), floor_type_wing));
			s.createQuery(update).executeUpdate();
			tx.commit();
		} catch (HibernateException e) {
			System.out.println(e.getMessage());
			System.out.println("error");
		} finally {
			s.close();
		}

	}

	public List<Room> getStuList(String wing_type_wing) {
		// TODO Auto-generated method stub
		Session s = getSession();
		List<Room> stulist = new ArrayList<Room>();
		try {
			Transaction tx = s.beginTransaction();
			CriteriaBuilder builder = s.getCriteriaBuilder();
			CriteriaQuery<Room> query = builder.createQuery(Room.class);
			Root<Room> root = query.from(Room.class);
			query.select(root).where(builder.equal(root.get("wing"), wing_type_wing));

			Query<Room> q = s.createQuery(query);
			stulist = q.getResultList();

			tx.commit();
		} catch (HibernateException e) {
			System.out.println(e.getMessage());
			System.out.println("error");
		} finally {
			s.close();
		}
		return stulist;
	}

	public int addStudentFee(String fee_id, float billvalue) {
		if (CheckId(fee_id)) {

			Session s = getSession();

			try {

				Transaction tx = s.beginTransaction();
				CriteriaBuilder builder = s.getCriteriaBuilder();
				CriteriaUpdate<Room> update = builder.createCriteriaUpdate(Room.class);
				@SuppressWarnings("rawtypes")
				Root e = update.from(Room.class);
				update.set("ele_bill", billvalue);
				update.where(builder.equal(e.get("stu_id"), fee_id));
				s.createQuery(update).executeUpdate();
				tx.commit();
				return 1;
			} catch (HibernateException e) {
				System.out.println(e.getMessage());
				System.out.println("error");
			} finally {
				s.close();
			}
			return 0;
		} else
			return 0;

	}

	private boolean CheckId(String fee_id) {
		// TODO Auto-generated method stub
		Session s = getSession();
		try {

			Transaction tx = s.beginTransaction();

//          
			CriteriaBuilder builder = s.getCriteriaBuilder();
			CriteriaQuery<Room> query = builder.createQuery(Room.class);
			Root<Room> root = query.from(Room.class);
			query.select(root).where(builder.equal(root.get("stu_id"), fee_id));
			Query<Room> q = s.createQuery(query);

			Room room = (Room) q.uniqueResult();
			if (room != null) {
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

	public int calculateStudentsWingFloor(String floor_type_wing, String wing_type_wing) {
		// TODO Auto-generated method stub
		Session s = getSession();
		int val = 0;

		if (floor_type_wing.equals("gnd")) {
			floor_type_wing = "0";
		} else if (floor_type_wing.equals("first")) {
			floor_type_wing = "1";
		} else
			floor_type_wing = "2";
		try {
			Transaction tx = s.beginTransaction();
			CriteriaBuilder builder = s.getCriteriaBuilder();
			CriteriaQuery<Room> query = builder.createQuery(Room.class);
			Root<Room> root = query.from(Room.class);
			query.select(root).where(builder.equal(root.get("wing"), wing_type_wing),
					builder.equal(root.get("floor"), floor_type_wing));
			Query<Room> q = s.createQuery(query);
			List<Room> results = q.getResultList();
			int itemCount = results.size();
			val = itemCount;
			tx.commit();
		} catch (HibernateException e) {
			System.out.println(e.getMessage());
			System.out.println("error");
		} finally {
			s.close();
		}
		return val;

	}

}
