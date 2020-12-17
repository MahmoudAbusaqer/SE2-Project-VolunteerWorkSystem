/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.DBConnection;
import Model.DOVMailbox;
import Model.InstitutionMailbox;
import Model.StudentMailbox;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

/**
 *
 * @author Mahmoud_Abusaqer
 */
public class dovMailboxManager {

    private DOVMailbox modelDOV;
    private InstitutionMailbox modelInstitution;
    private StudentMailbox modelStudent;
    private Connection connection;

    public dovMailboxManager(DOVMailbox model) {
        this.modelDOV = model;
        connection = DBConnection.getConnection();
    }

    public void showMailbox() {
        //need a select to show mails
    }

    //if the sender is a student the response should go to the student otherwise to the institutions
    public void mailResponse(int senderId, String senderName, String title, String body, Date date, boolean approveOrDeny) {
        if (senderId == 0) {//if senderId is for a student
            modelStudent.setSenderId(senderId);
            modelStudent.setSenderName(senderName);
            modelStudent.setTitle(title);
            modelStudent.setDate(date);
            modelStudent.setApproveOrDeny(approveOrDeny);
            addToStudent(modelStudent);
        } else if (senderId == 0) {//if the sender for an institution and it is a final rebort then the student get a resonse too
            modelInstitution.setSenderId(senderId);
            modelInstitution.setSenderName(senderName);
            modelInstitution.setTitle(title);
            modelInstitution.setDate(date);
            modelInstitution.setApproveOrDeny(approveOrDeny);
            addToInstitutions(modelInstitution);//to send the email to institution
            modelStudent.setSenderId(senderId);
            modelStudent.setSenderName(senderName);
            modelStudent.setTitle(title);
            modelStudent.setDate(date);
            modelStudent.setApproveOrDeny(approveOrDeny);
            addToStudent(modelStudent);//to send the email to student
        } else {
            //error message
        }
    }

    public void addToStudent(StudentMailbox newObject) {
        try {
            PreparedStatement statement = connection.prepareStatement("insert into studentmailbox(senderId, senderName, title, body, date, approveOrDeny) values (?, ?, ?, ?, ?, ?)");
            statement.setInt(1, newObject.getSenderId());
            statement.setString(2, newObject.getSenderName());
            statement.setString(3, newObject.getTitle());
            statement.setString(4, newObject.getBody());
            statement.setDate(5, new java.sql.Date(newObject.getDate().getTime()));
            statement.setBoolean(6, newObject.isApproveOrDeny());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addToInstitutions(InstitutionMailbox newObject) {
        try {
            PreparedStatement statement = connection.prepareStatement("insert into institutionmailbox(senderId, senderName, title, body, date, approveOrDeny) values (?, ?, ?, ?, ?, ?)");
            statement.setInt(1, newObject.getSenderId());
            statement.setString(2, newObject.getSenderName());
            statement.setString(3, newObject.getTitle());
            statement.setString(4, newObject.getBody());
            statement.setDate(5, new java.sql.Date(newObject.getDate().getTime()));
            statement.setBoolean(6, newObject.isApproveOrDeny());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int objectId) {
        try {
            PreparedStatement statement = connection.prepareStatement("delete from dovmailbox where id=?");
            statement.setInt(1, objectId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void edit(int objectId) {

    }
}
