package dnacraft.common.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnace;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.tileentity.TileEntityFurnace;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import dnacraft.common.tileentity.TileEntityCentrifuge;


public class ContainerCentrifuge extends BaseContainer
{

	protected IInventory playerInventory;
	protected TileEntityCentrifuge centrifuge;

	public ContainerCentrifuge(IInventory playerInventory, TileEntityCentrifuge centrifuge) {
		super(centrifuge.getSizeInventory());
		this.playerInventory = playerInventory;
		this.centrifuge = centrifuge;

		addSlotToContainer(new Slot(centrifuge, 0, 62, 22));
		addSlotToContainer(new Slot(centrifuge, 1, 62, 50));
		addSlotToContainer(new Slot(centrifuge, 2, 98, 36));

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
		return centrifuge.isUseableByPlayer(entityplayer);
	}
}
