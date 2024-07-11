package com.appiancorp.amp.mnrt.models;

import javax.xml.bind.annotation.XmlType;

import java.util.Date;

@XmlType(name="monitoringEvent", namespace = "https://manager.appiancloud.com/suite/types/",propOrder = { "eventId", "subdomain", "type", "message", "status", "engineer", "comment", "start", "scheduledEnd", "actualEnd",
        "monitoringAlertId", "supportCase", "triagedBy","monitoredEntityType" })
public class MonitoringEvent {
    private int eventId;
    private String subdomain;
    private String type;
    private String message;
    private String status;
    private String engineer;
    private String comment;
    private Date start;
    private Date scheduledEnd;
    private Date actualEnd;
    private Long monitoringAlertId;
    private String supportCase;
    private String triagedBy;
    private String monitoredEntityType;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getScheduledEnd() {
        return scheduledEnd;
    }

    public void setScheduledEnd(Date scheduledEnd) {
        this.scheduledEnd = scheduledEnd;
    }

    public Date getActualEnd() {
        return actualEnd;
    }

    public void setActualEnd(Date actualEnd) {
        this.actualEnd = actualEnd;
    }

    public String getEngineer() {
        return engineer;
    }

    public void setEngineer(String engineer) {
        this.engineer = engineer;
    }

    public String getTriagedBy() {
        return triagedBy;
    }

    public void setTriagedBy(String triagedBy) {
        this.triagedBy = triagedBy;
    }

    public String getMonitoredEntityType() {
        return monitoredEntityType;
    }

    public void setMonitoredEntityType(String monitoredEntityType) {
        this.monitoredEntityType = monitoredEntityType;
    }


    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public Long getMonitoringAlertId() {
        return monitoringAlertId;
    }

    public void setMonitoringAlertId(Long monitoringAlertId) {
        this.monitoringAlertId = monitoringAlertId;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public String getSubdomain() {
        return subdomain;
    }

    public void setSubdomain(String subdomain) {
        this.subdomain = subdomain;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getSupportCase() {
        return supportCase;
    }

    public void setSupportCase(String supportCase) {
        this.supportCase = supportCase;
    }
}
