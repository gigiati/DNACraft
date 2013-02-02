package dnacraft.client.rendering.mobs;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import dnacraft.api.IMobDefinition;

public class DefinitionZombie extends BaseDefinition implements IMobDefinition {

    public ModelRenderer bipedHead;
    public ModelRenderer bipedHeadwear;
    public ModelRenderer bipedBody;
    public ModelRenderer bipedRightArm;
    public ModelRenderer bipedLeftArm;
    public ModelRenderer bipedRightLeg;
    public ModelRenderer bipedLeftLeg;
    public ModelRenderer bipedEars;
    
    public DefinitionZombie(ModelBase base) {

        base.textureWidth = 64;
        base.textureHeight = 64;
        
		legAttachmentPoints2 = new Vec3[] {
				Vec3.createVectorHelper(-2.0F, -12.0F, 0.0F),
				Vec3.createVectorHelper(2.0F, -12.0F, 0.0F),
		};
		
		legAttachmentPoints4 = new Vec3[] {
				Vec3.createVectorHelper(2.0, -12.0F, 2.0),
				Vec3.createVectorHelper(-2.0, -12.0F, 2.0),
				Vec3.createVectorHelper(2.0, -12.0F, -2.0),
				Vec3.createVectorHelper(-2.0, -12.0F, -2.0),
		};
		
		legAttachmentPoints8 = new Vec3[] {
				Vec3.createVectorHelper(0.0, -9.0F, 0.0),
				Vec3.createVectorHelper(0.0, -9.0F, 0.0),
				Vec3.createVectorHelper(0.0, -9.0F, 0.0),
				Vec3.createVectorHelper(0.0, -9.0F, 0.0),
				Vec3.createVectorHelper(0.0, -9.0F, 0.0),
				Vec3.createVectorHelper(0.0, -9.0F, 0.0),
				Vec3.createVectorHelper(0.0, -9.0F, 0.0),
				Vec3.createVectorHelper(0.0, -9.0F, 0.0),
		};
		
		headAttachmentPoint = Vec3.createVectorHelper(0.0F, 0.0F, 0.0F);

		wingAttachmentPoints = new Vec3[] {
				Vec3.createVectorHelper(-4.0F, 0F, 0.0F),
				Vec3.createVectorHelper(4.0F, 0F, 0.0F),
		};
		
		armAttachmentPoints = new Vec3[] {
				Vec3.createVectorHelper(-5.0F, 0.0F, 0),
				Vec3.createVectorHelper(5.0F, 0.0F, 0),
		};
		
		tailAttachmentPoint = Vec3.createVectorHelper(0, -6, 2);

        this.bipedHead = new ModelRenderer(base, 0, 0);
        this.bipedHead.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8);
        
        this.bipedHeadwear = new ModelRenderer(base, 32, 0);
        this.bipedHeadwear.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.5F);
        
        this.bipedBody = new ModelRenderer(base, 16, 16);
        this.bipedBody.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4);
        
        this.bipedRightArm = new ModelRenderer(base, 40, 16);
        this.bipedRightArm.addBox(-3.0F, -2.0F, -2.0F, 4, 12, 4);
        
        this.bipedLeftArm = new ModelRenderer(base, 40, 16);
        this.bipedLeftArm.mirror = true;
        this.bipedLeftArm.addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4);
        
        this.bipedRightLeg = new ModelRenderer(base, 0, 16);
        this.bipedRightLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4);
        
        this.bipedLeftLeg = new ModelRenderer(base, 0, 16);
        this.bipedLeftLeg.mirror = true;
        this.bipedLeftLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4);
    	
    }
    
	@Override
	public void renderHead(Entity entity, float legSwing, float prevLegSwing,
			float wingSwing, float yaw, float pitch, float scale,
			int legHeight, int bodyHeight, Vec3 attachmentPoint) {
		bindToAttachmentPoint(bipedHead, attachmentPoint, legHeight, bodyHeight);
		bindTexture();
		bipedHead.rotateAngleX = pitch / (180F / (float) Math.PI);
		bipedHead.rotateAngleY = yaw / (180F / (float) Math.PI);
		bipedHead.render(scale);
	}

	@Override
	public void renderLegs(Entity entity, float legSwing, float prevLegSwing,
			float wingSwing, float yaw, float pitch, float scale,
			int bodyHeight, Vec3[] attachmentPoints) {

		bindTexture();
		bindToAttachmentPoint(bipedLeftLeg, attachmentPoints[0], getLegHeight(), bodyHeight);
		bindToAttachmentPoint(bipedRightLeg, attachmentPoints[1], getLegHeight(), bodyHeight);
		this.bipedRightLeg.rotateAngleX = MathHelper.cos(legSwing * 0.6662F) * 1.4F * prevLegSwing;
        this.bipedLeftLeg.rotateAngleX = MathHelper.cos(legSwing * 0.6662F + (float)Math.PI) * 1.4F * prevLegSwing;
        this.bipedRightLeg.rotateAngleY = 0.0F;
        this.bipedLeftLeg.rotateAngleY = 0.0F;
		bipedLeftLeg.render(scale);
		bipedRightLeg.render(scale);
	}

	@Override
	public void renderArms(Entity entity, float legSwing, float prevLegSwing,
			float wingSwing, float yaw, float pitch, float scale,
			int legHeight, int bodyHeight, Vec3[] attachmentPoints) {
		bindTexture();
		bindToAttachmentPoint(bipedLeftArm, attachmentPoints[0], legHeight, bodyHeight);
		bindToAttachmentPoint(bipedRightArm, attachmentPoints[1], legHeight, bodyHeight);
		
		float onGround = ((EntityLiving)entity).getSwingProgress(scale);
        float var8 = MathHelper.sin(onGround * (float)Math.PI);
        float var9 = MathHelper.sin((1.0F - (1.0F - onGround) * (1.0F - onGround)) * (float)Math.PI);
        this.bipedRightArm.rotateAngleZ = 0.0F;
        this.bipedLeftArm.rotateAngleZ = 0.0F;
        this.bipedRightArm.rotateAngleY = -(0.1F - var8 * 0.6F);
        this.bipedLeftArm.rotateAngleY = 0.1F - var8 * 0.6F;
        this.bipedRightArm.rotateAngleX = -((float)Math.PI / 2F);
        this.bipedLeftArm.rotateAngleX = -((float)Math.PI / 2F);
        this.bipedRightArm.rotateAngleX -= var8 * 1.2F - var9 * 0.4F;
        this.bipedLeftArm.rotateAngleX -= var8 * 1.2F - var9 * 0.4F;
        this.bipedRightArm.rotateAngleZ += MathHelper.cos(legSwing * 0.09F) * 0.05F + 0.05F;
        this.bipedLeftArm.rotateAngleZ -= MathHelper.cos(legSwing * 0.09F) * 0.05F + 0.05F;
        this.bipedRightArm.rotateAngleX += MathHelper.sin(legSwing * 0.067F) * 0.05F;
        this.bipedLeftArm.rotateAngleX -= MathHelper.sin(legSwing * 0.067F) * 0.05F;

        
        bipedRightArm.render(scale);
        bipedLeftArm.render(scale);
	}
	@Override
	public void renderBody(Entity entity, float legSwing, float prevLegSwing,
			float wingSwing, float yaw, float pitch, float scale, int legHeight) {
		bipedBody.setRotationPoint(0, (float) 24 - legHeight - getBodyHeight(), 0);
		bindTexture();
		bipedBody.render(scale);
	}

	@Override
	public String getName() {
		return "zombie";
	}

	@Override
	public int getLegHeight() {
		return 12;
	}

	@Override
	public int getNumberOfLegs() {
		return 2;
	}
	
	@Override
	public int getBodyHeight() {
		return 12;
	}

	private void bindTexture() {
		super.bindTexture("/mob/zombie.png");
	}
}
