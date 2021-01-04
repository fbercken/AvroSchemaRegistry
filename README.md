
# Load Schema
curl -X POST -H "Content-Type: application/vnd.schemaregistry.v1+json"  --data '{ "schema": "{ \"namespace\": \"com.example\", \"type\": \"record\", \"name\": \"DNSLog\", \"doc\" : \"Represents an DNSLog entry\", \"fields\": [ {\"name\": \"uid\", \"type\": \"string\"}, {\"name\": \"originh\", \"type\": \"string\", \"default\": \"\"}, {\"name\": \"originp\", \"type\": \"string\", \"default\": \"\"}, {\"name\": \"resph\", \"type\": \"string\", \"default\": \"\"}, {\"name\": \"respp\", \"type\": \"string\", \"default\": \"\"}, {\"name\": \"proto\", \"type\": \"string\", \"default\": \"\"},{\"name\": \"port\", \"type\": \"int\", \"default\": 8080}, {\"name\": \"ts\", \"type\": \"long\", \"default\": 0}, {\"name\": \"query\", \"type\": \"string\", \"default\": \"\"}, {\"name\": \"qclass\", \"type\": \"float\", \"default\": 0}, {\"name\": \"qclassname\", \"type\": \"string\", \"default\": \"\"}, {\"name\": \"qtype\", \"type\": \"float\", \"default\": 0}, {\"name\": \"qtypename\", \"type\": \"string\", \"default\": \"\"}, {\"name\": \"rcode\", \"type\": \"float\", \"default\": 0}, {\"name\": \"rcodename\", \"type\": \"string\", \"default\": \"\"},{\"name\": \"Z\", \"type\": \"int\", \"default\": 0}, {\"name\": \"OR\", \"type\": \"boolean\", \"default\": false}, {\"name\": \"AA\", \"type\": \"boolean\", \"default\": false}, {\"name\": \"TC\", \"type\": \"boolean\", \"default\": false}, {\"name\": \"rejected\", \"type\": \"boolean\", \"default\": false}, {\"name\": \"Answers\", \"default\":[], \"type\":{\"type\": \"array\", \"items\": \"string\"}}, {\"name\": \"TLLs\",  \"default\":[], \"type\":{\"type\": \"array\", \"items\": \"int\"}} ] }" }' http://ec2-3-10-141-194.eu-west-2.compute.amazonaws.com:8087/subjects/sensor:metric-value/versions

curl -X POST -H "Content-Type: application/vnd.schemaregistry.v1+json" \
  --data '{ "schema": "{ \"namespace\": \"com.example\",  \"type\": \"record\",  \"name\": \"DNSLog\", \"doc\" : \"Represents an DNSLog entry\", \"fields\": [  {\"name\": \"uid\", \"type\": \"string\"} ] }" }'   http://ec2-3-10-141-194.eu-west-2.compute.amazonaws.com:8087/subjects/sensor:metric-value/versions


# List schemas
curl http://ec2-3-10-141-194.eu-west-2.compute.amazonaws.com:8087/subjects/sensor:metric-value/versions/

# Display schema by ID
curl -X GET http://ec2-3-10-141-194.eu-west-2.compute.amazonaws.com:8087/schemas/ids/1

# Delete schema from registry
curl -X DELETE http://ec2-3-10-141-194.eu-west-2.compute.amazonaws.com:8087/subjects/sensor:metric-value/versions/1


