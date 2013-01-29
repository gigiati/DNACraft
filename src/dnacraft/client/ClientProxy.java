package dnacraft.client;

import cpw.mods.fml.client.registry.RenderingRegistry;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.tileentity.TileEntity;
import dnacraft.client.gui.GuiCentrifuge;
import dnacraft.client.gui.GuiSplicer;
import dnacraft.client.model.ModelMutant;
import dnacraft.client.renderer.entity.RenderMutant;
import dnacraft.common.CommonProxy;
import dnacraft.common.entity.EntityMutant;
import dnacraft.common.tileentity.TileEntityCentrifuge;
import dnacraft.common.tileentity.TileEntitySplicer;


public class ClientProxy extends CommonProxy {

	@Override
	public Object getGui(InventoryPlayer inventory, TileEntity tile) {
		if (tile instanceof TileEntityCentrifuge) {
			return new GuiCentrifuge(inventory, (TileEntityCentrifuge) tile);
		}else if (tile instanceof TileEntitySplicer) {
			return new GuiSplicer(inventory, (TileEntitySplicer) tile);
		}
		return null;
	}
	
	@Override
	public void registerRenderInformation() {
		RenderingRegistry.registerEntityRenderingHandler(EntityMutant.class,
				new RenderMutant(new ModelMutant(), 0.7F));
	}
}
