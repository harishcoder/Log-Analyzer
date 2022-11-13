package com.example.logAnalyzer.entity;


import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "LogDetails")
public class LogData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "log_type")
    private String logType;

    @Column(name = "created_time")
    private LocalDateTime createdTime;

    @Lob
    private String message;

    public LogData() {
    }

    public String getLogType() {
        return logType;
    }

    public void setLogType(String logType) {
        this.logType = logType;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "LogData{" +
                "id=" + id +
                ", logType='" + logType + '\'' +
                ", createdTime=" + createdTime +
                ", message='" + message + '\'' +
                '}';
    }
}
