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
import dnacraft.api.IMeta;
import dnacraft.common.evolution.DNA;
import dnacraft.common.item.ItemGeneric;
import dnacraft.common.item.metas.MetaDNA;
import dnacraft.common.item.metas.MetaDNAFragment;

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
					
					MetaDNA dna1 = null;
					MetaDNA dna2 = null;
					MetaDNAFragment fragment = null;
					
					if (meta1 instanceof MetaDNA) {
						dna1 = (MetaDNA)meta1;
					}
					if (meta2 instanceof MetaDNA) {
						if (dna1 == null) {
							dna1 = (MetaDNA)meta2;
						}else {
							dna2 = (MetaDNA)meta2;
						}
					}
					
					if (meta1 instanceof MetaDNAFragment) {
						fragment = (MetaDNAFragment)meta1;
					}else if (meta2 instanceof MetaDNAFragment) {
						fragment = (MetaDNAFragment)meta2;
					}

					if ((dna1 == null && dna2 == null) || (dna1 == null && fragment == null)) {
						return;
					}
					
					DNA newDNA = null;
					// additional logic here
					if (dna1 != null && dna2 != null) {
						DNA dnaFromTag1 = new DNA();
						DNA dnaFromTag2 = new DNA();
						if (input1.hasTagCompound()) {
							NBTTagCompound tag1 = input1.getTagCompound();
							dnaFromTag1 = DNA.fromTagCompound(tag1.getCompoundTag("traits"));
						}
						if (input2.hasTagCompound()) {
							NBTTagCompound tag2 = input2.getTagCompound();
							dnaFromTag2 = DNA.fromTagCompound(tag2.getCompoundTag("traits"));
						}
						newDNA = DNA.merge(dnaFromTag2, dnaFromTag2);
					}else if (dna1 != null && fragment != null) {
						DNA stackDNA = new DNA();
						ItemStack stack = null;
						if (meta1 instanceof MetaDNA) {
							stack = input1;
						}else {
							stack = input2;
						}
						if (stack.hasTagCompound()) {
							stackDNA = DNA.fromTagCompound(stack.getTagCompound().getCompoundTag("traits"));
						}
						newDNA = DNA.mergeFragment(stackDNA, fragment.getDNA());
					}
					NBTTagCompound newCompound = new NBTTagCompound();
					newCompound.setCompoundTag("traits", newDNA.toTagCompound());
					ItemStack newStack = dna1.newItemStack();
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
