docker build -f Dockerfile-WebServer ../ICINBankWebFront
docker create -p 8081:80 -p 8082:8086 --name icin-webserver -it brianharmon1970/webserver:0.0.1
