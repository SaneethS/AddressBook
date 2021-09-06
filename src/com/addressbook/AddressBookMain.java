package com.addressbook;

import java.math.BigInteger;

import java.util.*;


/**@class which is created to call all the funtions from Contact and AddressBook
 * @author saneeths
 *
 */
public class AddressBookMain {
	private static Scanner scanner = new Scanner(System.in);
	private static Map<String,AddressBook> map = new HashMap<String,AddressBook>();
	private static AddressBook addressBook = null;
	

	public static void main(String[] args) {
		System.out.println("Welcome to Address Book program");
		
		
		chooseBook();
		
		while(true) {
			System.out.println("\nEnter your choice\n1.Add Contacts\n2.Display Contact\n3.Edit Contacts\n"
					+ "4.Delete Contacts\n5.Choose Address Book\nAny other choice: Exit\n");
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
				case 5:
					chooseBook();
					break;
				default:
					return;
			}
		}
	}

	

	/**
	 * @method has been created choose the books
	 */
	private static void chooseBook() {
		System.out.println("1. Create New Address Book\n2. Select from existing Address Book");
        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice == 1) {
            System.out.println("Enter name of the Address Book");
            String addressBookName = scanner.nextLine();
            map.put(addressBookName, new AddressBook());
            addressBook = map.get(addressBookName);
            System.out.println("New address book has been created");
        }
        else {
            if (map.size() == 0) {
                System.out.println("Address Book Not found");
                chooseBook();
                return;
            }

            int index = 1;
            for (String book : map.keySet()) {
                System.out.println(index + ". " + book);
                index++;
            }
            System.out.println("Enter the name of address book");
            String bookName = scanner.nextLine();
            addressBook = map.get(bookName);
        }
	}



	/**
	 * method which is created to delete the contacts
	 */
	private static void deleteContacts() {
		Set<Contact> contactDetails = addressBook.getAddress();
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



	/**
	 * method which is created to edit contacts
	 */
	private static void editContacts() {
		Set<Contact> contactDetails = addressBook.getAddress();
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



	/**
	 * method which is created to add contacts
	 */
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
		addressBook.addContactDetails(contact);
	}
	
	/**
	 * method which is created to dispaly the contacts
	 */
	private static void displayContacts() {
		Set<Contact> contactDetails = addressBook.getAddress();
		if(contactDetails.size() == 0) {
			System.out.println("Address book is empty");
		}else {
			for(Contact c: contactDetails) {
				System.out.println(c);
			}
		}
	}
}
