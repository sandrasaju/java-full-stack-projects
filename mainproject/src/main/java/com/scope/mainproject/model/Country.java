package com.scope.mainproject.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="country")
public class Country {
		@Id
		@GeneratedValue(strategy= GenerationType.IDENTITY)
		@Column(name="countryid")
		private int  id;
		
		@Column(name="countryname")
		private String countryname;

		public String getCountryname() {
			return countryname;
		}

		

		public int getId() {
			return id;
		}



		public void setId(int id) {
			this.id = id;
		}



		public void setCountryname(String countryname) {
			this.countryname = countryname;
		}
	
}
