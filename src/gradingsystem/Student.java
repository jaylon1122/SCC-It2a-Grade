
package gradingsystem;

import java.util.Scanner;

 
public class Student {
     public void addStudents(){
            
          ConFig cf = new ConFig();
          Scanner sc = new Scanner(System.in);
          
        System.out.print("Enter Student First Name: ");
        String fname = sc.nextLine();
        System.out.print("Enter Student Last Name: ");
        String lname = sc.nextLine();
       
        System.out.print("Contact No. : ");
        String contact = sc.nextLine();
        System.out.print ("Address: ");
        String add = sc.nextLine();
        System.out.print("Gender: ");
        String gen = sc.nextLine();
        System.out.print("Age: ");
        int age = sc.nextInt();
        sc.nextLine();
        System.out.print("Status: ");
        String stats = sc.nextLine();          
        System.out.print("Email: ");
        String email = sc.nextLine();
        
        String sqladd = "INSERT INTO Students (First_Name, Last_Name, Contact_No, Address, Gender, Age, Status, Email) VALUES (?,?,?,?,?,?,?,?)";
            
        cf.addStudent(sqladd, fname, lname,contact, add, gen, age, stats, email);
      
     }
     
     public void viewStudent(){
         
          String qry = "SELECT * FROM Students";
        String[] hdrs = {"Student ID", "First Name", "Last Name", "Contact No", "Address","Gender", "Age","Status","Email"};
        String[] clmns = {"Student_ID", "First_Name", "Last_Name","Contact_No", "Address", "Gender", "Age","Status","Email"};

          ConFig cf = new ConFig();
         cf.viewStudent(qry, hdrs, clmns);
         
     }
     
     
     public void mainStudent(){
         
         
         
         Student sts = new Student();
           ConFig cf = new ConFig();
          Scanner sc = new Scanner(System.in);
         String response;
         do{
          System.out.println("\n=======================================");
        System.out.println("         Student Management Menu      ");
        System.out.println("=======================================");
        System.out.println("1. Add Student");
        System.out.println("2. View Student");
        System.out.println("3. Update Student");
        System.out.println("4. Delete Student");
        System.out.println("5. Exit");
        System.out.println("=======================================");
          
          
             int choice;
         while (true) {
                System.out.print("Enter choice: ");
                if (sc.hasNextInt()) {
                    choice = sc.nextInt();
                    if (choice >= 1 && choice <= 5) {
                        break;
                    } else {
                        System.out.println("Please enter a number between 1 and 5.");
                    }
                } else {
                    System.out.println("Invalid input. Please enter a valid number.");
                    sc.next();
                }
            }
        
         switch(choice){
             case 1:
                 sts.addStudents();
                 break;
             case 2:
                 sts.viewStudent();
                 break;
             case 3:
                   sts.viewStudent();
                   String sqlupdate = "UPDATE Students SET  Contact_No = ?, Address = ?, Gender = ?, Age = ?, Status = ?, Email = ? WHERE  Student_ID = ?";
                   
                    
       int id;
                while (true) {
                System.out.print("Enter Student ID: ");
                if (sc.hasNextInt()) {
                    id = sc.nextInt();
                    if (cf.getSingleValues("SELECT Student_ID  FROM Students  WHERE Student_ID = ?", id) != 0) {
                        break;
                    } else {
                        System.out.println("Selected Student doesn't exist.");
                    }
                } else {
                    System.out.println("Invalid input. Please enter a integer Student ID.");
                    sc.next();  
                }
            }
                 sc.nextLine();
                   System.out.print("Enter new Contact No: ");
                   String newcon = sc.nextLine();
                   System.out.print("Enter new Address: ");
                   String newadd = sc.nextLine();
                   System.out.print("Enter new Gender: ");
                   String newgen = sc.nextLine();
                   System.out.print("Enter new Age: ");
                   int newage = sc.nextInt();
                   sc.nextLine();
                   System.out.print("Enter new Status: ");
                   String newtats = sc.nextLine();
                   System.out.print("Enter new Email: ");
                   String newemail = sc.nextLine();
                   
                   cf.updateStudent(sqlupdate, newcon, newadd, newgen, newage, newtats, newemail, id);
                 break;
             case 4:
                 sts.viewStudent();
                 String sqldelete = "DELETE FROM Students WHERE Student_ID = ?";
             
                
                   int del;
                while (true) {
                System.out.print("Enter Student ID to delete: ");
                if (sc.hasNextInt()) {
                    del = sc.nextInt();
                    if (cf.getSingleValues("SELECT Student_ID  FROM Students  WHERE Student_ID = ?", del) != 0) {
                        break;
                    } else {
                        System.out.println("Selected Student doesn't exist.");
                    }
                } else {
                    System.out.println("Invalid input. Please enter a integer Student ID.");
                    sc.next(); 
                }
            }
                 
                 cf.deleteStudent(sqldelete, del);
                 break;
             case 5:
                 System.out.println("Exitingggggg.....");
                 break;
         }
             System.out.println("");
             System.out.print("Do you want to continue on Student Panel? Yes or No: ");
             response = sc.next();
         }while(response.equalsIgnoreCase("yes"));        
          
         
     }
}
