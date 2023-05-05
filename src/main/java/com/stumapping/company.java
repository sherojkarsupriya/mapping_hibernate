package com.stumapping;

import java.util.*;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class company {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Configuration con=new Configuration().configure()
	    		   .addAnnotatedClass(employee.class).addAnnotatedClass(projects.class);
	       
	        SessionFactory sf=con.buildSessionFactory();
	        
	        Session ses=sf.openSession();
	        Transaction tx=ses.beginTransaction();
		
		projects p1=new projects();
		p1.setProjectId(01);
		p1.setProjectName("Java Banking project");
		
		projects p2=new projects();
		p2.setProjectId(02);
		p2.setProjectName("Angular payment project");
		
		projects p3=new projects();
		p3.setProjectId(03);
		p3.setProjectName("Nodejs project");
		
		List<projects> projectList=new ArrayList<projects>();
		
		projectList.add(p1);
		projectList.add(p2);
		
		List<projects> projectList2=new ArrayList<projects>();
		
		projectList2.add(p3);
		employee e1=new employee();
		e1.setEmpId(101);
		e1.setEmpName("shruthi");
		e1.setEmpLocation("pune");
		e1.setProject(projectList);
		
		employee e2=new employee();
		e2.setEmpId(102);
		e2.setEmpName("Shradha");
		e2.setEmpLocation("Bengulur");
		e2.setProject(projectList2);
		
		ses.save(p1);
		ses.save(p2);
		ses.save(p3);
		ses.save(e1);
		ses.save(e2);
		
		
		Query qu=ses.createQuery("from employee");
		
		//to get list of employees
		List<employee> emplist=qu.getResultList();
		
		for(employee emp:emplist)
		{
		
        	//to get list of projects of a employee
			List<projects> pp=emp.getProject();
			for(projects p:pp)
				System.out.println(emp.getEmpName()+" is working on "+p.getProjectName());
			    
		}
		
		tx.commit();
	}
	

}
