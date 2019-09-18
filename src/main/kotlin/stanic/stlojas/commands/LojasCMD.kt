package stanic.stlojas.commands

import hazae41.minecraft.kotlin.bukkit.command
import org.bukkit.entity.Player
import stanic.stlojas.Main
import stanic.stlojas.utils.Menus

class LojasCMD {

    fun run(m: Main) {
        m.command("lojas") { sender, _ ->
            if (sender !is Player) return@command

            Menus().openLojas(sender, 1)
        }
    }

}