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

public class Events_Without_Alerts_Association {
  String Events_Without_Alerts_Association = "SELECT eventId, monitoringAlertId, start, subdomain, type, status, comment, supportCase FROM monitoringevent WHERE monitoringAlertId IS NULL AND status <> 'Clear' LIMIT 20";
  Connection con;
  public List<MonitoringEvent> Executeevents_Without_Alerts_Association(Context context) throws SQLException,
      NamingException {

    con = DButils.getTestingSiteConnection(context);
    Statement statement = con.createStatement();

    ResultSet resultSet2;
    resultSet2 = statement.executeQuery(Events_Without_Alerts_Association);
    List<MonitoringEvent> resultList2 = new ArrayList<>();
    System.out.println("In events without alerts association: "+  resultSet2);
    while (resultSet2.next()) {

      MonitoringEvent m2 = new MonitoringEvent();
      m2.setEventId(resultSet2.getInt(1));
      m2.setMonitoringAlertId(resultSet2.getLong(2));
      String dateString = resultSet2.getString(3);  // Get date string from ResultSet
      if (dateString.contains(".")) {
        dateString = dateString.substring(0, dateString.indexOf('.'));
      }
      DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
      LocalDateTime localDateTime = LocalDateTime.parse(dateString, inputFormatter);
      Instant instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant();
      Date utilDate = Date.from(instant);
      m2.setStart(utilDate);
      m2.setSubdomain(resultSet2.getString(4));
      m2.setType(resultSet2.getString(5));
      m2.setStatus(resultSet2.getString(6));
      m2.setComment(resultSet2.getString(7));
      m2.setSupportCase(resultSet2.getString(8));
      resultList2.add(m2);
    }

    resultSet2.close();

    statement.close();
    con.close();
    return resultList2;
  }
  }
