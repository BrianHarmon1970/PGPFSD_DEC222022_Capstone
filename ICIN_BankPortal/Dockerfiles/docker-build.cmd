docker build -t brianharmon1970/icin_database:0.0.1 -f Dockerfile-mysql ../ICIN_BankService
docker build -t brianharmon1970/icin_service:0.0.1 -f Dockerfile-ubuntu ../ICIN_BankService
docker build -t brianharmon1970/icin_webserver:0.0.1 -f Dockerfile-WebServer ../ICINBankWebFront
