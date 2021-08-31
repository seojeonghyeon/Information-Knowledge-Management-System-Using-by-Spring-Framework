# Information-Knowledge-Management-System-Using-by-Spring-Framework

* [Introduction](#introduction)
* [Prerequisites](#prerequisites)
* [How to install](#how-to-install)
* [Preview](#Preview)

## Introduction
  The janitor who manages trash bins in many buildings has the difficulty of going around and checking the bins every time. So we suggest a solution of trash bins monitoring system. This system Contributes easy manages for janitor. We used some platforms that 'Spring framework for server', 'ARTIK 053 for hardware', 'Android Studio for smartphone'. 'Spring Framework' is a server development tools for Java. This project was done a 'Spring Framework' applied 'MVC patterns(JSP+Spring Framework, RESTful API, Firebase). The server was communicated hardware of 'ARTIK 530' and smartphone of 'Android'. 'ARTIK053' is a platform supported 'ARTIK' of SAMSUNG. Support for that platform is now deprecated(~2018). This platform was make up 'C' language in Tizen RT. Hardware is attached trash bins. Hardware check a trash bins and request a server add the checked values real time. So server saved the reqeusted values and show this values through web pages and Android smartphone.
  
  
Some features of this project
* Values of trash bins display
* Values of trash bins check on realtime
* Security
* Values of trash bins record on realtime
* Periodically collection of data

![Features](https://user-images.githubusercontent.com/24422677/131491540-2df73f98-020d-4077-9dab-12f3eb02696d.png)
 
 
 Server databases is mariadb. This is ERD.
 ![ERD](https://user-images.githubusercontent.com/24422677/131491932-f232dd08-8a89-47c4-a9eb-511bd33bf1fc.png)
 
 
## Prerequisites
 1. You must have platform of 'ARTIK 053'. The 'SAMSUNG' was supported this platform. But support for that platform is now deprecated(~2018).
 2. Android Version is so low. It may not be supported in the current version.
 3. 'Spring Framework' is no 'Spring boot'.
 
## How to install
It is recommended for reference only. To install this project, do the following steps:
1. Prepare Platform of 'ARTIK053', Three color LED, Resist, Infrared sensor.
2. Add from 'ARTIK053' to the code 'IoTrash_ARTIK-master.zip'.
3. Java development tool(Eclipse, IntelliJ..) execute
4. Import the code 'IoTrash_Web.zip'.
5. Execute the code  or  Export a 'war' file and excute tomcat8 on server.
6. Import the code 'Application-master.zip' to Android development tool(Android Studio..).
7. excute the application.
 
## Preview
* Demonstration video : https://youtu.be/KdGmuvDZrII
