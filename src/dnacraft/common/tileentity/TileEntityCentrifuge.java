package dnacraft.common.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.common.ISidedInventory;

public class TileEntityCentrifuge extends TileEntity implements IInventory,
		ISidedInventory {

	private ItemStack[] centrifugeItemStacks = new ItemStack[2];

	@Override
	public int getStartInventorySide(ForgeDirection side) {
		return 0;
	}

	@Override
	public int getSizeInventorySide(ForgeDirection side) {
		return 0;
	}

	@Override
	public int getSizeInventory() {
		return this.centrifugeItemStacks.length;
	}

	@Override
	public ItemStack getStackInSlot(int i) {
		return this.centrifugeItemStacks[i];
	}

	@Override
	public ItemStack decrStackSize(int var1, int var2) {
		return null;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int var1) {
		return null;
	}

	@Override
	public void setInventorySlotContents(int var1, ItemStack var2) {

	}

	@Override
	public String getInvName() {
		return "dnacraft.container.centrifuge";
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		return this.worldObj.getBlockTileEntity(this.xCoord, this.yCoord,
				this.zCoord) != this ? false : player.getDistanceSq(
				(double) this.xCoord + 0.5D, (double) this.yCoord + 0.5D,
				(double) this.zCoord + 0.5D) <= 64.0D;
	}

	@Override
	public void openChest() {
	}

	@Override
	public void closeChest() {
	}

	@Override
	public void readFromNBT(NBTTagCompound par1NBTTagCompound) {
		super.readFromNBT(par1NBTTagCompound);
		NBTTagList var2 = par1NBTTagCompound.getTagList("Items");
		this.centrifugeItemStacks = new ItemStack[this.getSizeInventory()];

		for (int i = 0; i < var2.tagCount(); ++i) {
			NBTTagCompound itemTag = (NBTTagCompound) var2.tagAt(i);
			byte slot = itemTag.getByte("Slot");

			if (slot >= 0 && slot < this.centrifugeItemStacks.length) {
				this.centrifugeItemStacks[slot] = ItemStack
						.loadItemStackFromNBT(itemTag);
			}
		}

	}

	@Override
	public void writeToNBT(NBTTagCompound par1NBTTagCompound) {
		super.writeToNBT(par1NBTTagCompound);
		NBTTagList itemList = new NBTTagList();

		for (int i = 0; i < this.centrifugeItemStacks.length; ++i) {
			if (this.centrifugeItemStacks[i] != null) {
				NBTTagCompound itemCompound = new NBTTagCompound();
				itemCompound.setByte("Slot", (byte) i);
				this.centrifugeItemStacks[i].writeToNBT(itemCompound);
				itemList.appendTag(itemCompound);
			}
		}

		par1NBTTagCompound.setTag("Items", itemList);
	}
}
