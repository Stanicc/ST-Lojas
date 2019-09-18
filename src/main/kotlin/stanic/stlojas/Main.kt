package stanic.stlojas

import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.plugin.java.JavaPlugin
import stanic.stlojas.commands.LojaCMD
import stanic.stlojas.commands.LojasCMD
import stanic.stlojas.database.MongoDB
import stanic.stlojas.events.InventoryEvents
import stanic.stlojas.factory.LojasFactory
import stanic.stlojas.factory.model.Lojas

class Main: JavaPlugin() {

    val lojas = HashMap<String, Lojas>()

    override fun onEnable() {
        instance = this

        mongoDB = MongoDB()
        mongoDB.start()

        loadLojas()

        loadCommands()
        loadEvents()

        Bukkit.getConsoleSender().sendMessage("§e[ST-Lojas] §fAtivado!")
    }

    override fun onDisable() {
        LojasFactory().save()
    }

    private fun loadLojas() {
        if (!mongoDB.collectionExists) return
        for (loja in mongoDB.database().getCollection("lojas").find()) {
            val nick = loja.getString("loja")
            val location = loja.getString("location").split("/")

            val cache = Lojas(nick, Location(Bukkit.getWorld(location[0]), location[1].toDouble(), location[2].toDouble(), location[3].toDouble(), location[4].toFloat(), location[5].toFloat()))
            lojas[nick] = cache
        }
    }

    private fun loadCommands() {
        LojasCMD().run(this)
        LojaCMD().run(this)
    }

    private fun loadEvents() {
        InventoryEvents().onLojasClick(this)
    }

    companion object {
        lateinit var mongoDB: MongoDB
        lateinit var instance: Main
            private set
    }

}