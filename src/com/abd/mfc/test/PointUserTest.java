package com.abd.mfc.test;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.junit.runners.MethodSorters;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.abd.mfc.enums.TypePointEnum;
import com.abd.mfc.manager.PointUserManager;
import com.abd.mfc.vo.PointUserVO;

@RunWith(JUnit4.class)
@FixMethodOrder(MethodSorters.DEFAULT)
public class PointUserTest {

	private static ClassPathXmlApplicationContext context;
	private static PointUserManager manager;
	
	@BeforeClass
	public static void initHibernate() throws Exception {
		context = new ClassPathXmlApplicationContext("applicationContext_test.xml");
		manager = (PointUserManager) context.getBean("pointUserManager");
	}
	
	@Test
	public void addUser() {
		//AdministrateurVO user = getAdministrateur();
		PointUserVO user = new PointUserVO();
		user.setIdUser(1);
		//user.setTypePt(TypePointEnum.TOT_SAISON);
		//user.setIdSaison(1l);
		//user.setIdChampionnat(1);
		user.setNbrPt(523);
		
		try {
			
			manager.add(user);
			System.out.println("_____________________________test ok");
		} catch (Exception e) {
			e.printStackTrace();
			//assertTrue(false);
		}
		
	}
}
