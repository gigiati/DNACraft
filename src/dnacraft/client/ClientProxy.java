package dnacraft.client;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.tileentity.TileEntity;
import dnacraft.client.gui.GuiCentrifuge;
import dnacraft.client.gui.GuiSplicer;
import dnacraft.common.CommonProxy;
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
	}
}
