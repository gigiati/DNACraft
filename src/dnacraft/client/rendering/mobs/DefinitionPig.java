package dnacraft.client.rendering.mobs;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import dnacraft.api.IMobDefinition;
import dnacraft.common.evolution.Trait;

public class DefinitionPig extends BaseDefinition implements IMobDefinition {

	private ModelRenderer leg1;
	private ModelRenderer leg2;
	private ModelRenderer leg3;
	private ModelRenderer leg4;
	private ModelRenderer body;
	private ModelRenderer head;
	
	
	public DefinitionPig(ModelBase base) {
		
		legAttachmentPoints2 = new Vec3[] {
				Vec3.createVectorHelper(3.0, -8.0F, 0.0),
				Vec3.createVectorHelper(-3.0, -8.0F, 0.0),
		};
		legAttachmentPoints4 = new Vec3[] {
				Vec3.createVectorHelper(3.0, -8.0F, 7.0),
				Vec3.createVectorHelper(-3.0, -8.0F, 7.0),
				Vec3.createVectorHelper(3.0, -8.0F, -5.0),
				Vec3.createVectorHelper(-3.0, -8.0F, -5.0),
		};
		legAttachmentPoints8 = new Vec3[] {
				Vec3.createVectorHelper(0.0, -5.0F, 0.0),
				Vec3.createVectorHelper(0.0, -5.0F, 0.0),
				Vec3.createVectorHelper(0.0, -5.0F, 0.0),
				Vec3.createVectorHelper(0.0, -5.0F, 0.0),
				Vec3.createVectorHelper(0.0, -5.0F, 0.0),
				Vec3.createVectorHelper(0.0, -5.0F, 0.0),
				Vec3.createVectorHelper(0.0, -5.0F, 0.0),
				Vec3.createVectorHelper(0.0, -5.0F, 0.0),
		};
		
		headAttachmentPoint = Vec3.createVectorHelper(0.0F, -2.0F, -6.0F);
		
		wingAttachmentPoints = new Vec3[] {
				Vec3.createVectorHelper(-5.0F, -1.0F, 0.0F),
				Vec3.createVectorHelper(5.0F, -1.0F, 0.0F),
		};
		
		armAttachmentPoints = new Vec3[] {
				Vec3.createVectorHelper(-5.0F, -1.0F, 0.0F),
				Vec3.createVectorHelper(5.0F, -1.0F, 0.0F),
		};
		
		tailAttachmentPoint = Vec3.createVectorHelper(0, -1, 8);
		
		leg1 = new ModelRenderer(base, 0, 16);
		leg1.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4);
		leg2 = new ModelRenderer(base, 0, 16);
		leg2.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4);
		leg3 = new ModelRenderer(base, 0, 16);
		leg3.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4);
		leg4 = new ModelRenderer(base, 0, 16);
		leg4.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4);

		body = new ModelRenderer(base, 28, 8);
		body.addBox(-5.0F, -9.0F, -8.0F, 10, 16, 8);

		head = new ModelRenderer(base, 0, 0);
		head.addBox(-4.0F, -4.0F, -8.0F, 8, 8, 8);
		
        head.setTextureOffset(16, 16).addBox(-2.0F, 0.0F, -9.0F, 4, 3, 1);
	}

	private void bindTexture() {
		super.bindTexture("/mob/pig.png");
	}


	@Override
	public void renderLegs(Entity entity, float legSwing, float prevLegSwing,
			float wingSwing, float yaw, float pitch, float scale, int bodyHeight, 
			Vec3[] legAttachmentPoints) {

		bindTexture();
		
		bindToAttachmentPoint(leg1, legAttachmentPoints[0], getLegHeight(), bodyHeight);
		bindToAttachmentPoint(leg2, legAttachmentPoints[1], getLegHeight(), bodyHeight);
		bindToAttachmentPoint(leg3, legAttachmentPoints[2], getLegHeight(), bodyHeight);
		bindToAttachmentPoint(leg4, legAttachmentPoints[3], getLegHeight(), bodyHeight);

		this.leg1.rotateAngleX = MathHelper.cos(legSwing * 0.6662F) * 1.4F * prevLegSwing;
		this.leg2.rotateAngleX = MathHelper.cos(legSwing * 0.6662F + (float) Math.PI) * 1.4F * prevLegSwing;
		this.leg3.rotateAngleX = MathHelper.cos(legSwing * 0.6662F + (float) Math.PI) * 1.4F * prevLegSwing;
		this.leg4.rotateAngleX = MathHelper.cos(legSwing * 0.6662F) * 1.4F * prevLegSwing;

		leg1.render(scale);
		leg2.render(scale);
		leg3.render(scale);
		leg4.render(scale);
	}

	@Override
	public void renderBody(Entity entity, float legSwing, float prevLegSwing,
			float wingSwing, float yaw, float pitch, float scale, int legHeight) {
		body.setRotationPoint(0, (float) 24 - legHeight - getBodyHeight(), 1.0F);
		bindTexture();
		body.rotateAngleX = ((float) Math.PI / 2F);
		body.render(scale);

	}

	@Override
	public void renderHead(Entity entity, float legSwing, float prevLegSwing,
			float wingSwing, float yaw, float pitch, float scale,
			int legHeight, int bodyHeight, Vec3 attachmentpoint) {
		
		this.bindToAttachmentPoint(this.head, attachmentpoint, legHeight, bodyHeight);
		
		bindTexture();
		head.rotateAngleX = pitch / (180F / (float) Math.PI);
		head.rotateAngleY = yaw / (180F / (float) Math.PI);
		head.render(scale);
	}

	@Override
	public int getTrait() {
		return Trait.ANIMAL_PIG;
	}

	@Override
	public int getLegHeight() {
		return 6;
	}

	@Override
	public int getNumberOfLegs() {
		return 4;
	}

	@Override
	public int getBodyHeight() {
		return 8;
	}

}
