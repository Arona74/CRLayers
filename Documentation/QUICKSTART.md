# Quick Start Guide - CR Layers Generator

## ⚡ Getting Started in 5 Minutes

### 1. Open the Project
- Open the `crlayers-mod` folder in IntelliJ IDEA or your preferred IDE
- Wait for Gradle to sync (first time may take a few minutes)

### 2. Update Block Mappings (CRITICAL!)
Open `src/main/java/io/arona74/crlayers/BlockMappingRegistry.java` and update the placeholder block IDs:

**Current (placeholder):**
```java
blockToLayerMapping.put(Blocks.GRASS_BLOCK, "conquestre:grass_layer");
```

**You need to change to actual Conquest Reforged block IDs. Examples might be:**
```java
// Check Conquest Reforged documentation for actual IDs!
blockToLayerMapping.put(Blocks.GRASS_BLOCK, "conquestre:grass_block_layer");
blockToLayerMapping.put(Blocks.DIRT, "conquestre:dirt_layer");
// etc...
```

### 3. Build the Mod
```bash
./gradlew build
```
The compiled JAR will be in: `build/libs/crlayers-1.0.0.jar`

### 4. Install and Test
1. Copy the JAR to your Minecraft `mods` folder
2. Make sure you have:
   - Minecraft 1.20.1
   - Fabric Loader 0.15.11+
   - Fabric API 0.92.2+
   - Conquest Reforged installed
3. Launch Minecraft
4. Join a world
5. Run: `/generateLayers 10`

## 🔍 How to Find Conquest Reforged Block IDs

### Method 1: In-Game
1. Run Minecraft with Conquest Reforged installed
2. Type: `/give @s conquestre:` and press TAB
3. Look for blocks with "layer" in the name
4. Note down the IDs

### Method 2: Check Files
1. Navigate to: `.minecraft/mods/`
2. Open the Conquest Reforged JAR with a ZIP tool
3. Look in: `assets/conquestre/blockstates/`
4. Find layer block definitions

### Method 3: Documentation
- Check Conquest Reforged's official documentation
- Look at their GitHub if available
- Ask in their Discord server

## 📝 Key Files to Customize

1. **BlockMappingRegistry.java** - Add your block mappings here
2. **LayerGenerator.java** - Modify generation logic
   - `calculateLayerCount()` - Change how layer heights are determined
   - `canGenerateLayersOn()` - Add more block types
3. **fabric.mod.json** - Update mod metadata

## 🎮 Testing Without Conquest Reforged

The mod will work without CR installed! It will:
- Place snow layers instead of CR layers (fallback behavior)
- Log warnings about missing blocks
- Allow you to test the generation logic

This is useful for:
- Testing the algorithm
- Debugging surface detection
- Verifying command functionality

## 🐛 Troubleshooting

### "Block not found in registry"
**Cause:** Block ID doesn't exist or CR isn't installed
**Fix:** Update BlockMappingRegistry with correct IDs

### Layers appear as snow
**Cause:** Fallback behavior (CR blocks not found)
**Fix:** This is normal without CR; check your mappings if CR is installed

### Command not working
**Cause:** Insufficient permissions
**Fix:** Run `/op YourUsername` in single-player or have admin do it on server

### Build fails
**Cause:** Usually Gradle or Java version issues
**Fix:** Ensure Java 17+ is installed, try `./gradlew clean build --refresh-dependencies`

## 🚀 Next Steps

1. **Test the basics** - Generate layers in creative mode
2. **Refine mappings** - Add all Conquest Reforged blocks you want
3. **Tweak generation** - Adjust layer height algorithm
4. **Add features** - Implement vegetation replacement
5. **Share your work** - Publish to Modrinth/CurseForge!

## 📚 Documentation

- **README.md** - Full feature documentation
- **SETUP.md** - Detailed development setup
- **ARCHITECTURE.md** - Technical implementation details

## 💡 Pro Tips

1. Start with a small radius (5-10) for testing
2. Use flat terrain to verify basic functionality first
3. Check logs for useful debugging information
4. Back up your world before testing!
5. Test in creative mode first

## Need Help?

- Read the full documentation in README.md and SETUP.md
- Check the Fabric Discord for general modding help
- Review Conquest Reforged's documentation for their block system

Good luck with your mod! 🎉
