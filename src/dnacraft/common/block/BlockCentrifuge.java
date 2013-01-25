package dnacraft.common.block;

import dnacraft.common.tileentity.TileEntityCentrifuge;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockCentrifuge extends BlockContainer {

	public BlockCentrifuge(int par1, Material par2Material) {
		super(par1, par2Material);
		setCreativeTab(CreativeTabs.tabMisc);
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEntityCentrifuge();
	}

}
