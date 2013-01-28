package dnacraft.common.tileentity;

import dnacraft.common.item.metas.MetaDNAFragment;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
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
			if (input != null && input.stackSize > 0) {
				ItemStack output = getFragmentForItemStack(input);
				if (output != null) {
					if (itemStacks[1] != null) {
						ItemStack outputStack = itemStacks[1];
						if (outputStack.getItem() == output.getItem() &&
							outputStack.getItemDamage() == output.getItemDamage() &&
							outputStack.stackSize < outputStack.getMaxStackSize()) {
							this.decrStackSize(0, 1);
							outputStack.stackSize++;
						}
					}else {
						itemStacks[1] = output;
						this.decrStackSize(0, 1);
					}
				}
			}
		}
	}
	
	private ItemStack getFragmentForItemStack(ItemStack stack) {
		Item item = stack.getItem();
		if (item != null) {
			if (MetaDNAFragment.fragmentsForItems.containsKey(item)) {
				MetaDNAFragment meta = MetaDNAFragment.fragmentsForItems.get(item);
				return meta.newItemStack();
			}
		}
		return null;
	}

	@Override
	public String getInvName() {
		return "dnacraft.machines.centrifuge";
	}
	
}
