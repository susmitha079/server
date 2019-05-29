package org.cap.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.cap.model.LoginPojo;

public class LoginDaoImpl implements ILoginDao {

	@Override
	public boolean isValidLogin(LoginPojo loginPojo) {
		
		EntityManager manager=getEntityManagerFactory().createEntityManager();
		EntityTransaction transaction= manager.getTransaction();
		transaction.begin();
		String sql="from LoginPojo lgn where lgn.userName=:uName and lgn.userPwd=:uPwd";
		Query query= manager.createQuery(sql);
		query.setParameter("uName", loginPojo.getUserName());
		query.setParameter("uPwd", loginPojo.getUserPwd());
		
		List<LoginPojo> logins= query.getResultList();
		transaction.commit();
		manager.close();
		
		if(logins.size()>0) 
			return true;
		
		return false;
	}

	private EntityManagerFactory getEntityManagerFactory() {
		return Persistence.createEntityManagerFactory("jpademo");
	}
	
}
