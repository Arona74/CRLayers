package io.arona74.crlayers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.minecraft.block.Block;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

/**
 * Stores plant replacement data to disk for persistence
 * Format: JSON file mapping positions to original plant types
 */
public class PlantDataStorage {
    private static final String FILENAME = "crlayers_plants.json";
    private final Path dataFile;
    private final Gson gson;
    private Map<String, String> plantData; // BlockPos string -> Plant ID string
    private Map<String, Boolean> tallPlantFlags; // BlockPos string -> is tall plant
    
    public PlantDataStorage(Path worldDir) {
        this.dataFile = worldDir.resolve(FILENAME);
        this.gson = new GsonBuilder().setPrettyPrinting().create();
        this.plantData = new HashMap<>();
        this.tallPlantFlags = new HashMap<>();
        load();
    }
    
    /**
     * Store that a plant was replaced at this position
     */
    public void storePlant(BlockPos pos, Block vanillaPlant) {
        String posKey = posToString(pos);
        String plantId = Registries.BLOCK.getId(vanillaPlant).toString();
        plantData.put(posKey, plantId);
    }
    
    /**
     * Mark a position as having a tall plant (2 blocks)
     */
    public void storeTallPlant(BlockPos pos, boolean isTall) {
        String posKey = posToString(pos);
        tallPlantFlags.put(posKey, isTall);
    }
    
    /**
     * Check if the plant at this position was a tall plant
     */
    public boolean isTallPlant(BlockPos pos) {
        String posKey = posToString(pos);
        return tallPlantFlags.getOrDefault(posKey, false);
    }
    
    /**
     * Remove tall plant flag for this position
     */
    public void removeTallPlantFlag(BlockPos pos) {
        String posKey = posToString(pos);
        tallPlantFlags.remove(posKey);
    }
    
    /**
     * Get the original plant that was at this position
     * Returns null if no plant data exists
     */
    public Block getPlant(BlockPos pos) {
        String posKey = posToString(pos);
        String plantId = plantData.get(posKey);
        
        if (plantId == null) {
            return null;
        }
        
        Identifier identifier = Identifier.tryParse(plantId);
        if (identifier == null) {
            return null;
        }
        
        return Registries.BLOCK.get(identifier);
    }
    
    /**
     * Remove plant data for this position (after restoration)
     */
    public void removePlant(BlockPos pos) {
        String posKey = posToString(pos);
        plantData.remove(posKey);
    }
    
    /**
     * Check if we have plant data for this position
     */
    public boolean hasPlantData(BlockPos pos) {
        return plantData.containsKey(posToString(pos));
    }
    
    /**
     * Save plant data to disk
     */
    public void save() {
        try {
            // Create wrapper object with both maps
            Map<String, Object> data = new HashMap<>();
            data.put("plants", plantData);
            data.put("tallFlags", tallPlantFlags);
            
            String json = gson.toJson(data);
            Files.writeString(dataFile, json);
            CRLayers.LOGGER.info("Saved {} plant entries to {}", plantData.size(), dataFile);
        } catch (IOException e) {
            CRLayers.LOGGER.error("Failed to save plant data", e);
        }
    }
    
    /**
     * Load plant data from disk
     */
    private void load() {
        if (!Files.exists(dataFile)) {
            CRLayers.LOGGER.info("No existing plant data file found");
            return;
        }
        
        try {
            String json = Files.readString(dataFile);
            @SuppressWarnings("unchecked")
            Map<String, Object> data = gson.fromJson(json, Map.class);
            
            if (data != null) {
                // Load plant data
                if (data.containsKey("plants")) {
                    @SuppressWarnings("unchecked")
                    Map<String, Object> plantsObj = (Map<String, Object>) data.get("plants");
                    if (plantsObj != null) {
                        // Convert Map<String, Object> to Map<String, String>
                        plantData = new HashMap<>();
                        for (Map.Entry<String, Object> entry : plantsObj.entrySet()) {
                            plantData.put(entry.getKey(), entry.getValue().toString());
                        }
                    }
                } else {
                    // Old format compatibility - data itself is the plant map
                    plantData = new HashMap<>();
                    for (Map.Entry<String, Object> entry : data.entrySet()) {
                        if (!"tallFlags".equals(entry.getKey())) {
                            plantData.put(entry.getKey(), entry.getValue().toString());
                        }
                    }
                }
                
                // Load tall plant flags
                if (data.containsKey("tallFlags")) {
                    @SuppressWarnings("unchecked")
                    Map<String, Object> flagsObj = (Map<String, Object>) data.get("tallFlags");
                    if (flagsObj != null) {
                        // Convert Map<String, Object> to Map<String, Boolean>
                        tallPlantFlags = new HashMap<>();
                        for (Map.Entry<String, Object> entry : flagsObj.entrySet()) {
                            tallPlantFlags.put(entry.getKey(), Boolean.valueOf(entry.getValue().toString()));
                        }
                    }
                }
                
                CRLayers.LOGGER.info("Loaded {} plant entries from {}", plantData.size(), dataFile);
            }
        } catch (IOException e) {
            CRLayers.LOGGER.error("Failed to load plant data", e);
        }
    }
    
    /**
     * Clear all plant data
     */
    public void clear() {
        plantData.clear();
        tallPlantFlags.clear();
        save();
    }
    
    /**
     * Convert BlockPos to string key
     */
    private String posToString(BlockPos pos) {
        return pos.getX() + "," + pos.getY() + "," + pos.getZ();
    }
    
    /**
     * Get the number of stored plant entries
     */
    public int getSize() {
        return plantData.size();
    }
}