# Platform metadata

Use the matching description file for each project on both CurseForge and Modrinth. Keep the AI assistance disclosure as the final paragraph.

| Component | CurseForge title | Modrinth title | Loader | Minecraft | License | Modrinth slug | Modrinth project ID | Modrinth version ID | CurseForge project ID | CurseForge file ID |
| --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- |
| Core | Veinminer (UNOFFICIAL) | Veinminer 1.20.1 Fabric Backport (UNOFFICIAL) | Fabric | 1.20.1 | AGPL-3.0-only | `veinminer-1201-fabric-backport` | `nqAgcbUv` | `jOTkf12d` | `1743938` | `9168072` |
| Hotkey | Veinminer Hotkey (UNOFFICIAL) | Veinminer Hotkey 1.20.1 Fabric Backport (UNOFFICIAL) | Fabric, client-only | 1.20.1 | LGPL-3.0-only | `veinminer-hotkey-1201-fabric-backport` | `JaGV5o85` | `nMhN1l2F` | `1743940` | `9168079` |

## Current moderation state

- Modrinth: both project records are `draft` with `requested_status=approved`; both uploaded versions are `listed` beta versions. Draft projects are not yet publicly approved.
- CurseForge: both new projects are awaiting moderator approval; neither project is public yet. Authors UI still labels the core file `Processing` and the Hotkey file `Uploading`, while both CDN download links already return the uploaded JARs.
- CurseForge release options are set to publish each beta file automatically after approval.

## Uploaded artifact verification

The CurseForge CDN downloads were compared byte-for-byte by SHA-256 against the local build artifacts on 2026-10-14:

| Component | Bytes | SHA-256 |
| --- | ---: | --- |
| Core | 654,444 | `5D0F9F6577F24389C9CFC0DDCE6A8CCDCF73CA06AEC84D8897333532A6249A71` |
| Hotkey | 195,139 | `88D80EC8573F5821C3891692D447A26BD8C96F59AF3A70595925D828657B2E83` |

## Dependencies

- Modrinth metadata links the Hotkey project as a required dependency of the core add-on and the Hotkey add-on as optional for the core.
- The Hotkey description says it requires the core. CurseForge does not yet expose either unapproved project in its relation picker, so add the required/optional project relations after approval.

## Candidate gap checked 2026-10-14

The upstream Modrinth version API returned eight Fabric versions of Veinminer targeting Minecraft 1.20.1; the newest was `2.0.7+1.20.1`, published 2024-06-17. It returned no Fabric 1.20.1 versions for Veinminer Hotkey. This port brings the 2.11.2 code line to 1.20.1 for the core and supplies the missing 1.20.1 Hotkey add-on.

## Source and icons

The public source is [JustHari01/Veinminer-1.20.1-Fabric-Backport](https://github.com/JustHari01/Veinminer-1.20.1-Fabric-Backport). Project icon files are in `release-assets/`. CurseForge PNGs are 512×512 and exceed its 400×400 minimum. Modrinth JPEGs are 512×512 and under its 256 KiB icon limit.

