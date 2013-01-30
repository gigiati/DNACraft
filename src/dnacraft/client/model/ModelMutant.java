package dnacraft.client.model;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.RenderEngine;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelMutant extends ModelBase
{

	public final static int PIG = 0;
	public final static int COW = 1;
	
    public ModelRenderer head = new ModelRenderer(this, 0, 0);
    public ModelRenderer rightWing;
    public ModelRenderer leftWing;
    public ModelRenderer body;
    public ModelRenderer leg1;
    public ModelRenderer leg2;
    public ModelRenderer leg3;
    public ModelRenderer leg4;
    
	public ModelMutant() {

        this.head.addBox(-4.0F, -4.0F, -8.0F, 8, 8, 8, 0.0F);
        this.head.setRotationPoint(0.0F, 12.0F, -6.0F);
        
        this.body = new ModelRenderer(this, 28, 8);
        this.body.addBox(-5.0F, -10.0F, -7.0F, 10, 16, 8, 0.0F);
        this.body.setRotationPoint(0.0F, 11.0F, 2.0F);
        
        this.rightWing = new ModelRenderer(this, 24, 13);
        this.rightWing.addBox(-1.0F, 0.0F, 0.0F, 1, 4, 6);
        this.rightWing.setRotationPoint(-5.0F, 10.0F, 0.0F);
        
        this.leftWing = new ModelRenderer(this, 24, 13);
        this.leftWing.addBox(0.0F, 0.0F, 0.0F, 1, 4, 6);
        this.leftWing.setRotationPoint(5.0F, 10.0F, 0.0F);
        
        this.leg1 = new ModelRenderer(this, 0, 16);
        this.leg1.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4);
        this.leg1.setRotationPoint(-3.0F, 12.0F, 7.0F);
        /*
        this.leg2 = new ModelRenderer(this, 0, 16);
        this.leg2.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4);
        this.leg2.setRotationPoint(3.0F, 12.0F, 7.0F);
        */
        
        
        
	}
	
    public void render(Entity entity, float legSwing, float prevLegSwing, float wingSwing, float yaw, float pitch, float scale)
    {

        RenderEngine renderEngine = Minecraft.getMinecraft().renderEngine;
        
    	int feet = COW;
    	int body = PIG;
    	
    	switch(feet) {
    	case COW:
    		this.renderCowFeet(entity, legSwing, prevLegSwing, wingSwing, yaw, pitch, scale);
    		break;
    	}

    	renderEngine.bindTexture(renderEngine.getTexture("/mob/char.png"));
        this.head.rotateAngleX = pitch / (180F / (float)Math.PI);
        this.head.rotateAngleY = yaw / (180F / (float)Math.PI);
        this.head.render(scale);

        renderEngine.bindTexture(renderEngine.getTexture("/mob/pig.png"));
        this.body.rotateAngleX = ((float)Math.PI / 2F);
        this.body.render(scale);
        
        this.renderWings(entity, legSwing, prevLegSwing, wingSwing, yaw, pitch, scale);

    }
    
    public void renderCowFeet(Entity entity, float legSwing, float prevLegSwing, float wingSwing, float yaw, float pitch, float scale) {
        RenderEngine renderEngine = Minecraft.getMinecraft().renderEngine;
        renderEngine.bindTexture(renderEngine.getTexture("/mob/cow.png"));
        this.leg1.rotateAngleX = MathHelper.cos(legSwing * 0.6662F) * 1.4F * prevLegSwing;
    	this.leg1.render(scale);
    }
    
    public void renderWings(Entity entity, float legSwing, float prevLegSwing, float wingSwing, float yaw, float pitch, float scale)
    {
        RenderEngine renderEngine = Minecraft.getMinecraft().renderEngine;
        renderEngine.bindTexture(renderEngine.getTexture("/mob/chicken.png"));
        this.rightWing.rotateAngleZ = wingSwing;
        this.leftWing.rotateAngleZ = -wingSwing;
        this.rightWing.render(scale);
        this.leftWing.render(scale);
    }
    
}
