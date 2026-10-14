# Porting and license notice

This repository backports the Fabric Veinminer core and the Veinminer Hotkey client add-on from upstream version 2.11.2 for Minecraft 1.21.1 to Minecraft 1.20.1. The project is unofficial, community-maintained, and not endorsed by Miraculixx.

Upstream source: [MiraculixxT/Veinminer](https://github.com/MiraculixxT/Veinminer)

## Separate component licenses

The upstream project publishes the core and client add-on as separate projects with separate license metadata. Keep that distinction when copying or modifying this combined source tree:

| Component | Mod id | Upstream license |
| --- | --- | --- |
| Veinminer core and server/common implementation | `veinminer` | AGPL-3.0-only |
| Veinminer Hotkey client add-on | `veinminer_client` | LGPL-3.0-only |

The corresponding GNU license texts are included in `LICENSES/`. Do not treat this notice as relicensing either component under one common license. Preserve upstream copyright and license notices when redistributing source or binaries.

## Backport changes

The 1.20.1 branch contains compatibility changes for Minecraft 1.20.1, Fabric API 0.92.12+1.20.1, and Java 17, along with changes required to compile and package the Fabric core and Hotkey artifacts. The port version is `2.11.2-hari.1`; it is distinct from upstream version 2.11.2.

## Disclosure

The backport code and release documentation were prepared with AI assistance. This disclosure applies to the backport work only and does not describe the upstream authors' work.
