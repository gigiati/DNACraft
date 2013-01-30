package dnacraft.client.model;

import java.util.HashMap;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.RenderEngine;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import dnacraft.api.IMobDefinition;
import dnacraft.client.rendering.Body;
import dnacraft.client.rendering.BodyPart;

@SideOnly(Side.CLIENT)
public class ModelMutant extends ModelBase
{

    
    public HashMap<String, BodyPart[]> legs = new HashMap<String, BodyPart[]>();
    public HashMap<String, Body> bodies = new HashMap<String, Body>();
    
    public void register(IMobDefinition mobdef) {
    	
    	legs.put(mobdef.getName(), mobdef.getLegs(this));
    	
    	bodies.put(mobdef.getName(), mobdef.getBody(this));
    	
    }
    
	public ModelMutant() {
        
	}
	
    public void render(Entity entity, float legSwing, float prevLegSwing, float wingSwing, float yaw, float pitch, float scale)
    {

        RenderEngine renderEngine = Minecraft.getMinecraft().renderEngine;
        
    	Body bodyDef = this.bodies.get("pig");
    	BodyPart[] legsDef = this.legs.get("pig");
    	
    	int legheight = 6;

        renderEngine.bindTexture(renderEngine.getTexture("/mob/plig.png"));
    	ModelRenderer body = bodyDef.getRenderer();
    	body.setRotationPoint(-5.0F, 10.0F, -8.0F);
    	//bodyDef.setRotation(body, entity, legSwing, prevLegSwing, wingSwing, yaw, pitch, scale);
    	body.render(scale);
    	/*
    	renderEngine.bindTexture(renderEngine.getTexture("/mob/char.png"));
        this.head.rotateAngleX = pitch / (180F / (float)Math.PI);
        this.head.rotateAngleY = yaw / (180F / (float)Math.PI);
        this.head.render(scale);

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
    }  */
    }
    
}
