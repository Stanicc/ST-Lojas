package stanic.stlojas.factory.model

import org.bukkit.Location
import stanic.stlojas.Main
import java.util.stream.Collectors

class Lojas(var nick: String, var location: Location) {

    companion object {
        val all: List<Lojas>
            get() = Main.instance.lojas.values.stream().collect(Collectors.toList())
    }

}