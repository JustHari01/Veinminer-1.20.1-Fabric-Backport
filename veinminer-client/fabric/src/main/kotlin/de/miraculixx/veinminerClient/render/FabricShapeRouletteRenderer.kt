package de.miraculixx.veinminerClient.render

import net.minecraft.client.gui.GuiGraphics

object FabricShapeRouletteRenderer {
    fun render(graphics: GuiGraphics, partialTick: Float) {
        ShapeRouletteOverlay.render(graphics, partialTick)
    }
}
