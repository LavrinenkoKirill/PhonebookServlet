
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.*;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;

public class Contacts {
	public static Map<String,ArrayList<String>> contacts = new HashMap<String,ArrayList<String>>();
	public static Map<String,ArrayList<String>> groups = new HashMap<String,ArrayList<String>>();

	public static synchronized void init() {
		ArrayList<String> familyMembers = getFamilyMembers();
		ArrayList<String> friendsMembers = getFriendsMembers();
		ArrayList<String> workMembers = getWorkMembers();
		groups.put("Family",familyMembers);
		groups.put("Friends",friendsMembers);
		groups.put("Work",workMembers);
		File file = new File("/home/osboxes/apache-tomcat-9.0.69/webapps/phonebook/src/Phonebook");
		ArrayList<String> strings = new ArrayList<String>();
		Scanner sc;
		try {
			sc = new Scanner(file);
			while (sc.hasNextLine()) {
				String line = sc.nextLine().trim();
				strings.add(line);
			}
			sc.close();

		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		for (int i = 0; i < strings.size(); i++){
			String[] words = strings.get(i).split("-");
			if(words[1].contains(",")){
				String[] phones = words[1].split(",");
				ArrayList<String> numbers = new ArrayList<>();
				for (int j = 0; j < phones.length; j++){
					numbers.add(phones[j]);
				}
				contacts.put(words[0],numbers);
			}
			else {
				if (!contacts.containsKey(words[0])){
					ArrayList<String> numbers = new ArrayList<>();
					numbers.add(words[1]);
					contacts.put(words[0],numbers);
				}
				else{
					contacts.get(words[0]).add(words[1]);
				}
			}
		}


	}	

	public static synchronized void add(String name,String number){
		String filepath = "/home/osboxes/apache-tomcat-9.0.69/webapps/phonebook/src/Phonebook";
		if (contacts.containsKey(name)){
			if(!contacts.get(name).contains(number)) {
				contacts.get(name).add(number);
				try{
					String text = name + "-" + number + "\n";
                                	FileWriter writer = new FileWriter(filepath, true);
                                	BufferedWriter bufferWriter = new BufferedWriter(writer);
                                	bufferWriter.write(text);
                                	bufferWriter.close();
				}
				catch (IOException e){
                               		 System.out.println(e);
                       		}

			}
		}
		else{
			try{
				ArrayList<String> numbers = new ArrayList<>();
				numbers.add(number);
				contacts.put(name,numbers);
				String text = name + "-" + number + "\n";
				FileWriter writer = new FileWriter(filepath, true);
				BufferedWriter bufferWriter = new BufferedWriter(writer);
				bufferWriter.write(text);
				bufferWriter.close();
			}
			catch (IOException e){
				System.out.println(e);
			}
		}
	}


	public static synchronized void addGroup(String group,String name){
		if(!groups.get(group).contains(name)) {
                        groups.get(group).add(name);
			 String filepath;
                	if (group.equals("Family")){
                        	filepath = "/home/osboxes/apache-tomcat-9.0.69/webapps/phonebook/src/FamilyMembers";
                	}
                	else if (group.equals("Friends")){
                        	filepath = "/home/osboxes/apache-tomcat-9.0.69/webapps/phonebook/src/FriendsMembers";
                	}
                	else {
                        	filepath = "/home/osboxes/apache-tomcat-9.0.69/webapps/phonebook/src/WorkMembers";
                	}

                	try{
                        	String text = name + "\n";
                        	FileWriter writer = new FileWriter(filepath, true);
                        	BufferedWriter bufferWriter = new BufferedWriter(writer);
                        	bufferWriter.write(text);
                        	bufferWriter.close();
                	}
                	catch (IOException e){
                        	 System.out.println(e);
                	}
		}


	}

	public static synchronized void reset(){
		contacts.clear();
	}

	public static synchronized String[] getNames() {
		if (contacts.isEmpty()) return null;
		return contacts.keySet().toArray(new String [contacts.keySet().size()]);
	}


	public static synchronized String[] getGroupNames() {
                if (groups.isEmpty()) return null;
                return groups.keySet().toArray(new String [groups.keySet().size()]);
        }


	public static synchronized boolean contains(String name) {
		if (contacts.containsKey(name)) return true;
		else return false;
	}


	public static synchronized String getString(){
		if (contacts.isEmpty()){ return "<p>null</p>";}
			StringBuilder tmp = new StringBuilder();
			Set<String> setKeys = contacts.keySet();
			ArrayList<String> numbers;
			for (String k : setKeys){
				tmp.append("<p>" + k + "\t\t\t\t\t\t\t");
				numbers = contacts.get(k);
				for (String n : numbers){
					tmp.append(n + "| ");
				}
				tmp.append("</p>\n");
			}
		return tmp.toString();
	}


	public static synchronized String getFamilyString(){
                if (contacts.isEmpty()){ return "<p>null</p>";}
                ArrayList<String> names;
		StringBuilder tmp = new StringBuilder();
		names = groups.get("Family");

		ArrayList<String> numbers;
		for (String k : names){
               		 tmp.append("<p>" + k + "\t\t\t\t\t\t\t");
                         numbers = contacts.get(k);
                         for (String n : numbers){
                                   tmp.append(n + "| ");
                          }
                         tmp.append("</p>\n");
               }
		return tmp.toString();

        }


	public static synchronized String getFriendsString(){
                if (contacts.isEmpty()){ return "<p>null</p>";}
                ArrayList<String> names;
                StringBuilder tmp = new StringBuilder();
                names = groups.get("Friends");

                ArrayList<String> numbers;
                for (String k : names){
                         tmp.append("<p>" + k + "\t\t\t\t\t\t\t");
                         numbers = contacts.get(k);
                         for (String n : numbers){
                                   tmp.append(n + "| ");
                          }
                         tmp.append("</p>\n");
               }

		return tmp.toString();

        }


	public static synchronized String getWorkString(){
                if (contacts.isEmpty()){ return "<p>null</p>";}
                ArrayList<String> names;
                StringBuilder tmp = new StringBuilder();
                names = groups.get("Work");

                ArrayList<String> numbers;
                for (String k : names){
                         tmp.append("<p>" + k + "\t\t\t\t\t\t\t");
                         numbers = contacts.get(k);
                         for (String n : numbers){
                                   tmp.append(n + "| ");
                          }
                         tmp.append("</p>\n");
               }
		return tmp.toString();

        }


	public static synchronized ArrayList<String> getFamilyMembers(){
		File file = new File("/home/osboxes/apache-tomcat-9.0.69/webapps/phonebook/src/FamilyMembers");
                ArrayList<String> strings = new ArrayList<String>();
                Scanner sc;
                try {
                        sc = new Scanner(file);
                        while (sc.hasNextLine()) {
                                String line = sc.nextLine().trim();
                                strings.add(line);
                        }
                        sc.close();

                }
                catch (FileNotFoundException e) {
                        e.printStackTrace();
                }

		return strings;

	}


	public static synchronized ArrayList<String> getFriendsMembers(){
		File file = new File("/home/osboxes/apache-tomcat-9.0.69/webapps/phonebook/src/FriendsMembers");
                ArrayList<String> strings = new ArrayList<String>();
                Scanner sc;
                try {
                        sc = new Scanner(file);
                        while (sc.hasNextLine()) {
                                String line = sc.nextLine().trim();
                                strings.add(line);
                        }
                        sc.close();

                }
                catch (FileNotFoundException e) {
                        e.printStackTrace();
                }

                return strings;
	}


	public static synchronized ArrayList<String> getWorkMembers(){
		File file = new File("/home/osboxes/apache-tomcat-9.0.69/webapps/phonebook/src/WorkMembers");
                ArrayList<String> strings = new ArrayList<String>();
                Scanner sc;
                try {
                        sc = new Scanner(file);
                        while (sc.hasNextLine()) {
                                String line = sc.nextLine().trim();
                                strings.add(line);
                        }
                        sc.close();

                }
                catch (FileNotFoundException e) {
                        e.printStackTrace();
                }

                return strings;
	}

}
