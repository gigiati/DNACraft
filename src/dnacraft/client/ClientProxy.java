package dnacraft.client;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import cpw.mods.fml.client.registry.RenderingRegistry;
import dnacraft.client.gui.GuiCentrifuge;
import dnacraft.client.gui.GuiDNA;
import dnacraft.client.gui.GuiElectroporator;
import dnacraft.client.gui.GuiSequencer;
import dnacraft.client.gui.GuiSplicer;
import dnacraft.client.model.ModelMutant;
import dnacraft.client.renderer.entity.RenderMutant;
import dnacraft.client.rendering.mobs.DefinitionChicken;
import dnacraft.client.rendering.mobs.DefinitionCreeper;
import dnacraft.client.rendering.mobs.DefinitionEnderman;
import dnacraft.client.rendering.mobs.DefinitionOcelot;
import dnacraft.client.rendering.mobs.DefinitionPig;
import dnacraft.client.rendering.mobs.DefinitionSheep;
import dnacraft.client.rendering.mobs.DefinitionSpider;
import dnacraft.client.rendering.mobs.DefinitionZombie;
import dnacraft.common.CommonProxy;
import dnacraft.common.entity.EntityMutant;
import dnacraft.common.tileentity.TileEntityCentrifuge;
import dnacraft.common.tileentity.TileEntityElectroporator;
import dnacraft.common.tileentity.TileEntitySequencer;
import dnacraft.common.tileentity.TileEntitySplicer;

public class ClientProxy extends CommonProxy {

	@Override
	public Object getGui(InventoryPlayer inventory, TileEntity tile) {
		if (tile instanceof TileEntityCentrifuge) {
			return new GuiCentrifuge(inventory, (TileEntityCentrifuge) tile);
		} else if (tile instanceof TileEntitySplicer) {
			return new GuiSplicer(inventory, (TileEntitySplicer) tile);
		} else if (tile instanceof TileEntitySequencer) {
			return new GuiSequencer(inventory, (TileEntitySequencer) tile);
		} else if (tile instanceof TileEntityElectroporator) {
			return new GuiElectroporator(inventory, (TileEntityElectroporator) tile);
		}
		return null;
	}
	
	@Override
	public Object getDNAGui(ItemStack stack) {
		return new GuiDNA(stack);
	}
	
	@Override
	public void registerRenderInformation() {

		ModelMutant mutant = new ModelMutant();
		mutant.register(new DefinitionPig(mutant));
		mutant.register(new DefinitionChicken(mutant));
		mutant.register(new DefinitionEnderman(mutant));
		mutant.register(new DefinitionSpider(mutant));
		mutant.register(new DefinitionCreeper(mutant));
		mutant.register(new DefinitionOcelot(mutant));
		mutant.register(new DefinitionSheep(mutant));
		mutant.register(new DefinitionZombie(mutant));
		RenderingRegistry.registerEntityRenderingHandler(EntityMutant.class, new RenderMutant(mutant, 0.7F));
	}
}
