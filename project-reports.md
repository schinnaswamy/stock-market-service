# stock-market-service
a sample super stock market service developed using java

As a part of this project following reports are genaated with the help of maven reporting plugin

mvn checkstyle plugin
====================

For coding standards. The report will get generated under target/site folder.

mvn cobertura plugin 
====================
for code instrumentation. The report will get generated under target/site folder.

mvn surefire report plugin
==========================
for test report summary.  The report will get generated under target/site folder.

To view all these reports , run the following command from the maven project root directory

mvn site
