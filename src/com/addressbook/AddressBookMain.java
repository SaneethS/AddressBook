package com.addressbook;

import java.math.BigInteger;
import java.util.*;

public class AddressBookMain {
	private static Scanner scanner = new Scanner(System.in);
	private static AddressBook address = AddressBook.getInstance();
	private static Contact contact =new Contact();

	public static void main(String[] args) {
		System.out.println("Welcome to Address Book program");
		
		
		
		while(true) {
			System.out.println("Enter your choice\n1.Add Contacts\n2.Display Contact\n3.Exit\n");
			int choice = scanner.nextInt();
				switch(choice) {
				case 1:
					addContacts();
					break;
				case 2:
					displayContacts();
					break;
				case 3:
					return;
			}
		}
	}

	

	private static void addContacts() {
		System.out.println("Enter first name");
		scanner.nextLine();
		contact.setFirstName(scanner.nextLine());
		System.out.println("Enter last name");
		contact.setLastName(scanner.nextLine());
		System.out.println("Enter address");
		contact.setAddress(scanner.nextLine());
		System.out.println("Enter city");
		contact.setCity(scanner.nextLine());
		System.out.println("Enter state");
		contact.setState(scanner.nextLine());
		System.out.println("Enter zip");
		contact.setZip(scanner.nextInt());
		System.out.println("Enter mobile no.");
		contact.setMobileNo(scanner.nextBigInteger());
		System.out.println("Enter email");
		scanner.nextLine();
		contact.setEmail(scanner.nextLine());
		address.addContactDetails(contact);
		
	}
	
	private static void displayContacts() {
		for(Contact c: address.getAddress()) {
			System.out.println(c);
		}
	}

}
