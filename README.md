# Veinminer 1.20.1 Fabric Backport (Unofficial)

This repository contains a community backport of the Fabric Veinminer core and its client hotkey add-on from the upstream 2.11.2 release for Minecraft 1.21.1 to Minecraft 1.20.1. It is not an official release and is not endorsed by the upstream maintainer.

## Components

- **Veinminer core** (`veinminer`): server-side vein mining and configuration. Install it on the server; for singleplayer, install it in the client instance too.
- **Veinminer Hotkey** (`veinminer_client`): client-only hotkey, mining preview, and pattern controls. It requires the matching core on the server. The client add-on also declares the core as a client dependency, so install both jars in each client instance that uses Hotkey; singleplayer clients need both jars as well.
- **Veinminer Enchantment** (`veinminer_enchantment`): adds the Veinmine enchantment and requires this matching core. When installed, the core only vein-mines with an enchanted main-hand tool. Its configurable weight, enchanting costs, and anvil cost are stored in `config/veinminer_enchantment.json`.

The core project already has an older Fabric 1.20.1 release. This backport brings the newer 2.11.2 code line to 1.20.1; it does not replace or claim to be an official upstream update.

## Build

Use Java 17 and run:

```shell
./gradlew :veinminer:veinminer-fabric:build :veinminer-client:veinminer-client-fabric:build :veinminer-enchant:build
```

The Fabric jars are written to the `veinminer/fabric/build/libs`, `veinminer-client/fabric/build/libs`, and `veinminer-enchant/build/libs` folders.

## Validation

Both Fabric artifacts build successfully. A Minecraft 1.20.1 development client loaded both port jars with Fabric Loader 0.19.5 and reached the title screen. This startup check does not certify in-world mining, every hotkey/pattern interaction, other mods, or multiplayer environments.

## Attribution and licenses

The original project is [Veinminer by Miraculixx](https://github.com/MiraculixxT/Veinminer). The backport preserves separate upstream licenses: the Veinminer core is AGPL-3.0-only and the Hotkey add-on is LGPL-3.0-only. See [PORTING-NOTICE.md](PORTING-NOTICE.md) and the corresponding texts in `LICENSES/` for the component mapping.

This unofficial backport, its project artwork, and its release documentation were prepared with AI assistance; this note does not describe the upstream authors' work.
