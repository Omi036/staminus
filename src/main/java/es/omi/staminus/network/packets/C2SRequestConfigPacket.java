package es.omi.staminus.network.packets;

import es.omi.staminus.Config;
import es.omi.staminus.network.NetworkHandler;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.PacketDistributor;

import java.util.function.Supplier;

public class C2SRequestConfigPacket {

    public C2SRequestConfigPacket(){}
    public static void encode(C2SRequestConfigPacket _msg, FriendlyByteBuf buf) {}
    public static C2SRequestConfigPacket decode(FriendlyByteBuf buf) {return new C2SRequestConfigPacket();}

    public static void handle(C2SRequestConfigPacket msg, Supplier<NetworkEvent.Context> ctx){
        ctx.get().enqueueWork(() -> {
            ServerPlayer player = ctx.get().getSender();
            NetworkHandler.INSTANCE.send(
                PacketDistributor.PLAYER.with(() -> player),


                new S2CSendConfigPacket(
                    Config.staminaEnabled,
                    Config.autohideUi,
                    Config.jumpDropsStamina,
                    Config.jumpStaminaDrain,
                    Config.maxStamina,
                    Config.staminaDropRate,
                    Config.staminaRegainRate,
                    Config.regenCooldown,
                    Config.slownessLevel
                )
            );
        });
    }
}
