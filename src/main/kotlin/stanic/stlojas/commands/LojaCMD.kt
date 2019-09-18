package stanic.stlojas.commands

import hazae41.minecraft.kotlin.bukkit.command
import hazae41.minecraft.kotlin.bukkit.msg
import org.bukkit.entity.Player
import stanic.stlojas.Main
import stanic.stlojas.commands.subcmds.LojaAnunciar
import stanic.stlojas.commands.subcmds.LojaCriar
import stanic.stlojas.commands.subcmds.LojaDeletar

class LojaCMD {

    fun run(m: Main) {
        m.command("loja") { sender, args ->
            if (sender !is Player) return@command

            if (args.isEmpty()) {
                sender.msg("")
                sender.msg("§b* §a/loja criar §7- §fCria uma loja")
                sender.msg("§b* §a/loja deletar §7- §fDelete a sua loja")
                sender.msg("§b* §a/loja anunciar §7- §fAnúncia sua loja")
                sender.msg("")
                return@command
            }

            when (args[0].toLowerCase()) {
                "criar" -> LojaCriar().run(sender)
                "anunciar" -> LojaAnunciar().run(sender, args)
                "deletar" -> LojaDeletar().run(sender)
            }
        }
    }

}