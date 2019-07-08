package com.HMS.managedBeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.HMS.entity.Hor_room_allocate;

import com.HMS.entity.Student_details;
import com.HMS.functions.AllotRoomDetails;

@ManagedBean(name = "AllotRoomBean")
@ViewScoped
public class AllotRoomBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Map<String, Boolean> checked = new HashMap<String, Boolean>();
	private List<String> checklist = new ArrayList<String>();
	private Map<String, Boolean> checked1 = new HashMap<String, Boolean>();
	private List<String> checklist1 = new ArrayList<String>();
	private List<String> checklistmale = new ArrayList<String>();
	private List<String> checklistfemale = new ArrayList<String>();
	private List<String> checklistmen = new ArrayList<String>();
	private List<String> checklistwomen = new ArrayList<String>();
	private List<String> checklistmendouble = new ArrayList<String>();
	private List<String> checklistwomendouble = new ArrayList<String>();
	private List<Student_details> student_list = new ArrayList<Student_details>();
	private List<Hor_room_allocate> vaccant_room_list = new ArrayList<Hor_room_allocate>();
	private List<Hor_room_allocate> temp1 = new ArrayList<Hor_room_allocate>();
	private List<Hor_room_allocate> temp2 = new ArrayList<Hor_room_allocate>();
	private String deallocateroom;
	private String deallocatestudent;

	@PostConstruct
	public void init() {
		AllotRoomDetails allot = new AllotRoomDetails();
		student_list = allot.notAllotedlist();
		AllotRoomDetails allot1 = new AllotRoomDetails();
		vaccant_room_list = allot1.vaccantRooms();

	}

	public List<Student_details> getStudent_list() {

		return student_list;
	}

	public List<Hor_room_allocate> getVaccant_room_list() {

		return vaccant_room_list;
	}

	public String getDeallocateroom() {
		return deallocateroom;
	}

	public void setDeallocateroom(String deallocateroom) {
		this.deallocateroom = deallocateroom;
	}

	public String getDeallocatestudent() {
		return deallocatestudent;
	}

	public void setDeallocatestudent(String deallocatestudent) {
		this.deallocatestudent = deallocatestudent;
	}

	public Map<String, Boolean> getChecked() {
		return checked;
	}

	public void setChecked(Map<String, Boolean> checked) {
		this.checked = checked;
	}

	public Map<String, Boolean> getChecked1() {
		return checked1;
	}

	public void setChecked1(Map<String, Boolean> checked1) {
		this.checked1 = checked1;
	}

	public String submitsingle() {
		String navResult = "";

		for (Map.Entry<String, Boolean> entry : checked.entrySet()) {
			String key = entry.getKey();
			Boolean tab = entry.getValue();
			if (tab == false) {

			} else {
				checklist.add(key);
				/* System.out.println(key+" "+tab); */
			}
			// do something with key and/or tab
		}
		for (String c : checklist) {
			System.out.println(c + " Hello");
		}

		checked.clear();
		for (Map.Entry<String, Boolean> entry : checked1.entrySet()) {
			String key = entry.getKey();
			Boolean tab = entry.getValue();
			if (tab == false) {

			} else {
				checklist1.add(key);

			}

		}
		for (String c : checklist1) {
			System.out.println(c + " Hello");
		}

		AllotRoomDetails allot2 = new AllotRoomDetails();
		int result = allot2.validate(checklist, checklist1);
		if (result == 0) {
			FacesContext context = FacesContext.getCurrentInstance();
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Either no room selected or no student selected", "Message details");
			context.addMessage(null, message);
			context.validationFailed();
			navResult = "Room_Allot";
		} else if (result == 1) {

			FacesContext context = FacesContext.getCurrentInstance();
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"More than one room selected for allot singly.", "Message details");
			context.addMessage(null, message);
			context.validationFailed();
			navResult = "Room_Allot";
		} else if (result == 2) {

			FacesContext context = FacesContext.getCurrentInstance();
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"More than one student selected for allot singly", "Message details");
			context.addMessage(null, message);
			context.validationFailed();
			navResult = "Room_Allot";
		} else if (result == 3) {
			FacesContext context = FacesContext.getCurrentInstance();
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"More than one student and more than one room selected for allot singly", "Message details");
			context.addMessage(null, message);
			context.validationFailed();
			navResult = "Room_Allot";
		} else {
			List<Student_details> gender = new ArrayList<Student_details>();
			List<Hor_room_allocate> hor = new ArrayList<Hor_room_allocate>();
			AllotRoomDetails allot3 = new AllotRoomDetails();
			gender = allot3.checkgender(checklist);
			hor = allot3.checkhor(checklist1);
			String gender1 = "";
			String hor1 = "";
			String floor1 = "";
			String wing1 = "";
			for (Student_details c : gender) {
				gender1 = c.getGender();
			}
			for (Hor_room_allocate e : hor) {
				hor1 = e.getHor();
				floor1 = e.getFloor();
				wing1 = e.getWing();
			}
			System.out.println(gender1 + "  " + hor1);
			if (gender1.equals("male") && hor1.equals("women")) {
				FacesContext context = FacesContext.getCurrentInstance();
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
						"Cannot allocate a male student in HOR WOMEN", "Message details");
				context.addMessage(null, message);
				context.validationFailed();

				navResult = "Room_Allot";

			} else if (gender1.equals("female") && hor1.equals("men")) {
				FacesContext context = FacesContext.getCurrentInstance();
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
						"Cannot allocate a female student in HOR MEN", "Message details");
				context.addMessage(null, message);
				context.validationFailed();

				navResult = "Room_Allot";

			} else {
				AllotRoomDetails finalallotment = new AllotRoomDetails();
				int status = finalallotment.checkstatus(checklist1);
				finalallotment.changeroomstatus(checklist1, status);
				finalallotment.changestudentstatus(checklist);
				finalallotment.addentrytobill(checklist, checklist1, hor1, floor1, wing1);
				FacesContext context = FacesContext.getCurrentInstance();
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Room alloted successfully",
						"Message details");
				context.addMessage(null, message);
				context.validationFailed();

				navResult = "Room_Allot";
			}

		}
		checked1.clear();
		checklist.clear();
		checklist1.clear();
		return navResult;

	}

	public String submitall() {

		String gender;
		String id;
		String hor;
		String room;

		for (Student_details e : student_list) {
			gender = e.getGender();
			id = e.getStu_id();
			if (gender.equals("male")) {
				checklistmale.add(id);
			} else {
				checklistfemale.add(id);

			}

		}
		for (Hor_room_allocate e : vaccant_room_list) {
			hor = e.getHor();
			room = e.getRoom();
			if (hor.equals("men")) {
				checklistmen.add(room);
			} else {
				checklistwomen.add(room);

			}

		}

		AllotRoomDetails doublevaccant = new AllotRoomDetails();
		temp1 = doublevaccant.doublevaccantlistmen();
		temp2 = doublevaccant.doublevaccantlistwomen();

		String temproom = "";
		for (Hor_room_allocate e : temp1) {
			temproom = e.getRoom();
			checklistmendouble.add(temproom);

		}
		String temproom1 = "";
		for (Hor_room_allocate e : temp2) {
			temproom1 = e.getRoom();
			checklistwomendouble.add(temproom1);

		}

		checklistmen.addAll(checklistmendouble);
		checklistwomen.addAll(checklistwomendouble);

		Collections.shuffle(checklistmale);
		Collections.shuffle(checklistfemale);
		Collections.shuffle(checklistmen);
		Collections.shuffle(checklistwomen);
		int count = 0;

		System.out.println("Male students");
		for (String c : checklistmale) {
			count++;
			System.out.println(c + " " + count);
		}
		count = 0;
		System.out.println("Female students");

		for (String c : checklistfemale) {
			count++;
			System.out.println(c + " " + count);
		}
		count = 0;
		System.out.println("Hor Men");
		for (String c : checklistmen) {
			count++;
			System.out.println(c + " " + count);
		}
		count = 0;
		System.out.println("Hor Women");

		for (String c : checklistwomen) {
			count++;
			System.out.println(c + " " + count);
		}
		int malecount = checklistmale.size();
		int femalecount = checklistfemale.size();
		int menroomcount = checklistmen.size();
		int womenroomcount = checklistwomen.size();
		if (malecount > menroomcount) {
			for (int i = 0; i < menroomcount; i++) {

				String idtemp;
				String roomtemp;
				String hortemp = "men";
				String floortemp = "";
				String wingtemp = "";
				List<Hor_room_allocate> tempdetail = new ArrayList<Hor_room_allocate>();
				idtemp = checklistmale.get(i);
				roomtemp = checklistmen.get(i);
				AllotRoomDetails finalallotment1 = new AllotRoomDetails();
				int statustemp = finalallotment1.checkstatusrandom(roomtemp);
				finalallotment1.changeroomstatusrandom(roomtemp, statustemp);
				finalallotment1.changestudentstatusrandom(idtemp);
				tempdetail = finalallotment1.hordetailrandom(roomtemp);
				for (Hor_room_allocate e : tempdetail) {

					floortemp = e.getFloor();
					wingtemp = e.getWing();
				}

				finalallotment1.addentrytobillrandom(idtemp, roomtemp, hortemp, floortemp, wingtemp);

			}

		} else {

			for (int i = 0; i < malecount; i++) {

				String idtemp;
				String roomtemp;
				String hortemp = "men";
				String floortemp = "";
				String wingtemp = "";
				List<Hor_room_allocate> tempdetail = new ArrayList<Hor_room_allocate>();

				idtemp = checklistmale.get(i);
				roomtemp = checklistmen.get(i);
				AllotRoomDetails finalallotment1 = new AllotRoomDetails();
				int statustemp = finalallotment1.checkstatusrandom(roomtemp);
				finalallotment1.changeroomstatusrandom(roomtemp, statustemp);
				finalallotment1.changestudentstatusrandom(idtemp);
				tempdetail = finalallotment1.hordetailrandom(roomtemp);
				for (Hor_room_allocate e : tempdetail) {

					floortemp = e.getFloor();
					wingtemp = e.getWing();
				}
				finalallotment1.addentrytobillrandom(idtemp, roomtemp, hortemp, floortemp, wingtemp);
			}

		}

		if (femalecount > womenroomcount) {
			for (int i = 0; i < womenroomcount; i++) {

				String idtemp;
				String roomtemp;
				String hortemp = "women";
				String floortemp = "";
				String wingtemp = "";
				List<Hor_room_allocate> tempdetail = new ArrayList<Hor_room_allocate>();
				idtemp = checklistfemale.get(i);
				roomtemp = checklistwomen.get(i);
				AllotRoomDetails finalallotment1 = new AllotRoomDetails();
				int statustemp = finalallotment1.checkstatusrandom(roomtemp);
				finalallotment1.changeroomstatusrandom(roomtemp, statustemp);
				finalallotment1.changestudentstatusrandom(idtemp);
				tempdetail = finalallotment1.hordetailrandom(roomtemp);
				for (Hor_room_allocate e : tempdetail) {

					floortemp = e.getFloor();
					wingtemp = e.getWing();
				}

				finalallotment1.addentrytobillrandom(idtemp, roomtemp, hortemp, floortemp, wingtemp);

			}

		} else {

			for (int i = 0; i < femalecount; i++) {

				String idtemp;
				String roomtemp;
				String hortemp = "women";
				String floortemp = "";
				String wingtemp = "";
				List<Hor_room_allocate> tempdetail = new ArrayList<Hor_room_allocate>();

				idtemp = checklistmale.get(i);
				roomtemp = checklistmen.get(i);
				AllotRoomDetails finalallotment1 = new AllotRoomDetails();
				int statustemp = finalallotment1.checkstatusrandom(roomtemp);
				finalallotment1.changeroomstatusrandom(roomtemp, statustemp);
				finalallotment1.changestudentstatusrandom(idtemp);
				tempdetail = finalallotment1.hordetailrandom(roomtemp);
				for (Hor_room_allocate e : tempdetail) {

					floortemp = e.getFloor();
					wingtemp = e.getWing();
				}
				finalallotment1.addentrytobillrandom(idtemp, roomtemp, hortemp, floortemp, wingtemp);
			}

		}
		if (femalecount > womenroomcount && malecount > menroomcount) {
			FacesContext context = FacesContext.getCurrentInstance();
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Allocation is successfull, but number of rooms in HOR MEN and HOR WOMEN are less so some students not allocated.",
					"Message details");
			context.addMessage(null, message);
			context.validationFailed();
		} else if (femalecount > womenroomcount && malecount <= menroomcount) {
			FacesContext context = FacesContext.getCurrentInstance();
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Allocation is successfull, but number of rooms in HOR WOMEN are less so some students not allocated.",
					"Message details");
			context.addMessage(null, message);
			context.validationFailed();

		} else if (femalecount <= womenroomcount && malecount > menroomcount) {
			FacesContext context = FacesContext.getCurrentInstance();
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Allocation is successfull, but number of rooms in HOR MEN are less so some students not allocated.",
					"Message details");
			context.addMessage(null, message);
			context.validationFailed();
		} else {
			FacesContext context = FacesContext.getCurrentInstance();
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Allocation is successfull.",
					"Message details");
			context.addMessage(null, message);
			context.validationFailed();
		}
		checklistmen.clear();
		checklistwomen.clear();
		checklistmale.clear();
		checklistfemale.clear();
		temp1.clear();
		temp2.clear();
		checklistmendouble.clear();
		checklistwomendouble.clear();

		String navResult = "Room_Allot";
		return navResult;
	}
	
	
	public String  Redirectdeallocate()
	{
		
		String navResult = "deallocate";
		return navResult;
		
	}
	public String deallocatebyroom()
	{
		String navResult="";
		if(deallocateroom.equals(null))
		{
			FacesContext context = FacesContext.getCurrentInstance();
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Enter the room no.",
					"Message details");
			context.addMessage(null, message);
			context.validationFailed();
			navResult = "deallocate";
			
		}
		else
		{
			AllotRoomDetails check = new AllotRoomDetails();
			int status = check.checkroom(deallocateroom);
			if(status == 0)
			{
				AllotRoomDetails var = new AllotRoomDetails();
				int statustemp1 = var.checkstatusrandom(deallocateroom);
				if(statustemp1==0)
				{
					FacesContext context = FacesContext.getCurrentInstance();
					FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Room is already empty.",
							"Message details");
					context.addMessage(null, message);
					context.validationFailed();
					navResult="deallocate";
					
				}
				else
				{
					
					AllotRoomDetails var1 = new AllotRoomDetails();
					
					List<String> current_id = new ArrayList<String>();
					current_id = var1.currentresidents(deallocateroom);
					var1.statustozero(deallocateroom);
					for(String c: current_id)
					{
						
						var1.changestudentdeallocatestatus(c);
						Long tempid1 = var1.findid(c);
						var1.deletefromele(c,tempid1);
						
						
						
					}
					FacesContext context = FacesContext.getCurrentInstance();
					FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Room deallocated.",
							"Message details");
					context.addMessage(null, message);
					context.validationFailed();
					navResult="deallocate";
					
				}
				
				
				
			}
			else
			{
				
				FacesContext context = FacesContext.getCurrentInstance();
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Room not found.",
						"Message details");
				context.addMessage(null, message);
				context.validationFailed();
				navResult="deallocate";
			}
			
			
		}
		
		return navResult;
	}
	
	public String deallocatebystudent()
	{
		
		String navResult="";
		if(deallocatestudent.equals(null))
		{
			FacesContext context = FacesContext.getCurrentInstance();
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Enter the Student ID.",
					"Message details");
			context.addMessage(null, message);
			context.validationFailed();
			navResult = "deallocate";
			
		}
		else
		{
			
			AllotRoomDetails check = new AllotRoomDetails();
			int status = check.checkstudent(deallocatestudent);
			if(status == 0)
			{
				
				String room="";
				room = check.roomdetails(deallocatestudent);
				int capacity=0;
				capacity = check.checkstatusrandom(room);
				capacity=capacity-1;
				check.changeroomstatusdeallocate(room,capacity);
				check.changestudentdeallocatestatus(deallocatestudent);
				Long tempid2 = check.findid(deallocatestudent);
				check.deletefromele(deallocatestudent,tempid2);
				FacesContext context = FacesContext.getCurrentInstance();
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Student deallocated.",
						"Message details");
				context.addMessage(null, message);
				context.validationFailed();
				navResult="deallocate";
				
				
			}
			
			else
			{
				
				FacesContext context = FacesContext.getCurrentInstance();
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Either student id doesn't exist or student is not yet allocated.",
						"Message details");
				context.addMessage(null, message);
				context.validationFailed();
				navResult="deallocate";
				
			}
			
		}
		return navResult;
	}

}
