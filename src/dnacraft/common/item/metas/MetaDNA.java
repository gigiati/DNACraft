package dnacraft.common.item.metas;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import dnacraft.DNACraft;
import dnacraft.api.IMeta;

public class MetaDNA implements IMeta {

	private int id;
	
	public MetaDNA(int id) {
		this.id = id;
	}

	@Override
	public int getIconIndex() {
		return this.id;
	}

	@Override
	public String getItemNameIS() {
		return "dnacraft.dna";
	}

	@Override
	public int getId() {
		return this.id;
	}

	@Override
	public ItemStack newItemStack(int count) {
		return new ItemStack(DNACraft.Items.itemUnstackable, count, this.getId());
	}

	@Override
	public ItemStack newItemStack() {
		return newItemStack(1);
	}

}
