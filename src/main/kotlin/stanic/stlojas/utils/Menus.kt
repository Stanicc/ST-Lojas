package stanic.stlojas.utils

import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.entity.Player
import stanic.stlojas.factory.model.Lojas

class Menus {

    fun openLojas(p: Player, pag: Int) {
        val inv = Bukkit.createInventory(null, 54, "§aLojas - Página.$pag")

        for (i in 0..8) inv.setItem(i, ItemBuilder(Material.STAINED_GLASS_PANE).toItemStack())

        val lojas = ArrayList<Lojas>()
        for (loja in Lojas.all) lojas.add(loja)

        var slot = 9
        val size = lojas.size

        for (i in 0..4) {
            if (i + (44 * (pag - 1)) < size) {
                inv.setItem(slot, ItemBuilder(Material.SKULL_ITEM).setDurability(3).setSkullOwner(lojas[i + (44 * (pag - 1))].nick).setName("§b${lojas[i + (44 * (pag - 1))].nick}").addLores(listOf("", "§aClique para teleportar", "")).toItemStack())
            }
            if (slot == 44) break
        }

        inv.setItem(53, ItemBuilder(Material.CARPET).setDurability(5).setName("§aPróxima página").toItemStack())
        if (pag >= 2) inv.setItem(45, ItemBuilder(Material.CARPET).setDurability(14).setName("§cPágina anterior").toItemStack())

        p.openInventory(inv)
    }

}