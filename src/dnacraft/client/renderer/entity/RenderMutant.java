package dnacraft.client.renderer.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderMutant extends RenderLiving
{
	public RenderMutant(ModelBase par1ModelBase, float par2) {
		super(par1ModelBase, par2);
	}

}
