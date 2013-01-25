package dnacraft.common.item;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemGenome extends Item {

	public ItemGenome(int i) {
		super(i);
		setTextureFile("/dnacraft/resources/gfx/items.png");
		setMaxStackSize(1);
		setCreativeTab(CreativeTabs.tabMisc);
	}
	
	public static ItemStack createNewFromCraftingInventory(InventoryCrafting inventoryCrafting)
	{
		boolean hasChromosome = false;
		
		for (int x = 0; x < 3; x++)
		{
			for (int y = 0; y < 3; y++)
		    {
				ItemStack itemstack = inventoryCrafting.getStackInRowAndColumn(x, y);
				if (itemstack != null)
				{
					Item item = itemstack.getItem();
				}
	      	}
		}
		return null;
	}
}
