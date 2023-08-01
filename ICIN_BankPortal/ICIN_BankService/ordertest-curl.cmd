curl.exe localhost:8080/api/bank-service/order-submit -X POST -H "Content-Type: application/json" -d {\"accountId\":1,\"userId\":8,\"productId\":1,\"quantity\":2,\"orderStatus\":\"ORDERSTATUS_CREATED\"}
curl.exe localhost:8080/api/bank-service/order-update -X POST -H "Content-Type: application/json" -d {\"orderId\":2,\"accountId\":1,\"newOrderStatus\":\"ORDERSTATUS_APPROVED\"}
