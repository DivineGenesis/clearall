package xyz.divinegenesis.clearall;

import baubles.api.BaublesApi;
import io.github.nucleuspowered.nucleus.api.events.NucleusClearInventoryEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.filter.cause.First;
import org.spongepowered.api.plugin.Dependency;
import org.spongepowered.api.plugin.Plugin;

@Plugin (id = "clearall", name = "Clearall",dependencies = {
        @Dependency(id = "nucleus")
})
public class Clearall {

    @Listener
    public void onClearAll (NucleusClearInventoryEvent.Post event,@First Player player) {
        if(event.isClearingAll()) {
            int number = BaublesApi.getBaublesHandler(getNativePlayer(player)).getSlots();
            for (int i = 0;i < number;i++) {
                ItemStack stack = BaublesApi.getBaublesHandler(getNativePlayer(player)).getStackInSlot(i);
                if (!stack.isEmpty()) {
                    stack.setCount(-1);
                }
            }
        }
    }

    private static EntityPlayer getNativePlayer(Player player) {
        return (EntityPlayer) player;
    }
}
