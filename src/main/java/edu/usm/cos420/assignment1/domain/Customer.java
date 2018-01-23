package edu.usm.cos420.assignment1.domain;

import java.io.Serializable;
import java.util.Map;

public class Customer implements Serializable{
	
		private static final long serialVersionUID = -8605464037542649412L;
		
		private int id;
		private String address;
		private String name;
		
		public Customer(int id, String address, String name) {
			super();
			this.id = id;
			this.address = address;
			this.name = name;
		}

		public int getId() {
			return id;
		}

		public String getAddress() {
			return address;
		}

		public String getName() {
			return name;
		}

		public void setId(int id) {
			this.id = id;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		public void setName(String name) {
			this.name = name;
		}
		
}
