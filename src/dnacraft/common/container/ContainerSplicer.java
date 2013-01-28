package dnacraft.common.container;

import dnacraft.common.tileentity.TileEntitySplicer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;

public class ContainerSplicer extends BaseContainer {

	protected IInventory playerInventory;
	protected TileEntitySplicer splicer;
	
	public ContainerSplicer(IInventory playerInventory, TileEntitySplicer splicer) {
		super(splicer.getSizeInventory());
		
		this.playerInventory = playerInventory;
		this.splicer = splicer;
		
		addSlotToContainer(new Slot(splicer, 0, 44, 36));
		addSlotToContainer(new Slot(splicer, 1, 80, 36));
		addSlotToContainer(new Slot(splicer, 2, 116, 36));

		for (int l = 0; l < 3; l++) {
			for (int k1 = 0; k1 < 9; k1++) {
				addSlotToContainer(new Slot(playerInventory, k1 + l * 9 + 9, 8 + k1 * 18, 84 + l * 18));
			}
		}

		for (int i1 = 0; i1 < 9; i1++) {
			addSlotToContainer(new Slot(playerInventory, i1, 8 + i1 * 18, 142));
		}
	}

	@Override
	public boolean canInteractWith(EntityPlayer var1) {
		return true;
	}

}
