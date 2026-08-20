package es.omi.staminus.core;

import es.omi.staminus.ClientConfig;
import es.omi.staminus.StaminusMod;
import es.omi.staminus.network.NetworkHandler;
import es.omi.staminus.network.packets.C2SSendTirednessStatusPacket;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.common.Mod;


@Mod.EventBusSubscriber(modid = StaminusMod.MODID, value = Dist.CLIENT)
public class StaminaCore {
    public static double stamina = 0;
    public static boolean onTimeout = false;
    public static boolean wasOnTimeout = false;
    public static boolean running = false;

    public static int lastStaminaDropTick = 0; // Último tick en el que se redujo la stamina
    public static int lastJumpTick = 0;
    public static int currentTick = 0; // Tick actual


    public static void resetStamina() {
        stamina = ClientConfig.maxStamina;
        onTimeout = false;
        wasOnTimeout = false;
        running = false;
        lastStaminaDropTick = 0;
        lastJumpTick = 0;
        NetworkHandler.INSTANCE.sendToServer(new C2SSendTirednessStatusPacket(false));
    }
}
