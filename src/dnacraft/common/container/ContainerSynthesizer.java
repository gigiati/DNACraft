package dnacraft.common.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import dnacraft.common.tileentity.TileEntitySynthesizer;

public class ContainerSynthesizer extends BaseContainer {

	protected IInventory playerInventory;
	protected TileEntitySynthesizer synthesizer;
	
	public ContainerSynthesizer(IInventory playerInventory, TileEntitySynthesizer synthesizer) {
		super(synthesizer.getSizeInventory());
		this.playerInventory = playerInventory;
		this.synthesizer = synthesizer;
		
		addSlotToContainer(new Slot(synthesizer, 0, 44, 36));
		addSlotToContainer(new Slot(synthesizer, 1, 80, 36));

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
		return synthesizer.isUseableByPlayer(entityplayer);
	}

}
