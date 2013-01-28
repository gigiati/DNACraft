package dnacraft.common.item;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemUnstackable extends ItemGeneric {

	public ItemUnstackable(int i) {
		super(i);
		setMaxStackSize(1);
		setTextureFile("/dnacraft/resources/gfx/items_unstackable.png");
	}

}
