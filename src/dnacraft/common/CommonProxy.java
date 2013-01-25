package dnacraft.common;

import net.minecraft.block.material.Material;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import dnacraft.DNACraft;
import dnacraft.DNACraft.Items;
import dnacraft.common.block.BlockCentrifuge;
import dnacraft.common.item.ItemGenome;
import dnacraft.common.tileentity.TileEntityCentrifuge;

public class CommonProxy {
	
	public void init() {
		
		Items.itemGenome = new ItemGenome(821);
		DNACraft.Blocks.blockCentrifuge = new BlockCentrifuge(500, Material.ground);
		GameRegistry.registerBlock(DNACraft.Blocks.blockCentrifuge, "dnacraft.centrifuge");
		GameRegistry.registerTileEntity(TileEntityCentrifuge.class, "centrifuge");

	}
	
	public void registerRenderInformation()
	{
		
	}
}
