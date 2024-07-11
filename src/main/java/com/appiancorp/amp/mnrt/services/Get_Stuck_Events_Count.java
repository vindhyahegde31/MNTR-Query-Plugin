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

public class Get_Stuck_Events_Count {
  String Get_Stuck_Events_Count = "SELECT monitoringEventId, monitoringAlertId, eventStart, serverName, type, monitoringEventStatus FROM stuck_monitoring_processing_layer_events LIMIT 20";
  Connection con;
  public List<MonitoringEvent> Execute_Get_Stuck_Events_Counts(Context context) throws SQLException,
      NamingException {
    con = DButils.getTestingSiteConnection(context);
    Statement statement = con.createStatement();
    ResultSet resultSet1;

    List<MonitoringEvent> resultList1 = new ArrayList<>();
    resultSet1 = statement.executeQuery(Get_Stuck_Events_Count);
    System.out.println("In get stuck events:" + resultSet1);
    while (resultSet1.next()) {

      MonitoringEvent m1 = new MonitoringEvent();
      System.out.println(resultSet1);
      m1.setEventId(resultSet1.getInt(1));
      m1.setMonitoringAlertId(resultSet1.getLong(2));
      String dateString = resultSet1.getString(3);  // Get date string from ResultSet
      if (dateString.contains(".")) {
        dateString = dateString.substring(0, dateString.indexOf('.'));
      }
      DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
      LocalDateTime localDateTime = LocalDateTime.parse(dateString, inputFormatter);
      Instant instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant();
      Date utilDate = Date.from(instant);
      m1.setStart(utilDate);
      m1.setSubdomain(resultSet1.getString(4));
      m1.setType(resultSet1.getString(5));
      m1.setStatus(resultSet1.getString(6));
      resultList1.add(m1);
    }
    resultSet1.close();
    con.close();
    return resultList1;
  }
}
