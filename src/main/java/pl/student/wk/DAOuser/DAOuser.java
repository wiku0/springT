package pl.student.wk.DAOuser;

import java.util.List;

import pl.student.wk.domain.User;;

public interface DAOuser {
	
	public void addUser(User user);
	public List<User> listUser();
	public void removeUser (int id);
	public User getUser(String column, String arg);
	public void editUser(User user);
	public User updateUser(User user);

}
