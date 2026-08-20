package es.omi.staminus.network;

import es.omi.staminus.StaminusMod;
import es.omi.staminus.network.packets.C2SRequestConfigPacket;
import es.omi.staminus.network.packets.C2SSendTirednessStatusPacket;
import es.omi.staminus.network.packets.S2CSendConfigPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

public class NetworkHandler {
    private static final String PROTOCOL_VERSION = "1";

    public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(
            ResourceLocation.fromNamespaceAndPath(StaminusMod.MODID, "main"),
            () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals,
            PROTOCOL_VERSION::equals
    );


    private static int packetId = 0;
    public static void register() {
        INSTANCE.registerMessage(
                packetId++,
                S2CSendConfigPacket.class,
                S2CSendConfigPacket::encode,
                S2CSendConfigPacket::decode,
                S2CSendConfigPacket::handle
        );

        INSTANCE.registerMessage(
                packetId++,
                C2SRequestConfigPacket.class,
                C2SRequestConfigPacket::encode,
                C2SRequestConfigPacket::decode,
                C2SRequestConfigPacket::handle
        );

        INSTANCE.registerMessage(
                packetId++,
                C2SSendTirednessStatusPacket.class,
                C2SSendTirednessStatusPacket::encode,
                C2SSendTirednessStatusPacket::decode,
                C2SSendTirednessStatusPacket::handle
        );
    }
}
