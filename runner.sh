echo "Checking if hub is ready..!"
count=0
while [ "$( curl -s http://192.168.192.1:4444/status | jq -r .value.ready )" != "true" ]
do
  count=$((count+1))
  echo "Attempt: ${count}"
  if [ "$count" -ge 30 ]
  then
      echo "**** HUB IS NOT READY WITHIN 30 SECONDS ****"
      exit 1
  fi
  sleep 1
done


echo "Selenium Grid is up and running. Running the test...."

echo "Path:"
pwd
java -cp "libs/*" org.testng.TestNG Runner.xml