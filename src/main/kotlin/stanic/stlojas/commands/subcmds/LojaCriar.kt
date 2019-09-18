package stanic.stlojas.commands.subcmds

import hazae41.minecraft.kotlin.bukkit.msg
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import stanic.stlojas.Main
import stanic.stlojas.factory.LojasFactory
import stanic.stlojas.factory.model.Lojas

class LojaCriar {

    fun run(sender: Player) {
        if (LojasFactory().exists(sender.name)) {
            sender.msg("§cVocê já possui uma loja.")
            return
        }
        val loja = Lojas(sender.name, sender.location)
        Main.instance.lojas[sender.name] = loja
        sender.msg("§aLoja criada com sucesso!")
        for (players in Bukkit.getOnlinePlayers()) players.msg("\n§f${sender.name} §acriou sua loja!\n")
    }

}