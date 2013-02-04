package dnacraft.client.rendering.mobs;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.RenderEngine;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import dnacraft.api.IMobDefinition;
import dnacraft.common.evolution.Trait;

public class DefinitionEnderman extends BaseDefinition implements IMobDefinition {

	private ModelRenderer head;
    private ModelRenderer headwear;
    private ModelRenderer body;
	private ModelRenderer leftLeg;
    private ModelRenderer rightLeg;
    private ModelRenderer leftArm;
    private ModelRenderer rightArm;

	public DefinitionEnderman(ModelBase base) {
		
		legAttachmentPoints2 = new Vec3[] {
				Vec3.createVectorHelper(2.0, -12.0, 0.0),
				Vec3.createVectorHelper(-2.0, -12.0, 0.0),
		};
		
		legAttachmentPoints4 = new Vec3[] {
				Vec3.createVectorHelper(1.0, -12.0, 1.0),
				Vec3.createVectorHelper(-1.0, -12.0, 1.0),
				Vec3.createVectorHelper(1.0, -12.0, -1.0),
				Vec3.createVectorHelper(-1.0, -12.0, -1.0),
		};

		legAttachmentPoints8 = new Vec3[] {
				Vec3.createVectorHelper(0.0, -10.0F, 0.0),
				Vec3.createVectorHelper(0.0, -10.0F, 0.0),
				Vec3.createVectorHelper(0.0, -10.0F, 0.0),
				Vec3.createVectorHelper(0.0, -10.0F, 0.0),
				Vec3.createVectorHelper(0.0, -10.0F, 0.0),
				Vec3.createVectorHelper(0.0, -10.0F, 0.0),
				Vec3.createVectorHelper(0.0, -10.0F, 0.0),
				Vec3.createVectorHelper(0.0, -10.0F, 0.0),
		};

		headAttachmentPoint = Vec3.createVectorHelper(0, 0, 0);
		
		wingAttachmentPoints = new Vec3[] {
				Vec3.createVectorHelper(-4.0F, -1.0F, 0.0F),
				Vec3.createVectorHelper(4.0F, -1.0F, 0.0F),
		};
		
		armAttachmentPoints = new Vec3[] {
				Vec3.createVectorHelper(-5.0F, -1.0F, 0.0F),
				Vec3.createVectorHelper(5.0F, -1.0F, 0.0F),
		};

		tailAttachmentPoint = Vec3.createVectorHelper(0, -10, 2);
		
        head = new ModelRenderer(base, 0, 0);
        head.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8);

        headwear = new ModelRenderer(base, 0, 16);
        headwear.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, -0.5F);
        
        rightLeg = new ModelRenderer(base, 56, 0);
        rightLeg.addBox(-1.0F, 0.0F, -1.0F, 2, 30, 2);
        
        leftLeg = new ModelRenderer(base, 56, 0);
        leftLeg.mirror = true;
        leftLeg.addBox(-1.0F, 0.0F, -1.0F, 2, 30, 2);
        

        rightArm = new ModelRenderer(base, 56, 0);
        rightArm.addBox(-1.0F, -2.0F, -1.0F, 2, 30, 2);
        leftArm = new ModelRenderer(base, 56, 0);
        leftArm.mirror = true;
        leftArm.addBox(-1.0F, -2.0F, -1.0F, 2, 30, 2);
        
        body = new ModelRenderer(base, 32, 16);
        body.addBox(-4.0F, -0.0F, -2.0F, 8, 12, 4);
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

        rightLeg.rotateAngleX = MathHelper.cos(legSwing * 0.6662F) * 1.4F * prevLegSwing;
        leftLeg.rotateAngleX = MathHelper.cos(legSwing * 0.6662F + (float)Math.PI) * 1.4F * prevLegSwing;
        rightLeg.rotateAngleY = 0.0F;
        leftLeg.rotateAngleY = 0.0F;

        rightLeg.rotateAngleX = (float)((double)rightLeg.rotateAngleX * 0.5D);
        leftLeg.rotateAngleX = (float)((double)leftLeg.rotateAngleX * 0.5D);
        float var9 = 0.4F;
        float var8 = -14.0F;
        if (rightLeg.rotateAngleX > var9)
        {
            rightLeg.rotateAngleX = var9;
        }

        if (leftLeg.rotateAngleX > var9)
        {
            leftLeg.rotateAngleX = var9;
        }

        if (rightLeg.rotateAngleX < -var9)
        {
            rightLeg.rotateAngleX = -var9;
        }

        if (leftLeg.rotateAngleX < -var9)
        {
            leftLeg.rotateAngleX = -var9;
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
	public void renderArms(Entity entity, float legSwing, float prevLegSwing,
			float wingSwing, float yaw, float pitch, float scale,
			int legHeight, int bodyHeight, Vec3[] attachmentPoints) {
		bindTexture();
		bindToAttachmentPoint(leftArm, attachmentPoints[0], legHeight, bodyHeight);
		bindToAttachmentPoint(rightArm, attachmentPoints[1], legHeight, bodyHeight);
		rightArm.rotateAngleX = MathHelper.cos(legSwing * 0.6662F + (float)Math.PI) * 2.0F * prevLegSwing * 0.5F;
        leftArm.rotateAngleX = MathHelper.cos(legSwing * 0.6662F) * 2.0F * prevLegSwing * 0.5F;

        rightArm.rotateAngleX = (float)((double)rightArm.rotateAngleX * 0.5D);
        leftArm.rotateAngleX = (float)((double)leftArm.rotateAngleX * 0.5D);
        float var9 = 0.4F;

        if (rightArm.rotateAngleX > var9)
        {
            rightArm.rotateAngleX = var9;
        }

        if (leftArm.rotateAngleX > var9)
        {
            leftArm.rotateAngleX = var9;
        }

        if (rightArm.rotateAngleX < -var9)
        {
            rightArm.rotateAngleX = -var9;
        }

        if (leftArm.rotateAngleX < -var9)
        {
            leftArm.rotateAngleX = -var9;
        }
        leftArm.render(scale);
        rightArm.render(scale);
	}

	@Override
	public int getTrait() {
		return Trait.MONSTER_ENDERMAN;
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
		return 2;
	}
}
