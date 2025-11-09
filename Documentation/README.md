# CR Layers Generator

A Fabric mod for Minecraft 1.20.1 that automatically generates Conquest Reforged terrain layers based on the surrounding blocks.

## Features

- **Automatic Layer Generation**: Generates layers that mimic natural terrain variation
- **Smart Height Detection**: Uses neighbor block heights to determine appropriate layer counts
- **Configurable Radius**: Generate layers in any radius around the player
- **Conquest Reforged Integration**: Maps vanilla blocks to their Conquest Reforged layer equivalents

## Installation

1. Install Fabric Loader 0.15.11+ for Minecraft 1.20.1
2. Install Fabric API 0.92.2+
3. Install Conquest Reforged mod
4. Place this mod's JAR file in your mods folder

## Usage

Use the `/generateLayers` command in-game:

```
/generateLayers [radius]
```

- **radius** (optional): The radius in blocks to generate layers (1-32). Default is 16.

Example:
```
/generateLayers 20
```

This will generate layers in a 20-block radius around your current position.

## How It Works

1. **Surface Detection**: The mod scans all blocks in the specified radius and finds the topmost solid block at each X,Z position
2. **Layer Calculation**: For each surface block, it examines the neighboring blocks' heights to determine how many layers should be placed (mimicking natural terrain variation)
3. **Block Mapping**: The mod looks up the appropriate Conquest Reforged layer block that corresponds to the vanilla block beneath
4. **Layer Placement**: Places the layer blocks above the surface with the correct height/layer count

## Configuration

### Block Mappings

The mod comes with default mappings for common terrain blocks. You can customize these mappings in `BlockMappingRegistry.java`:

```java
blockToLayerMapping.put(Blocks.GRASS_BLOCK, "conquestre:grass_layer");
```

**IMPORTANT**: The current mappings are placeholders. You need to update them with the actual Conquest Reforged block IDs. To find the correct IDs:

1. Install Conquest Reforged
2. Use `/give @s conquestre:` and press TAB to see available blocks
3. Look for blocks with "layer" in their name
4. Update the mappings in `BlockMappingRegistry.java`

### Supported Blocks

Currently, layers can be generated on:
- Grass Block
- Dirt (all variants)
- Stone (all variants)
- Sand
- Gravel
- Cobblestone
- And more terrain blocks...

You can add more blocks by modifying the `canGenerateLayersOn()` method in `LayerGenerator.java`.

## Building from Source

1. Clone this repository
2. Open a terminal in the project directory
3. Run `./gradlew build` (Linux/Mac) or `gradlew.bat build` (Windows)
4. The compiled JAR will be in `build/libs/`

## Development

### Project Structure

```
src/main/java/io/arona74/crlayers/
├── CRLayers.java                 # Main mod class
├── LayerGeneratorCommand.java    # Command registration and handling
├── LayerGenerator.java           # Core generation logic
└── BlockMappingRegistry.java     # Block-to-layer mappings

src/main/resources/
└── fabric.mod.json              # Mod metadata
```

### TODO / Future Enhancements

- [ ] Update block mappings with actual Conquest Reforged block IDs
- [ ] Add vegetation replacement (switching plants with CR layer-compatible variants)
- [ ] Add configuration file for customizable mappings
- [ ] Add biome-specific layer generation
- [ ] Optimize performance for large radius generation
- [ ] Add undo/redo functionality
- [ ] Add preview mode before committing changes
- [ ] Support for Conquest Reforged multi-layer blocks

### Testing Without Conquest Reforged

The mod will fall back to placing snow layers if Conquest Reforged blocks are not found. This allows you to test the core generation logic without having CR installed.

## Requirements

- Minecraft 1.20.1
- Fabric Loader 0.15.11+
- Fabric API 0.92.2+
- Conquest Reforged (for actual layer blocks)

## License

MIT License - Feel free to modify and distribute

## Author

arona74

## Contributing

Contributions are welcome! Please feel free to submit issues or pull requests.

## Notes

- The mod requires OP permission level 2 to use the command
- Generation happens synchronously, so very large radii may cause temporary lag
- Make backups before using in important worlds
- The block mapping system is designed to be easily extensible
