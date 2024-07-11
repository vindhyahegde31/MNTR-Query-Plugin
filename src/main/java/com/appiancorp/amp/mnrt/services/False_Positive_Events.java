package com.appiancorp.amp.mnrt.services;

import com.appiancorp.amp.mnrt.models.MonitoringEvent;
import com.appiancorp.amp.mnrt.utils.DButils;

import javax.naming.Context;
import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class False_Positive_Events {

    String False_Positive_Events = "SELECT eventid, monitoringAlertId, start, subdomain, type, status, comment, supportCase FROM monitoringevent WHERE status = 'False Positive' AND type NOT IN ('App server has been restarted', 'Checkengine reported an issue') LIMIT 20";


    Connection con;


    public List<MonitoringEvent> Execute_False_Positive_Events(Context context) throws SQLException, NamingException {
        con = DButils.getTestingSiteConnection(context);
        Statement statement = con.createStatement();

        ResultSet resultSet3;
        resultSet3 = statement.executeQuery(False_Positive_Events);
      System.out.println("In false positive events: " + resultSet3);
        List<MonitoringEvent> resultList3 = new ArrayList<>();
        while (resultSet3.next()) {

          MonitoringEvent m3 = new MonitoringEvent();
          m3.setEventId(resultSet3.getInt(1));
          m3.setMonitoringAlertId(resultSet3.getLong(2));
          String dateString = resultSet3.getString(3);  // Get date string from ResultSet
          if (dateString.contains(".")) {
            dateString = dateString.substring(0, dateString.indexOf('.'));
          }
          DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
          LocalDateTime localDateTime = LocalDateTime.parse(dateString, inputFormatter);
          Instant instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant();
          Date utilDate = Date.from(instant);
          m3.setStart(utilDate);
          m3.setSubdomain(resultSet3.getString(4));
          m3.setType(resultSet3.getString(5));
          m3.setStatus(resultSet3.getString(6));
          m3.setComment(resultSet3.getString(7));
          m3.setSupportCase(resultSet3.getString(8));
          resultList3.add(m3);
        }
       
        resultSet3.close();
       
        statement.close();
        con.close();
        return resultList3;


    }


}



