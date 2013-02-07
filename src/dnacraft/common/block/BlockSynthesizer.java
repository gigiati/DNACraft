package dnacraft.common.block;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import dnacraft.DNACraft;
import dnacraft.common.tileentity.TileEntitySynthesizer;

public class BlockSynthesizer extends BlockGeneric {

	public BlockSynthesizer(int id, Material material) {
		super(id, material);
		textureIndex = 32;
	}
	
	@Override
	public String getBlockName() {
		return "dnacraft.machines.synthesizer";
	}
	
	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEntitySynthesizer();
	}

}
