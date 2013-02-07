package dnacraft.common.block;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import dnacraft.DNACraft;
import dnacraft.common.tileentity.TileEntitySequencer;

public class BlockSequencer extends BlockGeneric {

	public BlockSequencer(int par1, Material par2Material) {
		super(par1, par2Material);
		textureIndex = 0;
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEntitySequencer();
	}

	@Override
	public String getBlockName() {
		return "dnacraft.machines.sequencer";
	}
}
