
package gradingsystem;

import java.util.Scanner;


public class Program {
    
    
    public void addProgram(){
        
            
          ConFig cf = new ConFig();
          Scanner sc = new Scanner(System.in);
        
         System.out.print("Enter name of Program: ");
         String name = sc.next();
         sc.nextLine();
         System.out.print("Program Head: ");
         String head = sc.nextLine();
         System.out.print("Duration of Program: ");
         String duration = sc.nextLine();
         System.out.print("Credits: ");
         int credits = sc.nextInt();
         
         String sqlprogram = "INSERT INTO Program (Program, Program_Head, Duration, Credits) VALUES (?,?,?,?)";
         cf.addStudent(sqlprogram,name, head, duration, credits); 
        
    }
    public void viewProgram(){
            ConFig cf = new ConFig();
        
         String qry = "SELECT * FROM Program";
        String[] hdrs = {"Program ID", "Program", "Program Head", "Duration", "Credits"};
        String[] clmns = {"Program_ID", "Program", "Program_Head", "Duration", "Credits"};
        
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
        System.out.println("1. Add Program");
        System.out.println("2. View Program");
        System.out.println("3. Update Program");
        System.out.println("4. Delete Program");
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
                    String update = "UPDATE Program SET Program_Head = ?, Duration = ?, Credits = ? WHERE Program_ID = ? ";
                    
                 
                     int up;
                while (true) {
                System.out.print("Enter Program ID to update: ");
                if (sc.hasNextInt()) {
                    up = sc.nextInt();
                    if (cf.getSingleValues("SELECT Program_ID  FROM Program  WHERE Program_ID = ?", up) != 0) {
                        break;
                    } else {
                        System.out.println("Selected Program doesn't exist.");
                    }
                } else {
                    System.out.println("Invalid input. Please enter a integer Program ID.");
                    sc.next(); 
                }
            }
                    System.out.print("Enter new Program Head: ");
                    String newhead = sc.next();
                    System.out.print("Enter new Duration: ");
                    String newdur = sc.next();
                    System.out.print("Enter new Credits: ");
                    String newcred = sc.next();
                    
                    cf.updateStudent(update, newhead, newdur, newcred, up);
                 break;
             case 4:
                  pg.viewProgram();
                  String delete = "DELETE FROM Program  WHERE Program_ID = ?";
                
                  
                  int del;
                while (true) {
                System.out.print("Enter Program ID to delete: ");
                if (sc.hasNextInt()) {
                    del = sc.nextInt();
                    if (cf.getSingleValues("SELECT Program_ID  FROM Program  WHERE Program_ID = ?", del) != 0) {
                        break;
                    } else {
                        System.out.println("Selected Program doesn't exist.");
                    }
                } else {
                    System.out.println("Invalid input. Please enter a integer Program ID.");
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
