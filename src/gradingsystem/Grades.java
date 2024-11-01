
package gradingsystem;

import java.util.Scanner;


public class Grades {
    
    public void addGrades(){
        
          ConFig cf = new ConFig();
          Scanner sc = new Scanner(System.in);
          
        System.out.println(" - Student List - ");
        Student st = new Student();
        st.viewStudent();
        System.out.println(" - Program List - ");
        Program pg = new Program();
        pg.viewProgram();
        
       int std;
                while (true) {
                System.out.print("Enter Student ID: ");
                if (sc.hasNextInt()) {
                    std = sc.nextInt();
                    if (cf.getSingleValues("SELECT Student_ID  FROM Students  WHERE Student_ID = ?", std) != 0) {
                        break;
                    } else {
                        System.out.println("Selected Student doesn't exist.");
                    }
                } else {
                    System.out.println("Invalid input. Please enter a integer Student ID.");
                    sc.next(); 
                }
            }
        
      int pid;
                while (true) {
                System.out.print("Enter Program  ID: ");
                if (sc.hasNextInt()) {
                    pid = sc.nextInt();
                    if (cf.getSingleValues("SELECT Program_ID  FROM Program  WHERE Program_ID = ?", pid) != 0) {
                        break;
                    } else {
                        System.out.println("Selected Program doesn't exist.");
                    }
                } else {
                    System.out.println("Invalid input. Please enter a integer Program ID.");
                    sc.next(); 
                }
            }
       String year  = "SELECT Year_and_Section FROM Students WHERE Student_ID = ?";
       String sec = cf.getSingleVal(year, std);
        
        System.out.print("Prelim: ");
        double pre = sc.nextDouble();
        
        System.out.print("Midterm: ");
        double mid  = sc.nextDouble();
        
        System.out.print("Pre - Final: ");
        double pref = sc.nextDouble();
        
        double divide = 3;
        double average = pre + mid + pref;
        
        double averag1 = average / divide;
        
        System.out.printf("Final Grade: %.2f\n", averag1);
        
           System.out.print("Status; ");
           String status = sc.next();
     

        
        String grades = "INSERT INTO Grades (Student_ID, Program_ID, Year_and_Section, Prelim, Midterm, Pre_Final, Final, Status) VALUES (?,?,?,?,?,?,?,?)";
        cf.addStudent(grades, std, pid, sec, pre, mid, pref, averag1, status);
    }
    
    public void viewGrade() {
    String view = "SELECT Grades.Grades_ID, Students.First_Name, Students.Last_Name, Program.Program, Students.Year_and_Section, "
                + "Grades.Prelim, Grades.Midterm, Grades.Pre_Final, Grades.Final, Grades.Status "
                + "FROM Grades "
                + "LEFT JOIN Students ON Students.Student_ID = Grades.Student_ID "
                + "LEFT JOIN Program ON Program.Program_ID = Grades.Program_ID";
    
    String[] header = {"Grade ID", "First Name ", "Last Name", "Program", "Year and Section", "Prelim", "Midterm", "Pre - Final", "Final", "Status"};
    String[] column = {"Grades_ID", "First_Name", "Last_Name", "Program", "Year_and_Section", "Prelim", "Midterm", "Pre_Final", "Final", "Status"};
    
    ConFig cf = new ConFig();
    cf.viewStudent(view, header, column);
}

    
    
    public void mainGrade(){
        
         ConFig cf = new ConFig();
          Scanner sc = new Scanner(System.in);
        Grades gr = new Grades();
        
        String res;
        do{
            System.out.println("\n=======================================");
        System.out.println("        Grade Management Menu         ");
        System.out.println("=======================================");
        System.out.println("1. Add Grade Record");
        System.out.println("2. View Grade Record");
        System.out.println("3. Update Grade Record");
        System.out.println("4. Delete Grade Record");
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
                 gr.addGrades();
                 break;
             case 2:
                 gr.viewGrade();
                 break;
             case 3:
                    gr.viewGrade();
                    String updating = "UPDATE Grades SET Prelim = ?, Midterm = ?,  Pre_Final = ?, Final = ?, Status = ? WHERE Grades_ID  = ?";


                     int iupdate;
                while (true) {
                System.out.print("Enter Grade Record ID to update: ");
                if (sc.hasNextInt()) {
                    iupdate = sc.nextInt();
                    if (cf.getSingleValues("SELECT Grades_ID  FROM Grades  WHERE Grades_ID = ?", iupdate) != 0) {
                        break;
                    } else {
                        System.out.println("Selected Grade Record doesn't exist.");
                    }
                } else {
                    System.out.println("Invalid input. Please enter a integer Grade Record ID.");
                    sc.next(); 
                }
            }
                    System.out.print("Enter new Prelim Grade: ");
                    double newpre = sc.nextDouble();
                    System.out.print("Enter new Midterm Grade: ");
                    double newmid = sc.nextDouble();
                    System.out.print("Enter new Pre - Final Grade: ");
                    double newpref = sc.nextDouble();

                    System.out.print("Enter new Status: ");
                    String newstats = sc.next();

                    double newAverage = (newpre + newmid + newpref) / 3.0;
                    System.out.printf("New Final Grade: %.2f\n", newAverage);

                    cf.updateStudent(updating, newpre, newmid, newpref,newAverage, newstats, iupdate);
                    break;
                case 4:
                    gr.viewGrade();
                 String deleting = "DELETE FROM Grades WHERE Grades_ID = ?  ";
                 
                 
                 
                 
                     int gradeID;
                while (true) {
                System.out.print("Enter Grade Record ID to delete: ");
                if (sc.hasNextInt()) {
                    gradeID = sc.nextInt();
                    if (cf.getSingleValues("SELECT Grades_ID  FROM Grades  WHERE Grades_ID = ?", gradeID) != 0) {
                        break;
                    } else {
                        System.out.println("Selected Grade Record doesn't exist.");
                    }
                } else {
                    System.out.println("Invalid input. Please enter a integer Grade Record ID.");
                    sc.next(); 
                }
            }
                 cf.deleteStudent(deleting, gradeID);
                 break;
             
             case 5:
                 System.out.println("Exiting.......");
                 break;
             
         }
         System.out.println("");
         System.out.print("Do you want to continue on Grade Panel? Yes or No: ");
         res = sc.next();
    }while(res.equalsIgnoreCase("yes"));
        
        
        
    }
    
    
    
    
    
    
    
    
    
    
    
}
