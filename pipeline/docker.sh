echo "*** Delete container if exists ***"
docker ps -q -f name=timesheet-api
if [ $? -eq 0 ];
then
     echo "*** Remove container ***"
     docker rm -f timesheet-api
fi

echo "*** Delete image if exists ***"
docker images -q nenodias/timesheet:timesheet
if [ $? -eq 0 ];
then
     echo "*** Remove image ***"
     docker rmi -f nenodias/timesheet:timesheet
fi

echo "*** Docker create image ***"
docker build -t timesheet-api .

docker logout
docker login --username nenodias
docker pull nenodias/timesheet:timesheet

echo "*** Docker create container ***"
docker run -d --name timesheet-api -e APP_OPTIONS='--spring.profiles.active=local' -p 8080:8080 --network=host nenodias/timesheet:timesheet
