package dnacraft.common.tileentity;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import dnacraft.DNACraft;
import dnacraft.api.IMeta;
import dnacraft.common.evolution.DNA;
import dnacraft.common.item.ItemGeneric;
import dnacraft.common.item.metas.MetaBloodSample;
import dnacraft.common.item.metas.MetaDNA;
import dnacraft.common.item.metas.MetaDNADataCard;
import dnacraft.common.item.metas.MetaOrganicSample;

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
			ItemStack paperStack = itemStacks[1];
			ItemStack sampleStack = itemStacks[2];
			ItemStack outputStack = itemStacks[3];
			if (outputStack != null && outputStack.stackSize > 0 ) {
				return;
			}
			if (fuelStack == null || paperStack == null || sampleStack == null) {
				return;
			}
			if (fuelStack.getItem() != Item.diamond ||
					paperStack.getItem() != Item.paper
			) {
				return;
			}
			Item sample = sampleStack.getItem();
			if (sample == null || !(sample instanceof ItemGeneric)) {
				return;
			}
			DNA dnaToUse = null;
			ItemGeneric genericSample = (ItemGeneric) sample;
			IMeta sampleMeta = genericSample.getMeta(sampleStack);
			if (genericSample.isA(sampleStack, MetaBloodSample.class) || genericSample.isA(sampleStack, MetaDNA.class)) {
				if (sampleStack.hasTagCompound()) {
					NBTTagCompound compound = sampleStack.getTagCompound();
					dnaToUse = DNA.fromNBT(compound.getCompoundTag("traits"));
				}
			}else if (genericSample.isA(sampleStack, MetaOrganicSample.class)) {
				dnaToUse = ((MetaOrganicSample)genericSample.getMeta(sampleStack)).getDNA();
			}
			if (dnaToUse != null) {
				ItemStack result = DNACraft.Items.itemUnstackable.newItemStack(MetaDNADataCard.class);
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
