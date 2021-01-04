package com.hpe;

import com.example.DNSLog;
import java.util.Collections;
import java.util.Properties;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import io.confluent.kafka.serializers.KafkaAvroDeserializer;
import io.confluent.kafka.serializers.KafkaAvroDeserializerConfig;
import io.confluent.kafka.serializers.KafkaAvroSerializerConfig;

public class DNSLogConsumer {

	public static void main(String[] args) {
		
		Properties properties = new Properties();
		properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
		properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, KafkaAvroDeserializer.class.getName());
		properties.put(KafkaAvroDeserializerConfig.SPECIFIC_AVRO_READER_CONFIG, "true");

		//MAPR Schema registry location.
		properties.setProperty(KafkaAvroSerializerConfig.SCHEMA_REGISTRY_URL_CONFIG,"http://ec2-18-135-100-201.eu-west-2.compute.amazonaws.com:8087");
		
		KafkaConsumer<String, DNSLog> consumer = new KafkaConsumer<>(properties);

		String topic = "/sensor:metric";
		
		consumer.subscribe(Collections.singletonList(topic));

		try {
		    while (true) {
		        ConsumerRecords<String, DNSLog> records = consumer.poll(100);
		        
		        records.forEach(record -> {
		        	DNSLog dnslogRecord = record.value();
		            System.out.printf("%s %d %d %s \n", record.topic(), record.partition(), record.offset(), dnslogRecord);
		        });
		    }
		} finally {
		    consumer.close();
		}

	}

}
