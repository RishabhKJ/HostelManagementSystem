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

import com.HMS.entity.Hor_room_allocate;
import com.HMS.entity.Room;

import com.HMS.entity.Student_details;

public class AllotRoomDetails {
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

	public List<Student_details> notAllotedlist() {
		Session s = getSession();
		List<Student_details> student_list = new ArrayList<Student_details>();
		try {
			Transaction tx = s.beginTransaction();
			CriteriaBuilder builder = s.getCriteriaBuilder();
			CriteriaQuery<Student_details> query = builder.createQuery(Student_details.class);
			Root<Student_details> root = query.from(Student_details.class);
			query.select(root).where(builder.equal(root.get("allocated"), "NO"));

			Query<Student_details> q = s.createQuery(query);
			student_list = q.getResultList();
			tx.commit();

		} catch (HibernateException e) {
			System.out.println(e.getMessage());
			System.out.println("error");
		} finally {
			s.close();
		}
		return student_list;

	}

	public List<Hor_room_allocate> vaccantRooms() {
		// TODO Auto-generated method stub
		Session s = getSession();
		List<Hor_room_allocate> vaccant_room_list = new ArrayList<Hor_room_allocate>();
		try {
			Transaction tx = s.beginTransaction();
			CriteriaBuilder builder = s.getCriteriaBuilder();
			CriteriaQuery<Hor_room_allocate> query = builder.createQuery(Hor_room_allocate.class);
			Root<Hor_room_allocate> root = query.from(Hor_room_allocate.class);
			query.select(root)
					.where(builder.or(builder.equal(root.get("status"), 0), builder.equal(root.get("status"), 1)));

			Query<Hor_room_allocate> q = s.createQuery(query);
			vaccant_room_list = q.getResultList();
			tx.commit();

		} catch (HibernateException e) {
			System.out.println(e.getMessage());
			System.out.println("error");
		} finally {
			s.close();
		}
		return vaccant_room_list;

	}

	public int validate(List<String> checklist, List<String> checklist1) {
		// TODO Auto-generated method stub
		int sizestu = checklist.size();
		int sizeroom = checklist1.size();
		if (sizestu == 0 || sizeroom == 0) {
			return 0;

		} else if (sizestu == 1 && sizeroom > 1) {
			return 1;
		} else if (sizestu > 1 && sizeroom == 1) {
			return 2;

		} else if (sizestu > 1 && sizeroom > 1) {
			return 3;
		} else {
			return 4;
		}
	}

	public List<Student_details> checkgender(List<String> checklist) {
		// TODO Auto-generated method stub

		Session s = getSession();
		List<Student_details> gender = new ArrayList<Student_details>();

		try {
			Transaction tx = s.beginTransaction();
			CriteriaBuilder builder = s.getCriteriaBuilder();
			CriteriaQuery<Student_details> query = builder.createQuery(Student_details.class);
			Root<Student_details> root = query.from(Student_details.class);
			query.select(root).where(builder.equal(root.get("stu_id"), checklist.get(0)));

			Query<Student_details> q = s.createQuery(query);
			gender = q.getResultList();
			tx.commit();

		} catch (HibernateException e) {
			System.out.println(e.getMessage());
			System.out.println("error");
		} finally {
			s.close();
		}
		return gender;

	}

	public List<Hor_room_allocate> checkhor(List<String> checklist1) {
		// TODO Auto-generated method stub
		Session s = getSession();
		List<Hor_room_allocate> hor = new ArrayList<Hor_room_allocate>();
		try {
			Transaction tx = s.beginTransaction();
			CriteriaBuilder builder = s.getCriteriaBuilder();
			CriteriaQuery<Hor_room_allocate> query = builder.createQuery(Hor_room_allocate.class);
			Root<Hor_room_allocate> root = query.from(Hor_room_allocate.class);
			query.select(root).where(builder.equal(root.get("room"), checklist1.get(0)));

			Query<Hor_room_allocate> q = s.createQuery(query);
			hor = q.getResultList();
			tx.commit();

		} catch (HibernateException e) {
			System.out.println(e.getMessage());
			System.out.println("error");
		} finally {
			s.close();
		}
		return hor;
	}

	public void changeroomstatus(List<String> checklist1, int status) {
		Session s = getSession();
		int newstatus = status + 1;
		try {
			Transaction tx = s.beginTransaction();
			CriteriaBuilder builder = s.getCriteriaBuilder();
			CriteriaUpdate<Hor_room_allocate> update = builder.createCriteriaUpdate(Hor_room_allocate.class);
			@SuppressWarnings("rawtypes")
			Root e = update.from(Hor_room_allocate.class);
			update.set("status", newstatus);
			update.where(builder.equal(e.get("room"), checklist1.get(0)));
			s.createQuery(update).executeUpdate();
			tx.commit();
		} catch (HibernateException e) {
			System.out.println(e.getMessage());
			System.out.println("error");
		} finally {
			s.close();
		}

	}

	public int checkstatus(List<String> checklist1) {
		Session s = getSession();
		List<Hor_room_allocate> status1 = new ArrayList<Hor_room_allocate>();
		int status = 0;
		try {
			Transaction tx = s.beginTransaction();
			CriteriaBuilder builder = s.getCriteriaBuilder();
			CriteriaQuery<Hor_room_allocate> query = builder.createQuery(Hor_room_allocate.class);
			Root<Hor_room_allocate> root = query.from(Hor_room_allocate.class);
			query.select(root).where(builder.equal(root.get("room"), checklist1.get(0)));

			Query<Hor_room_allocate> q = s.createQuery(query);
			status1 = q.getResultList();
			tx.commit();
			for (Hor_room_allocate e : status1) {
				status = e.getStatus();
			}

		} catch (HibernateException e) {
			System.out.println(e.getMessage());
			System.out.println("error");
		} finally {
			s.close();
		}
		return status;

	}

	public void changestudentstatus(List<String> checklist) {
		Session s = getSession();
		try {
			Transaction tx = s.beginTransaction();
			CriteriaBuilder builder = s.getCriteriaBuilder();
			CriteriaUpdate<Student_details> update = builder.createCriteriaUpdate(Student_details.class);
			@SuppressWarnings("rawtypes")
			Root e = update.from(Student_details.class);
			update.set("allocated", "YES");
			update.where(builder.equal(e.get("stu_id"), checklist.get(0)));
			s.createQuery(update).executeUpdate();
			tx.commit();
		} catch (HibernateException e) {
			System.out.println(e.getMessage());
			System.out.println("error");
		} finally {
			s.close();
		}

	}

	public void addentrytobill(List<String> checklist, List<String> checklist1, String hor1, String floor1,
			String wing1) {
		Session s = getSession();
		try {
			Transaction tx = s.beginTransaction();
			Room room = new Room();
			room.setStu_id(checklist.get(0));
			room.setHor(hor1);
			room.setRoom(checklist1.get(0));
			room.setFloor(floor1);
			room.setEle_bill((float) 0.0);
			room.setWing(wing1);
			s.save(room);
			tx.commit();
		} catch (HibernateException e) {
			System.out.println(e.getMessage());
			System.out.println("error");
		} finally {
			s.close();
		}

	}

	public List<Hor_room_allocate> doublevaccantlistmen() {
		Session s = getSession();
		List<Hor_room_allocate> double_room_men = new ArrayList<Hor_room_allocate>();
		try {
			Transaction tx = s.beginTransaction();
			CriteriaBuilder builder = s.getCriteriaBuilder();
			CriteriaQuery<Hor_room_allocate> query = builder.createQuery(Hor_room_allocate.class);
			Root<Hor_room_allocate> root = query.from(Hor_room_allocate.class);
			query.select(root).where(builder.equal(root.get("status"), 0), builder.equal(root.get("hor"), "men"));

			Query<Hor_room_allocate> q = s.createQuery(query);
			double_room_men = q.getResultList();
			tx.commit();

		} catch (HibernateException e) {
			System.out.println(e.getMessage());
			System.out.println("error");
		} finally {
			s.close();
		}
		return double_room_men;
	}

	public List<Hor_room_allocate> doublevaccantlistwomen() {
		Session s = getSession();
		List<Hor_room_allocate> double_room_women = new ArrayList<Hor_room_allocate>();
		try {
			Transaction tx = s.beginTransaction();
			CriteriaBuilder builder = s.getCriteriaBuilder();
			CriteriaQuery<Hor_room_allocate> query = builder.createQuery(Hor_room_allocate.class);
			Root<Hor_room_allocate> root = query.from(Hor_room_allocate.class);
			query.select(root).where(builder.equal(root.get("status"), 0), builder.equal(root.get("hor"), "women"));

			Query<Hor_room_allocate> q = s.createQuery(query);
			double_room_women = q.getResultList();
			tx.commit();

		} catch (HibernateException e) {
			System.out.println(e.getMessage());
			System.out.println("error");
		} finally {
			s.close();
		}
		return double_room_women;
	}

	public int checkstatusrandom(String roomtemp) {
		// TODO Auto-generated method stub
		Session s = getSession();
		List<Hor_room_allocate> status1 = new ArrayList<Hor_room_allocate>();
		int status = 0;
		try {
			Transaction tx = s.beginTransaction();
			CriteriaBuilder builder = s.getCriteriaBuilder();
			CriteriaQuery<Hor_room_allocate> query = builder.createQuery(Hor_room_allocate.class);
			Root<Hor_room_allocate> root = query.from(Hor_room_allocate.class);
			query.select(root).where(builder.equal(root.get("room"), roomtemp));

			Query<Hor_room_allocate> q = s.createQuery(query);
			status1 = q.getResultList();
			tx.commit();
			for (Hor_room_allocate e : status1) {
				status = e.getStatus();
			}

		} catch (HibernateException e) {
			System.out.println(e.getMessage());
			System.out.println("error");
		} finally {
			s.close();
		}
		return status;
	}

	public void changeroomstatusrandom(String roomtemp, int statustemp) {
		Session s = getSession();
		int newstatus = statustemp + 1;
		try {
			Transaction tx = s.beginTransaction();
			CriteriaBuilder builder = s.getCriteriaBuilder();
			CriteriaUpdate<Hor_room_allocate> update = builder.createCriteriaUpdate(Hor_room_allocate.class);
			@SuppressWarnings("rawtypes")
			Root e = update.from(Hor_room_allocate.class);
			update.set("status", newstatus);
			update.where(builder.equal(e.get("room"), roomtemp));
			s.createQuery(update).executeUpdate();
			tx.commit();
		} catch (HibernateException e) {
			System.out.println(e.getMessage());
			System.out.println("error");
		} finally {
			s.close();
		}

	}

	public void changestudentstatusrandom(String idtemp) {
		Session s = getSession();
		try {
			Transaction tx = s.beginTransaction();
			CriteriaBuilder builder = s.getCriteriaBuilder();
			CriteriaUpdate<Student_details> update = builder.createCriteriaUpdate(Student_details.class);
			@SuppressWarnings("rawtypes")
			Root e = update.from(Student_details.class);
			update.set("allocated", "YES");
			update.where(builder.equal(e.get("stu_id"), idtemp));
			s.createQuery(update).executeUpdate();
			tx.commit();
		} catch (HibernateException e) {
			System.out.println(e.getMessage());
			System.out.println("error");
		} finally {
			s.close();
		}

	}

	public List<Hor_room_allocate> hordetailrandom(String roomtemp) {
		// TODO Auto-generated method stub
		Session s = getSession();
		List<Hor_room_allocate> details = new ArrayList<Hor_room_allocate>();

		try {
			Transaction tx = s.beginTransaction();
			CriteriaBuilder builder = s.getCriteriaBuilder();
			CriteriaQuery<Hor_room_allocate> query = builder.createQuery(Hor_room_allocate.class);
			Root<Hor_room_allocate> root = query.from(Hor_room_allocate.class);
			query.select(root).where(builder.equal(root.get("room"), roomtemp));

			Query<Hor_room_allocate> q = s.createQuery(query);
			details = q.getResultList();
			tx.commit();

		} catch (HibernateException e) {
			System.out.println(e.getMessage());
			System.out.println("error");
		} finally {
			s.close();
		}
		return details;
	}

	public void addentrytobillrandom(String idtemp, String roomtemp, String hortemp, String floortemp,
			String wingtemp) {
		Session s = getSession();
		try {
			Transaction tx = s.beginTransaction();
			Room room = new Room();
			room.setStu_id(idtemp);
			room.setHor(hortemp);
			room.setRoom(roomtemp);
			room.setFloor(floortemp);
			room.setEle_bill((float) 0.0);
			room.setWing(wingtemp);
			s.save(room);
			tx.commit();
		} catch (HibernateException e) {
			System.out.println(e.getMessage());
			System.out.println("error");
		} finally {
			s.close();
		}

	}

	public int checkroom(String deallocateroom) {
		// TODO Auto-generated method stub
		int result = 0;
		Session s = getSession();
		try {

			Transaction tx = s.beginTransaction();

//          
			CriteriaBuilder builder = s.getCriteriaBuilder();
			CriteriaQuery<Hor_room_allocate> query = builder.createQuery(Hor_room_allocate.class);
			Root<Hor_room_allocate> root = query.from(Hor_room_allocate.class);
			query.select(root).where(builder.equal(root.get("room"), deallocateroom));
			Query<Hor_room_allocate> q = s.createQuery(query);

			Hor_room_allocate room = (Hor_room_allocate) q.uniqueResult();
			if (room != null) {
				System.out.println("room found:");
				tx.commit();
				result = 0;
			} else {
				System.out.println("room not found:");
				tx.commit();
				result = 1;
			}

		} catch (HibernateException e) {
			System.out.println(e.getMessage());
			System.out.println("error");
		} finally {
			s.close();
		}

		return result;

	}

	public List<String> currentresidents(String deallocateroom) {
		// TODO Auto-generated method stub
		Session s = getSession();
		List<Room> room = new ArrayList<Room>();
		List<String> roomtemp1 = new ArrayList<String>();

		try {
			Transaction tx = s.beginTransaction();
			CriteriaBuilder builder = s.getCriteriaBuilder();
			CriteriaQuery<Room> query = builder.createQuery(Room.class);
			Root<Room> root = query.from(Room.class);
			query.select(root).where(builder.equal(root.get("room"), deallocateroom));

			Query<Room> q = s.createQuery(query);
			room = q.getResultList();
			for(Room c: room)
			{
				
				roomtemp1.add(c.getStu_id());
			}
			tx.commit();

		} catch (HibernateException e) {
			System.out.println(e.getMessage());
			System.out.println("error");
		} finally {
			s.close();
		}
		return roomtemp1;
	}

	public void statustozero(String deallocateroom) {
		Session s = getSession();
		try {
			Transaction tx = s.beginTransaction();
			CriteriaBuilder builder = s.getCriteriaBuilder();
			CriteriaUpdate<Hor_room_allocate> update = builder.createCriteriaUpdate(Hor_room_allocate.class);
			@SuppressWarnings("rawtypes")
			Root e = update.from(Hor_room_allocate.class);
			update.set("status", 0);
			update.where(builder.equal(e.get("room"), deallocateroom));
			s.createQuery(update).executeUpdate();
			tx.commit();
		} catch (HibernateException e) {
			System.out.println(e.getMessage());
			System.out.println("error");
		} finally {
			s.close();
		}
		
	}

	public void changestudentdeallocatestatus(String c) {
		Session s = getSession();
		try {
			Transaction tx = s.beginTransaction();
			CriteriaBuilder builder = s.getCriteriaBuilder();
			CriteriaUpdate<Student_details> update = builder.createCriteriaUpdate(Student_details.class);
			@SuppressWarnings("rawtypes")
			Root e = update.from(Student_details.class);
			update.set("allocated", "NO");
			update.where(builder.equal(e.get("stu_id"), c));
			s.createQuery(update).executeUpdate();
			tx.commit();
		} catch (HibernateException e) {
			System.out.println(e.getMessage());
			System.out.println("error");
		} finally {
			s.close();
		}
		
	}

	public void deletefromele(String c, Long tempid1) {
		Session s = getSession();
		try {
			Transaction tx = s.beginTransaction();
		Room detail = new Room();
		detail.setId(tempid1);
		s.delete(detail);
		tx.commit();}catch (HibernateException e) {
			System.out.println(e.getMessage());
			System.out.println("error");
		} finally {
			s.close();
		}
		
	}

	public Long findid(String c) {
		Session s = getSession();
		List<Room> room = new ArrayList<Room>();
		Long id = (long) 0;
		

		try {
			Transaction tx = s.beginTransaction();
			CriteriaBuilder builder = s.getCriteriaBuilder();
			CriteriaQuery<Room> query = builder.createQuery(Room.class);
			Root<Room> root = query.from(Room.class);
			query.select(root).where(builder.equal(root.get("stu_id"), c));

			Query<Room> q = s.createQuery(query);
			room = q.getResultList();
			for(Room e: room)
			{
				id=e.getId();				
			}
			tx.commit();

		} catch (HibernateException e) {
			System.out.println(e.getMessage());
			System.out.println("error");
		} finally {
			s.close();
		}
		return id;
		
	}

	public int checkstudent(String deallocatestudent) {
		// TODO Auto-generated method stub
		int result = 0;
		Session s = getSession();
		try {

			Transaction tx = s.beginTransaction();

//          
			CriteriaBuilder builder = s.getCriteriaBuilder();
			CriteriaQuery<Room> query = builder.createQuery(Room.class);
			Root<Room> root = query.from(Room.class);
			query.select(root).where(builder.equal(root.get("stu_id"), deallocatestudent));
			Query<Room> q = s.createQuery(query);

			Room stu = (Room) q.uniqueResult();
			if (stu != null) {
				System.out.println("student found:");
				tx.commit();
				result = 0;
			} else {
				System.out.println("student not found:");
				tx.commit();
				result = 1;
			}

		} catch (HibernateException e) {
			System.out.println(e.getMessage());
			System.out.println("error");
		} finally {
			s.close();
		}

		return result;

	}

	public String roomdetails(String deallocatestudent) {
		// TODO Auto-generated method stub
		Session s = getSession();
		List<Room> room = new ArrayList<Room>();
		String roomtemp1 = "";

		try {
			Transaction tx = s.beginTransaction();
			CriteriaBuilder builder = s.getCriteriaBuilder();
			CriteriaQuery<Room> query = builder.createQuery(Room.class);
			Root<Room> root = query.from(Room.class);
			query.select(root).where(builder.equal(root.get("stu_id"), deallocatestudent));

			Query<Room> q = s.createQuery(query);
			room = q.getResultList();
			for(Room c: room)
			{
				
				roomtemp1 = c.getRoom();
			}
			tx.commit();

		} catch (HibernateException e) {
			System.out.println(e.getMessage());
			System.out.println("error");
		} finally {
			s.close();
		}
		return roomtemp1;
	}

	public void changeroomstatusdeallocate(String room, int capacity) {
		Session s = getSession();
	
		try {
			Transaction tx = s.beginTransaction();
			CriteriaBuilder builder = s.getCriteriaBuilder();
			CriteriaUpdate<Hor_room_allocate> update = builder.createCriteriaUpdate(Hor_room_allocate.class);
			@SuppressWarnings("rawtypes")
			Root e = update.from(Hor_room_allocate.class);
			update.set("status", capacity);
			update.where(builder.equal(e.get("room"), room));
			s.createQuery(update).executeUpdate();
			tx.commit();
		} catch (HibernateException e) {
			System.out.println(e.getMessage());
			System.out.println("error");
		} finally {
			s.close();
		}

		
	}

}
