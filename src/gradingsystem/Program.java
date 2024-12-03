
package gradingsystem;

import java.util.Scanner;


public class Program {
    
    
    public void addProgram(){
        
            
          ConFig cf = new ConFig();
          Scanner sc = new Scanner(System.in);
        
         System.out.print("Enter name of Course: ");
         String name = sc.nextLine();
         
         System.out.print("Enter Course Code: ");
         String code = sc.nextLine();
         System.out.print("Program Head: ");
         String head = sc.nextLine();
         String sqlprogram = "INSERT INTO Course (Course, Course_Code, Program_Head) VALUES (?,?,?)";
         cf.addStudent(sqlprogram,name, code, head); 
        
    }
    public void viewProgram(){
            ConFig cf = new ConFig();
        
         String qry = "SELECT * FROM Course";
        String[] hdrs = {"Course ID ", "Course", "Course Code", "Program Head"};
        String[] clmns = {"Program_ID", "Course", "Course_Code", "Program_Head"};
        
        cf.viewStudent(qry, hdrs, clmns);
      
        
    }
    public void mainProgram(){
        
        
        Program pg = new Program();
           ConFig cf = new ConFig();
          Scanner sc = new Scanner(System.in);
        
          String res;
          do{
        System.out.println("\n=======================================");
        System.out.println("        Program Management Menu       ");
        System.out.println("=======================================");
        System.out.println("1. Add Course");
        System.out.println("2. View Course");
        System.out.println("3. Update Course");
        System.out.println("4. Delete Course");
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
                 pg.addProgram();
                 break;
             case 2:
                 pg.viewProgram();
                 break;
             case 3:
                    pg.viewProgram();
                    String update = "UPDATE Course SET Course = ?, Course_Code  = ?, Program_Head = ? WHERE Program_ID = ? ";
                    
                 
                     int up;
                while (true) {
                System.out.print("Enter Course ID to update: ");
                if (sc.hasNextInt()) {
                    up = sc.nextInt();
                    if (cf.getSingleValues("SELECT Program_ID  FROM Course WHERE Program_ID = ?", up) != 0) {
                        break;
                    } else {
                        System.out.println("Selected Course doesn't exist.");
                    }
                } else {
                    System.out.println("Invalid input. Please enter a integer Course ID.");
                    sc.next(); 
                }
            }
                    sc.nextLine();
                    System.out.print("Enter new Course: ");
                    String newcourse  = sc.nextLine();
                    System.out.print("Enter new Course Code: ");
                    String newcode  = sc.nextLine();
                    System.out.print("Enter new Program Head: ");
                    String newhead = sc.nextLine();
                    
                    cf.updateStudent(update, newcourse,newcode, newhead, up);
                 break;
             case 4:
                  pg.viewProgram();
                  String delete = "DELETE FROM Course  WHERE Program_ID = ?";
                
                  
                  int del;
                while (true) {
                System.out.print("Enter Course ID to delete: ");
                if (sc.hasNextInt()) {
                    del = sc.nextInt();
                    if (cf.getSingleValues("SELECT Program_ID  FROM Course  WHERE Program_ID = ?", del) != 0) {
                        break;
                    } else {
                        System.out.println("Selected Course doesn't exist.");
                    }
                } else {
                    System.out.println("Invalid input. Please enter a integer Course ID.");
                    sc.next(); 
                }
            }
                  cf.deleteStudent(delete, del);
                 break;
             case 5:
                 System.out.println("Exitinggg.....");
                 break;
                 
         }
         System.out.println("");
         System.out.print("Do you want to continue on Program Panel? Yes or No: ");
         res = sc.next();
    }while(res.equalsIgnoreCase("yes"));
    }

    
}
