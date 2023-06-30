curl.exe localhost:8080/api/bank-service/system-configuration/account-capacity/
curl.exe localhost:8080/api/bank-service/system-configuration/account-capacity/1
curl.exe localhost:8080/api/bank-service/system-configuration/account-capacity/2
curl.exe localhost:8080/api/bank-service/system-configuration/account-capacity/3
curl.exe localhost:8080/api/bank-service/system-configuration/account-capacity/4
echo ''
curl.exe localhost:8080/api/bank-service/system-configuration/account-capacity/ -X PUT -H "Content-Type: application/json" ^
 -d {\"canBeMasterEnabled\":true,\"canBeSubEnabled\":false,\"checkingEnabled\":true,\"accountFeeEnabled\":false,\"checkLimitEnabled\":false,\"interestEnabled\":false,\"accountFee\":null,\"checkLimit\":null,\"interestRate\":null,\"overdraftLimitEnabled\":true,\"overdraftLimit\":200.0,\"overdraftFee\":30.0,\"accountEnabled\":true,\"withdrawEnabled\":true,\"depositEnabled\":true,\"transferEnabled\":true,\"intraAccountTransferEnabled\":true,\"interAccountTransferEnabled\":true,\"interBankTransferEnabled\":true,\"id\":2,\"idTagname\":\"PRIMARY-CHECKING\"}
echo ''
curl.exe localhost:8080/api/bank-service/system-configuration/account-capacity/ -X POST -H "Content-Type: application/json" ^
 -d {\"canBeMasterEnabled\":true,\"canBeSubEnabled\":false,\"checkingEnabled\":true,\"accountFeeEnabled\":false,\"checkLimitEnabled\":false,\"interestEnabled\":false,\"accountFee\":null,\"checkLimit\":null,\"interestRate\":null,\"overdraftLimitEnabled\":true,\"overdraftLimit\":200.0,\"overdraftFee\":30.0,\"accountEnabled\":true,\"withdrawEnabled\":true,\"depositEnabled\":true,\"transferEnabled\":true,\"intraAccountTransferEnabled\":true,\"interAccountTransferEnabled\":true,\"interBankTransferEnabled\":true,\"id\":2,\"idTagname\":\"PRIMARY-CHECKING\"}
echo ''
