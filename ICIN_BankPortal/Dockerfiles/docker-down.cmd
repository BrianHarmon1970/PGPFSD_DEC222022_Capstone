docker-compose -p icinbank_webportal -f icin-bank.yml  exec -it icin_web sh -c "service nginx stop"
 docker-compose -p icinbank_webportal -f icin-bank.yml down
