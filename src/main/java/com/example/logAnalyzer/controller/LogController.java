package com.example.logAnalyzer.controller;

import com.example.logAnalyzer.entity.LogData;
import com.example.logAnalyzer.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/log/search")
public class LogController {

    @Autowired
    LogService logService;

    @GetMapping
    public List<LogData> getAllLogs(){
        List<LogData> allLogs = logService.getAllLogs();
        return allLogs;
    }

    @GetMapping("keyword/{keyword}")
    public List<LogData> getLogsByKeyword(@PathVariable (name = "keyword") String keyword){
        List<LogData> logByKeyword = logService.getLogByKeyword(keyword);
        return logByKeyword;
    }

    @GetMapping("/time")
    public List<LogData> getLogsByCreatedTime(@RequestParam("startTime") String startTime , @RequestParam("endTime") String endTime){
        List<LogData> logByTime = logService.getLogByTime(startTime,endTime);
        return logByTime;
    }

    @GetMapping("/type/{level}")
    public List<LogData> getLogsByType(@PathVariable(name = "level") String level){
        List<LogData> logByType = logService.getLogByLevel(level);
        return logByType;
    }
}
