package dnacraft.common.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import dnacraft.common.tileentity.TileEntityElectroporator;

public class ContainerElectroporator extends BaseContainer
{

	protected IInventory playerInventory;
	protected TileEntityElectroporator electroporator;

	public ContainerElectroporator(IInventory playerInventory, TileEntityElectroporator electroporator) {
		super(electroporator.getSizeInventory());
		this.playerInventory = playerInventory;
		this.electroporator = electroporator;

		addSlotToContainer(new Slot(electroporator, 0, 55, 35)); // fuel
		addSlotToContainer(new Slot(electroporator, 1, 83, 49)); // egg
		addSlotToContainer(new Slot(electroporator, 2, 83, 21)); // dna
		addSlotToContainer(new Slot(electroporator, 3, 119, 35)); // output

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
	public boolean canInteractWith(EntityPlayer entityplayer) {
		return electroporator.isUseableByPlayer(entityplayer);
	}

}
