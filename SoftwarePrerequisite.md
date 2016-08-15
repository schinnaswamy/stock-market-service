# stock-market-service
a sample super stock market service developed using java and its a maven project.

To run this project in any machine following softwares are required 

JDK 1.8
=======
Set JAVA_HOME and path entries

For Example : In windows environment

JAVA_HOME = C:/Java/JdK-1.8
Path = %JAVA_HOME%/bin

Apache Maven - 3.3.9
====================
Set MVN_HOME and path entries

MVN_HOME = C:/apache-3.3.9/
Path = %MVN_HOME%/bin

Once the following environment configuration

Run the following command from the project root path
===================================================

mvn clean install

Once this command is executed the output stock-market-service artifact will get created in the target directory. 

Important : To run the application run the following command
============================================================

mvn exec:java

This command runs the main class from the jar by doing a lookup in the MANIFEST.MF file.

The main class is com.jpm.stocks.ApplicationMain











