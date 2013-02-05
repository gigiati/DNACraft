package dnacraft.common.tileentity;

import dnacraft.common.item.ItemGeneric;
import dnacraft.common.item.metas.MetaDNAFragment;
import dnacraft.common.item.metas.MetaTestTube;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGlassBottle;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.common.ISidedInventory;

public class TileEntityCentrifuge extends BaseInventoryTileEntity implements IInventory {

	@Override
	public void updateEntity() {
		super.updateEntity();
		if (!worldObj.isRemote) {
			ItemStack input = itemStacks[0];
			ItemStack tube = itemStacks[1];

			if (input != null && input.stackSize > 0 && tube != null && tube.stackSize > 0) {
				ItemStack output = MetaDNAFragment.getFragmentForItemStack(input).newItemStack();
				Item tubeItem = tube.getItem();
				if (output != null &&
					tubeItem != null &&
					tubeItem instanceof ItemGeneric &&
					((ItemGeneric)tubeItem).isA(tube, MetaTestTube.class)) {
					if (itemStacks[2] != null) {
						ItemStack outputStack = itemStacks[2];
						if (outputStack.getItem() == output.getItem() &&
							outputStack.getItemDamage() == output.getItemDamage() &&
							outputStack.stackSize < outputStack.getMaxStackSize()) {
							this.decrStackSize(0, 1);
							this.decrStackSize(1, 1);
							outputStack.stackSize++;
						}
					}else {
						itemStacks[2] = output;
						this.decrStackSize(0, 1);
						this.decrStackSize(1, 1);
					}
				}
			}
		}
	}

	@Override
	public String getInvName() {
		return "dnacraft.machines.centrifuge";
	}
	
}
