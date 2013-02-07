package dnacraft.common.tileentity;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagDouble;
import dnacraft.DNACraft;
import dnacraft.api.IMeta;
import dnacraft.common.evolution.DNA;
import dnacraft.common.item.ItemGeneric;
import dnacraft.common.item.metas.MetaDNASampleCard;
import dnacraft.common.item.metas.MetaOrganicSample;

public class TileEntitySplicer extends BaseInventoryTileEntity implements IInventory {

	public TileEntitySplicer() {
		super();
	}
	

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
					
					MetaDNASampleCard dna1 = null;
					MetaDNASampleCard dna2 = null;
					
					if (!(meta1 instanceof MetaDNASampleCard) || !(meta2 instanceof MetaDNASampleCard)) {
						return;
					}
					dna1 = (MetaDNASampleCard) meta1;
					dna2 = (MetaDNASampleCard) meta2;

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
					ItemStack newStack = DNACraft.Items.itemUnstackable.newItemStack(MetaDNASampleCard.class);
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
