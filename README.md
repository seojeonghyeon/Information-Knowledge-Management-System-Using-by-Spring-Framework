# Information-Knowledge-Management-System-Using-by-Spring-Framework

* [Introduction](#introduction)
* [Prerequisites](#prerequisites)
* [How to install](#how-to-install)
* [Preview](#Preview)

## Introduction
![IKAMS시스템구성도](https://user-images.githubusercontent.com/24422677/131522635-ffee83fe-f24c-4c25-bacf-e8ede05b7f6a.png)

  Every house has a variety of information and knowledge assets, such as books or CDs/DVDs. However, managing information assets at home is very difficult. This project manages the information knowledge assets held at home through the creation, distribution and recognition of QR-Codes. In addition, it records and stores the status and impressions of individual information and knowledge assets so that individuals can systematically manage smart information knowledge even at home. We used some platforms that 'Spring framework for server', 'Android Studio for smartphone'. 'Spring Framework' is a server development tools for Java. This project was done a 'Spring Framework' applied 'MVC patterns(JSP+Spring Framework, RESTful API, Firebase). The server was communicated smartphone of 'Android'. Hardware is QR-code. Hardware is printed on web page and attached some things. server saved the QR-code values and show this values through web pages and Android smartphone.
  
  
Some features of this project
* It is economical by using QR-Code and NFC Sticker.
* Manage knowledge assets systematically with goal setting and notification functions for knowledge assets.
* It can be used on mobile, so it can be easily managed anytime, anywhere.

 
 Server databases is mariadb. This is ERD.
![hanium_db](https://user-images.githubusercontent.com/24422677/131522718-c7703967-bf45-4575-8823-2154e7c39312.png)
 
 
## Prerequisites
1. Android Version is low. It may not be supported in the current version.
2. 'Spring Framework' is no 'Spring boot'.
 
## How to install
It is recommended for reference only. To install this project, do the following steps:
1. Java development tool(Eclipse, IntelliJ..) execute
2. Import the code 'IoTrash_Web.zip'.
3. Execute the code  or  Export a 'war' file and excute tomcat8 on server.
4. Import the code 'Application-master.zip' to Android development tool(Android Studio..).
5. excute the application.
 
## Preview
* Demonstration video : https://youtu.be/KdGmuvDZrII
