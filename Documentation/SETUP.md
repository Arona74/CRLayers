# Development Setup Guide

## Prerequisites

1. **Java Development Kit (JDK) 17 or higher**
   - Download from: https://adoptium.net/
   - Verify installation: `java -version`

2. **IntelliJ IDEA** (recommended) or **Eclipse**
   - IntelliJ IDEA Community Edition: https://www.jetbrains.com/idea/download/
   - Or Eclipse with Buildship Gradle plugin

## Setting Up the Project

### Option 1: IntelliJ IDEA

1. Open IntelliJ IDEA
2. Click "Open" and select the `crlayers-mod` folder
3. IntelliJ will automatically detect it's a Gradle project
4. Wait for Gradle to sync (this may take a few minutes the first time)
5. Once synced, go to Run → Edit Configurations
6. Click the + button and add "Gradle" configuration
7. Set the task to `runClient`
8. Click OK and run the configuration

### Option 2: Eclipse

1. Open Eclipse
2. File → Import → Gradle → Existing Gradle Project
3. Select the `crlayers-mod` folder
4. Wait for Gradle to import
5. Right-click project → Gradle → Refresh Gradle Project

### Option 3: Command Line

```bash
# Navigate to project directory
cd crlayers-mod

# Generate IDE files (IntelliJ)
./gradlew idea

# Or for Eclipse
./gradlew eclipse

# Run Minecraft client for testing
./gradlew runClient

# Build the mod
./gradlew build
```

## First Time Setup Tasks

### 1. Update Conquest Reforged Block Mappings

The most important task is updating the block mappings in `BlockMappingRegistry.java`. Currently, they're placeholders:

```java
blockToLayerMapping.put(Blocks.GRASS_BLOCK, "conquestre:grass_layer");
```

**To find the correct block IDs:**

1. Install Conquest Reforged in your development environment
2. Run the game: `./gradlew runClient`
3. In-game, type: `/give @s conquestre:` and press TAB
4. Look for blocks with "layer" in their name
5. Update the mappings accordingly

**Alternative method:**
- Look at Conquest Reforged's source code or documentation
- Check their block registration files
- Look at their assets folder for block models

### 2. Test Layer Generation

1. Run the game: `./gradlew runClient`
2. Create a new world or load an existing one
3. Give yourself OP permissions: `/op YourUsername`
4. Test the command: `/generateLayers 10`
5. Observe the results

### 3. Verify Conquest Reforged Integration

If you have Conquest Reforged installed:
- Check the game logs for warnings about missing blocks
- The mod will log: "Layer block not found in registry: [block_id]"
- Use this information to correct your mappings

## Common Gradle Commands

```bash
# Clean build artifacts
./gradlew clean

# Build the mod
./gradlew build

# Run Minecraft client for testing
./gradlew runClient

# Run Minecraft server for testing
./gradlew runServer

# Generate sources for debugging Minecraft code
./gradlew genSources

# Refresh dependencies
./gradlew --refresh-dependencies
```

## Project Structure Explained

```
crlayers-mod/
├── src/main/java/io/arona74/crlayers/
│   ├── CRLayers.java              # Entry point, initializes mod
│   ├── LayerGeneratorCommand.java # Handles /generateLayers command
│   ├── LayerGenerator.java        # Core logic for generation
│   └── BlockMappingRegistry.java  # Maps vanilla → CR blocks
├── src/main/resources/
│   └── fabric.mod.json           # Mod metadata (name, version, etc.)
├── build.gradle                  # Build configuration
├── gradle.properties             # Version numbers and settings
└── settings.gradle              # Gradle settings
```

## Debugging Tips

### Enable Debug Logging

Add this to your run configuration VM options:
```
-Dlog4j.configurationFile=log4j2.xml
```

Or add debug logging in the code:
```java
CRLayers.LOGGER.debug("Debug message here");
```

### Common Issues

**Issue: "Block not found in registry"**
- Solution: Update block mappings with correct Conquest Reforged block IDs

**Issue: Gradle sync fails**
- Solution: Check your internet connection, try `./gradlew clean build --refresh-dependencies`

**Issue: Minecraft won't start**
- Solution: Ensure Java 17+ is installed and set as your JDK

**Issue: Layers appear as snow**
- This is the fallback behavior - means the CR block wasn't found
- Update your block mappings

## Next Steps

1. **Update Block Mappings**: This is critical for the mod to work with Conquest Reforged
2. **Test Generation**: Try different radii and terrain types
3. **Add More Blocks**: Extend `canGenerateLayersOn()` and add more mappings
4. **Implement Vegetation**: Add logic to replace plants with CR-compatible variants
5. **Add Configuration**: Create a config file for user-customizable settings

## Useful Resources

- Fabric Wiki: https://fabricmc.net/wiki/
- Fabric API Javadocs: https://maven.fabricmc.net/docs/fabric-api-0.92.2+1.20.1/
- Minecraft Development: https://minecraft.fandom.com/wiki/Tutorials/Creating_Forge_mods
- Conquest Reforged: (check their Discord/documentation for API details)

## Need Help?

- Check the Fabric Discord: https://discord.gg/v6v4pMv
- Fabric documentation: https://fabricmc.net/wiki/tutorial:start
- Ask in modding communities

Happy modding! 🎮
