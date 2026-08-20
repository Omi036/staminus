package es.omi.staminus;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.config.ModConfigEvent;

public class Config
{
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

    private static final ForgeConfigSpec.Builder b = BUILDER.comment("======= STAMINUS CONFIG =======");
    private static final ForgeConfigSpec.Builder c = BUILDER.comment("Keep in mind that 20ticks = 1s\n");

    private static final ForgeConfigSpec.BooleanValue STAMINA_ENABLED = BUILDER
            .comment("Whether stamina system is enabled")
            .define("staminaEnabled", true);

    private static final ForgeConfigSpec.BooleanValue AUTOHIDE = BUILDER
            .comment("Whether the stamina bar hides when full")
            .define("autohide", true);

    private static final ForgeConfigSpec.BooleanValue JUMP_DROPS_STAMINA = BUILDER
            .comment("Whether jumping drops stamina")
            .define("jumpDropsStamina", true);

    private static final ForgeConfigSpec.IntValue JUMP_STAMINA_DRAIN = BUILDER
            .comment("Stamina amount that jumping drains")
            .defineInRange("jumpStaminaDrain", 30, 0, Integer.MAX_VALUE);

    private static final ForgeConfigSpec.IntValue MAX_STAMINA = BUILDER
            .comment("Maximum stamina value")
            .defineInRange("maxStamina", 200, 0, Integer.MAX_VALUE);

    private static final ForgeConfigSpec.IntValue SLOWNESS_LEVEL = BUILDER
            .comment("How much slowness should apply to the tired player")
            .defineInRange("slownessLevel", 2, 0, 4);

    private static final ForgeConfigSpec.DoubleValue STAMINA_DROP_RATE = BUILDER
            .comment("Rate at which stamina drops (How much stamina sprinting takes per tick)")
            .defineInRange("staminaDropRate", 1.0, 0.0, Double.MAX_VALUE);

    private static final ForgeConfigSpec.DoubleValue STAMINA_REGAIN_RATE = BUILDER
            .comment("Rate at which stamina regenerates (How much stamina regens per tick")
            .defineInRange("staminaRegainRate", 0.7, 0.0, Double.MAX_VALUE);

    private static final ForgeConfigSpec.IntValue REGEN_COOLDOWN = BUILDER
            .comment("How much ticks needs to pass since last drain to start regen")
            .defineInRange("regenCooldown", 60, 0, Integer.MAX_VALUE);


    static final ForgeConfigSpec SPEC = BUILDER.build();

    // Variables estáticas para acceder a los valores
    public static boolean staminaEnabled;
    public static boolean autohideUi;
    public static boolean jumpDropsStamina;
    public static int jumpStaminaDrain;
    public static int maxStamina;
    public static double staminaDropRate;
    public static double staminaRegainRate;
    public static int regenCooldown;
    public static int slownessLevel;

    @SubscribeEvent
    static void onLoad(final ModConfigEvent event)
    {
        staminaEnabled = STAMINA_ENABLED.get();
        jumpDropsStamina = JUMP_DROPS_STAMINA.get();
        jumpStaminaDrain = JUMP_STAMINA_DRAIN.get();
        maxStamina = MAX_STAMINA.get();
        staminaDropRate = STAMINA_DROP_RATE.get();
        staminaRegainRate = STAMINA_REGAIN_RATE.get();
        regenCooldown = REGEN_COOLDOWN.get();
        autohideUi = AUTOHIDE.get();
        slownessLevel = SLOWNESS_LEVEL.get();
    }
}
