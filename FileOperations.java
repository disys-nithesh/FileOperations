package java_project;

import java.io.*;
import java.util.*;

class Student implements Serializable{
	int age;
	String name;
	String adress;
	String email;
	long contact;

	Student(int age,String name,String adress,String email, long contact){
		this.age=age;
		this.name=name;
		this.adress=adress;
		this.email=email;
		this.contact=contact;
	}

}


public class FileOperations {
	public static void main(String[]args)throws Exception {
		int choice=-1;
		Scanner s=new Scanner(System.in);
		Scanner sc=new Scanner(System.in);
		File file = new File("C:\\Users\\nithesh\\Desktop\\Students.txt");
		ArrayList<Student> al=new ArrayList<Student>();
		ObjectOutputStream oos =null;
		ObjectInputStream ois = null;
		ListIterator li = null;
		if(file.isFile()){
			ois = new ObjectInputStream(new FileInputStream(file));
			al = (ArrayList<Student>)ois.readObject();
			ois.close();
		}
		do {
			System.out.println("1.Insert");
			System.out.println("2.Display");
			System.out.println("3.Delete");
			System.out.println("0.Exit");
			System.out.println("Enter Your Choice : ");
			choice=s.nextInt();

			switch(choice) {
			case 1:
			{
				System.out.println("Enter the Number of Students you want to Add : ");
				int n=s.nextInt();
				for(int i=0;i<n;i++) {
					System.out.println("Enter Student Name : ");
					String name=sc.nextLine();

					System.out.println("Enter Student Adress : ");
					String adress=sc.nextLine();

					System.out.println("Enter Student Email : ");
					String email=sc.nextLine();

					System.out.println("Enter Student Age : ");
					int age=s.nextInt();

					System.out.println("Enter Student Contact : ");
					long contact=s.nextLong();

					al.add(new Student(age,name,adress,email,contact));
				}
				oos=new ObjectOutputStream(new FileOutputStream(file));
				oos.writeObject(al);
				oos.close();
				break;
			}
			case 2:
			{
				if(file.isFile()){
					ois = new ObjectInputStream(new FileInputStream(file));
					al = (ArrayList<Student>)ois.readObject();
					ois.close();

					System.out.println("");
					li = al.listIterator();
					while(li.hasNext())
						System.out.println(li.next());
					System.out.println("");
				}else{
					System.out.println("File not Exists....!");
				}
				break;
			}
			case 3:
			{
				ois = new ObjectInputStream(new FileInputStream(file));
				al = (ArrayList<Student>)ois.readObject();
				ois.close();
				if(file.isFile()){
					boolean found = false;
					System.out.print("Enter Student Name to Delete : ");
					String name= sc.nextLine();
					System.out.println("");
					li = al.listIterator();
					while(li.hasNext()){
						Student e = (Student)li.next();
						if(e.name == name){
							li.remove();
							found = true;
						}
					}
					if(found){
						oos = new ObjectOutputStream(new FileOutputStream(file));
						oos.writeObject(al);
						oos.close();
						System.out.println("Record Deleted Successfully....!");
					}
					else{
						System.out.println("Record Not Found...!");                      
					}
					System.out.println("");
				}else{
					System.out.println("File not Exists....!");
				}
				break;  
			}
			}
		}while(choice!=0);
	}
}
