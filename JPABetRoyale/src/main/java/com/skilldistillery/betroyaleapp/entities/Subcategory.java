package com.skilldistillery.betroyaleapp.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Subcategory {

//Variable Declarations ------------------------

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String name;
	private String description;

	@ManyToMany(mappedBy="subcategories")
	private List<BettableEvent> bettableEvents;
	
	
	
//End Variable Declarations -----------------------

	// Begin Constructors ============================

	public Subcategory() {
		super();
	}


	// End Constructors ============================
	
	public void addBettableEvent(BettableEvent event) {
		if(bettableEvents == null) {
			bettableEvents = new ArrayList<>();
			
		}
		if(!bettableEvents.contains(event)) {
			bettableEvents.add(event);
			event.addSubcategory(this);
		}
	
		
		
	}
	
	public void removeBettableEvent(BettableEvent event) {
		if(event != null && bettableEvents.contains(event)) {
			bettableEvents.remove(event);
			event.removeSubcategory(this);
		}
	}
	
	
	
	
	
	
	

//Begin G&S %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
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



	
//End G&S %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%	
	
//Begin Hashcode, Equals, toString *******************************

	public List<BettableEvent> getBettableEvents() {
		return bettableEvents;
	}


	public void setBettableEvents(List<BettableEvent> bettableEvents) {
		this.bettableEvents = bettableEvents;
	}


	@Override
	public int hashCode() {
		return Objects.hash(description, id, name);
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Subcategory other = (Subcategory) obj;
		return Objects.equals(description, other.description) && id == other.id && Objects.equals(name, other.name);
	}
	
//End Hashcode, Equals, toString *******************************
}
