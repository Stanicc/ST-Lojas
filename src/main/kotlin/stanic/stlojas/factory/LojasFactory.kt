package stanic.stlojas.factory

import org.bson.Document
import stanic.stlojas.Main
import stanic.stlojas.factory.model.Lojas

class LojasFactory {

    fun exists(nick: String): Boolean = Main.instance.lojas.containsKey(nick)

    fun save() {
        for (lojas in Lojas.all) {
            val location = lojas.location
            val document = Document("loja", lojas.nick).append("location", location.world.name + "/" + location.x + "/" + location.y + "/" + location.z + "/" + location.yaw + "/" + location.pitch)
            Main.mongoDB.database().getCollection("lojas").insertOne(document)
        }
    }

    fun delete(nick: String) {
        if (exists(nick)) {
            val lojas = Main.instance.lojas[nick]!!
            val document = Document("loja", lojas.nick).append("location", lojas.location)
            Main.mongoDB.database().getCollection("lojas").findOneAndDelete(document)
        }
    }

}