package dnacraft.common.tileentity;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import dnacraft.DNACraft;
import dnacraft.api.IMeta;
import dnacraft.common.evolution.Genome;
import dnacraft.common.helper.NetworkHelper;
import dnacraft.common.item.ItemGeneric;
import dnacraft.common.item.ItemUnstackable;
import dnacraft.common.item.metas.MetaDNA;
import dnacraft.common.item.metas.MetaDNAFragment;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagDouble;
import net.minecraft.tileentity.TileEntity;

public class TileEntitySplicer extends BaseInventoryTileEntity implements IInventory {

	public TileEntitySplicer() {
		super();
		itemStacks = new ItemStack[3];
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
					
					HashMap<String, Double> traitsToAdd = new HashMap<String, Double>();
					NBTTagCompound newCompound = null;
					if (fragment != null) {
						Genome genomeForFragment = fragment.getGenome();
						if (genomeForFragment == null) {
							return;
						}
						if (meta1 instanceof MetaDNA && input1.hasTagCompound()) {
							newCompound = (NBTTagCompound) input1.getTagCompound().copy();
						}else if (meta2 instanceof MetaDNA && input2.hasTagCompound()) {
							newCompound = (NBTTagCompound) input2.getTagCompound().copy();	
						}
						Entry<String,Double> trait = genomeForFragment.getRandomTrait();
						if (trait != null) {
							traitsToAdd.put(trait.getKey(), trait.getValue());
						}
					}else {
						if (meta1 instanceof MetaDNA && input1.hasTagCompound()) {
							newCompound = (NBTTagCompound) input1.getTagCompound().copy();
						}
						if (meta2 instanceof MetaDNA && input2.hasTagCompound()) {
							Collection col = input2.getTagCompound().getTags();
							Iterator it = col.iterator();
							while (it.hasNext()) {
								NBTBase nbt = (NBTBase) it.next();
								if (nbt.getId() == 6) {
									traitsToAdd.put(nbt.getName(), ((NBTTagDouble) nbt).data);
								}
							}
						}
					}

					if (newCompound == null) {
						newCompound = new NBTTagCompound();
					}
					for (Map.Entry<String, Double> trait : traitsToAdd.entrySet()) {
						double val = 0.0;
						if (newCompound.hasKey(trait.getKey())) {
							val = newCompound.getDouble(trait.getKey());
						}
						val += trait.getValue();
						newCompound.setDouble(trait.getKey(), val);
					}
					
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
