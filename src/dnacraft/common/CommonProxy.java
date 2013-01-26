package dnacraft.common;

import net.minecraft.block.material.Material;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import dnacraft.DNACraft;
import dnacraft.DNACraft.Items;
import dnacraft.common.block.BlockCentrifuge;
import dnacraft.common.item.ItemGeneric;
import dnacraft.common.item.ItemUnstackable;
import dnacraft.common.item.metas.MetaDNA;
import dnacraft.common.tileentity.TileEntityCentrifuge;

public class CommonProxy {
	
	public void init() {
		
		DNACraft.Blocks.blockCentrifuge = new BlockCentrifuge(500, Material.ground);
		GameRegistry.registerBlock(DNACraft.Blocks.blockCentrifuge, "dnacraft.centrifuge");
		GameRegistry.registerTileEntity(TileEntityCentrifuge.class, "centrifuge");


		Items.itemUnstackable = new ItemUnstackable(821);
		Items.itemUnstackable.addMeta(1, new MetaDNA());
		
		Items.itemGeneric = new ItemGeneric(822);
	}
	
	public void registerRenderInformation()
	{
		
	}
}
