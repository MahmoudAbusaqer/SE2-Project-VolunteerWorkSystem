/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.DBConnection;
import Model.ViewNewVolunteers;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mahmoud_Abusaqer
 */
//This class is only for the Institutions to see all the new volunteers in the Institution.
public class ViewNewVolunteersManager {

    private ViewNewVolunteers model;
    private Connection connection;

    public ViewNewVolunteersManager(ViewNewVolunteers model) {
        this.model = model;
        connection = DBConnection.getConnection();
    }

    public List<ViewNewVolunteers> showNewVolunteers(int institutionId) throws SQLException {
        List<ViewNewVolunteers> newVolunteerses = new ArrayList<>();
        PreparedStatement preparedStatement = connection.prepareStatement("select * from vws.viewnewvolunteers where institutionId=?;");
        preparedStatement.setInt(1, institutionId);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            ViewNewVolunteers viewNewVolunteers = new ViewNewVolunteers();
            viewNewVolunteers.setId(resultSet.getInt(2));
            viewNewVolunteers.setName(resultSet.getString(3));
            viewNewVolunteers.setFaculty(resultSet.getString(4));
            viewNewVolunteers.setAddress(resultSet.getString(5));
            viewNewVolunteers.setEmail(resultSet.getString(6));
            viewNewVolunteers.setPhone(resultSet.getInt(7));
            newVolunteerses.add(viewNewVolunteers);
        }
        return newVolunteerses;
    }
}
