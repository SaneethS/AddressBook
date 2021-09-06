package com.addressbook;

import java.util.*;

/**@class which is created where collection class is used
 * @author saneeths
 *
 */
public class AddressBook {
	
	 private Set<Contact> contactInfo = new HashSet<Contact>(); 
	 
	 
	 public void addContactDetails(Contact c) {
		 contactInfo.add(c);
	 }
	 
	 public Set<Contact> getAddress(){
		 return contactInfo;
	 }
}
