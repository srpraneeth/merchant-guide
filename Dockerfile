FROM java:latest
MAINTAINER sr.praneeth@gmail.com
COPY ./target/merchant-guide.jar /home/merchant-guide.jar
COPY ./Input.txt /home/Input.txt
RUN java -jar /home/merchant-guide.jar /home/Input.txt
