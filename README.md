# Log-Analyzer
The Spring boot application helps to analyze the log files efficiently.

The rest services are as follows

1. Fetch all the logs
  http://localhost:8080/log/search
  
2. Fetch the logs on the basis of the log level
  http://localhost:8080/log/search/type/{level}
  
3. Fetch the logs on the basis of the keyword
  http://localhost:8080/log/search/keyword/{keyword}
  
4. Fetch the logs on the basis of time
  http://localhost:8080/log/search/time?startTime=yyyy-MM-dd HH:mm:ss.SSS&endTime=yyyy-MM-dd HH:mm:ss.SSS
 
 

