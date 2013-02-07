package dnacraft.common.tileentity;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import dnacraft.DNACraft;
import dnacraft.api.IMeta;
import dnacraft.common.evolution.DNA;
import dnacraft.common.item.ItemGeneric;
import dnacraft.common.item.metas.MetaDNADataCard;

public class TileEntitySplicer extends BaseInventoryTileEntity implements IInventory {

	@Override
	public void updateEntity() {
		super.updateEntity();
		if (!worldObj.isRemote) {
			
			ItemStack input1 = itemStacks[0];
			ItemStack input2 = itemStacks[1];
			ItemStack output = itemStacks[2];
			
			if (input1 != null &&
				input2 != null &&
				output == null)
			{
				Item item1 = input1.getItem();
				Item item2 = input2.getItem();
				
				if (item1 instanceof ItemGeneric && item2 instanceof ItemGeneric) {
					
					IMeta meta1 = ((ItemGeneric)item1).getMeta(input1);
					IMeta meta2 = ((ItemGeneric)item2).getMeta(input2);
					
					MetaDNADataCard dna1 = null;
					MetaDNADataCard dna2 = null;
					
					if (!(meta1 instanceof MetaDNADataCard) || !(meta2 instanceof MetaDNADataCard)) {
						return;
					}
					dna1 = (MetaDNADataCard) meta1;
					dna2 = (MetaDNADataCard) meta2;

					DNA dnaFromTag1 = new DNA();
					DNA dnaFromTag2 = new DNA();
					if (input1.hasTagCompound()) {
						NBTTagCompound tag1 = input1.getTagCompound();
						dnaFromTag1 = DNA.fromNBT(tag1.getCompoundTag("traits"));
					}
					if (input2.hasTagCompound()) {
						NBTTagCompound tag2 = input2.getTagCompound();
						dnaFromTag2 = DNA.fromNBT(tag2.getCompoundTag("traits"));
					}
					DNA newDNA = DNA.merge(dnaFromTag1, dnaFromTag2);
					
					NBTTagCompound newCompound = new NBTTagCompound();
					newCompound.setCompoundTag("traits", newDNA.toNBT());
					ItemStack newStack = DNACraft.Items.itemUnstackable.newItemStack(MetaDNADataCard.class);
					newStack.setTagCompound(newCompound);
					itemStacks[2] = newStack;

					decrStackSize(0, 1);
					decrStackSize(1, 1);
				}
			}
		}
	}
	
	@Override
	public String getInvName() {
		return "dnacraft.machines.splicer";
	}

}
