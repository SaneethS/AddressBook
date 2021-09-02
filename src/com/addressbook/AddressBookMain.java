package com.addressbook;

import java.math.BigInteger;

public class AddressBookMain {

	public static void main(String[] args) {
		System.out.println("Welcome to Address Book program");
		
		AddressBook address = AddressBook.getInstance();
		Contact contact =new Contact();
		
		contact.setFirstName("Saneeth");
		contact.setLastName("S");
		contact.setAddress("Mannagudda");
		contact.setCity("Mangalore");
		contact.setState("Karnataka");
		contact.setZip(575003);
		contact.setMobileNo(new BigInteger("98756123456"));
		contact.setEmail("saneeth@gmail.com");
		address.addContactDetails(contact);
		
		for(Contact c: address.getAddress()) {
			System.out.println(c);
		}
		
	}

}
