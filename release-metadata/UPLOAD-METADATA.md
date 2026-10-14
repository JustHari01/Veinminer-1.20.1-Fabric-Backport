# Platform metadata

Use the matching description file for each project on both CurseForge and Modrinth. Keep the AI assistance disclosure as the final paragraph.

| Component | CurseForge title | Modrinth title | Loader | Minecraft | License | Modrinth slug | Modrinth project ID | Modrinth version ID | CurseForge project ID | CurseForge file ID |
| --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- |
| Core | Veinminer (UNOFFICIAL) | Veinminer 1.20.1 Fabric Backport (UNOFFICIAL) | Fabric | 1.20.1 | AGPL-3.0-only | `veinminer-1201-fabric-backport` | `nqAgcbUv` | `jOTkf12d` | `1743938` | `9169352` |
| Hotkey | Veinminer Hotkey (UNOFFICIAL) | Veinminer Hotkey 1.20.1 Fabric Backport (UNOFFICIAL) | Fabric, client-only | 1.20.1 | LGPL-3.0-only | `veinminer-hotkey-1201-fabric-backport` | `JaGV5o85` | `nMhN1l2F` | `1743940` | `9169361` |
| Enchantment | Veinminer Enchantment (UNOFFICIAL) | Veinminer Enchantment 1.20.1 Fabric Backport (UNOFFICIAL) | Fabric | 1.20.1 | LGPL-3.0-only | `veinminer-enchantment-1201-fabric-backport` | Pending new project | Pending | `1744153` | `9169394` |

## Current moderation state

- Modrinth: both project records are `draft` with `requested_status=approved`; both uploaded versions are `listed` beta versions. Draft projects are not yet publicly approved.
- CurseForge: all three projects remain `New` and are awaiting moderator approval; none is public yet. The new `.2` Core, Hotkey, and Enchantment files are uploaded, and their CDN downloads match the local JARs by SHA-256.
- CurseForge release options are set to publish each beta file automatically after approval.

## Uploaded artifact verification

The CurseForge CDN downloads were compared byte-for-byte by SHA-256 against the local build artifacts on 2026-10-14:

| Component | Bytes | SHA-256 |
| --- | ---: | --- |
| Core | 654,444 | `5D0F9F6577F24389C9CFC0DDCE6A8CCDCF73CA06AEC84D8897333532A6249A71` |
| Hotkey | 195,139 | `88D80EC8573F5821C3891692D447A26BD8C96F59AF3A70595925D828657B2E83` |

The 2.11.2-hari.2 CurseForge uploads were also downloaded from the CDN and matched on 2026-10-14:

| Component | CurseForge file ID | Bytes | SHA-256 |
| --- | ---: | ---: | --- |
| Core | `9169352` | 654,530 | `0AB2D251FB1953B7E09314E94E8D10D1E10860F46B467564200C6D6C6E254BF3` |
| Hotkey | `9169361` | 183,905 | `E1E968FE0A7ABC8F47875A6F5730A9DF6AC483487FB6BBAFCB8EF142057D9B2B` |
| Enchantment | `9169394` | 184,851 | `03C528B93B994274D54DD84246E3731756BA79CABB07D85DA622149A6D8548C8` |

## Current local build artifacts for 2.11.2-hari.2

These are the release candidates built from the current source. They are distinct from the previously uploaded files above and should be matched by hash when new files are uploaded.

| Component | Bytes | SHA-256 |
| --- | ---: | --- |
| Core | 654,530 | `0AB2D251FB1953B7E09314E94E8D10D1E10860F46B467564200C6D6C6E254BF3` |
| Hotkey | 183,905 | `E1E968FE0A7ABC8F47875A6F5730A9DF6AC483487FB6BBAFCB8EF142057D9B2B` |
| Enchantment | 184,851 | `03C528B93B994274D54DD84246E3731756BA79CABB07D85DA622149A6D8548C8` |

## Dependencies

- Modrinth metadata marks the core as required for the Hotkey add-on and the Hotkey add-on as optional for the core.
- The Hotkey and Enchantment descriptions say they require the core. CurseForge's relation picker returned no options while the projects are unapproved; add the required/optional project relations after approval.

## Candidate gap checked 2026-10-14

The upstream Modrinth version API returned eight Fabric versions of Veinminer targeting Minecraft 1.20.1; the newest was `2.0.7+1.20.1`, published 2024-06-17. It returned no Fabric 1.20.1 versions for Veinminer Hotkey. This port brings the 2.11.2 code line to 1.20.1 for the core and supplies the missing 1.20.1 Hotkey add-on.

## Source and icons

The public source is [JustHari01/Veinminer-1.20.1-Fabric-Backport](https://github.com/JustHari01/Veinminer-1.20.1-Fabric-Backport). Project icon files are in `release-assets/`. CurseForge PNGs are 512×512 and exceed its 400×400 minimum. The Modrinth core and Hotkey JPEGs are 512×512 and under its 256 KiB icon limit; the Enchantment PNG is 512×512 and 30,357 bytes.
