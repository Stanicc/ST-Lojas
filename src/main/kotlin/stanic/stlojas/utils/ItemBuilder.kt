package stanic.stlojas.utils

import org.bukkit.Material
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.SkullMeta

class ItemBuilder {

    private var itemStack: ItemStack? = null

    constructor(itemStack: ItemStack) {
        this.itemStack = itemStack
    }

    @JvmOverloads
    constructor(m: Material, quantia: Int = 1) {
        itemStack = ItemStack(m, quantia)
    }

    fun setName(nome: String): ItemBuilder {
        val im = itemStack!!.itemMeta
        im.displayName = nome
        itemStack!!.itemMeta = im
        return this
    }

    fun setDurability(durabilidade: Int): ItemBuilder {
        itemStack!!.durability = java.lang.Short.valueOf("" + durabilidade)
        return this
    }

    fun setSkullOwner(dono: String): ItemBuilder {
        try {
            val im = itemStack!!.itemMeta as SkullMeta
            im.owner = dono
            itemStack!!.itemMeta = im
        } catch (expected: ClassCastException) {
        }

        return this
    }

    fun addLores(linha: List<String>): ItemBuilder {
        val im = itemStack!!.itemMeta
        im.lore = null
        var lore: MutableList<String> = ArrayList()
        if (im.hasLore()) lore = ArrayList(im.lore)
        for (s in linha) {
            lore.add(s)
        }
        im.lore = lore
        itemStack!!.itemMeta = im
        return this
    }

    fun toItemStack(): ItemStack? {
        return itemStack
    }

}
