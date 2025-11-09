# CR Layers Architecture

## Flow Diagram

```
Player runs command: /generateLayers 20
           ↓
LayerGeneratorCommand receives command
           ↓
Creates LayerGenerator instance
           ↓
LayerGenerator.generateLayers(playerPos, radius)
           ↓
For each position in radius:
    ├→ findSurfaceBlock(pos)
    │     └→ Uses Heightmap to find top block
    │
    ├→ canGenerateLayersOn(block)?
    │     └→ Check if block type supports layers
    │
    ├→ calculateLayerCount(surfacePos)
    │     └→ Check 4 neighbors' heights
    │     └→ Calculate average height difference
    │     └→ Return layer count (1-8)
    │
    └→ BlockMappingRegistry.getLayerBlock(block, count)
          └→ Look up Conquest Reforged layer block
          └→ Return CR block or fallback to snow
          
Final: Place layer blocks in world
```

## Component Responsibilities

### CRLayers (Main Class)
- Mod initialization
- Command registration
- Logging setup

### LayerGeneratorCommand
- Parse command arguments (radius)
- Validate player permissions
- Execute generation
- Send feedback to player

### LayerGenerator
- **Surface Detection**: Find topmost solid blocks
- **Height Analysis**: Check neighbor blocks
- **Layer Calculation**: Determine appropriate layer count
- **Block Placement**: Place layers in world

### BlockMappingRegistry
- **Mapping Storage**: Vanilla block → CR layer block
- **Block Lookup**: Retrieve appropriate CR block
- **Fallback Handling**: Use snow if CR block missing

## Key Algorithms

### Surface Detection
1. Use world heightmap for initial Y position
2. Iterate downward until solid block found
3. Return BlockPos of surface

### Layer Count Calculation
```
For each cardinal neighbor (N, S, E, W):
    - Find neighbor surface height
    - Calculate height difference from current position
    - Average all differences
    
Convert height difference to layer count:
    - avgDiff >= 2:  8 layers (peaks)
    - avgDiff >= 1:  6 layers (high)
    - avgDiff >= 0:  4 layers (level)
    - avgDiff >= -1: 2 layers (low)
    - else:          1 layer  (valleys)
```

### Block Mapping
```
Input: Vanilla block (e.g., GRASS_BLOCK)
       Layer count (1-8)

Process:
    1. Look up block in mapping registry
    2. Get Conquest Reforged block ID
    3. Check if block exists in game registry
    4. If not found, fallback to SNOW
    5. Apply layer count property if supported

Output: Block to place
```

## Data Flow

```
User Input → Command Parser → Generator → Registry → World
                                ↓            ↓
                            Heightmap    Block IDs
                                ↓            ↓
                            Surface      CR Blocks
                                ↓            ↓
                            Height       Mappings
                                Calculation   ↓
                                      ↓       ↓
                                  Layer Count ↓
                                           ↓  ↓
                                    Block Placement
```

## Extension Points

### Adding New Block Types
1. Add to `canGenerateLayersOn()` in LayerGenerator
2. Add mapping in BlockMappingRegistry constructor

### Custom Layer Logic
- Modify `calculateLayerCount()` algorithm
- Add biome-specific logic
- Add block-type-specific logic

### Vegetation Handling (Future)
```
After layer placement:
    For each position:
        ├→ Check block above layer
        ├→ If vegetation detected
        │     ├→ Get CR-compatible plant variant
        │     └→ Replace block
        └→ Continue
```

## Performance Considerations

- **Heightmap Usage**: Fast surface detection
- **Single Pass**: One iteration over area
- **Registry Caching**: Block lookups cached in map
- **Chunk Loading**: Ensure chunks loaded before access

## Error Handling

1. **Missing Blocks**: Fallback to snow layers
2. **Invalid Radius**: Clamped to 1-32 range
3. **Permission Denied**: Error message to player
4. **CR Not Installed**: Warning logged, snow fallback
