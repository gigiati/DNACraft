package dnacraft.client.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.StatCollector;

import org.lwjgl.opengl.GL11;

import dnacraft.common.container.ContainerElectroporator;
import dnacraft.common.tileentity.TileEntityElectroporator;

public class GuiElectroporator extends GuiContainer {

	public GuiElectroporator(InventoryPlayer inventory, TileEntityElectroporator electroporator) {
		super(new ContainerElectroporator(inventory, electroporator));
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float var1, int var2,
			int var3) {
		int var4 = this.mc.renderEngine
				.getTexture("/dnacraft/resources/gfx/gui-sequencer.png");
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.renderEngine.bindTexture(var4);
		int var5 = (this.width - this.xSize) / 2;
		int var6 = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(var5, var6, 0, 0, this.xSize, this.ySize);
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int par1, int par2) {
		String sensorGuiName = StatCollector
				.translateToLocal("dnacraft.gui.electroporator");
		fontRenderer.drawString(sensorGuiName,
				this.xSize / 2
						- (fontRenderer.getStringWidth(sensorGuiName) / 2), 6,
				4210752);
		fontRenderer.drawString(
				StatCollector.translateToLocal("container.inventory"), 8,
				this.ySize - 96 + 2, 4210752);
	}
}
