package dnacraft.common.item.metas;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import dnacraft.DNACraft;
import dnacraft.api.IMeta;

public class MetaBloodSample implements IMeta {

	private int id;
	
	public MetaBloodSample(int id) {
		this.id = id;
	}
	
	@Override
	public int getIconIndex() {
		return this.id;
	}

	@Override
	public String getItemNameIS(ItemStack stack) {
		return "dnacraft.bloodsample";
	}

	@Override
	public int getId() {
		return this.id;
	}

	@Override
	public ItemStack newItemStack(int number) {
		return new ItemStack(DNACraft.Items.itemUnstackable, number, this.getId());
	}

	@Override
	public ItemStack newItemStack() {
		return newItemStack(1);
	}

	@Override
	public boolean hitEntity(ItemStack itemStack, EntityLiving target,
			EntityLiving player) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean onItemUse(ItemStack itemStack, EntityPlayer player,
			World world, int x, int y, int z, int side, float par8, float par9,
			float par10) {
		return false;
	}

}
