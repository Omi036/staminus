package es.omi.staminus.events.client;

import es.omi.staminus.ClientConfig;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static es.omi.staminus.StaminusMod.MODID;
import static es.omi.staminus.core.StaminaCore.*;

@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class COnKeyInput {

    @SubscribeEvent
    public static void onKeyInput(InputEvent.Key event) {
        if (!ClientConfig.jumpDropsStamina) return;
        if (!ClientConfig.staminaEnabled) return;

        Minecraft mc = Minecraft.getInstance();
        if (mc.player != null) {

            if(mc.player.isCreative()) return;

            if (mc.options.keyJump.isDown()) {
                if (!mc.player.onGround()) return;
                if (currentTick - 3 < lastJumpTick) return;

                lastJumpTick = currentTick;

                if (onTimeout) {
                    mc.options.keyJump.setDown(false);
                    return;
                }

                stamina -= ClientConfig.jumpStaminaDrain;
                lastStaminaDropTick = currentTick; // Actualizar el último tick de reducción al saltar
            }
        }
    }

}
