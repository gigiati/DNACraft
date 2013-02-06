package dnacraft.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import dnacraft.DNACraft;
import dnacraft.DNACraft.Items;
import dnacraft.common.block.BlockCentrifuge;
import dnacraft.common.block.BlockElectroporator;
import dnacraft.common.block.BlockSequencer;
import dnacraft.common.block.BlockSplicer;
import dnacraft.common.container.ContainerCentrifuge;
import dnacraft.common.container.ContainerElectroporator;
import dnacraft.common.container.ContainerSequencer;
import dnacraft.common.container.ContainerSplicer;
import dnacraft.common.entity.EntityMutant;
import dnacraft.common.evolution.DNA;
import dnacraft.common.item.ItemGeneric;
import dnacraft.common.item.ItemUnstackable;
import dnacraft.common.item.metas.MetaBloodSample;
import dnacraft.common.item.metas.MetaDNA;
import dnacraft.common.item.metas.MetaDNAFragment;
import dnacraft.common.item.metas.MetaMutantEgg;
import dnacraft.common.item.metas.MetaNeedle;
import dnacraft.common.item.metas.MetaSyringe;
import dnacraft.common.item.metas.MetaTestTube;
import dnacraft.common.tileentity.TileEntityCentrifuge;
import dnacraft.common.tileentity.TileEntityElectroporator;
import dnacraft.common.tileentity.TileEntitySequencer;
import dnacraft.common.tileentity.TileEntitySplicer;

public class CommonProxy {

	private class GuiHandler implements IGuiHandler {

		@Override
		public Object getClientGuiElement(int ID, EntityPlayer player,
				World world, int x, int y, int z) {
			
			if (ID == 1988) {
				return getDNAGui(player.getHeldItem());
			}
			
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
			if (ID == 1988) {
				return null;
			}
			TileEntity tile = world.getBlockTileEntity(x, y, z);

			if (tile != null) {
				if (tile instanceof TileEntityCentrifuge) {
					return new ContainerCentrifuge(player.inventory, (TileEntityCentrifuge) tile);
				}else if (tile instanceof TileEntitySplicer) {
					return new ContainerSplicer(player.inventory, (TileEntitySplicer) tile);
				}else if (tile instanceof TileEntitySequencer) {
					return new ContainerSequencer(player.inventory, (TileEntitySequencer) tile);
				}else if (tile instanceof TileEntityElectroporator) {
					return new ContainerElectroporator(player.inventory, (TileEntityElectroporator) tile);
				}
			}

			return null;
		}

	}


	public Object getGui(InventoryPlayer inventory, TileEntity tileentity) {
		return null;
	}
	
	public Object getDNAGui(ItemStack stack) {
		return null;
	}

	public void init() {

		DNACraft.Blocks.blockCentrifuge = new BlockCentrifuge(500, Material.ground);
		GameRegistry.registerBlock(DNACraft.Blocks.blockCentrifuge, "dnacraft.machines.centrifuge");
		GameRegistry.registerTileEntity(TileEntityCentrifuge.class, "centrifuge");

		DNACraft.Blocks.blockSplicer = new BlockSplicer(501, Material.ground);
		GameRegistry.registerBlock(DNACraft.Blocks.blockSplicer, "dnacraft.machines.splicer");
		GameRegistry.registerTileEntity(TileEntitySplicer.class, "splicer");

		DNACraft.Blocks.blockSequencer = new BlockSequencer(502, Material.ground);
		GameRegistry.registerBlock(DNACraft.Blocks.blockSequencer, "dnacraft.machines.sequencer");
		GameRegistry.registerTileEntity(TileEntitySequencer.class, "sequencer");
		
		DNACraft.Blocks.blockElectroporator = new BlockElectroporator(503, Material.ground);
		GameRegistry.registerBlock(DNACraft.Blocks.blockElectroporator, "dnacraft.machines.electroporator");
		GameRegistry.registerTileEntity(TileEntityElectroporator.class, "electroporator");

		Items.itemUnstackable = new ItemUnstackable(821);

		Items.itemUnstackable.addMeta(new MetaDNA(0));
		Items.itemUnstackable.addMeta(new MetaMutantEgg(1));
		Items.itemUnstackable.addMeta(new MetaSyringe(2));
		Items.itemUnstackable.addMeta(new MetaBloodSample(3));

		Items.itemGeneric = new ItemGeneric(822);

		Items.itemGeneric.addMeta(new MetaTestTube(0));
		Items.itemGeneric.addMeta(new MetaNeedle(1));

		Items.itemGeneric.addMeta(new MetaDNAFragment(30, "dnacraft.fragments.pig", DNA.pig, Item.porkRaw, Item.porkCooked));
		Items.itemGeneric.addMeta(new MetaDNAFragment(31, "dnacraft.fragments.chicken", DNA.chicken, Item.feather, Item.chickenRaw, Item.chickenCooked));
		Items.itemGeneric.addMeta(new MetaDNAFragment(32, "dnacraft.fragments.zombie", DNA.zombie, Item.rottenFlesh));
		Items.itemGeneric.addMeta(new MetaDNAFragment(33, "dnacraft.fragments.enderman", DNA.enderman, Item.enderPearl, Item.eyeOfEnder));
		Items.itemGeneric.addMeta(new MetaDNAFragment(34, "dnacraft.fragments.spider", DNA.spider, Item.spiderEye, Item.silk));
		Items.itemGeneric.addMeta(new MetaDNAFragment(35, "dnacraft.fragments.sheep", DNA.sheep, Item.itemsList[Block.cloth.blockID]));
		Items.itemGeneric.addMeta(new MetaDNAFragment(36, "dnacraft.fragments.ocelot", DNA.ocelot, Item.fishRaw, Item.fishCooked));
		Items.itemGeneric.addMeta(new MetaDNAFragment(37, "dnacraft.fragments.creeper", DNA.creeper, Item.gunpowder));

		int IDs = 600;

		EntityRegistry.registerModEntity(EntityMutant.class, "Mutant", 600, DNACraft.instance, 64, 1, true);

		NetworkRegistry.instance().registerGuiHandler(DNACraft.instance, new GuiHandler());
		setupLanguages();
		addRecipes();
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

	public void addRecipes() {

		ItemGeneric generic = DNACraft.Items.itemGeneric;
		ItemGeneric unstackable = DNACraft.Items.itemUnstackable;

		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(
				generic.newItemStack(MetaTestTube.class, 5),
				new Object[] {
					"g g",
					"g g",
					" g ",
					Character.valueOf('g'), new ItemStack(Block.thinGlass)			
				}
		));
		
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(
				generic.newItemStack(MetaNeedle.class, 16),
				new Object[] {
					"i  ",
					" i ",
					"  i",
					Character.valueOf('i'), new ItemStack(Item.ingotIron)			
				}
		));
		
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(
				unstackable.newItemStack(MetaSyringe.class),
				new Object[] {
					"n  ",
					" t ",
					"  g",
					Character.valueOf('n'), generic.newItemStack(MetaNeedle.class),
					Character.valueOf('t'), generic.newItemStack(MetaTestTube.class),
					Character.valueOf('g'), new ItemStack(Block.glass)		
				}
		));
		
		CraftingManager.getInstance().getRecipeList().add(new ShapelessOreRecipe(
				unstackable.newItemStack(MetaSyringe.class),
				unstackable.newItemStack(MetaBloodSample.class)
		));
	}

	public void registerRenderInformation()
	{

	}
}
