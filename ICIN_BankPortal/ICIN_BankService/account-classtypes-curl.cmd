curl.exe localhost:8080/api/bank-service/system-configuration/account-classtypes/
curl.exe localhost:8080/api/bank-service/system-configuration/account-classtypes/1
curl.exe localhost:8080/api/bank-service/system-configuration/account-classtypes/2
curl.exe localhost:8080/api/bank-service/system-configuration/account-classtypes/3
curl.exe localhost:8080/api/bank-service/system-configuration/account-classtypes/4

curl.exe localhost:8080/api/bank-service/system-configuration/account-classtypes/ -X PUT -H "Content-Type: application/json" ^
 -d '{\"accountClass\":\"PRIMARY\",\"accountType\":\"CHECKING\",\"idTagname\":\"PRIMARY-XXX-CHECKING\",\"id\":2,\"accountClassTypeId\":2}'
