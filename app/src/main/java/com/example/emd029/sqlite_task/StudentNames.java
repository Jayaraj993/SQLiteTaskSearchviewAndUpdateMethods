package com.example.emd029.sqlite_task;

/**
 * Created by EMD029 on 6/1/2015.
 */
public class StudentNames {
    private int id;
    private String name;
    private String subject;
    private String assignmentTask;

    private String description;
    private String date;
    private String time;

    public StudentNames(){
      /*  this.id=1;
        this.name="jayaraj";
        this.subject="Maths";
        this.assignmentTask="Work Completed";*/
    }
    public StudentNames(int id,String name,String subject,String assignmentTask){
        this.id=id;
        this.name=name;
        this.subject=subject;
        this.assignmentTask=assignmentTask;

        this.description=description;
        this.date=date;
        this.time=time;
    }
    //using encapsulation set and get data
    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id=id;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name=name;
    }
    public String getSubject(){
        return subject;
    }
    public void setSubject(String subject){
        this.subject=subject;
    }
    public String getAssignmentTask(){
        return assignmentTask;
    }
    public void setAssignmentTask(String assignmentTask){
        this.assignmentTask=assignmentTask;
    }

    public String getDescription(){
        return description;
    }
    public void setDescription(String description){
        this.description=description;
    }
    public String getDate(){
        return date;
    }
    public void setDate(String date){
        this.date=date;
    }
    public String getTime(){
        return time;
    }
    public void setTime(String time){
        this.time=time;
    }

}
