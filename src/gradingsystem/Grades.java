
package gradingsystem;

import java.util.Scanner;


public class Grades {
    
    public void addGrades(){
        
          ConFig cf = new ConFig();
          Scanner sc = new Scanner(System.in);
          
        System.out.println(" - Enrolled List - ");
         Course_Enroll ce = new Course_Enroll();
         ce.view();
        
       int std;
                while (true) {
                System.out.print("Enter Enroll ID: ");
                if (sc.hasNextInt()) {
                    std = sc.nextInt();
                    if (cf.getSingleValues("SELECT Enroll_ID  FROM Course_Enroll  WHERE Enroll_ID = ?", std) != 0) {
                        break;
                    } else {
                        System.out.println("Selected ID doesn't exist.");
                    }
                } else {
                    System.out.println("Invalid input. Please enter a integerID.");
                    sc.next(); 
                }
            }
        
      
       String year  = "SELECT Year_and_Section FROM Course_Enroll WHERE Enroll_ID = ?";
       String sec = cf.getSingleVal1(year, std);
       System.out.print("Prelim: ");
        double pre = sc.nextDouble();

        System.out.print("Midterm: ");
        double mid = sc.nextDouble();

        System.out.print("Pre - Final: ");
        double pref = sc.nextDouble();

        double divide = 3;
        double average = (pre + mid + pref) / divide;

        System.out.printf("Final Grade: %.2f\n", average);

       
        String status = average >= 3.0 ? "Failed" : "Passed";
        System.out.println("Status: " + status);
        

        
        String grades = "INSERT INTO Grades (Enroll_ID, Year_and_Section, Prelim, Midterm, Pre_Final, Final, Status) VALUES (?,?,?,?,?,?,?)";
        cf.addStudent(grades, std, sec, pre, mid, pref, average, status);
    }
    
    public void viewGrade() {
   String view = "SELECT Grades.Grades_ID, Students.First_Name, Students.Last_Name, "
                + "Course.Course, Course.Course_Code, Course_Enroll.Year_and_Section, "
                + "Grades.Prelim, Grades.Midterm, Grades.Pre_Final, Grades.Final, Grades.Status "
                + "FROM Grades "
                + "JOIN Course_Enroll ON Course_Enroll.Enroll_ID = Grades.Enroll_ID "
                + "JOIN Students ON Students.Student_ID = Course_Enroll.Student_ID "
                + "JOIN Course ON Course.Program_ID = Course_Enroll.Course_ID";


    
    String[] header = {"Grade ID", "First Name", "Last Name", "Course","Course Code","Year and Section","Prelim", "Midterm", "Pre - Final", "Final", "Status"};
String[] column = {"Grades_ID", "First_Name", "Last_Name", "Course","Course_Code","Year_and_Section","Prelim", "Midterm", "Pre_Final", "Final", "Status"};

    ConFig cf = new ConFig();
    cf.viewStudent(view, header, column);
}
    public void viewIT201() {
    String viewing = "SELECT Grades.Grades_ID, Students.First_Name, Students.Last_Name, Grades.Final, Grades.Status "
            + "FROM Grades " 
            + "JOIN Course_Enroll ON Course_Enroll.Enroll_ID = Grades.Enroll_ID "
            + "JOIN Students ON Students.Student_ID = Course_Enroll.Student_ID "
            + "JOIN Course ON Course.Program_ID = Course_Enroll.Course_ID "
            + "WHERE Course.Course_Code = 'IT201'";

    String[] header = {"Grade ID", "First Name", "Last Name", "Final Grade", "Status"};
    String[] column = {"Grades_ID", "First_Name", "Last_Name", "Final", "Status"};

   
    ConFig cf = new ConFig();
    cf.viewStudent(viewing, header, column);
}

public void viewPF201(){
    String viewing = "SELECT Grades.Grades_ID, Students.First_Name, Students.Last_Name, Grades.Final, Grades.Status "
            + "FROM Grades " 
            + "JOIN Course_Enroll ON Course_Enroll.Enroll_ID = Grades.Enroll_ID "
            + "JOIN Students ON Students.Student_ID = Course_Enroll.Student_ID "
            + "JOIN Course ON Course.Program_ID = Course_Enroll.Course_ID "
            + "WHERE Course.Course_Code = 'PF201'";

    String[] header = {"Grade ID", "First Name", "Last Name", "Final Grade", "Status"};
    String[] column = {"Grades_ID", "First_Name", "Last_Name", "Final", "Status"};

   
    ConFig cf = new ConFig();
    cf.viewStudent(viewing, header, column);
    
}
public void viewById(){
      
      ConFig cf = new ConFig();
     Scanner sc = new Scanner(System.in);
     
     int studentId;
while (true) {
    System.out.print("Enter Student ID to view their grades and details: ");
    if (sc.hasNextInt()) {
        studentId = sc.nextInt();
        if (cf.getSingleValues("SELECT Student_ID  FROM Students WHERE Student_ID = ?", studentId) != 0) {
            break;
        } else {
            System.out.println("Student with this ID does not exist.");
        }
    } else {
        System.out.println("Invalid input. Please enter a valid numeric Student ID.");
        sc.next(); 
    }
}
 
String viewQuery = "SELECT Grades.Grades_ID, Students.First_Name, Students.Last_Name, Course_Enroll.Year_and_Section, " +
                   "Grades.Prelim, Grades.Midterm, Grades.Pre_Final, Grades.Final, Grades.Status " +
                   "FROM Grades " +
                   "JOIN Course_Enroll ON Grades.Enroll_ID = Course_Enroll.Enroll_ID " +
                   "JOIN Students ON Course_Enroll.Student_ID = Students.Student_ID " +
                   "WHERE Students.Student_ID = ?";



String[] header = {"ID","First Name", "Last Name", "Year & Section", "Prelim", "Midterm", "Pre-Final", "Final Grade", "Status"};
String[] columns = {"Grades_ID","First_Name", "Last_Name", "Year_and_Section", "Prelim", "Midterm", "Pre_Final", "Final", "Status"};
    
    
    cf.viewStudents(viewQuery, header, columns, studentId);
    
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
        System.out.println("5. View IT201 course With Final Grade");
        System.out.println("6. View PF201 course With Final Grade");
        System.out.println("7. View Students By Specific Id");
        System.out.println("8. Exit");
        System.out.println("=======================================");
          int choice;
         while (true) {
                System.out.print("Enter choice: ");
                if (sc.hasNextInt()) {
                    choice = sc.nextInt();
                    if (choice >= 1 && choice <= 8) {
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

                     double newAverage = (newpre + newmid + newpref) / 3.0;
                     System.out.printf("New Final Grade: %.2f\n", newAverage);


                    String newstats = newAverage >= 3.0 ? "Failed" : "Passed";
                    System.out.println("New Status: " + newstats);

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
                 gr.viewIT201();
                 break;
             case 6:
               gr.viewPF201();
                 break;
             case 7:
                Student sts = new Student();
                sts.viewStudent();
               gr = new Grades();
               gr.viewById();
                 break;
             case 8:
                 
                 break;
         }
         System.out.println("");
         System.out.print("Do you want to continue on Grade Panel? Yes or No: ");
         res = sc.next();
    }while(res.equalsIgnoreCase("yes"));
        
        
        
    }
    
    
    
    
    
    
    
    
    
    
    
}
