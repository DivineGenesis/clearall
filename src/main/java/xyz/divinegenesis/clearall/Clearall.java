package xyz.divinegenesis.clearall;

import baubles.api.BaublesApi;
import com.lothrazar.cyclicmagic.playerupgrade.storage.InventoryPlayerExtended;
import com.lothrazar.cyclicmagic.util.UtilPlayerInventoryFilestorage;
import io.github.nucleuspowered.nucleus.api.module.inventory.NucleusClearInventoryEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.filter.cause.First;
import org.spongepowered.api.plugin.Dependency;
import org.spongepowered.api.plugin.Plugin;



@Plugin (id = "clearall",
        version = "1.2.0",
        name = "Clearall",
        dependencies = {
        @Dependency(
                id = "nucleus", version = "[2.0.0,)"
        )
})
public class Clearall {

    @Listener
    public void onClearAll (NucleusClearInventoryEvent.Post event, @First Player player) {

        if (event.isClearingAll()) {
            if (Sponge.getPluginManager().isLoaded("baubles")) {
                clearBaubles(player);
            }
            if (Sponge.getPluginManager().isLoaded("cyclicmagic")) {
                clearCyclic(player);
            }
        }
    }

    private static void clearBaubles (Player player) {

        int size = BaublesApi.getBaublesHandler(getNativePlayer(player)).getSlots();
        for (int i = 0;i < size;i++) {
            ItemStack stack = BaublesApi.getBaublesHandler(getNativePlayer(player)).getStackInSlot(i);
            if (!stack.isEmpty()) {
                stack.setCount(-1);
            }
        }
    }

    private static void clearCyclic (Player player) {

        InventoryPlayerExtended inventory = UtilPlayerInventoryFilestorage.getPlayerInventory(getNativePlayer(player));
        int size = inventory.getSizeInventory();
        for (int i = 0;i < size;i++) {
            ItemStack stack = inventory.getStackInSlot(i);
            if (!stack.isEmpty()) {
                stack.setCount(-1);
            }
        }
    }

    private static EntityPlayer getNativePlayer (Player player) {

        return (EntityPlayer) player;
    }
}
