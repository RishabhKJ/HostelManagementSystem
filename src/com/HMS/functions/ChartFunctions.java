package com.HMS.functions;

import java.util.ArrayList;
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


public class ChartFunctions {
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
	public List<Wing> getStudentsWingWise() {
		String[] wing_types = { "A", "B", "C", "D","E","F","G","H","I","J","K" };
		//int[] floor_types = {0,1,2};
		String[] floor_types = {"0","1","2"};
		List<Wing> wings = new ArrayList<Wing>();
		Session s = getSession();
		int i,j;
		for(i=0;i<wing_types.length;i++) {
			Wing w = new Wing();
			w.setType(wing_types[i]);
			List<Floor> flrs = new ArrayList<Floor>();
			for(j=0;j<floor_types.length;j++) {
				try {
					Transaction tx = s.beginTransaction();
					CriteriaBuilder builder = s.getCriteriaBuilder();
					CriteriaQuery<Hor_room_allocate> query = builder.createQuery(Hor_room_allocate.class);
					Root<Hor_room_allocate> root = query.from(Hor_room_allocate.class);
					query.select(root).where(builder.equal(root.get("wing"),wing_types[i]),builder.equal(root.get("floor"),floor_types[j]) );
					Query<Hor_room_allocate> q = s.createQuery(query);
					List<Hor_room_allocate> results=q.getResultList();
					List<RoomString> roomstrings=new ArrayList<RoomString>();
					tx.commit();
					for(Hor_room_allocate hra:results) {
						RoomString rstr = new RoomString();
						rstr.setName(hra.getRoom());
						List<String> str=new ArrayList<String>();
						if(hra.getStatus()!=0) {
							System.out.println("challa");
							System.out.println(hra.getRoom());
							try {
								Transaction tx1 = s.beginTransaction();
								CriteriaBuilder builder1 = s.getCriteriaBuilder();
								CriteriaQuery<Room> query1 = builder1.createQuery(Room.class);
								Root<Room> root1 = query1.from(Room.class);
								query1.select(root1).where(builder1.equal(root1.get("room"),hra.getRoom()) );
								Query<Room> q1 = s.createQuery(query1);
								List<Room> results1=q1.getResultList();
								System.out.println("size="+results1.size());
								for(Room xr:results1) {
									str.add(xr.getStu_id());
								}
								
								tx1.commit();
							} catch (HibernateException e) {
								System.out.println(e.getMessage());
								System.out.println("error");
							}
						}
						rstr.setStudents(str);
						roomstrings.add(rstr);
					}
					Floor fl = new Floor();
//					fl.setType(Integer.parseInt(floor_types[j]));
					fl.setType(floor_types[j]);
					fl.setRooms(roomstrings);
					flrs.add(fl);
//					tx.commit();
				} catch (HibernateException e) {
					System.out.println(e.getMessage());
					System.out.println("error");
				}
			}
			w.setFloors(flrs);
			wings.add(w);
		}
		for(Wing w : wings) {
			System.out.println(w.getType());
			for(Floor fl: w.getFloors()) {
				System.out.println(fl.getType());
				for(RoomString r: fl.getRooms()) {
					for(String s1: r.getStudents()) {
						System.out.println("student="+s1);
					}
				}
			}
		}
		return wings;
		
//		 finally {
//			s.close();
//		}
//		return val;
	}

}
