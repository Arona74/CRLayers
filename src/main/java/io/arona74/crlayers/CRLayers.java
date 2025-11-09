package io.arona74.crlayers;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CRLayers implements ModInitializer {
    public static final String MOD_ID = "crlayers";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        LOGGER.info("Initializing CR Layers Generator");
        
        // Register command
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            LayerGeneratorCommand.register(dispatcher);
        });
        
        LOGGER.info("CR Layers Generator initialized successfully");
    }
}
