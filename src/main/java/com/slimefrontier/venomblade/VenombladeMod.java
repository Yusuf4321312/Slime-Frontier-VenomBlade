package com.slimefrontier.venomblade;

import com.mojang.logging.LogUtils;
import com.slimefrontier.venomblade.registry.ModContent;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import org.slf4j.Logger;

@Mod(VenombladeMod.MOD_ID)
public class VenombladeMod {
    public static final String MOD_ID = "venomblade";
    private static final Logger LOGGER = LogUtils.getLogger();

    public VenombladeMod(IEventBus modEventBus, ModContainer modContainer) {
        ModContent.register(modEventBus);
        LOGGER.info("Slime Frontier: Venomblade Ultimate loaded for Minecraft 1.21.1");
    }
}
