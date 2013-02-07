package dnacraft.client.renderer.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.MathHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import dnacraft.common.entity.EntityMutant;

@SideOnly(Side.CLIENT)
public class RenderMutant extends RenderLiving
{
	public RenderMutant(ModelBase par1ModelBase, float par2) {
		super(par1ModelBase, par2);
	}

	/* Wing rotation! */
	@Override
    protected float handleRotationFloat(EntityLiving _mutant, float par2)
    {
		EntityMutant mutant = (EntityMutant) _mutant;
        float var3 = mutant.field_70888_h + (mutant.field_70886_e - mutant.field_70888_h) * par2;
        float var4 = mutant.field_70884_g + (mutant.destPos - mutant.field_70884_g) * par2;
        return (MathHelper.sin(var3) + 1.0F) * var4;
    }
}
