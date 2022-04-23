package com.skilldistillery.betroyaleapp.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class User {

//Variable Declarations ----------------------------	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String username;
	private String password;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	private String email;
	private Boolean active;
	private String role;

	@Column(name = "profile_image")
	private String profileImage;

	@Column(name = "about_me")
	private String aboutMe;

	@ManyToMany(mappedBy = "users")
	private List<ViewingParty> viewPartys;

	@OneToMany(mappedBy = "user")
	private List<Wager> wagers;

	@ManyToMany(mappedBy = "users")
	private List<Category> categories;

	@ManyToMany(mappedBy = "users")
	private List<Subcategory> subcategories;

//End Variable Declarations --------------------------

//Begin Constructors ===========================
	public User() {
		super();
	}

	public void removeCategory(Category category) {
		if (categories != null && categories.contains(category)) {
			categories.remove(category);
			category.removeUser(this);
		}

	}

	public void addCategory(Category category) {
		
		if (categories == null) {
			categories = new ArrayList<>();
			if (!categories.contains(category)) {
				categories.add(category);
				category.addUser(this);

			}
		}
	}
//End Constructors ======================

//Begin G&S %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public String getRoll() {
		return role;
	}

	public void setRoll(String roll) {
		this.role = roll;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getProfileImage() {
		return profileImage;
	}

	public void setProfileImage(String profileImage) {
		this.profileImage = profileImage;
	}

	public String getAboutMe() {
		return aboutMe;
	}

	public void setAboutMe(String aboutMe) {
		this.aboutMe = aboutMe;
	}

//End G&S %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

//Begin Hashcode, Equals, toString ***********************************

	public List<ViewingParty> getViewPartys() {
		return viewPartys;
	}

	public void setViewPartys(List<ViewingParty> viewPartys) {
		this.viewPartys = viewPartys;
	}

	public List<Wager> getWagers() {
		return wagers;
	}

	public void setWagers(List<Wager> wagers) {
		this.wagers = wagers;
	}

	@Override
	public int hashCode() {
		return Objects.hash(aboutMe, active, email, firstName, id, lastName, password, profileImage, role, username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(aboutMe, other.aboutMe) && Objects.equals(active, other.active)
				&& Objects.equals(email, other.email) && Objects.equals(firstName, other.firstName) && id == other.id
				&& Objects.equals(lastName, other.lastName) && Objects.equals(password, other.password)
				&& Objects.equals(profileImage, other.profileImage) && Objects.equals(role, other.role)
				&& Objects.equals(username, other.username);
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", email=" + email + ", active=" + active + ", role=" + role
				+ ", profileImage=" + profileImage + ", aboutMe=" + aboutMe + "]";
	}

	// End Hashcode, Equals, toString ***********************************
}
