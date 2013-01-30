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
import net.minecraft.util.Vec3;
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
    	
    	// first get the leg height from one of the legs
    	// so we know where to render the body
    	int legheight = 5;//legsDef[0].getHeight();

    	// bind the body texture
        renderEngine.bindTexture(renderEngine.getTexture(bodyDef.getTexture()));
        // get the body model
    	ModelRenderer body = bodyDef.getRenderer();
    	
    	//set the body x, y, z
    	body.setRotationPoint(0, (float)24 - legheight, bodyDef.getWidth() / 2);
    	
    	bodyDef.setRotation(body, entity, legSwing, prevLegSwing, wingSwing, yaw, pitch, scale);
    	
    	// render the body
    	body.render(scale);
    	
    	/*
    	// so, a body has relative attachment points for either 2, 4 or 8 legs.
    	// we know we have 2 legs (because we're rendering pig legs), so we get
    	// the attachment point on the body for when there's 2 legs
    	Vec3[] legAttachments = bodyDef.getLegAttachmentPoints(legsDef.length);
    	
    	// loop through the leg defintions
    	for (int i=0; i<legsDef.length; i++) {
    		
    		BodyPart leg = legsDef[i];
    		
    		// get the attachment point
    		Vec3 attachmentPoint = legAttachments[i];
    		
    		// now render the leg..etc.
    		
    	}
        */

    }
    /*
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
