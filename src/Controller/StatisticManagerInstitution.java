/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.DBConnection;
import Model.StatisticsInstitution;
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
//This class is only for the Institutions to see all the statistics that they need about the volunteer work in their Institution.
public class StatisticManagerInstitution {

    private StatisticsInstitution model;
    private Connection connection;

    public StatisticManagerInstitution(StatisticsInstitution model) {
        this.model = model;
        connection = DBConnection.getConnection();
    }

    public List<StatisticsInstitution> showStatistics(int institutionId) throws SQLException {
        List<StatisticsInstitution> statisticsInstitutions = new ArrayList<>();
        PreparedStatement preparedStatement = connection.prepareStatement("select * from vws.statisticsinstitution where institutionId=?;");
        preparedStatement.setInt(1, institutionId);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            StatisticsInstitution statisticsInstitution = new StatisticsInstitution();
            statisticsInstitution.setInstitutionId(resultSet.getInt(2));
            statisticsInstitution.setInstitutionName(resultSet.getString(3));
            statisticsInstitution.setNumberOfAllStudents(resultSet.getInt(4));
            statisticsInstitution.setStudentsFinishedNumbers(resultSet.getInt(5));
            statisticsInstitution.setActiveVolunteers(resultSet.getInt(6));
            statisticsInstitutions.add(statisticsInstitution);
        }
        return statisticsInstitutions;
    }

}
