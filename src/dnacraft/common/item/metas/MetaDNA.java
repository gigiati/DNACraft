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

	@Override
	public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int side, float par8, float par9, float par10)
    {
        if (world.isRemote)
        {
            return true;
        }
        else
        {	
            int blockID = world.getBlockId(x, y, z);
            x += Facing.offsetsXForSide[side];
            y += Facing.offsetsYForSide[side];
            z += Facing.offsetsZForSide[side];
            double var12 = 0.0D;

            if (side == 1 && Block.blocksList[blockID] != null && Block.blocksList[blockID].getRenderType() == 11)
            {
                var12 = 0.5D;
            }

            EntityMutant mutant = new EntityMutant(world);
            
            if (mutant != null)
            {
                mutant.setLocationAndAngles((double)x + 0.5D, (double)y + var12, (double)z + 0.5D, MathHelper.wrapAngleTo180_float(world.rand.nextFloat() * 360.0F), 0.0F);
                mutant.rotationYawHead = mutant.rotationYaw;
                mutant.renderYawOffset = mutant.rotationYaw;
                mutant.setDNAFromItemStack(itemStack.copy());
                world.spawnEntityInWorld(mutant);
            }

            --itemStack.stackSize;

            return true;
        }
    }

}
