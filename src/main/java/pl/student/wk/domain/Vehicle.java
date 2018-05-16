/*package pl.student.wk.domain;

import javax.persistence.*;

@Entity
@Table(name = "Vehicle")
public class Vehicle {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Vehicle_id")
	int id;
	
	
	private long przebieg;
	private String name;
	private boolean available;
	private int hp;
	@Column(name = "user_id")
	@ManyToOne(fetch=FetchType.EAGER)
	private User user;

	public long getPrzebieg() {
		return przebieg;
	}

	public void setPrzebieg(long przebieg) {
		this.przebieg = przebieg;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	

}
*/