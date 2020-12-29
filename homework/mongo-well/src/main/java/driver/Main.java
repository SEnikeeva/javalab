package driver;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import driver.config.Configuration;
import org.bson.Document;

import static com.mongodb.client.model.Projections.excludeId;
import static com.mongodb.client.model.Projections.fields;
import static com.mongodb.client.model.Projections.include;


public class Main {
    public static void main(String[] args) {
        Configuration configuration = new Configuration("well_db");
        MongoDatabase mongoDatabase = configuration.getMongoDatabase();
        //first example(Use getCollection)
        MongoCollection<Document> wellsCollections = mongoDatabase.getCollection("well");
        wellsCollections.find().forEach(document -> System.out.println(document.get("name", "no name")));
        //second example(Use Document,FindIterable with projection)
        Document searchQuery = new Document();
        searchQuery
                .append("field", "roma");
        FindIterable<Document> resultDocuments = wellsCollections.find(searchQuery)
                .projection(fields(include("name", "field"), excludeId()));
        for (Document document : resultDocuments) {
            System.out.println(document.toJson());
        }
    }
}
