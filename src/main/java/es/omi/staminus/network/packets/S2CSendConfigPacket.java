package es.omi.staminus.network.packets;

import es.omi.staminus.ClientConfig;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

import static es.omi.staminus.core.StaminaCore.resetStamina;

public class S2CSendConfigPacket {
    private final boolean staminaEnabled;
    private final boolean autohideUi;
    private final boolean jumpDropsStamina;
    private final int jumpStaminaDrain;
    private final int maxStamina;
    private final double staminaDropRate;
    private final double staminaRegainRate;
    private final int regenCooldown;
    private final int slownessLevel;

    public S2CSendConfigPacket(
            boolean staminaEnabled,
            boolean autohideUi,
            boolean jumpDropsStamina,
            int jumpStaminaDrain,
            int maxStamina,
            double staminaDropRate,
            double staminaRegainRate,
            int regenCooldown,
            int slownessLevel
        ){

        this.staminaEnabled = staminaEnabled;
        this.autohideUi = autohideUi;
        this.jumpDropsStamina = jumpDropsStamina;
        this.jumpStaminaDrain = jumpStaminaDrain;
        this.maxStamina = maxStamina;
        this.staminaDropRate = staminaDropRate;
        this.staminaRegainRate = staminaRegainRate;
        this.regenCooldown = regenCooldown;
        this.slownessLevel = slownessLevel;
    }

    public static void encode(S2CSendConfigPacket msg, FriendlyByteBuf buf) {
        buf.writeBoolean(msg.staminaEnabled);
        buf.writeBoolean(msg.autohideUi);
        buf.writeBoolean(msg.jumpDropsStamina);
        buf.writeInt(msg.jumpStaminaDrain);
        buf.writeInt(msg.maxStamina);
        buf.writeDouble(msg.staminaDropRate);
        buf.writeDouble(msg.staminaRegainRate);
        buf.writeInt(msg.regenCooldown);
        buf.writeInt(msg.slownessLevel);
    }

    public static S2CSendConfigPacket decode(FriendlyByteBuf buf) {
        boolean staminaEnabled = buf.readBoolean();
        boolean autohideUi = buf.readBoolean();
        boolean jumpDropsStamina = buf.readBoolean();
        int jumpStaminaDrain = buf.readInt();
        int maxStamina = buf.readInt();
        double staminaDropRate = buf.readDouble();
        double staminaRegainRate = buf.readDouble();
        int regenCooldown = buf.readInt();
        int slownessLevel = buf.readInt();

        return new S2CSendConfigPacket(
            staminaEnabled,
            autohideUi,
            jumpDropsStamina,
            jumpStaminaDrain,
            maxStamina,
            staminaDropRate,
            staminaRegainRate,
            regenCooldown,
            slownessLevel
        );
    }

    public static void handle(S2CSendConfigPacket msg, Supplier<NetworkEvent.Context> ctx){
        ctx.get().enqueueWork(() -> {
            ClientConfig.staminaEnabled = msg.staminaEnabled;
            ClientConfig.autohideUi = msg.autohideUi;
            ClientConfig.jumpDropsStamina = msg.jumpDropsStamina;
            ClientConfig.jumpStaminaDrain = msg.jumpStaminaDrain;
            ClientConfig.maxStamina = msg.maxStamina;
            ClientConfig.staminaDropRate = msg.staminaDropRate;
            ClientConfig.staminaRegainRate = msg.staminaRegainRate;
            ClientConfig.regenCooldown = msg.regenCooldown;
            ClientConfig.slownessLevel = msg.slownessLevel;

            resetStamina();
        });
    }

}
