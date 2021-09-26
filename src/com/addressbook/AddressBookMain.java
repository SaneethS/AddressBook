package com.addressbook;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;


/**@class which is created to call all the funtions from Contact and AddressBook
 * @author saneeths
 *
 */
public class AddressBookMain {
	private static Scanner scanner = new Scanner(System.in);
	private static Map<String,AddressBook> map = new HashMap<String,AddressBook>();
	private static AddressBook addressBook = null;
	private static Map<String, List<Contact>> stateMap = new HashMap<String, List<Contact>>();
    private static Map<String, List<Contact>> cityMap = new HashMap<String, List<Contact>>();
    private static String filePath = "data/result.txt";

	public static void main(String[] args) {
		System.out.println("Welcome to Address Book program");
		
		
		chooseBook();
		
		while(true) {
			System.out.println("\nEnter your choice\n1.Add Contacts\n2.Display Contact\n3.Edit Contacts\n"
					+ "4.Delete Contacts\n5.Choose Address Book\n6.Search"
					+ " by City or State\n7.View Person by State or City\n8.Sort Person\n"
					+ "9.Write into File\nAny other choice: Exit\n");
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
				case 6:
					search();
					break;
				case 7:
					view();
					break;
				case 8:
					sortedDisplay();
					break;
				case 9:
					write(filePath);
					break;
				default:
					return;
			}
		}
	}

	
	 /**
	  * method used to write the person details to file
	 * @param fileName
	 */
	private static void write(String fileName) {
			File file = new File(fileName);
			try {
				file.createNewFile();
				FileWriter fileWriter= new FileWriter(file);
				for(Contact contact: addressBook.contactInfo) {
					fileWriter.write(contact.toString()+"\n");
				}
				fileWriter.flush();
				fileWriter.close();
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	 
	/**
	 * method in which contacts sorted according to Name, City, State or Zip
	 */
	private static void sortedDisplay() {
		List<Contact> contactDetails = addressBook.getAddress();
		if(contactDetails.size() == 0) {
			System.out.println("Address book is empty");
			return;
		}
		System.out.println("Sort by\n1. Name\n2. City\n3. State\n4. Zip\n");
		int choice = scanner.nextInt();
		scanner.nextLine();
		switch(choice) {
			case 1:
				Collections.sort(contactDetails);
				break;
			case 2:
				Collections.sort(contactDetails, new City());
				break;
			case 3:
				Collections.sort(contactDetails, new State());
				break;
			case 4:
				Collections.sort(contactDetails, new Zip());
				break;
			default:
				System.out.println("Invalid choice");
				return;
		}
		
		contactDetails.stream().forEach((contact)->{
			System.out.println(contact);
		});
		
	}



	/**
	 * method to view person by city or state
	 */
	private static void view() {
		System.out.println();
		System.out.println("View Persons by\n1.City\n2.State");
		int choice = scanner.nextInt();
		scanner.nextLine();
		switch(choice) {
			case 1:
				cityMap.clear();
				addressBook.getAddress().stream().forEach((contact)->{
					List<Contact> contactList1 = cityMap.get(contact.getCity());
		            if (contactList1 == null) {
		                List<Contact> contactList2 = new ArrayList<>();
		                contactList2.add(contact);
		                cityMap.put(contact.getCity(), contactList2);
		            }
		            else {
		                contactList1.add(contact);
		                cityMap.put(contact.getCity(), contactList1);
		            }
				});
				
				for(Map.Entry<String, List<Contact>> e: cityMap.entrySet()) {
					System.out.println("City : "+e.getKey());
					for(Contact contact: e.getValue()) {
						System.out.print(contact.getFirstName()+" "+contact.getLastName());
						System.out.println();
					}
					System.out.println();
					System.out.print("No. of Persons in City:"+e.getValue().size()+"\n");
					System.out.println("---------------------------------------------------------");
				}
				break;
				
			case 2:
				stateMap.clear();
				addressBook.getAddress().stream().forEach((contact)->{
					List<Contact> contactList1 = stateMap.get(contact.getState());
		            if (contactList1 == null) {
		                List<Contact> contactList2 = new ArrayList<>();
		                contactList2.add(contact);
		                stateMap.put(contact.getState(), contactList2);
		            }
		            else {
		                contactList1.add(contact);
		                stateMap.put(contact.getState(), contactList1);
		            }
				});
				
				for(Map.Entry<String, List<Contact>> e: stateMap.entrySet()) {
					System.out.println("State : "+e.getKey());
					for(Contact contact: e.getValue()) {
						System.out.print(contact.getFirstName()+" "+contact.getLastName());
						System.out.println();
					}
					System.out.println();
					System.out.print("No. of Persons in State: "+e.getValue().size()+"\n");
					System.out.println("---------------------------------------------------------");
					
				}
				break;
			
			default:
				return;
		}
	}



	/**
	 * method to search a person in certain city or state
	 */
	private static void search() {
		System.out.println();
		System.out.println("Search Person by\n1.City\n2.State");
		int choice = scanner.nextInt();
		scanner.nextLine();
		
		switch(choice) {
			case 1:
				System.out.println("Enter City Name");
				String city = scanner.nextLine();
				int cityCount = 0;
				for(AddressBook addressBook: map.values()) {
					List<Contact> cityContacts = addressBook.getAddress().stream().filter((contact)->{
						return contact.getCity().equals(city);
					}).collect(Collectors.toList());
					
					cityCount+=cityContacts.size();
					
					for(Contact contact: cityContacts) {
						System.out.print(contact.getFirstName()+" "+contact.getLastName());
					}
					System.out.println();
					System.out.print("No. of Persons in City:"+cityCount+"\n");
					System.out.println("---------------------------------------------------------");
				}
				break;
			case 2:
				System.out.println("Enter State Name");
				String state = scanner.nextLine();
				int stateCount=0;
				for(AddressBook addressBook: map.values()) {
					List<Contact> stateContacts = addressBook.getAddress().stream().filter((contact)->{
						return contact.getState().equals(state);
					}).collect(Collectors.toList());
					
					stateCount+=stateContacts.size();
					
					for(Contact contact: stateContacts) {
						System.out.print(contact.getFirstName()+" "+contact.getLastName());
					}
					System.out.println();
					System.out.print("No. of Persons in State:"+stateCount+"\n");
					System.out.println("---------------------------------------------------------");
				}
				break;
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
		List<Contact> contactDetails = addressBook.getAddress();
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
		List<Contact> contactDetails = addressBook.getAddress();
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
		List<Contact> contactDetails = addressBook.getAddress();
		if(contactDetails.size() == 0) {
			System.out.println("Address book is empty");
		}else {
			contactDetails.stream().forEach((contact)->{
				System.out.println(contact);
			});
		}
	}
}
