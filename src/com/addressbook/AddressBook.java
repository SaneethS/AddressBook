package com.addressbook;

import java.util.*;

public class AddressBook {
	
	 private Set<Contact> contactInfo = new HashSet<Contact>(); 
	 
	 private static AddressBook instance;
	 
	 private AddressBook() {
		 
	 }
	 
	 public static AddressBook getInstance() {
		 if(instance == null) {
			 instance = new AddressBook();
		 }
		 return instance;
	 }
	 
	 public void addContactDetails(Contact c) {
		 contactInfo.add(c);
	 }
	 
	 public Set<Contact> getAddress(){
		 return contactInfo;
	 }
}
