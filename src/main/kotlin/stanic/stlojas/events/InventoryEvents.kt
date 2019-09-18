package stanic.stlojas.events

import hazae41.minecraft.kotlin.bukkit.listen
import hazae41.minecraft.kotlin.bukkit.msg
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.inventory.InventoryClickEvent
import stanic.stlojas.Main
import stanic.stlojas.utils.Menus

class InventoryEvents {

    fun onLojasClick(m: Main) {
        m.listen<InventoryClickEvent> {
            val p = it.whoClicked as Player
            if (it.inventory.title.contains("§aLojas - Página")) {
                when (it.slot) {
                    53 -> {
                        val pag = it.inventory.title.split("Página.")[1].replace(" ", "").toInt()
                        Menus().openLojas(p, pag + 1)
                    }
                    45 -> {
                        if (it.currentItem == null || it.currentItem.type == Material.AIR) return@listen
                        val pag = it.inventory.title.split("Página.")[1].replace(" ", "").toInt()
                        Menus().openLojas(p, pag - 1)
                    }
                }
                if (it.currentItem == null) return@listen
                if (it.currentItem.type == Material.SKULL_ITEM) {
                    p.teleport(Main.instance.lojas[it.currentItem.itemMeta.displayName.replace("§b", "")]!!.location)
                    p.msg("§aVocê foi para a loja dê ${it.currentItem.itemMeta.displayName} §acom sucesso!")
                }
                it.isCancelled = true
            }
        }
    }

}