package pl.student.wk.DAOuser;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pl.student.wk.domain.User;

@Component
public class DAOuserI implements DAOuser {

	@Autowired
	SessionFactory sessionFactory;

	public Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public void addUser(User user) {
		getCurrentSession().save(user);
	}

	public List<User> listUser() {
		Query q = getCurrentSession().createQuery("from User");
		List<User> userlist = (List<User>) q.list();
		return userlist;

	}

	public void removeUser(int id) {

		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();

		User user = (User) session.load(User.class, id);
		if (null != user) {
			session.delete(user);

		}
		transaction.commit();
		session.close();

	}

	public User getUser(String column, String arg) {

		if (column.matches("login")) {
			Query q = getCurrentSession().createQuery("from User where login = :arg").setParameter("arg", arg);
			User user = (User) q.list().get(0);
			return user;
		} else if (column.matches("email")) {
			Query q = getCurrentSession().createQuery("from User where email = :arg").setParameter("arg", arg);
			User user = (User) q.list().get(0);
			return user;
		}

		return null;

	}

	public void editUser(User user) {

		sessionFactory.getCurrentSession().update(user);
	}

	public User updateUser(User user) {

		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		session.update(user);
		transaction.commit();
		session.close();
		return user;

	}

}
