import com.student.manage.Student;
import com.student.manage.StudentDAO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Welcome to Student Management App");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while(true) {
            System.out.println("PRESS 1 TO ADD STUDENT");
            System.out.println("PRESS 2 TO ADD STUDENT (BY ID)");
            System.out.println("PRESS 3 TO DELETE STUDENT (BY ID)");
            System.out.println("PRESS 4 TO DISPLAY ALL STUDENTS");
            System.out.println("PRESS 5 TO EXIT APP");
            int c = Integer.parseInt(br.readLine());

            if(c==1) {
                // Add Student
                System.out.println("Enter Student Name : ");
                String name = br.readLine();

                System.out.println("Enter Student Phone No.: ");
                String phone = br.readLine();

                System.out.println("Enter Student City : ");
                String city = br.readLine();

                boolean existence = true;
                int k = 1;
                while(existence) {
                    existence = StudentDAO.checkStudentExistence(k);
                    if(existence)
                        k++;
                }

                Student st = new Student(k, name, phone, city);
                boolean answer = StudentDAO.insertStudentToDB(k,st);
                if(answer) {
                    System.out.println("Student is Added Successfully.");
                }
                else {
                    System.out.println("Something went Wrong. Try Again!");
                }
                System.out.println(st);
            }
            else if(c==2) {
                // Add Student (By ID)
                System.out.println("Enter Student ID : ");
                int id = Integer.parseInt(br.readLine());

                System.out.println("Enter Student Name : ");
                String name = br.readLine();

                System.out.println("Enter Student Phone No.: ");
                String phone = br.readLine();

                System.out.println("Enter Student City : ");
                String city = br.readLine();

                Student st = new Student(id, name, phone, city);
                boolean existence = StudentDAO.checkStudentExistence(id);
                boolean answer;
                if(existence) {
                    System.out.println("Student with ID "+id+" already exists.");
                    System.out.println("Do you want to update the student? (Y/N)");
                    String choice = br.readLine();
                    if(choice.equalsIgnoreCase("Y")) {
                        StudentDAO.deleteStudent(id);
                        answer = StudentDAO.insertStudentToDB(id, st);
                    }
                    else if(choice.equalsIgnoreCase("N")) {
                        boolean exists = true;
                        int k = 1;
                        while(exists) {
                            exists = StudentDAO.checkStudentExistence(k);
                            if(exists)
                                k++;
                        }

                        Student student = new Student(k, name, phone, city);
                        answer = StudentDAO.insertStudentToDB(k,student);
                    }
                    else {
                        System.out.println("Wrong Input! Try Again.");
                        continue;
                    }
                }
                else {
                    answer = StudentDAO.insertStudentToDB(id, st);
                }
                if(answer) {
                    System.out.println("Student is Added Successfully.");
                }
                else {
                    System.out.println("Something went Wrong. Try Again!");
                }
                System.out.println(st);
            }
            else if(c==3) {
                // Delete Student
                System.out.println("Enter Student ID to Delete : ");
                int userId = Integer.parseInt(br.readLine());
                int f = StudentDAO.deleteStudent(userId);
                if(f>0) {
                    System.out.println("Student is Deleted Successfully.");
                }
                else if(f==0) {
                    System.out.println("Student is not Found.");
                }
                else {
                    System.out.println("Something went Wrong. Try Again!");
                }
            }
            else if(c==4) {
                // Display Students
                boolean f = StudentDAO.showAllStudents();
                if(!f) {
                    System.out.println("Something went Wrong. Try Again!");
                }
            }
            else if(c==5){
                // Exit
                System.out.println("Exiting the Application.");
                break;
            }
            else {
                System.out.println("Wrong Input. Try Again!");
            }
        }
        System.out.println("Thank You for using My Application.");
    }
}