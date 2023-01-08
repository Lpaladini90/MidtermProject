package com.skilldistillery.betroyaleapp.entities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "bettable_event")
public class BettableEvent {
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	@Id
	private Integer id;

	private String name;

	private String description;

	@Column(name = "image_url")
	private String imageURL;

	@Column(name = "end_date")
	private LocalDateTime endDate;

	@Column(name = "date_created")
	private LocalDateTime dateCreated;

	private Boolean active;

	private Boolean completion;

	@OneToMany(mappedBy = "event")
	private List<Contender> contenders;

	@OneToMany(mappedBy = "bettableEvent")
	private List<EventReview> eventReview;

	@OneToOne
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "bettable_event_has_subcategory", joinColumns = @JoinColumn(name = "bettable_event_id"), inverseJoinColumns = @JoinColumn(name = "subcategory_id"))
	private List<Subcategory> subcategories;

	public BettableEvent() {
		super();
	}

	public void addSubcategory(Subcategory sub) {
		if (subcategories == null) {
			subcategories = new ArrayList<>();
		}
		if (!subcategories.contains(sub)) {
			subcategories.add(sub);
			sub.addBettableEvent(this);
		}
	}

	public void removeSubcategory(Subcategory sub) {
		if (sub != null && subcategories.contains(sub)) {
			subcategories.remove(sub);
			sub.removeBettableEvent(this);
		}
	}

	public String formattedEndDate() {
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM dd yyyy");
	    String formattedDateTime = endDate.format(formatter);
		return formattedDateTime;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

	public LocalDateTime getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDateTime endDate) {
		this.endDate = endDate;
	}

	public LocalDateTime getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(LocalDateTime dateCreated) {
		this.dateCreated = dateCreated;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Boolean getCompletion() {
		return completion;
	}

	public void setCompletion(Boolean completion) {
		this.completion = completion;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Subcategory> getSubcategories() {
		return subcategories;
	}

	public void setSubcategories(List<Subcategory> subcategories) {
		this.subcategories = subcategories;
	}

	public List<Contender> getContenders() {
		return contenders;
	}

	public void setContenders(List<Contender> contenders) {
		this.contenders = contenders;
	}

	public List<EventReview> getEventReview() {
		return eventReview;
	}

	public void setEventReview(List<EventReview> eventReview) {
		this.eventReview = eventReview;
	}

	@Override
	public String toString() {
		return "BettableEvent [id=" + id + ", name=" + name + ", description=" + description + ", imageURL=" + imageURL
				+ ", endDate=" + endDate + ", dateCreated=" + dateCreated + ", active=" + active + ", completion="
				+ completion + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(active, completion, dateCreated, description, endDate, id, imageURL, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BettableEvent other = (BettableEvent) obj;
		return Objects.equals(active, other.active) && Objects.equals(completion, other.completion)
				&& Objects.equals(dateCreated, other.dateCreated) && Objects.equals(description, other.description)
				&& Objects.equals(endDate, other.endDate) && id == other.id && Objects.equals(imageURL, other.imageURL)
				&& Objects.equals(name, other.name);
	}

}
