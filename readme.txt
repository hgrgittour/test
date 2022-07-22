currently support currency type :USD,HKD,CNY,EUR,GBP,JPY,CAD,AUD,NZD,FRF,NLG,DEM.
if user enter wrong code or wrong number ,program will exit.

support one parameter:  file dictionary path
if file is: D:\testmodifyfile\test.txt
after maven install complete, you can use :
java -jar test-1.0-SNAPSHOT.jar D:\testmodifyfile
in windows env.
you can write in test.txt like:
CNY 100
USD 200
CNY -100
need to save after one line complete.
when program running , you can use http://localhost:8080/getNum/CNY to check each currency amount
and http://localhost:8080/showUpdate to monitor currency and amount update.
Also console log output current currency ,show once every 60 seconds
It also does not include automated tests...