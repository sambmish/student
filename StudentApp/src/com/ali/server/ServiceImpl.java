package com.ali.server;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import com.ali.shared.HibernateUtil;
import com.ali.shared.Student;
import com.ali.shared.UIValidator;

public class ServiceImpl {
	private static SessionFactory sf;
	public void saveStudent(Student s){
		
		//System.out.println(sf);
		sf= HibernateUtil.getSessionFactory();
		Session session=sf.openSession();
		session.getTransaction().begin();
		session.save(s);
		session.getTransaction().commit();
		
	}
	public List<Student> searchStudent(String name,String rollNo,String standard){
		
		sf= HibernateUtil.getSessionFactory();
		Session session=sf.openSession();
		UIValidator validator=new UIValidator();
		
		Criteria cr = session.createCriteria(Student.class);
		Criterion rest1=null;
		if(name.equals(new String("")) && rollNo.equals(new String("")) && standard.equals(new String(""))){
			//don't add Restriction
		}
		if(!name.equals(new String("")) && rollNo.equals(new String("")) && standard.equals(new String("")) && validator.validateName(name)){
			 rest1=Restrictions.ilike("name", name);
		}
		if(name.equals(new String("")) && !rollNo.equals(new String("")) && standard.equals(new String("")) && validator.validateRollNo(rollNo)){
			 rest1=Restrictions.eq("rollNo", Integer.parseInt(rollNo));
		}
		if(name.equals(new String("")) && rollNo.equals(new String("")) && !standard.equals(new String(""))){
			 rest1=Restrictions.eq("class_", standard);
		}
		if(!name.equals(new String("")) && !rollNo.equals(new String("")) && standard.equals(new String("")) && validator.validateName(name) && validator.validateRollNo(rollNo)){
			 rest1=Restrictions.or(Restrictions.ilike("name", name),
					Restrictions.eq("rollNo", Integer.parseInt(rollNo)));
		}
		if(!name.equals(new String("")) && rollNo.equals(new String("")) && !standard.equals(new String("")) && validator.validateName(name)){
			 rest1=Restrictions.or(Restrictions.ilike("name", name),
					Restrictions.eq("class_", standard));
		}
		if(name.equals(new String("")) && !rollNo.equals(new String("")) && !standard.equals(new String("")) && validator.validateRollNo(rollNo)){
			 rest1=Restrictions.or(Restrictions.eq("rollNo", Integer.parseInt(rollNo)),
					Restrictions.eq("class_", standard));
		}
		if(!name.equals(new String("")) && !rollNo.equals(new String("")) && !standard.equals(new String("")) && validator.validateRollNo(rollNo) && validator.validateName(name)){
			rest1=Restrictions.or(Restrictions.eq("rollNo", Integer.parseInt(rollNo)),
					Restrictions.eq("class_", standard),Restrictions.ilike("name", name));
		}
		
		if(rest1!=null)
		cr.add(rest1);
		//String q="from Student s where s.name=: "+name;
		//session.getTransaction().begin();
		//Query query=session.createQuery(q);
		List<Student> stuList=cr.list();
		//session.getTransaction().commit();
		
		return stuList;
		
	}

}
