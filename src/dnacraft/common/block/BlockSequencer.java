package dnacraft.common.block;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import dnacraft.DNACraft;
import dnacraft.common.tileentity.TileEntitySequencer;

public class BlockSequencer extends BlockContainer {

	public BlockSequencer(int par1, Material par2Material) {
		super(par1, par2Material);
		setCreativeTab(CreativeTabs.tabMisc);
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEntitySequencer();
	}

	@Override
	public String getBlockName() {
		return "dnacraft.machines.sequencer";
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z,
			EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		if (!world.isRemote) {
			if (player.isSneaking()) {
				return false;
			}
			player.openGui(DNACraft.instance, 1989, world, x, y, z);
			return true;
		}

		return true;
	}
}
