package com.abd.mfc.test;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.junit.runners.MethodSorters;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.abd.mfc.entities.User;
import com.abd.mfc.exception.ApplicationAbdException;
import com.abd.mfc.manager.UserManager;
import com.abd.mfc.vo.ResultPaginateVO;
import com.abd.mfc.vo.UserVO;

@RunWith(JUnit4.class)
@FixMethodOrder(MethodSorters.DEFAULT)
public class UserTest {

	private static ClassPathXmlApplicationContext context;
	private static UserManager manager;
	@BeforeClass
	public static void initHibernate() throws Exception {
		context = new ClassPathXmlApplicationContext("applicationContext_test.xml");
		manager = (UserManager) context.getBean("userManager");
	}
	
	/*@Test
	public void addUser() {
		//AdministrateurVO user = getAdministrateur();
		UserVO user = new UserVO();
		user.setUsername("abbd__5");
		user.setMail("abbd5@gmail.com");
		user.setPwd("eeer");
		try {
			
			manager.add(user);
			System.out.println("_____________________________test ok");
		} catch (Exception e) {
			e.printStackTrace();
			//assertTrue(false);
		}
		
	}*/
	
	@Test
	public void searchUsers() {
		/*UserVO filter = new UserVO();
		filter.setUsername("abbd__5");
		ResultPaginateVO<User, UserVO> list = null;
		try {
			list = manager.search(filter );
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (list != null && list.getListVO() != null && !list.getListVO().isEmpty()) {
			for (UserVO user : list.getListVO()) {
				System.out.println("user filter : " + user.getId());
			}
		}*/
		
	}
	
	/*@Test
	public void updateUser() {
		// add user
		UserVO user = null;
		try {
			user = manager.get(Long.valueOf(4));
		} catch (ApplicationAbdException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		user.setAdress("5 rue espagne");
		
		try {
			
			manager.update(user);
			System.out.println("_____________________________test update ok");
		} catch (Exception e) {
			e.printStackTrace();
			//assertTrue(false);
		}
		
	}
	
	@Test
	public void getAllUser() throws ApplicationAbdException {
		List<UserVO> list = manager.getAll();
		
		for (UserVO user : list) {
			System.out.println(user.getUsername());
		}
	}
	
	@Test
	public void getUsers() {
		UserVO filter = new UserVO();
		filter.setUsername("abbd__5");
		List<UserVO> list = manager.getUsers(filter );
		
		for (UserVO user : list) {
			System.out.println("user filter : " + user.getMail());
		}
	}*/
}
