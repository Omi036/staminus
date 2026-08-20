package es.omi.staminus.events.client;

import es.omi.staminus.ClientConfig;
import es.omi.staminus.network.NetworkHandler;
import es.omi.staminus.network.packets.C2SSendTirednessStatusPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static es.omi.staminus.StaminusMod.MODID;
import static es.omi.staminus.core.StaminaCore.*;

@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class COnPlayerTick {

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (!ClientConfig.staminaEnabled) return;
        Player player = event.player;

        if(player != Minecraft.getInstance().player) return;
        if(player.isCreative()) return;
        currentTick++; // Incrementar el tick actual


        if (player.isSprinting()) {
            if (onTimeout) {
                player.setSprinting(false);
                onTimeout = true;
            } else {
                stamina -= ClientConfig.staminaDropRate;
                running = true;
                lastStaminaDropTick = currentTick; // Actualizar el último tick de reducción
            }
        } else {
            running = false;
        }

        if (stamina < 1) {
            onTimeout = true;
            Minecraft.getInstance().options.keyJump.setDown(false);
        }

        if (onTimeout && stamina >= ClientConfig.maxStamina) {
            onTimeout = false;
            wasOnTimeout = false;
            NetworkHandler.INSTANCE.sendToServer(new C2SSendTirednessStatusPacket(false));
        }

        // Regenerar stamina solo si han pasado 60 ticks desde la última reducción
        if (stamina < ClientConfig.maxStamina && !running && (currentTick - lastStaminaDropTick >= ClientConfig.regenCooldown)) {
            stamina += ClientConfig.staminaRegainRate;
        }

        if (onTimeout) {
            if(!wasOnTimeout){
                NetworkHandler.INSTANCE.sendToServer(new C2SSendTirednessStatusPacket(true));
            } else {
                wasOnTimeout = true;
            }
        }
    }
}
