package dnacraft.api;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public interface IMeta {

	public int getIconIndex();
	public String getItemNameIS();
	public int getId();
	public ItemStack newItemStack(int number);
	public ItemStack newItemStack();
}
