package com.addressbook;

import java.math.BigInteger;
import java.util.*;

public class AddressBookMain {
	private static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		System.out.println("Welcome to Address Book program");
		
		
		while(true) {
			System.out.println("\nEnter your choice\n1.Add Contacts\n2.Display Contact\n3.Edit Contacts\n"
					+ "4.Delete Contacts\nAny other choice: Exit\n");
			int choice = scanner.nextInt();
				switch(choice) {
				case 1:
					addContacts();
					break;
				case 2:
					displayContacts();
					break;
				case 3:
					editContacts();
					break;
				case 4:
					deleteContacts();
					break;
				default:
					return;
			}
		}
	}

	

	private static void deleteContacts() {
		Set<Contact> contactDetails = AddressBook.getInstance().getAddress();
		boolean flag = false;
		Contact delete = null;
		
		System.out.println("Enter the first name to edit");
		scanner.nextLine();
		String firstName = scanner.nextLine();
		System.out.println("Enter the last name to edit");
		String lastName = scanner.nextLine();
		
		for(Contact c: contactDetails) {
			if(c.getFirstName().equals(firstName) && c.getLastName().equals(lastName)) {
				delete = c;
				flag = true;
			}
		}
		
		if(flag == false) {
			System.out.println("No contacts found");
		}else {
			contactDetails.remove(delete);
			System.out.println("Deleted the contact!!!");
		}
	}



	private static void editContacts() {
		Set<Contact> contactDetails = AddressBook.getInstance().getAddress();
		boolean flag = false;
		Contact edit = null;
		
		System.out.println("Enter the first name to edit");
		scanner.nextLine();
		String firstName = scanner.nextLine();
		System.out.println("Enter the last name to edit");
		String lastName = scanner.nextLine();
		
		for(Contact c: contactDetails) {
			if(c.getFirstName().equals(firstName) && c.getLastName().equals(lastName)) {
				edit = c;
				flag = true;
			}
		}
		
		if(flag == false) {
			System.out.println("Contact not found!!");
			return;
		}
		
		while(true) {
			System.out.println("Enter the field from the folloeing which needs to be edited:");
			System.out.println("1.First Name\n2.Last Name\n3.Address\n4.City\n5.State\n6.Zip\n7.Mobile No."
					+ "\n8.Email\nAny other choice: Exit\n");
			int choice = scanner.nextInt();
			
			switch(choice) {
			case 1:
				System.out.println("Enter new first name");
				scanner.nextLine();
				edit.setFirstName(scanner.nextLine());
				break;
			case 2:
				System.out.println("Enter new last name");
				scanner.nextLine();
				edit.setLastName(scanner.nextLine());
				break;
			case 3:
				System.out.println("Enter new Address");
				scanner.nextLine();
				edit.setAddress(scanner.nextLine());
				break;
			case 4:
				System.out.println("Enter new city name");
				scanner.nextLine();
				edit.setCity(scanner.nextLine());
				break;
			case 5:
				System.out.println("Enter new State");
				scanner.nextLine();
				edit.setState(scanner.nextLine());
				break;
			case 6:
				System.out.println("Enter new zip code");
				edit.setZip(scanner.nextInt());
				break;
			case 7:
				System.out.println("Enter new Mobile Number");
				edit.setMobileNo(scanner.nextBigInteger());
				break;
			case 8:
				System.out.println("Enter new Email");
				scanner.nextLine();
				edit.setEmail(scanner.nextLine());
				break;
			default:
				return;
			}
		}
	}



	private static void addContacts() {
		
		Contact contact = new Contact();
		
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
		AddressBook.getInstance().addContactDetails(contact);
	}
	
	private static void displayContacts() {
		Set<Contact> contactDetails = AddressBook.getInstance().getAddress();
		if(contactDetails.size() == 0) {
			System.out.println("Address book is empty");
		}else {
			for(Contact c: contactDetails) {
				System.out.println(c);
			}
		}
	}
}
