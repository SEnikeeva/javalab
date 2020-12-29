package driver.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class Configuration {
    private final MongoDatabase mongoDatabase;

    public Configuration(String database) {
        MongoClient mongoClient = MongoClients.create();
        mongoDatabase = mongoClient.getDatabase(database);
    }

    public MongoDatabase getMongoDatabase() {
        return mongoDatabase;
    }
}
