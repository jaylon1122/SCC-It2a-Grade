
package gradingsystem;

import java.util.Scanner;

public class GradingSystem {

     
     
      
      
    public static void main(String[] args) {
        
      Scanner sc = new Scanner(System.in);
      
      boolean exit = true;
      
      do{
          System.out.println("\n=======================================");
            System.out.println("           Grading System Menu        ");
            System.out.println("=======================================");
            System.out.println("1. Student");
            System.out.println("2. Program");
            System.out.println("3. Grades");
            System.out.println("4. Exit");
            System.out.println("=======================================");
         int choice;
         while (true) {
                System.out.print("Enter choice: ");
                if (sc.hasNextInt()) {
                    choice = sc.nextInt();
                    if (choice >= 1 && choice <= 4) {
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
                 Student st = new Student();
               st.mainStudent();
                 break;
             case 2:
                  Program pg = new Program();
                  pg.mainProgram();
                 break;
             case 3:
                   System.out.print("Are you a Teacher or Instructor? Yes or No: ");
                        String resp = sc.next();

                        if (resp.equalsIgnoreCase("yes")) {

                        System.out.print("Enter Teacher or Instructor password: ");
                        String hrPassword = sc.next();

                        final String HR_PASSWORD = "maestro2020";


                        if (hrPassword.equals(HR_PASSWORD)) {
                             Grades gr = new Grades();
                             gr.mainGrade();
                        } else {
                            System.out.println("Invalid Instructor password. Access denied.");
                        }
                    } else {
                        System.out.println("You do not have permission to access Teacher-only features.");
                    }
              
                 break;
             case 4:
                 System.out.print("Are you sure you want to exit? Yes or3 No: ");
                 String res = sc.next();
                 if(res.equalsIgnoreCase("yes")){
               exit = false;      
                 }
                 break;           
         }
      }while(exit);
        
        
        
        
        
        
        
        
        
        
        
        
        
        
    }
    
}