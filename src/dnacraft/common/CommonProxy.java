package dnacraft.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import dnacraft.DNACraft;
import dnacraft.DNACraft.Items;
import dnacraft.common.block.BlockCentrifuge;
import dnacraft.common.block.BlockSplicer;
import dnacraft.common.container.ContainerCentrifuge;
import dnacraft.common.container.ContainerSplicer;
import dnacraft.common.entity.EntityMutant;
import dnacraft.common.evolution.DNA;
import dnacraft.common.item.ItemGeneric;
import dnacraft.common.item.ItemUnstackable;
import dnacraft.common.item.metas.MetaDNA;
import dnacraft.common.item.metas.MetaDNAFragment;
import dnacraft.common.item.metas.MetaMutantEgg;
import dnacraft.common.tileentity.TileEntityCentrifuge;
import dnacraft.common.tileentity.TileEntitySplicer;

public class CommonProxy {
	
	private class GuiHandler implements IGuiHandler {

		@Override
		public Object getClientGuiElement(int ID, EntityPlayer player,
				World world, int x, int y, int z) {
			TileEntity tile = world.getBlockTileEntity(x, y, z);

			if (tile != null) {
				return getGui(player.inventory, tile);
			}

			return null;
		}

		/**
		 * Gets the Gui Container on the server
		 */
		@Override
		public Object getServerGuiElement(int ID, EntityPlayer player,
				World world, int x, int y, int z) {
			TileEntity tile = world.getBlockTileEntity(x, y, z);

			if (tile != null) {
				if (tile instanceof TileEntityCentrifuge) {
					return new ContainerCentrifuge(player.inventory, (TileEntityCentrifuge) tile);
				}else if (tile instanceof TileEntitySplicer) {
					return new ContainerSplicer(player.inventory, (TileEntitySplicer) tile);
				}
			}

			return null;
		}

	}
	

	public Object getGui(InventoryPlayer inventory, TileEntity tileentity) {
		return null;
	}
	
	public void init() {
		
		DNACraft.Blocks.blockCentrifuge = new BlockCentrifuge(500, Material.ground);
		GameRegistry.registerBlock(DNACraft.Blocks.blockCentrifuge, "dnacraft.machines.centrifuge");
		GameRegistry.registerTileEntity(TileEntityCentrifuge.class, "centrifuge");

		DNACraft.Blocks.blockSplicer = new BlockSplicer(501, Material.ground);
		GameRegistry.registerBlock(DNACraft.Blocks.blockSplicer, "dnacraft.machines.splicer");
		GameRegistry.registerTileEntity(TileEntitySplicer.class, "splicer");

		Items.itemUnstackable = new ItemUnstackable(821);
		Items.itemUnstackable.addMeta(new MetaDNA(0));
		Items.itemUnstackable.addMeta(new MetaMutantEgg(1));
		
		Items.itemGeneric = new ItemGeneric(822);
		Items.itemGeneric.addMeta(new MetaDNAFragment(0, "dnacraft.fragments.porkRaw", Item.porkRaw, DNA.pig));
		Items.itemGeneric.addMeta(new MetaDNAFragment(1, "dnacraft.fragments.chicken", Item.feather, DNA.chicken));
		
		int IDs = 600;

	    EntityRegistry.registerModEntity(EntityMutant.class, "Mutant", 600, DNACraft.instance, 64, 1, true);
		
	    NetworkRegistry.instance().registerGuiHandler(DNACraft.instance, new GuiHandler());
		setupLanguages();
	}

	private void setupLanguages() {

		ArrayList arrayList = new ArrayList();

		try {
			InputStream input = CommonProxy.class.getResourceAsStream("/dnacraft/resources/languages/languages.txt");

			if (input == null) {
				return;
			}

			BufferedReader var2 = new BufferedReader(new InputStreamReader(input, "UTF-8"));

			for (String var3 = var2.readLine(); var3 != null; var3 = var2.readLine()) {
				arrayList.add(var3);
			}
		} catch (IOException var5) {
			var5.printStackTrace();
			return;
		}

		Iterator iterator = arrayList.iterator();

		while (iterator.hasNext()) {
			String langString = (String) iterator.next();
			String langPath = "/dnacraft/resources/languages/" + langString + ".lang";
			URL url = CommonProxy.class.getResource(langPath);
			if (url == null) {
				continue;
			}

			LanguageRegistry.instance().loadLocalization(url, langString, false);
		}

	}
	public void registerRenderInformation()
	{
		
	}
}
