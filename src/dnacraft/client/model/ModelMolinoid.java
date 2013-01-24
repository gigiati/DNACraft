package dnacraft.client.model;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.model.ModelQuadruped;
import net.minecraft.entity.Entity;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
@SideOnly(Side.CLIENT)
public class ModelMolinoid extends ModelQuadruped
{

	public ModelMolinoid() {
		super(4, 2);
	}
	
}
