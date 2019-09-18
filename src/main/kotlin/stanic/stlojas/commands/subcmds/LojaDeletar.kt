package stanic.stlojas.commands.subcmds

import hazae41.minecraft.kotlin.bukkit.msg
import org.bukkit.entity.Player
import stanic.stlojas.Main
import stanic.stlojas.factory.LojasFactory

class LojaDeletar {

    fun run(sender: Player) {
        if (!LojasFactory().exists(sender.name)) {
            sender.msg("§cVocê precisa ter uma loja para fazer isso.")
            return
        }
        Main.instance.lojas.remove(sender.name)
        LojasFactory().delete(sender.name)
        sender.msg("§aLoja deletada com sucesso!")
    }

}