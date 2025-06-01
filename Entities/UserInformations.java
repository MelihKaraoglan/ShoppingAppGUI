package Entities;

public class UserInformations {
    private String name, surname, username, password, role;
    private int age;

    public UserInformations(String name, String surname, String username, String password, String role, int age) {
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.password = password;
        this.role = role;
        this.age = age;
    }
	
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	
	

}
