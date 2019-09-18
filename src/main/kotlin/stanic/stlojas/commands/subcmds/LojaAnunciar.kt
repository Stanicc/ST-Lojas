package stanic.stlojas.commands.subcmds

import hazae41.minecraft.kotlin.bukkit.msg
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import stanic.stlojas.factory.LojasFactory

class LojaAnunciar {

    fun run(sender: Player, args: Array<out String>) {
        if (!LojasFactory().exists(sender.name)) {
            sender.msg("§cVocê precisa ter uma loja para fazer isso.")
            return
        }
        if (args.size < 2) {
            sender.msg("§cColoque uma mensagem para anunciar!")
            return
        }
        var mensagem = ""
        (1 until args.size).forEach { i ->
            mensagem = mensagem + args[i] + " "
        }
        for (players in Bukkit.getOnlinePlayers()) players.msg("\n§f${sender.name} §aestá anunciando sua loja!\n§7$mensagem\n")
        sender.msg("§aLoja anunciada com sucesso!")
    }

}