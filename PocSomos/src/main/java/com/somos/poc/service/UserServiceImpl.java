package com.somos.poc.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.somos.poc.model.User;



@Service("userService")
public class UserServiceImpl implements UserService{
	
	private String querieSoa = "SELECT Partition,Component,action, instance,state,tempo FROM (\r\n" + 
			"SELECT SUBSTR(COMPONENT_NAME, 1, INSTR(COMPONENT_NAME,'/')-1)   PARTITION, SUBSTR(COMPONENT_NAME, INSTR(COMPONENT_NAME,'/')+1,INSTR(COMPONENT_NAME,'!')-INSTR(COMPONENT_NAME,'/')-1) COMPONENT,SOURCE_ACTION_NAME ACTION,COMPOSITE_INSTANCE_ID INSTANCE,DECODE(COMPONENT_STATE,'0','COMPLETED','2','FAULTED','3','ABORTED','4','RECOVERY NEEDED','8','RUNNING','16','STALE') STATE,lpad(trunc((TO_DATE(TO_CHAR(UPDATED_TIME,'DD/MM/YYYY HH24:MI:SS'),'DD/MM/YYYY HH24:MI:SS') - TO_DATE(TO_CHAR(CREATED_TIME,'DD/MM/YYYY HH24:MI:SS'),'DD/MM/YYYY HH24:MI:SS')) * 24),2,'0')\r\n" + 
			" || ':' || lpad(trunc((TO_DATE(TO_CHAR(UPDATED_TIME,'DD/MM/YYYY HH24:MI:SS'),'DD/MM/YYYY HH24:MI:SS') - TO_DATE(TO_CHAR(CREATED_TIME,'DD/MM/YYYY HH24:MI:SS'),'DD/MM/YYYY HH24:MI:SS')) * 24*60) -  60 * trunc((TO_DATE(TO_CHAR(UPDATED_TIME,'DD/MM/YYYY HH24:MI:SS'),'DD/MM/YYYY HH24:MI:SS') - TO_DATE(TO_CHAR(CREATED_TIME,'DD/MM/YYYY HH24:MI:SS'),'DD/MM/YYYY HH24:MI:SS')) * 24),2,'0')\r\n" + 
			"           || ':' || lpad(trunc(MOD(((TO_DATE(TO_CHAR(UPDATED_TIME,'DD/MM/YYYY HH24:MI:SS'),'DD/MM/YYYY HH24:MI:SS') - TO_DATE(TO_CHAR(CREATED_TIME,'DD/MM/YYYY HH24:MI:SS'),'DD/MM/YYYY HH24:MI:SS')) * 24*60),(trunc((TO_DATE(TO_CHAR(UPDATED_TIME,'DD/MM/YYYY HH24:MI:SS'),'DD/MM/YYYY HH24:MI:SS') - TO_DATE(TO_CHAR(CREATED_TIME,'DD/MM/YYYY HH24:MI:SS'),'DD/MM/YYYY HH24:MI:SS')) * 24*60)))*60),2,'0') TEMPO,\r\n" + 
			"       TO_DATE(TO_CHAR(CREATED_TIME,'DD/MM/YYYY HH24:MI:SS'),'DD/MM/YYYY HH24:MI:SS'),\r\n" + 
			"       TO_DATE(TO_CHAR(UPDATED_TIME,'DD/MM/YYYY HH24:MI:SS'),'DD/MM/YYYY HH24:MI:SS'),\r\n" + 
			"       TO_DATE(TO_CHAR(CREATED_TIME,'DD/MM/YYYY HH24:MI:SS'),'DD/MM/YYYY HH24:MI:SS') data_criacao\r\n" + 
			"  FROM MEDIATOR_INSTANCE) t\r\n" + 
			" WHERE t.PARTITION = 'financeiro' and rownum <= 100  \r\n" + 
			" ORDER BY t.INSTANCE DESC";
	
	
	
	@Autowired 
	private JdbcTemplate jdbcTemplate;
	
	private static final AtomicLong counter = new AtomicLong();
	
	private static List<User> users;
	
	static{
		users= populateDummyUsers();
	}

	public List<User> findAllUsers() {
		return users;
	}
	
	public User findById(long id) {
		for(User user : users){
			if(user.getId() == id){
				return user;
			}
		}
		return null;
	}
	
	public User findByName(String name) {
		for(User user : users){
			if(user.getName().equalsIgnoreCase(name)){
				return user;
			}
		}
		return null;
	}
	
public List<com.somos.poc.model.ListaSomos> findAll() {
		
		List<com.somos.poc.model.ListaSomos> listofRows = jdbcTemplate.query(querieSoa, new com.somos.poc.model.PocSomosRowMapper());
				
		return listofRows;
	}
	public void saveUser(User user) {
		user.setId(counter.incrementAndGet());
		users.add(user);
	}

	public void updateUser(User user) {
		int index = users.indexOf(user);
		users.set(index, user);
	}

	public void deleteUserById(long id) {
		
		for (Iterator<User> iterator = users.iterator(); iterator.hasNext(); ) {
		    User user = iterator.next();
		    if (user.getId() == id) {
		        iterator.remove();
		    }
		}
	}

	public boolean isUserExist(User user) {
		return findByName(user.getName())!=null;
	}
	
	public void deleteAllUsers(){
		users.clear();
	}

	private static List<User> populateDummyUsers(){
		List<User> users = new ArrayList<User>();
		users.add(new User(counter.incrementAndGet(),"Sam",30, 70000));
		users.add(new User(counter.incrementAndGet(),"Tom",40, 50000));
		users.add(new User(counter.incrementAndGet(),"Jerome",45, 30000));
		users.add(new User(counter.incrementAndGet(),"Silvia",50, 40000));
		return users;
	}

}
