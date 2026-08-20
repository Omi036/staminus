package es.omi.staminus.network.packets;

import es.omi.staminus.Config;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class C2SSendTirednessStatusPacket {
    private final boolean isTired;

    public C2SSendTirednessStatusPacket(boolean playerIsTired){
        this.isTired = playerIsTired;
    }

    public static void encode(C2SSendTirednessStatusPacket msg, FriendlyByteBuf buf) {
        buf.writeBoolean(msg.isTired);
    }

    public static C2SSendTirednessStatusPacket decode(FriendlyByteBuf buf) {
        return new C2SSendTirednessStatusPacket(buf.readBoolean());
    }

    public static void handle(C2SSendTirednessStatusPacket msg, Supplier<NetworkEvent.Context> ctx){
        ctx.get().enqueueWork(() -> {
            ServerPlayer player = ctx.get().getSender();

            if(msg.isTired){
                player.addEffect(
                    new MobEffectInstance(
                        MobEffects.MOVEMENT_SLOWDOWN,
                        1000000, // duration
                        Config.slownessLevel, // intensity
                        false, // ambient
                        false // show particles
                    )
                );

            } else {
                player.removeEffect(MobEffects.MOVEMENT_SLOWDOWN);
            }
        });
    }
}
