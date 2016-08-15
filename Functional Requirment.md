# stock-market-service
a sample super stock market service developed using java

Functional Requirement :

The Global Beverage Corporation Exchange is a new stock market trading in drinks companies.
a. Your company is building the object-oriented system to run that trading.
b. You have been assigned to build part of the core object model for a limited phase 1

2. Provide the source code for an application that will:-
a. For a given stock,
i. Given any price as input, calculate the dividend yield
ii. Given any price as input, calculate the P/E Ratio
iii. Record a trade, with timestamp, quantity, buy or sell indicator and price
iv. Calculate Volume Weighted Stock Price based on trades in past 5 minutes
b. Calculate the GBCE All Share Index using the geometric mean of the Volume Weighted Stock Price for all stocks

Constraints & Notes
1. Written in one of these languages - Java, C#, C++, Python
2. The source code should be complete and able to be run and tested.
3. No database, GUI or I/O is required, all data need only be held in memory
4. No prior knowledge of stock markets or trading is required – all formulas are provided below.
5. The code should provide only the functionality requested, however must be production quality.

Sample Stock Data:

Stock Symbol     Type    Last Dividend      Fixed Dividend    Par Value

TEA             common        0                                   100
POP             common        8                                   100 
ALE             common       23                                    60
GIN             Preffered     8                   2               100
JOE             common       13                                   250


Formulas:

Dividend Yield for common stock type = LastDividend/Price

Dividend Yield for preffered stock type = FixedDividend.ParValue/Price

P/E Ratio = Price/Dividend

Geometric Mean = n pow (pl..p2...p base n)

Volume Weighted Stock price = sum(tradeprice * quantity)/sum (Quantity) [per stock]

