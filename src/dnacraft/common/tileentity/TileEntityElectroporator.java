package dnacraft.common.tileentity;

import dnacraft.DNACraft;
import dnacraft.api.IMeta;
import dnacraft.common.evolution.DNA;
import dnacraft.common.item.ItemGeneric;
import dnacraft.common.item.ItemUnstackable;
import dnacraft.common.item.metas.MetaBloodSample;
import dnacraft.common.item.metas.MetaDNASampleCard;
import dnacraft.common.item.metas.MetaOrganicSample;
import dnacraft.common.item.metas.MetaMutantEgg;
import dnacraft.common.item.metas.MetaTestTube;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemEgg;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class TileEntityElectroporator extends BaseInventoryTileEntity implements IInventory {

	public TileEntityElectroporator() {
	}

	@Override
	public void updateEntity() {
		super.updateEntity();
		if (!worldObj.isRemote) {
			ItemStack fuelStack = itemStacks[0];
			ItemStack eggStack = itemStacks[1];
			ItemStack dnaStack = itemStacks[2];
			ItemStack outputStack = itemStacks[3];
			if (outputStack != null && outputStack.stackSize > 0 ) {
				return;
			}
			if (fuelStack == null || eggStack == null || dnaStack == null) {
				return;
			}
			if (fuelStack.getItem() != Item.diamond ||
				eggStack.getItem() != Item.egg ||
				!(dnaStack.getItem() instanceof ItemGeneric)
			) {
				return;
			}
			ItemGeneric dnaItem = (ItemGeneric)dnaStack.getItem();
			if (!dnaItem.isA(dnaStack, MetaDNASampleCard.class)) {
				return;
			}
			NBTTagCompound compound = dnaStack.getTagCompound();
			if (compound == null) { 
				return;
			}
			ItemStack mutantEgg = DNACraft.Items.itemUnstackable.newItemStack(MetaMutantEgg.class);
			NBTTagCompound newCompound = new NBTTagCompound();
			NBTTagCompound oldTraits = compound.getCompoundTag("traits");
			if (oldTraits == null) {
				return;
			}
			newCompound.setCompoundTag("traits", (NBTTagCompound)oldTraits.copy());
			mutantEgg.setTagCompound(newCompound);
			this.decrStackSize(0, 1);
			this.decrStackSize(1, 1);
			this.decrStackSize(2, 1);
			itemStacks[3] = mutantEgg;
		}
	}
	
	@Override
	public String getInvName() {
		return "dnacraft.machines.electroporator";
	}

}
