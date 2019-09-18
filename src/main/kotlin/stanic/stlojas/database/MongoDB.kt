package stanic.stlojas.database

import com.mongodb.MongoClientSettings.builder
import com.mongodb.ServerAddress
import com.mongodb.client.MongoClient
import com.mongodb.client.MongoClients
import com.mongodb.client.MongoDatabase
import java.util.*

class MongoDB {

    private lateinit var client: MongoClient

    val collectionExists: Boolean
        get() = database().listCollectionNames().into(ArrayList<String>()).contains("lojas")

    fun start() {
        client = MongoClients.create(
            builder().run {
                applyToClusterSettings {
                    it.hosts(Collections.singletonList(ServerAddress("localhost", 27017)))
                }
                build()
            }
        )
        if (collectionExists) return
        else database().createCollection("lojas")
    }

    fun database(): MongoDatabase {
        return client.getDatabase("stlojas")
    }

}
