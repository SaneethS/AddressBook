package com.addressbook;

import java.util.*;

/**@class which is created where collection class is used
 * @author saneeths
 *
 */
public class AddressBook {
	
	 private List<Contact> contactInfo = new ArrayList<Contact>(); 
	 
	 
	 public void addContactDetails(Contact c) {
		 contactInfo.add(c);
	 }
	 
	 public List<Contact> getAddress(){
		 return contactInfo;
	 }
}
