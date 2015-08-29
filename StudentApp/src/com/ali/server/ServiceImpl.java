package com.ali.server;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import com.ali.shared.HibernateUtil;
import com.ali.shared.Student;

public class ServiceImpl {
	private static SessionFactory sf= HibernateUtil.getSessionFactory();
	public void saveStudent(Student s){
		
		//System.out.println(sf);
		sf= HibernateUtil.getSessionFactory();
		Session session=sf.openSession();
		session.getTransaction().begin();
		session.save(s);
		session.getTransaction().commit();
		
	}
	public List<Student> searchStudent(String name){
		
		System.out.println(sf);
		Session session=sf.openSession();
		Criteria cr = session.createCriteria(Student.class);
		cr.add(Restrictions.ilike("name", name));
		//String q="from Student s where s.name=: "+name;
		session.getTransaction().begin();
		//Query query=session.createQuery(q);
		List<Student> stuList=cr.list();
		session.getTransaction().commit();
		
		return stuList;
		
	}

}
