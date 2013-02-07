package dnacraft.common.block;

import dnacraft.DNACraft;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

public abstract class BlockGeneric extends BlockContainer {

	protected int textureIndex = 0;
	
	protected BlockGeneric(int par1, Material par2Material) {
		super(par1, par2Material);
		setCreativeTab(CreativeTabs.tabMisc);
		setTextureFile("/dnacraft/resources/gfx/machines.png");
	}
	
	@Override
	public int getBlockTextureFromSideAndMetadata(int i, int j) {
		if (j == 0 && i == 3)
			return textureIndex;

		if (i == j)
			return textureIndex;
		
		return textureIndex + (i == 1 ? 2 : 1);
	}
	
	@Override
	public void onBlockPlacedBy(World world, int i, int j, int k, EntityLiving entityliving) {
		super.onBlockPlacedBy(world, i, j, k, entityliving);
		ForgeDirection orientation = get2dOrientation(entityliving.getPosition(1.0F), Vec3.createVectorHelper(i, j, k));
		world.setBlockMetadataWithNotify(i, j, k, orientation.getOpposite().ordinal());
	}
	
	public ForgeDirection get2dOrientation(Vec3 pos1, Vec3 pos2) {
		double Dx = pos1.xCoord - pos2.xCoord;
		double Dz = pos1.zCoord - pos2.zCoord;
		double angle = Math.atan2(Dz, Dx) / Math.PI * 180 + 180;

		if (angle < 45 || angle > 315)
			return ForgeDirection.EAST;
		else if (angle < 135)
			return ForgeDirection.SOUTH;
		else if (angle < 225)
			return ForgeDirection.WEST;
		else
			return ForgeDirection.NORTH;
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
