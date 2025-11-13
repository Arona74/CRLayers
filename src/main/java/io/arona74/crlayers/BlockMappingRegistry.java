package io.arona74.crlayers;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

import java.util.HashMap;
import java.util.Map;

/**
 * Registry for mapping vanilla blocks to their Conquest Reforged layer equivalents
 */
public class BlockMappingRegistry {
    private final Map<Block, String> blockToLayerMapping;
    
    public BlockMappingRegistry() {
        this.blockToLayerMapping = new HashMap<>();
        registerDefaultMappings();
    }
    
    /**
     * Load block to Conquest Reforged layer mappings from config file
     */
    private void registerDefaultMappings() {
        // Load mappings from config file
        Map<String, String> configMappings = ConfigLoader.loadMappings("block_mappings.json");

        // Convert string IDs to Block objects
        for (Map.Entry<String, String> entry : configMappings.entrySet()) {
            Identifier vanillaId = Identifier.tryParse(entry.getKey());
            if (vanillaId == null) {
                CRLayers.LOGGER.warn("Invalid vanilla block ID in config: {}", entry.getKey());
                continue;
            }

            Block vanillaBlock = Registries.BLOCK.get(vanillaId);
            if (vanillaBlock == Blocks.AIR) {
                CRLayers.LOGGER.warn("Vanilla block not found: {}", entry.getKey());
                continue;
            }

            blockToLayerMapping.put(vanillaBlock, entry.getValue());
        }

        CRLayers.LOGGER.info("Registered {} block-to-layer mappings from config", blockToLayerMapping.size());
    }
    
    /**
     * Get the Conquest Reforged layer block for a given vanilla block
     * @param vanillaBlock The vanilla block
     * @param layerCount The number of layers (1-8, though this may not be used depending on CR's implementation)
     * @return The Conquest Reforged layer block, or null if no mapping exists or block not found
     */
    public Block getLayerBlock(Block vanillaBlock, int layerCount) {
        String layerBlockId = blockToLayerMapping.get(vanillaBlock);
        
        if (layerBlockId == null) {
            // No mapping found, fall back to snow layers for now
            CRLayers.LOGGER.debug("No layer mapping found for block: {}, using snow as fallback", 
                Registries.BLOCK.getId(vanillaBlock));
            return Blocks.SNOW;
        }
        
        // Try to get the block from registry
        Identifier identifier = Identifier.tryParse(layerBlockId);
        if (identifier == null) {
            CRLayers.LOGGER.warn("Invalid block identifier: {}", layerBlockId);
            return Blocks.SNOW; // Fallback
        }
        
        // Check if Conquest Reforged uses different blocks for different layer counts
        // Some implementations might use: conquest:grass_layer_1, conquest:grass_layer_2, etc.
        // If so, you can modify the identifier here before looking it up
        
        Block layerBlock = Registries.BLOCK.get(identifier);
        
        if (layerBlock == Blocks.AIR) {
            // Block not found in registry, might mean Conquest Reforged isn't loaded
            CRLayers.LOGGER.warn("Layer block not found in registry: {}. Is Conquest Reforged installed?", layerBlockId);
            return Blocks.SNOW; // Fallback to snow for testing
        }
        
        return layerBlock;
    }
    
    /**
     * Register a custom mapping
     * @param vanillaBlock The vanilla block
     * @param conquestLayerBlockId The Conquest Reforged layer block ID (e.g., "conquest:grass_layer")
     */
    public void registerMapping(Block vanillaBlock, String conquestLayerBlockId) {
        blockToLayerMapping.put(vanillaBlock, conquestLayerBlockId);
        CRLayers.LOGGER.info("Registered custom mapping: {} -> {}", 
            Registries.BLOCK.getId(vanillaBlock), conquestLayerBlockId);
    }
    
    /**
     * Check if a mapping exists for a block
     */
    public boolean hasMapping(Block block) {
        return blockToLayerMapping.containsKey(block);
    }
}
