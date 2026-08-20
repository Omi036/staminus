package es.omi.staminus.events.client;

import es.omi.staminus.network.NetworkHandler;
import es.omi.staminus.network.packets.C2SRequestConfigPacket;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ClientPlayerNetworkEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static es.omi.staminus.StaminusMod.MODID;

@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class COnPlayerJoinEvent {

    @SubscribeEvent
    public static void onPlayerJoin(ClientPlayerNetworkEvent.LoggingIn event){
        NetworkHandler.INSTANCE.sendToServer(new C2SRequestConfigPacket());
    }

}