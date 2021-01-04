package com.hpe;

import com.example.DNSLog;
import java.util.Properties;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import io.confluent.kafka.serializers.KafkaAvroSerializer;
import io.confluent.kafka.serializers.KafkaAvroSerializerConfig;

public class DNSLogProducer {

	public static void main(String[] args) {
		
		Properties properties = new Properties();
		properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaAvroSerializer.class.getName());

		//MAPR Schema registry location.
		properties.setProperty(KafkaAvroSerializerConfig.SCHEMA_REGISTRY_URL_CONFIG,"http://ec2-18-135-100-201.eu-west-2.compute.amazonaws.com:8087");
		
		KafkaProducer<String,DNSLog> producer = new KafkaProducer<>(properties);

		DNSLog dnslog;
		String topic = "/sensor:metric";
		

		for (int i = 0; i < 3; i++) {
		
		    dnslog = DNSLog.newBuilder()
		    		.setUid("test")
		    		.setOriginh("test")
		            .build();
		
		    ProducerRecord<String,DNSLog> record = new ProducerRecord(topic, String.valueOf(i), dnslog);
		
		    producer.send(record, (recordMetadata, e) -> {
		        if (e == null) {
		            System.out.println("Success!" );
		            System.out.println(recordMetadata.toString());
		        } else {
		            e.printStackTrace();
		        }
		    });
		}
		
		producer.flush();
		producer.close();
		
	}
	
}
