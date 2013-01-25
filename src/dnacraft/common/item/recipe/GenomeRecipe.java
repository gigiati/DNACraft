package dnacraft.common.item.recipe;

import dnacraft.DNACraft;
import dnacraft.common.item.ItemGenome;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;

public class GenomeRecipe implements IRecipe {

	@Override
	public boolean matches(InventoryCrafting inventoryCrafting, World world) {
		return getCraftingResult(inventoryCrafting) != null;
	}

	@Override
	public ItemStack getCraftingResult(InventoryCrafting inventoryCrafting) {
		return ItemGenome.createNewFromCraftingInventory(inventoryCrafting);
	}

	@Override
	public int getRecipeSize() {
		return 3;
	}

	@Override
	public ItemStack getRecipeOutput() {
	    return new ItemStack(DNACraft.Items.itemGenome, 1, 1);
	}

}
