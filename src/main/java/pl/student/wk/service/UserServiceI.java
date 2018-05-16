package pl.student.wk.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.student.wk.DAOuser.DAOuser;
import pl.student.wk.domain.User;

@Service
@Transactional
public class UserServiceI implements UserService{

	@Autowired
	DAOuser userDAO;
	
	@Override
	public void addUser(User user) {
		userDAO.addUser(user);
	}
	
	
	public void editUser(User user) {
		userDAO.editUser(user);
	}

	
	public List<User> listUser() {
		
		return userDAO.listUser();
	}

	
	public void removeUser(int id) {
		userDAO.removeUser(id);
	}
	
	
	public User getUser(String column, String arg) {
		return userDAO.getUser(column, arg);
	}


	@Override
	public User updateUser(User user) {
		return userDAO.updateUser(user);
		
	}
}
	

