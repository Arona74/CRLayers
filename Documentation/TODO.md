# CR Layers - Development TODO List

## 🔴 Critical (Must Do First)

- [ ] **Update Block Mappings with Real Conquest Reforged IDs**
  - Find actual CR block IDs for layers
  - Update BlockMappingRegistry.java
  - Test each mapping in-game
  - Document the mapping format CR uses

- [ ] **Test with Conquest Reforged Installed**
  - Verify blocks generate correctly
  - Check layer heights work as expected
  - Ensure no crashes or conflicts

## 🟡 High Priority (Core Functionality)

- [ ] **Vegetation Replacement System**
  - Detect plants/vegetation above generated layers
  - Create mapping system for vanilla → CR plants
  - Implement plant replacement logic
  - Handle tall plants (2-block high flowers, etc.)

- [ ] **Performance Optimization**
  - Implement async generation for large radii
  - Add progress feedback for long operations
  - Batch block updates for better performance
  - Add cancellation support for long-running operations

- [ ] **Undo/Redo System**
  - Store previous block states
  - Implement `/undoLayers` command
  - Add maximum undo history limit
  - Clear history on world change

## 🟢 Medium Priority (UX Improvements)

- [ ] **Configuration System**
  - Create config file (JSON or TOML)
  - Add customizable block mappings via config
  - Add per-block layer count overrides
  - Add enable/disable vegetation replacement option

- [ ] **Preview Mode**
  - Add `/previewLayers` command
  - Show temporary client-side blocks
  - Allow player to confirm before placing
  - Add particle effects for preview boundaries

- [ ] **Better Feedback**
  - Show progress bar for large operations
  - Add statistics (blocks changed, time taken)
  - Show what block types were converted
  - Add sound effect on completion

- [ ] **Region Selection**
  - Add WorldEdit integration (optional)
  - Support cuboid selection instead of just radius
  - Add `/generateLayersHere` for current chunk only
  - Support generating in specific biomes only

## 🔵 Low Priority (Nice to Have)

- [ ] **Biome-Specific Generation**
  - Different layer patterns per biome
  - Biome-specific block mappings
  - Variable layer heights by biome

- [ ] **Advanced Layer Calculation**
  - Add noise-based variation
  - Consider slope angle, not just height
  - Add "erosion" patterns
  - Weather/climate-based variation

- [ ] **Multi-layer Support**
  - If CR supports stacking multiple layer types
  - Implement gradual transitions
  - Add depth-based layer variation

- [ ] **GUI Interface**
  - In-game GUI for configuration
  - Visual block mapping editor
  - Generation preview window
  - Quick preset buttons

## 🛠️ Code Quality & Maintenance

- [ ] **Unit Tests**
  - Test surface detection algorithm
  - Test layer count calculation
  - Test block mapping system
  - Mock world testing

- [ ] **Documentation**
  - Add JavaDoc to all public methods
  - Create wiki/web documentation
  - Add in-game help command
  - Video tutorial

- [ ] **Code Refactoring**
  - Extract configuration to separate class
  - Create interfaces for extensibility
  - Add more detailed logging
  - Improve error messages

- [ ] **Compatibility**
  - Test with other terrain mods
  - Test with world generation mods
  - Add compatibility layers if needed
  - Document known conflicts

## 🎨 Polish & Features

- [ ] **Custom Commands**
  - `/layerInfo` - Show info about block under cursor
  - `/clearLayers` - Remove layers in area
  - `/convertLayers` - Convert vanilla layers to CR layers
  - `/exportMapping` - Export current mappings to file

- [ ] **Permissions System**
  - Fine-grained permission nodes
  - Per-radius permission limits
  - Cooldown between uses

- [ ] **Statistics Tracking**
  - Track total layers generated
  - Per-player statistics
  - Leaderboards (optional)

## 🔬 Research & Investigation

- [ ] **Conquest Reforged API**
  - Investigate if CR has a public API
  - Check for helper methods for layers
  - Look for official block registries
  - Find if there are built-in conversion tools

- [ ] **Layer Mechanics**
  - How does CR handle multi-layer blocks?
  - Are there height limitations?
  - How do they interact with redstone/water?
  - Collision box behavior

- [ ] **Performance Profiling**
  - Identify bottlenecks
  - Measure memory usage
  - Test with large radii (100+)
  - Optimize hot paths

## 📦 Distribution & Release

- [ ] **Release Preparation**
  - Create mod icon (64x64 PNG)
  - Write detailed changelog
  - Create screenshots/GIFs
  - Record demo video

- [ ] **Publishing**
  - Publish to Modrinth
  - Publish to CurseForge
  - Create GitHub repository
  - Set up issue tracking

- [ ] **Community**
  - Create Discord server/channel
  - Write contribution guidelines
  - Add code of conduct
  - Set up automated builds (CI/CD)

## 🐛 Known Issues to Fix

- [ ] Chunk boundaries might cause issues
- [ ] Large radii cause server lag
- [ ] No protection against placing layers in protected regions
- [ ] Blocks above layers aren't checked for space

## 💡 Ideas for Future Versions

- [ ] **Procedural Patterns**
  - Add pre-made layer patterns (paths, roads, erosion)
  - Template system for common structures
  - Brush tool for manual placement

- [ ] **Integration with Other Mods**
  - Chisel integration
  - Connected Textures Mod support
  - Shader compatibility

- [ ] **Advanced Features**
  - Layer aging/weathering over time
  - Player footstep layer deformation
  - Snow accumulation that converts to layers
  - Rain creates mud layers

- [ ] **Creative Tools**
  - Terrain sculpting with layers
  - Erosion simulator
  - Terrain smoothing/roughening
  - Auto-detailing for structures

---

## Progress Tracking

Use this section to track your progress:

### Current Sprint
- Working on: _____________________
- Blockers: _____________________
- Next up: _____________________

### Completed
- [x] Basic mod structure
- [x] Command system
- [x] Surface detection
- [x] Layer calculation algorithm
- [x] Block mapping system
- [x] Documentation

### Version Planning

**v1.0.0** (Current)
- Basic layer generation
- Command interface
- Placeholder mappings

**v1.1.0** (Next)
- Real CR block mappings
- Performance optimization
- Vegetation system

**v1.2.0** (Future)
- Config system
- Preview mode
- Undo/redo

**v2.0.0** (Long-term)
- GUI interface
- Advanced features
- Full compatibility suite
