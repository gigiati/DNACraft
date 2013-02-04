package dnacraft.common.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import dnacraft.common.tileentity.TileEntitySequencer;

public class ContainerSequencer extends BaseContainer
{

	protected IInventory playerInventory;
	protected TileEntitySequencer sequencer;

	public ContainerSequencer(IInventory playerInventory, TileEntitySequencer sequencer) {
		super(sequencer.getSizeInventory());
		this.playerInventory = playerInventory;
		this.sequencer = sequencer;

		addSlotToContainer(new Slot(sequencer, 0, 55, 35)); // fuel
		addSlotToContainer(new Slot(sequencer, 1, 83, 49)); //bottle
		addSlotToContainer(new Slot(sequencer, 2, 83, 21)); // sample
		addSlotToContainer(new Slot(sequencer, 3, 119, 35)); // output

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
		return sequencer.isUseableByPlayer(entityplayer);
	}

}
