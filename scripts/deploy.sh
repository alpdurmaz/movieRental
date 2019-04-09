#!/usr/bin/env bash

address="3.17.6.129/"

echo "Deploying to remote tomcat: " $address


scp -i ~/Downloads/alp.pem /Users/alpdurmaz/Documents/movierent/movieRental/build/libs/ROOT.war bitnami@ec2-3-17-6-129.us-east-2.compute.amazonaws.com:/opt/bitnami/apache-tomcat/webapps/