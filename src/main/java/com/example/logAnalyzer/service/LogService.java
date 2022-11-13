package com.example.logAnalyzer.service;

import com.example.logAnalyzer.entity.LogData;
import com.example.logAnalyzer.repository.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class LogService {

    final static String regExPattern = "^(?<level>[^\\s]+)\\s+:\\s+(?<time>\\d{4}-\\d{1,2}-\\d{1,2} \\d{1,2}:\\d{1,2}:\\d{1,2}.\\d{3})\\s+(?<message>.*)";

    final static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

    @Value("${logAnalyzer.file.path}")
    private String logFilePath;

    @Autowired
    LogRepository logRepo;

    @PostConstruct
    public void saveLogsInDatabase(){
        //Read the file and save the list in the h2 database
        List<LogData> logDataList = new ArrayList<>();
        BufferedReader reader;
        try{
            reader = new BufferedReader(new FileReader(logFilePath));
            String line = reader.readLine();
            while(line != null){
                LogData data = parseLog(line);
                logDataList.add(data);
                line = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        logRepo.saveAll(logDataList);
    }

    public List<LogData> getAllLogs(){
        //get all the database entries of the logs
        List<LogData> logDataList = logRepo.findAll();
        return logDataList;
    }

    public List<LogData> getLogByLevel(String type){
        //get all the database entries of the logs on the basis of log level
        List<LogData> logLevelList = logRepo.findByLogType(type);
        return logLevelList;
    }

    public List<LogData> getLogByTime(String startTimeStr, String endTimeStr){
        //get all the database entries of the logs on the basis on the time
        LocalDateTime startTime = LocalDateTime.parse(startTimeStr, formatter);
        LocalDateTime endTime = LocalDateTime.parse(endTimeStr, formatter);
        List<LogData> logByTime = logRepo.findAllByCreatedTimeBetween(startTime,endTime);
        return logByTime;
    }

    public List<LogData> getLogByKeyword(String keyword){
        //get all the database entries of the logs on the basis on the keyword
        List<LogData> logByKeyword = logRepo.findByMessageContaining(keyword);
         return logByKeyword;
    }

    //Parse the each line and extract the log level, created time and the remaining text.
    private LogData parseLog(String line) {
        Pattern pattern = Pattern.compile(regExPattern);
        Matcher match = pattern.matcher(line);
        LogData data = new LogData();
        if(match.find()){
            data.setLogType(match.group(1));
            data.setCreatedTime(LocalDateTime.parse(match.group(2), formatter));
            data.setMessage(match.group(3));
        }
        return data;
    }
}
