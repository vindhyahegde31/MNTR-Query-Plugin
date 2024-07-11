package com.appiancorp.amp.mnrt.services;

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

import javax.naming.Context;
import javax.naming.NamingException;

import com.appiancorp.amp.mnrt.models.MonitoringEvent;
import com.appiancorp.amp.mnrt.utils.DButils;

public class Routing_Type_Unknown {
  String Routing_Type_Unknown = "SELECT eventId, monitoringAlertId, start, subdomain, type, status, comment, supportCase FROM monitoringevent WHERE status != 'Clear' AND monitoredEntityTypeId < 1 LIMIT 20";
  Connection con;
  public List<MonitoringEvent> Execute_Routing_Type_Unknown(Context context) throws SQLException,
      NamingException {
    con = DButils.getTestingSiteConnection(context);
    Statement statement = con.createStatement();

    ResultSet resultSet4;
    resultSet4 = statement.executeQuery(Routing_Type_Unknown);
    List<MonitoringEvent> resultList4 = new ArrayList<>();
    System.out.println("In RAOUTING TYPE UNKNOWN\t :" + resultSet4);
    while (resultSet4.next()) {

      MonitoringEvent m4 = new MonitoringEvent();
      m4.setEventId(resultSet4.getInt(1));
      m4.setMonitoringAlertId(resultSet4.getLong(2));
      String dateString = resultSet4.getString(3);  // Get date string from ResultSet
      if (dateString.contains(".")) {
        dateString = dateString.substring(0, dateString.indexOf('.'));
      }
      System.out.println("Trimmed date string: " + dateString);
      DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
      LocalDateTime localDateTime = LocalDateTime.parse(dateString, inputFormatter);
      System.out.println("Parsed date-time: " + localDateTime);
      Instant instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant();
      Date utilDate = Date.from(instant);
      System.out.println("Converted date: " + utilDate);
      m4.setStart(utilDate);
      m4.setSubdomain(resultSet4.getString(4));
      m4.setType(resultSet4.getString(5));
      m4.setStatus(resultSet4.getString(6));
      m4.setComment(resultSet4.getString(7));
      m4.setSupportCase(resultSet4.getString(8));
      resultList4.add(m4);
    }

    resultSet4.close();

    statement.close();
    con.close();
    return resultList4;


  }

}
