package com.appiancorp.amp.mnrt.smartservice;
import com.appiancorp.amp.mnrt.models.MonitoringEvent;
import com.appiancorp.amp.mnrt.services.Events_Without_Alerts_Association;
import com.appiancorp.amp.mnrt.services.False_Positive_Events;
import com.appiancorp.amp.mnrt.services.Get_Stuck_Events_Count;
import com.appiancorp.amp.mnrt.services.Routing_Type_Unknown;
import com.appiancorp.suiteapi.common.Name;
import com.appiancorp.suiteapi.process.exceptions.SmartServiceException;
import com.appiancorp.suiteapi.process.framework.AppianSmartService;
import com.appiancorp.suiteapi.process.framework.Order;
import com.appiancorp.suiteapi.process.palette.PaletteInfo;


import javax.naming.Context;
import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.List;


@PaletteInfo(
    paletteCategory = "monitoringEvent",
    palette = "MNRT Smart service"
)
@Order({"stuckEvents","eventsWithoutAlertsAssociation","eventsMarkedAsFalsePositive","eventsOfUnknownMonitoredEntityTypeId"})
public class MonitorEvent2 extends AppianSmartService
{
  private final Context context;
  public MonitorEvent2(Context context){
    this.context = context;
  }
  private MonitoringEvent[] get_stuck_events_count;
  private MonitoringEvent[] routingtypeunkown;
  private MonitoringEvent[] falsepositiveevents;
  private MonitoringEvent[] events_without_alerts_association;



  @Override
  public void run() throws SmartServiceException {

   Get_Stuck_Events_Count gs = new Get_Stuck_Events_Count();
   Routing_Type_Unknown rn = new Routing_Type_Unknown();
    False_Positive_Events pv = new False_Positive_Events();
    Events_Without_Alerts_Association ev = new Events_Without_Alerts_Association();
    List<MonitoringEvent> routing_type_unknownlist;
    List<MonitoringEvent> false_positive_eventlist;
    List<MonitoringEvent> events_without_alerts_associationlist;
    List<MonitoringEvent> get_stuck_events_countlist;
    try {
     get_stuck_events_countlist = gs.Execute_Get_Stuck_Events_Counts(context);
     routing_type_unknownlist = rn.Execute_Routing_Type_Unknown(context);
     false_positive_eventlist = pv.Execute_False_Positive_Events(context);
      events_without_alerts_associationlist = ev.Executeevents_Without_Alerts_Association(context);
     this.get_stuck_events_count =  get_stuck_events_countlist.toArray(new MonitoringEvent[] {});
   this.events_without_alerts_association = events_without_alerts_associationlist.toArray(new MonitoringEvent[] {});
     this.falsepositiveevents = false_positive_eventlist.toArray(new MonitoringEvent[] {});
    this.routingtypeunkown = routing_type_unknownlist.toArray(new MonitoringEvent[] {});
    } catch (SQLException | NamingException e) {
      throw new RuntimeException(e);
    }

  }

  @Name("get_stuck_events_count")
  public MonitoringEvent[] getGet_Stuck_Events_Count() {
    return get_stuck_events_count;
  }

  @Name("events_without_alerts_association")
  public MonitoringEvent[] getEvents_without_alerts_association() {
    return events_without_alerts_association;
  }

  @Name("falsepositiveevents")
  public MonitoringEvent[] getFalse_Positive_Events() {
    return falsepositiveevents;
  }

  @Name("routingtypeunkown")
  public MonitoringEvent[] getRouting_Type_Unknown(){
    return routingtypeunkown;
  }


}
