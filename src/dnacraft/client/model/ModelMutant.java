package dnacraft.client.model;

import org.lwjgl.opengl.GL11;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelMutant extends ModelBase
{

    public ModelRenderer head = new ModelRenderer(this, 0, 0);
    public ModelRenderer rightWing;
    public ModelRenderer leftWing;
    
	public ModelMutant() {
        this.head.addBox(-4.0F, -4.0F, -8.0F, 8, 8, 8, 0.0F);
        this.head.setRotationPoint(0.0F, (float)(18 - 6), -6.0F);
        
        this.rightWing = new ModelRenderer(this, 24, 13);
        this.rightWing.addBox(0.0F, 0.0F, -3.0F, 1, 4, 6);
        this.rightWing.setRotationPoint(-4.0F, (float)(-3 + 16), 0.0F);
        this.leftWing = new ModelRenderer(this, 24, 13);
        this.leftWing.addBox(-1.0F, 0.0F, -3.0F, 1, 4, 6);
        this.leftWing.setRotationPoint(4.0F, (float)(-3 + 16), 0.0F);
	}
	
    public void render(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7)
    {
        this.setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
        this.head.render(par7);

    }
    public void renderWings(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7)
    {
        this.rightWing.render(par7);
        this.leftWing.render(par7);
    }
    
    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity)
    {
        this.head.rotateAngleX = par5 / (180F / (float)Math.PI);
        this.head.rotateAngleY = par4 / (180F / (float)Math.PI);
        this.rightWing.rotateAngleZ = par3;
        this.leftWing.rotateAngleZ = -par3;
    }
}
