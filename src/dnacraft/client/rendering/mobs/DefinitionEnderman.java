package dnacraft.client.rendering.mobs;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.RenderEngine;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import dnacraft.api.IMobDefinition;

public class DefinitionEnderman extends BaseDefinition implements IMobDefinition {

	private ModelRenderer head;
    private ModelRenderer headwear;
    private ModelRenderer body;
	private ModelRenderer leftLeg;
    private ModelRenderer rightLeg;

	public DefinitionEnderman(ModelBase base) {
		
		legAttachmentPoints2 = new Vec3[] {
				Vec3.createVectorHelper(3.0, -8.0, 0.0),
				Vec3.createVectorHelper(-3.0, -8.0, 0.0),
		};
		
		legAttachmentPoints4 = new Vec3[] {
				Vec3.createVectorHelper(1.0, -8.0, 1.0),
				Vec3.createVectorHelper(-1.0, -8.0, 1.0),
				Vec3.createVectorHelper(1.0, -8.0, -1.0),
				Vec3.createVectorHelper(-1.0, -8.0, -1.0),
		};

		legAttachmentPoints8 = new Vec3[] {
				Vec3.createVectorHelper(0.0, 3.0F, 0.0),
				Vec3.createVectorHelper(0.0, 3.0F, 0.0),
				Vec3.createVectorHelper(0.0, 3.0F, 0.0),
				Vec3.createVectorHelper(0.0, 3.0F, 0.0),
				Vec3.createVectorHelper(0.0, 3.0F, 0.0),
				Vec3.createVectorHelper(0.0, 3.0F, 0.0),
				Vec3.createVectorHelper(0.0, 3.0F, 0.0),
				Vec3.createVectorHelper(0.0, 3.0F, 0.0),
		};

		headAttachmentPoint = Vec3.createVectorHelper(0, 0, 0);
		
		wingAttachmentPoints = new Vec3[] {
				Vec3.createVectorHelper(-4.0F, -1.0F, 0.0F),
				Vec3.createVectorHelper(4.0F, -1.0F, 0.0F),
		};
		
        this.head = new ModelRenderer(base, 0, 0);
        this.head.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8);

        this.headwear = new ModelRenderer(base, 0, 16);
        this.headwear.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, -0.5F);
        
        this.rightLeg = new ModelRenderer(base, 56, 0);
        this.rightLeg.addBox(-1.0F, 0.0F, -1.0F, 2, 30, 2);
        
        this.leftLeg = new ModelRenderer(base, 56, 0);
        this.leftLeg.mirror = true;
        this.leftLeg.addBox(-1.0F, 0.0F, -1.0F, 2, 30, 2);
        
        this.body = new ModelRenderer(base, 32, 16);
        this.body.addBox(-4.0F, -0.0F, -2.0F, 8, 12, 4);
	}

	private void bindTexture() {
		super.bindTexture("/mob/enderman.png");
	}
	
	@Override
	public void renderHead(Entity entity, float legSwing, float prevLegSwing,
			float wingSwing, float yaw, float pitch, float scale,
			int legHeight, int bodyHeight, Vec3 attachmentPoint) {
		head.showModel = true;
		bindToAttachmentPoint(head, attachmentPoint, legHeight, bodyHeight);
		bindToAttachmentPoint(headwear, attachmentPoint, legHeight, bodyHeight);
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
			float wingSwing, float yaw, float pitch, float scale, int bodyHeight,
			Vec3[] attachmentPoints) {
		
		bindTexture();
		
		bindToAttachmentPoint(leftLeg, attachmentPoints[0], getLegHeight(), bodyHeight);
		bindToAttachmentPoint(rightLeg, attachmentPoints[1], getLegHeight(), bodyHeight);

        this.rightLeg.rotateAngleX = MathHelper.cos(legSwing * 0.6662F) * 1.4F * prevLegSwing;
        this.leftLeg.rotateAngleX = MathHelper.cos(legSwing * 0.6662F + (float)Math.PI) * 1.4F * prevLegSwing;
        this.rightLeg.rotateAngleY = 0.0F;
        this.leftLeg.rotateAngleY = 0.0F;

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
		leftLeg.render(scale);
		rightLeg.render(scale);
	}

	@Override
	public void renderBody(Entity entity, float legSwing, float prevLegSwing,
			float wingSwing, float yaw, float pitch, float scale, int legHeight) {
		body.setRotationPoint(0, (float) 24 - legHeight - getBodyHeight(), 0);
		bindTexture();
		body.render(scale);
	}

	@Override
	public void renderWings(Entity entity, float legSwing, float prevLegSwing,
			float wingSwing, float yaw, float pitch, float scale,
			int legHeight, int bodyHeight, Vec3[] attachmentPoints) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getName() {
		return "enderman";
	}

	@Override
	public int getLegHeight() {
		return 30;
	}
	
	@Override
	public int getBodyHeight() {
		return 12;
	}

	@Override
	public int getNumberOfLegs() {
		// TODO Auto-generated method stub
		return 2;
	}

}
