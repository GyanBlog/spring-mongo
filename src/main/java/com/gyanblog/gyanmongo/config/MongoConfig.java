package com.gyanblog.gyanmongo.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

@Configuration
@EnableMongoRepositories(basePackages= {"com.gyanblog.gyanmongo.repository"})
public class MongoConfig extends AbstractMongoConfiguration {

	@Value("${gyanblog.mongodb.dbname}")
	private String dbName;

	@Value("${gyanblog.mongodb.hosts}")
	private String[] hosts;

	@Value("${gyanblog.mongodb.port}")
	private Integer port;

	@Value("${gyanblog.mongodb.username}")
	private String username;
	
	@Value("${gyanblog.mongodb.password}")
	private String password;
	
	@Override
	public MongoClient mongoClient() {
		List<ServerAddress> replicaSet = new ArrayList<>();
		for (String host : this.hosts) {
			replicaSet.add(new ServerAddress(host, this.port));
		}
		
		return new MongoClient(
				replicaSet,
				MongoCredential.createCredential(this.username, this.dbName, this.password.toCharArray()),
				MongoClientOptions.builder().connectTimeout(1000).build()
			);
	}

	@Override
	protected String getDatabaseName() {
		return this.dbName;
	}


	@Override
	@Bean
	public SimpleMongoDbFactory mongoDbFactory() {
	    return new SimpleMongoDbFactory(mongoClient(), getDatabaseName());
	}

	@Override
	@Bean
	public MongoTemplate mongoTemplate() throws Exception {
	    return new MongoTemplate(mongoDbFactory());
	}
}
