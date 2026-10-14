package de.miraculixx.veinminerClient.render

import net.minecraft.client.gui.GuiGraphics

object FabricHUDRenderer : HUDRenderer() {
    fun render(graphics: GuiGraphics, partialTick: Float) {
        renderCrosshair(graphics, partialTick)
    }
}
