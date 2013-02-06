package dnacraft.common.item.metas;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.Facing;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import dnacraft.DNACraft;
import dnacraft.api.IMeta;
import dnacraft.common.entity.EntityMutant;
import dnacraft.common.evolution.Genome;

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
	public String getItemNameIS(ItemStack stack) {
		return "dnacraft.dna";
	}

	@Override
	public int getId() {
		return this.id;
	}

	@Override
	public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int side, float par8, float par9, float par10)
    {
		if (!world.isRemote)
	    {
	    	return true;
	    }
		player.openGui(DNACraft.instance, 1988,  world, 0, 0, 0);
		return false;
    }

	@Override
	public boolean hitEntity(ItemStack itemStack, EntityLiving  target,
			EntityLiving player) {
		return true;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack itemStack, EntityPlayer player,
			World world) {
		player.openGui(DNACraft.instance, 1988, world, 0, 0, 0);
		return itemStack;
	}
	
	

}
