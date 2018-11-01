package com.donation.DonationMakeDifference;
import java.util.*;
import java.lang.*;
import java.io.*;
import java.io.File;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class User implements Serializable  {
	private int id;
	private String name;
	private String email;
	private String password;
	private Payment payment;
	private Donation donation;
	private int applicationCount;
	private DonationApplication application;
	
	public User(String name, String email, String password) {
		Random rand = new Random();
		id = rand.nextInt(99999999) + 100000000;
		setId(id);
		setName(name);
		setEmail(email);
		setPassword(password);
	}
	
	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
	public boolean registerAccount(User user){
		try
        {    
            //Saving of object in a file 
            FileOutputStream file = new FileOutputStream(String.format("userdata/%s.ser",email)); 
            ObjectOutputStream out = new ObjectOutputStream(file); 
              
            // Method for serialization of object 
            out.writeObject(user); 
              
            out.close(); 
            file.close(); 
              
            System.out.println("Object has been serialized"); 
  
        } 
          
        catch(IOException ex) 
        { 
            System.out.println("IOException is caught"); 
        } 
		return false;
	}
	
	public User logIn(String email, String password, String filepath) {
		User user = null;
        // Deserialization 
        try
        {    
            // Reading the object from a file 
            FileInputStream file = new FileInputStream(String.format("/userdata/%s.ser",email)); 
            ObjectInputStream in = new ObjectInputStream(file); 
              
            // Method for deserialization of object 
            user = (User)in.readObject(); 
              
            in.close(); 
            file.close(); 
              
            System.out.println("Object has been deserialized "); 
        } 
          
        catch(IOException ex) 
        { 
            System.out.println("IOException is caught"); 
        } 
          
        catch(ClassNotFoundException ex) 
        { 
            System.out.println("ClassNotFoundException is caught"); 
        } 
        return user;
	}
	
//	public abstract boolean displayDonationList(ArrayList<Donation> arrayList1);
//	public abstract Donation checkDonationDetail(Donation donationinfo);
//	public abstract boolean linkCard(Payment payment);
//	public abstract String donate(int amount);
	
	public void submitApplication() {
		Scanner input = new Scanner(System.in);
		application = new DonationApplication(id,email,name);
		System.out.println("Please enter your valid government issued id: ");
		String govid = input.nextLine();
		System.out.println("Please enter donation title: ");
		String title = input.nextLine();
		System.out.println("Please enter donation Reason: ");
		String reason = input.nextLine();
		System.out.println("Please enter donation amount: ");
		String amount = input.nextLine();
		application.setInformation(govid, title, reason, amount);
		applicationCount+=1;
		System.out.println("Submit Successfully");
		input.close();
	}
	
}
