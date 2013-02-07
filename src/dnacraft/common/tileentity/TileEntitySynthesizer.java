package dnacraft.common.tileentity;

import dnacraft.DNACraft;
import dnacraft.common.item.metas.MetaDNA;
import dnacraft.common.item.metas.MetaDNADataCard;
import dnacraft.common.item.metas.MetaMutantEgg;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class TileEntitySynthesizer extends BaseInventoryTileEntity implements IInventory {
	
	public TileEntitySynthesizer() {
		itemStacks = new ItemStack[2];
	}
	
	@Override
	public String getInvName() {
		return "dnacraft.machines.synthesizer";
	}

	@Override
	public void updateEntity() {
		super.updateEntity();
		if (!worldObj.isRemote) {

			ItemStack input = itemStacks[0];
			ItemStack output = itemStacks[1];
			if (output != null || input == null) { return; }
			Item inputItem = input.getItem();
			
			if (inputItem == DNACraft.Items.itemUnstackable) {
				
				if (DNACraft.Items.itemUnstackable.isA(input, MetaDNADataCard.class)) {

					NBTTagCompound compound = input.getTagCompound();
					if (compound == null) { 
						return;
					}
					ItemStack dna = DNACraft.Items.itemUnstackable.newItemStack(MetaDNA.class);
					NBTTagCompound newCompound = new NBTTagCompound();
					NBTTagCompound oldTraits = compound.getCompoundTag("traits");
					if (oldTraits == null) {
						return;
					}
					newCompound.setCompoundTag("traits", (NBTTagCompound)oldTraits.copy());
					dna.setTagCompound(newCompound);
					itemStacks[1] = dna;
					this.decrStackSize(0, 1);
					
				}
				
			}
			
		}
	}

}
