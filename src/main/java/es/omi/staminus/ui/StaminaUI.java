package es.omi.staminus.ui;

import es.omi.staminus.ClientConfig;
import es.omi.staminus.StaminusMod;
import es.omi.staminus.core.StaminaCore;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = StaminusMod.MODID, value = Dist.CLIENT)
public class StaminaUI {

    private static final int width = 80;
    private static final int height = 6;

    private static final int dx = 2;
    private static final int dy = 2;
    private static final int color = 0xff5a5a5a;
    private static final int bgColor = 0xff222222;
    private static final int borderColor = 0xff939393;
    private static final int outerBorderColor = 0xff000000;

    @SubscribeEvent
    public static void onRenderGui(RenderGuiOverlayEvent.Post event) {

        GuiGraphics guiGraphics = event.getGuiGraphics();

        if(!ClientConfig.autohideUi){
            renderBar(guiGraphics);
        } else if(StaminaCore.stamina < ClientConfig.maxStamina){
            renderBar(guiGraphics);
        }
    }

    public static void renderBar(GuiGraphics guiGraphics){
        int progressWith = (int)(Math.max(Math.ceil((StaminaCore.stamina/ClientConfig.maxStamina)*width), 1));

        guiGraphics.fill(
                dx,
                guiGraphics.guiHeight() - dy - height,
                width+1,
                guiGraphics.guiHeight() - dy,
                bgColor);


        guiGraphics.fill(
                dx,
                guiGraphics.guiHeight() - dy - height,
                progressWith,
                guiGraphics.guiHeight() - dy,
                color);


        guiGraphics.fill(
                dx,
                guiGraphics.guiHeight() - dy - height,
                progressWith,
                guiGraphics.guiHeight() - dy - height + 1,
                borderColor
        );

        guiGraphics.renderOutline(
                dx-1,
                guiGraphics.guiHeight() - dy - height-1,
                width+1,
                height+1,
                outerBorderColor
        );
    }
}
