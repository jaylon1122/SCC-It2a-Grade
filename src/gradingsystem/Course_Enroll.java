
package gradingsystem;

import java.util.Scanner;


public class Course_Enroll {
    public void addenroll(){
        
        
        Scanner sc = new Scanner(System.in);
         ConFig cf = new ConFig();
        
        
         System.out.println(" - Student List  - ");
           Student sts = new Student();
           sts.viewStudent();;
         System.out.println(" - Course List  - ");
          Program pg = new Program();
          pg.viewProgram();
          
          
          
        System.out.print("Enter Student ID: ");
        int id1 = sc.nextInt();
        System.out.print("Enter Course ID: ");
        int id2 = sc.nextInt();
        System.out.print("Year and Section( 2A / 1A): ");
        String ysec = sc.next();
        
        
        String head = "SELECT Program_Head FROM Course WHERE Program_ID = ? ";
        String head1 = cf.getSingleVal(head, id2);
        
        
        
        String sql = "INSERT INTO Course_Enroll (Student_ID, Course_ID, Year_and_Section, Program_Head) VALUES (?,?,?,?)";
        cf.addStudent(sql, id1, id2, ysec,head1);
       
        
    }
    public void view(){
        
        String view = "SELECT Course_Enroll.Enroll_ID, Students.First_Name, Students.Last_Name, Course_Enroll.Year_and_Section, Course.Course, Course.Course_Code FROM Course_Enroll "
                + "LEFT JOIN Students ON Students.Student_ID = Course_Enroll.Student_ID "
                + "LEFT JOIN Course ON Course.Program_ID = Course_Enroll.Course_ID";
        
        
         String[] header = {"Enroll ID", "First Name ", "Last Name", "Year and Section", "Course", "Course Code"};
    String[] column = {"Enroll_ID", "First_Name", "Last_Name", "Year_and_Section", "Course", "Course_Code"};
        
        ConFig cf = new ConFig();
    cf.viewStudent(view, header, column);    
    }
    public void viewPF201(){
         
        String view = "SELECT Course_Enroll.Enroll_ID, Students.First_Name, Students.Last_Name, Course_Enroll.Year_and_Section, Course.Course, Course.Course_Code FROM Course_Enroll "
                + "LEFT JOIN Students ON Students.Student_ID = Course_Enroll.Student_ID "
                + "LEFT JOIN Course ON Course.Program_ID = Course_Enroll.Course_ID "
                + "WHERE Course.Course_Code = 'PF201'";
        
        
         String[] header = {"Enroll ID", "First Name ", "Last Name", "Year and Section", "Course", "Course Code"};
    String[] column = {"Enroll_ID", "First_Name", "Last_Name", "Year_and_Section", "Course", "Course_Code"};
        
        ConFig cf = new ConFig();
    cf.viewStudent(view, header, column);    
        
        
    }
    public void viewIT201(){
        
         
        String view = "SELECT Course_Enroll.Enroll_ID, Students.First_Name, Students.Last_Name, Course_Enroll.Year_and_Section, Course.Course, Course.Course_Code FROM Course_Enroll "
                + "LEFT JOIN Students ON Students.Student_ID = Course_Enroll.Student_ID "
                + "LEFT JOIN Course ON Course.Program_ID = Course_Enroll.Course_ID "
                + "WHERE Course.Course_Code = 'IT201'";
        
        
         String[] header = {"Enroll ID", "First Name ", "Last Name", "Year and Section", "Course", "Course Code"};
    String[] column = {"Enroll_ID", "First_Name", "Last_Name", "Year_and_Section", "Course", "Course_Code"};
        
        ConFig cf = new ConFig();
    cf.viewStudent(view, header, column);    
    }
    
    
    
    public void mainCourse(){
        
        
         Course_Enroll ce = new Course_Enroll();
        Scanner sc = new Scanner(System.in);
         ConFig cf = new ConFig();
        
         String res;
         do{
        System.out.println("\n=======================================");
        System.out.println("         Course Enroll  Menu         ");
        System.out.println("=======================================");
        System.out.println("1. Add Records");
        System.out.println("2. View Records");
        System.out.println("3. Update Records");
        System.out.println("4. Delete Records");
        System.out.println("5. View Student Who enrolled OOP or PF201");
        System.out.println("6. View Student Who enrolled DSA or IT201 ");  
        System.out.println("7. Exit");
        System.out.println("=======================================");
          
        
        
          
             int choice;
         while (true) {
                System.out.print("Enter choice: ");
                if (sc.hasNextInt()) {
                    choice = sc.nextInt();
                    if (choice >= 1 && choice <= 7) {
                        break;
                    } else {
                        System.out.println("Please enter a number between 1 and 6.");
                    }
                } else {
                    System.out.println("Invalid input. Please enter a valid number.");
                    sc.next();
                }
            }
        
         
         switch(choice){
             case 1:
                 ce.addenroll();
                 break;
             case 2:
                 ce.view();
                 break;
             case 3:
                 ce.view();
                 String sqlUpdate = "UPDATE Course_Enroll SET Year_and_Section = ? WHERE Enroll_ID = ? ";
                 
                 System.out.print("Enter ID to update: ");
                 int id = sc.nextInt();
                 sc.nextLine();
                 System.out.print("Enter new Year and Section(1A / 2A): ");
                 String newsec = sc.nextLine();
                 
                 cf.updateStudent(sqlUpdate, newsec, id);
                 break;
             case 4:
                 String del = "DELETE FROM Course_Enroll WHERE Enroll_ID = ? ";
                 System.out.print("Enter ID to delete: ");
                 int id1 = sc.nextInt();
                 cf.deleteStudent(del, id1);
                     break;
             case 5:
                 ce.viewPF201();
                 break;
             case 6:
                  ce.viewIT201();
                 break;
             case 7:
                 System.out.println("Exiting");
                 break;
         }
             System.out.println("");
             System.out.print("Do you want to continue? Yes or No: ");
             res = sc.next();
         }while(res.equalsIgnoreCase("yes"));
        
        
    }
}
