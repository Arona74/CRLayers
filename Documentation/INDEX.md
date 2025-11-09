# 📚 CR Layers Generator - Documentation Index

Welcome! This index will help you find exactly what you need.

## 🚀 I Want To...

### Get Started Quickly
→ **[QUICKSTART.md](QUICKSTART.md)** - Get up and running in 5 minutes

### Understand the Project
→ **[PROJECT_SUMMARY.md](../PROJECT_SUMMARY.md)** - Complete overview of the project
→ **[REFERENCE_CARD.md](REFERENCE_CARD.md)** - Quick reference for commands & key info

### Set Up Development
→ **[SETUP.md](SETUP.md)** - Detailed development environment setup
→ **[build.gradle](build.gradle)** - Gradle build configuration

### Learn How It Works
→ **[ARCHITECTURE.md](ARCHITECTURE.md)** - Technical implementation details
→ **[README.md](README.md)** - Full feature documentation

### Contribute or Extend
→ **[TODO.md](TODO.md)** - Development roadmap and task list

## 📋 Documentation by Purpose

### For New Users
1. **[QUICKSTART.md](QUICKSTART.md)** - Start here
2. **[REFERENCE_CARD.md](REFERENCE_CARD.md)** - Keep this handy
3. **[README.md](README.md)** - Learn all features

### For Developers
1. **[SETUP.md](SETUP.md)** - Environment setup
2. **[ARCHITECTURE.md](ARCHITECTURE.md)** - How it works
3. **[TODO.md](TODO.md)** - What to work on
4. Source code in `src/main/java/io/arona74/crlayers/`

### For Contributors
1. **[TODO.md](TODO.md)** - Priority tasks
2. **[ARCHITECTURE.md](ARCHITECTURE.md)** - Codebase structure
3. **[README.md](README.md)** - Feature list

## 🗂️ All Documentation Files

| File | Purpose | Read Time |
|------|---------|-----------|
| **QUICKSTART.md** | Get started fast | 5 min |
| **REFERENCE_CARD.md** | Quick reference | 2 min |
| **README.md** | Complete documentation | 15 min |
| **SETUP.md** | Development setup | 10 min |
| **ARCHITECTURE.md** | Technical details | 15 min |
| **TODO.md** | Development roadmap | 10 min |
| **PROJECT_SUMMARY.md** | Project overview | 10 min |
| **INDEX.md** | This file | - |

## 📁 Source Code Files

### Core Classes
- **`CRLayers.java`** - Mod initialization and entry point
- **`LayerGeneratorCommand.java`** - Command registration and handling
- **`LayerGenerator.java`** - Core generation algorithm
- **`BlockMappingRegistry.java`** - Block mapping system ⚠️ Update this first!

### Configuration
- **`fabric.mod.json`** - Mod metadata and dependencies
- **`build.gradle`** - Build configuration
- **`gradle.properties`** - Version settings

## 🎯 Quick Navigation

### By Topic

**Commands & Usage**
- In-game commands: [README.md](README.md#usage) | [REFERENCE_CARD.md](REFERENCE_CARD.md)

**Installation**
- User installation: [README.md](README.md#installation)
- Dev setup: [SETUP.md](SETUP.md)

**Configuration**
- Block mappings: [README.md](README.md#configuration) | `BlockMappingRegistry.java`
- Supported blocks: [README.md](README.md#supported-blocks)

**Technical Details**
- How it works: [ARCHITECTURE.md](ARCHITECTURE.md)
- Algorithm: [ARCHITECTURE.md](ARCHITECTURE.md#key-algorithms)
- Performance: [TODO.md](TODO.md#high-priority)

**Troubleshooting**
- Common issues: [README.md](README.md#troubleshooting)
- Quick fixes: [REFERENCE_CARD.md](REFERENCE_CARD.md#common-issues)

**Development**
- Build commands: [SETUP.md](SETUP.md#common-gradle-commands)
- What to work on: [TODO.md](TODO.md)

## 📖 Recommended Reading Order

### First Time Setup
1. [QUICKSTART.md](QUICKSTART.md) - Get oriented
2. [REFERENCE_CARD.md](REFERENCE_CARD.md) - Learn commands
3. Update `BlockMappingRegistry.java` - Critical!
4. Build and test

### Learning the System
1. [README.md](README.md) - Understand features
2. [ARCHITECTURE.md](ARCHITECTURE.md) - Learn how it works
3. Source code - Read through the classes

### Contributing
1. [TODO.md](TODO.md) - Pick a task
2. [ARCHITECTURE.md](ARCHITECTURE.md) - Understand codebase
3. [SETUP.md](SETUP.md) - Set up environment
4. Start coding!

## 🔧 Key Locations

**Most Important File**
→ `src/main/java/io/arona74/crlayers/BlockMappingRegistry.java`
   Update the placeholder block IDs with real Conquest Reforged IDs!

**Main Algorithm**
→ `src/main/java/io/arona74/crlayers/LayerGenerator.java`
   The core generation logic lives here

**Command Handler**
→ `src/main/java/io/arona74/crlayers/LayerGeneratorCommand.java`
   How the `/generateLayers` command works

**Build Output**
→ `build/libs/crlayers-1.0.0.jar`
   Your compiled mod (after running `./gradlew build`)

## 💡 Pro Tips

- **New to the project?** Start with QUICKSTART.md
- **Want to code?** Read SETUP.md then ARCHITECTURE.md
- **Stuck?** Check REFERENCE_CARD.md for quick answers
- **Planning work?** Check TODO.md for priorities
- **Need details?** README.md has everything

## 🆘 Help & Support

**Can't find something?**
- Try the search function in your editor
- Check the relevant documentation file above
- Look through the source code comments

**Still stuck?**
- Review the troubleshooting sections
- Check Fabric Wiki for general modding help
- Ask in Minecraft modding communities

## 📜 License & Credits

**Author:** arona74  
**License:** MIT (see project root)  
**Minecraft Version:** 1.20.1  
**Mod Loader:** Fabric

---

**Happy modding!** Start with [QUICKSTART.md](QUICKSTART.md) and you'll be generating layers in minutes! 🎮✨
