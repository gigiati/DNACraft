package dnacraft.common.tileentity;

import dnacraft.DNACraft;
import dnacraft.api.IMeta;
import dnacraft.common.evolution.DNA;
import dnacraft.common.item.ItemUnstackable;
import dnacraft.common.item.metas.MetaBloodSample;
import dnacraft.common.item.metas.MetaDNA;
import dnacraft.common.item.metas.MetaDNAFragment;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileEntitySequencer extends BaseInventoryTileEntity implements IInventory {

	public TileEntitySequencer() {
		itemStacks = new ItemStack[4];
	}
	
	@Override
	public String getInvName() {
		return "dnacraft.machines.sequencer";
	}
	
	@Override
	public void updateEntity() {
		super.updateEntity();
		if (!worldObj.isRemote) {
			ItemStack fuelStack = itemStacks[0];
			ItemStack bottleStack = itemStacks[1];
			ItemStack sampleStack = itemStacks[2];
			ItemStack outputStack = itemStacks[3];
			if (outputStack != null && outputStack.stackSize > 0 ) {
				return;
			}
			if (fuelStack == null || bottleStack == null || sampleStack == null) {
				return;
			}
			if (fuelStack.getItem() != Item.diamond ||
				bottleStack.getItem() != Item.glassBottle
			) {
				return;
			}
			DNA dnaToUse = null;
			MetaDNAFragment fragment = MetaDNAFragment.getFragmentForItemStack(sampleStack);
			if (fragment != null) {
				dnaToUse = fragment.getDNA();
			} else {
				if (sampleStack.getItem() == DNACraft.Items.itemUnstackable ) {
					ItemUnstackable item = (ItemUnstackable)sampleStack.getItem();
					IMeta meta = item.getMeta(sampleStack);
					if (meta instanceof MetaBloodSample) {
						if (sampleStack.hasTagCompound()) {
							NBTTagCompound compound = sampleStack.getTagCompound();
							dnaToUse = DNA.fromNBT(compound.getCompoundTag("traits"));
						}
					}
				}
			}
			if (dnaToUse != null) {
				ItemStack result = DNACraft.Items.itemUnstackable.getMeta(MetaDNA.class).newItemStack();
				NBTTagCompound compound = new NBTTagCompound();
				compound.setCompoundTag("traits", dnaToUse.toNBT());
				result.setTagCompound(compound);
				this.decrStackSize(0, 1);
				this.decrStackSize(1, 1);
				this.decrStackSize(2, 1);
				itemStacks[3] = result;
			}
		}
	}
}
