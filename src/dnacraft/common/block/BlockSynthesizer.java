package dnacraft.common.block;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import dnacraft.DNACraft;
import dnacraft.common.tileentity.TileEntitySynthesizer;

public class BlockSynthesizer extends BlockContainer {

	public BlockSynthesizer(int id, Material material) {
		super(id, material);
		setCreativeTab(CreativeTabs.tabMisc);
	}

	@Override
	public String getBlockName() {
		return "dnacraft.machines.synthesizer";
	}
	
	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEntitySynthesizer();
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z,
			EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		if (!world.isRemote) {
			if (player.isSneaking()) {
				return false;
			}
			player.openGui(DNACraft.instance, 1987, world, x, y, z);
			return true;
		}

		return true;
	}
}
