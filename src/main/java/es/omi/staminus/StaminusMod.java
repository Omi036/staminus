package es.omi.staminus;

import com.mojang.logging.LogUtils;
import es.omi.staminus.network.NetworkHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(StaminusMod.MODID)
public class StaminusMod {
    public static final String MODID = "staminus";
    public static final Logger LOGGER = LogUtils.getLogger();

    public StaminusMod(FMLJavaModLoadingContext context)
    {
        IEventBus modEventBus = context.getModEventBus();

        NetworkHandler.register();
        MinecraftForge.EVENT_BUS.register(this);

        modEventBus.register(Config.class);
        context.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }
}
