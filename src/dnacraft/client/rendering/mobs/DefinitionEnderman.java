package dnacraft.client.rendering.mobs;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.RenderEngine;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import dnacraft.api.IMobDefinition;

public class DefinitionEnderman implements IMobDefinition {

	private ModelRenderer head;
    private ModelRenderer headwear;
	private ModelRenderer leftLeg;
    private ModelRenderer rightLeg;
    
	public DefinitionEnderman(ModelBase base) {
        this.head = new ModelRenderer(base, 0, 0);
        this.head.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8);

        this.headwear = new ModelRenderer(base, 0, 16);
        this.headwear.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, -0.5F);
        

        this.rightLeg = new ModelRenderer(base, 56, 0);
        this.rightLeg.addBox(-1.0F, 0.0F, -1.0F, 2, 30, 2);
        
        this.leftLeg = new ModelRenderer(base, 56, 0);
        this.leftLeg.mirror = true;
        this.leftLeg.addBox(-1.0F, 0.0F, -1.0F, 2, 30, 2);
	}

	private void bindTexture() {
		RenderEngine renderEngine = Minecraft.getMinecraft().renderEngine;
		renderEngine.bindTexture(renderEngine.getTexture("/mob/enderman.png"));
	}
	
	@Override
	public void renderHead(Entity entity, float legSwing, float prevLegSwing,
			float wingSwing, float yaw, float pitch, float scale,
			int legHeight, Vec3 attachmentPoint) {
		head.showModel = true;
		bindToAttachmentPoint(head, attachmentPoint, legHeight);
		bindToAttachmentPoint(headwear, attachmentPoint, legHeight);
		bindTexture();
		head.rotateAngleX = pitch / (180F / (float) Math.PI);
		head.rotateAngleY = yaw / (180F / (float) Math.PI);
        headwear.rotateAngleY = head.rotateAngleY;
        headwear.rotateAngleX = head.rotateAngleX;
		head.render(scale);
		headwear.render(scale);
	}

	@Override
	public void renderLegs(Entity entity, float legSwing, float prevLegSwing,
			float wingSwing, float yaw, float pitch, float scale,
			Vec3[] attachmentPoints) {

		bindTexture();
		setLegRotation(leftLeg, attachmentPoints[0]);
		setLegRotation(rightLeg, attachmentPoints[1]);

        this.rightLeg.rotateAngleX = MathHelper.cos(legSwing * 0.6662F) * 1.4F * prevLegSwing;
        this.leftLeg.rotateAngleX = MathHelper.cos(legSwing * 0.6662F + (float)Math.PI) * 1.4F * prevLegSwing;
        this.rightLeg.rotateAngleY = 0.0F;
        this.leftLeg.rotateAngleY = 0.0F;

        this.rightLeg.rotationPointZ = 0.1F;
        this.leftLeg.rotationPointZ = 0.1F;
        this.rightLeg.rotationPointY = 12.0F;
        this.leftLeg.rotationPointY = 12.0F;
        
        this.rightLeg.rotateAngleX = (float)((double)this.rightLeg.rotateAngleX * 0.5D);
        this.leftLeg.rotateAngleX = (float)((double)this.leftLeg.rotateAngleX * 0.5D);
        float var9 = 0.4F;
        float var8 = -14.0F;
        if (this.rightLeg.rotateAngleX > var9)
        {
            this.rightLeg.rotateAngleX = var9;
        }

        if (this.leftLeg.rotateAngleX > var9)
        {
            this.leftLeg.rotateAngleX = var9;
        }

        if (this.rightLeg.rotateAngleX < -var9)
        {
            this.rightLeg.rotateAngleX = -var9;
        }

        if (this.leftLeg.rotateAngleX < -var9)
        {
            this.leftLeg.rotateAngleX = -var9;
        }
        this.rightLeg.rotationPointY = 9.0F + var8;
        this.leftLeg.rotationPointY = 9.0F + var8;
		leftLeg.render(scale);
		rightLeg.render(scale);
	}

	@Override
	public void renderBody(Entity entity, float legSwing, float prevLegSwing,
			float wingSwing, float yaw, float pitch, float scale, int legHeight) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void renderWings(Entity entity, float legSwing, float prevLegSwing,
			float wingSwing, float yaw, float pitch, float scale,
			int legHeight, Vec3[] attachmentPoints) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Vec3[] getLegAttachmentPoints(int numLegs) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vec3 getHeadAttachmentPoint() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vec3[] getWingAttachmentPoints() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getName() {
		return "enderman";
	}

	@Override
	public int getLegHeight() {
		// TODO Auto-generated method stub
		return 29;
	}

	@Override
	public float getBodyLength() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getNumberOfLegs() {
		// TODO Auto-generated method stub
		return 2;
	}

	private void bindToAttachmentPoint(ModelRenderer renderer, Vec3 attachmentPoint, float legHeight) {
		renderer.setRotationPoint(
				(float) attachmentPoint.xCoord,
				(float) (24 - legHeight - attachmentPoint.yCoord),
				(float) attachmentPoint.zCoord
		);
	}
	private void setLegRotation(ModelRenderer leg, Vec3 point) {
		leg.setRotationPoint((float) point.xCoord,
				(float) (24 - getLegHeight() - point.yCoord),
				(float) point.zCoord);
	}
}
