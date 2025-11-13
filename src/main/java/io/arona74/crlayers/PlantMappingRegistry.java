package io.arona74.crlayers;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

import java.util.HashMap;
import java.util.Map;

/**
 * Registry for mapping vanilla plants to their Conquest Reforged equivalents
 */
public class PlantMappingRegistry {
    private final Map<Block, String> vanillaToConquestPlant;
    private final Map<String, Block> conquestToVanillaPlant;
    
    public PlantMappingRegistry() {
        this.vanillaToConquestPlant = new HashMap<>();
        this.conquestToVanillaPlant = new HashMap<>();
        registerDefaultMappings();
    }
    
    /**
     * Load plant mappings from config file
     */
    private void registerDefaultMappings() {
        // Load mappings from config file
        Map<String, String> configMappings = ConfigLoader.loadMappings("plant_mappings.json");

        // Convert string IDs to Block objects and register bidirectional mappings
        for (Map.Entry<String, String> entry : configMappings.entrySet()) {
            Identifier vanillaId = Identifier.tryParse(entry.getKey());
            if (vanillaId == null) {
                CRLayers.LOGGER.warn("Invalid vanilla plant ID in config: {}", entry.getKey());
                continue;
            }

            Block vanillaBlock = Registries.BLOCK.get(vanillaId);
            if (vanillaBlock == Blocks.AIR) {
                CRLayers.LOGGER.warn("Vanilla plant not found: {}", entry.getKey());
                continue;
            }

            registerPlantMapping(vanillaBlock, entry.getValue());
        }

        CRLayers.LOGGER.info("Registered {} plant mappings from config", vanillaToConquestPlant.size());
    }
    
    /**
     * Register a bidirectional plant mapping
     */
    private void registerPlantMapping(Block vanillaPlant, String conquestPlantId) {
        vanillaToConquestPlant.put(vanillaPlant, conquestPlantId);
        conquestToVanillaPlant.put(conquestPlantId, vanillaPlant);
    }
    
    /**
     * Get Conquest Reforged plant for a vanilla plant
     * @return Conquest plant block, or null if no mapping or block not found
     */
    public Block getConquestPlant(Block vanillaPlant) {
        String conquestId = vanillaToConquestPlant.get(vanillaPlant);
        if (conquestId == null) {
            return null; // No mapping
        }
        
        Identifier identifier = Identifier.tryParse(conquestId);
        if (identifier == null) {
            CRLayers.LOGGER.warn("Invalid conquest plant identifier: {}", conquestId);
            return null;
        }
        
        Block conquestPlant = Registries.BLOCK.get(identifier);
        if (conquestPlant == Blocks.AIR) {
            CRLayers.LOGGER.warn("Conquest plant not found: {}. Is Conquest Reforged installed?", conquestId);
            return null;
        }
        
        return conquestPlant;
    }
    
    /**
     * Get vanilla plant for a Conquest Reforged plant ID
     * Used when restoring plants
     */
    public Block getVanillaPlant(String conquestPlantId) {
        return conquestToVanillaPlant.get(conquestPlantId);
    }
    
    /**
     * Get vanilla plant for a Conquest Reforged plant block
     */
    public Block getVanillaPlant(Block conquestPlant) {
        String conquestId = Registries.BLOCK.getId(conquestPlant).toString();
        return conquestToVanillaPlant.get(conquestId);
    }
    
    /**
     * Check if a vanilla block is a plant that can be replaced
     */
    public boolean isReplaceablePlant(Block block) {
        return vanillaToConquestPlant.containsKey(block);
    }
    
    /**
     * Check if a block is a Conquest Reforged plant
     */
    public boolean isConquestPlant(Block block) {
        String blockId = Registries.BLOCK.getId(block).toString();
        return conquestToVanillaPlant.containsKey(blockId);
    }
}