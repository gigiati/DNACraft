package dnacraft.common.block;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import dnacraft.DNACraft;
import dnacraft.common.tileentity.TileEntityElectroporator;

public class BlockElectroporator extends BlockGeneric {
	
	public BlockElectroporator(int par1, Material par2Material) {
		super(par1, par2Material);
		textureIndex = 16;
	}
	
	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEntityElectroporator();
	}
	
	@Override
	public String getBlockName() {
		return "dnacraft.machines.electroporator";
	}
}
