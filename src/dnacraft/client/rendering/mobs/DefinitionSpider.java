package dnacraft.client.rendering.mobs;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import dnacraft.api.IMobDefinition;

public class DefinitionSpider extends BaseDefinition implements IMobDefinition {

    public ModelRenderer head;
    public ModelRenderer neck;
    public ModelRenderer body;
    public ModelRenderer leg1;
    public ModelRenderer leg2;
    public ModelRenderer leg3;
    public ModelRenderer leg4;
    public ModelRenderer leg5;
    public ModelRenderer leg6;
    public ModelRenderer leg7;
    public ModelRenderer leg8;
    
    
	public DefinitionSpider(ModelBase base) {
		
		legAttachmentPoints2 = new Vec3[] {
				Vec3.createVectorHelper(-2.0F, -8.0F, 7.0F),
				Vec3.createVectorHelper(2.0F, -8.0F, 7.0F),
		};
		legAttachmentPoints4 = new Vec3[] {
				Vec3.createVectorHelper(3.0, -8.0F, 9.0),
				Vec3.createVectorHelper(-3.0, -8.0F, 9.0),
				Vec3.createVectorHelper(3.0, -8.0F, 2.0),
				Vec3.createVectorHelper(-3.0, -8.0F, 2.0),
		};
		legAttachmentPoints8 = new Vec3[] {
				Vec3.createVectorHelper(-4.0, -3.0F, -1.0),
				Vec3.createVectorHelper(4.0, -3.0F, -1.0),
				Vec3.createVectorHelper(-4.0, -3.0F, -1.0),
				Vec3.createVectorHelper(4.0, -3.0F, -1.0),
				Vec3.createVectorHelper(-4.0, -3.0F, -1.0),
				Vec3.createVectorHelper(4.0, -3.0F, -1.0),
				Vec3.createVectorHelper(-4.0, -3.0F, -1.0),
				Vec3.createVectorHelper(4.0, -3.0F, -1.0),
		};
		
		headAttachmentPoint = Vec3.createVectorHelper(0.0F, -4.0F, -6.0F);
		
		wingAttachmentPoints = new Vec3[] {
				Vec3.createVectorHelper(-5.0F, -1.0F, 7.0F),
				Vec3.createVectorHelper(5.0F, -1.0F, 7.0F),
		};
		
		armAttachmentPoints = new Vec3[] {
				Vec3.createVectorHelper(-5.0F, -1.0F, 7.0F),
				Vec3.createVectorHelper(5.0F, -1.0F, 7.0F),
		};
		
		tailAttachmentPoint = Vec3.createVectorHelper(0, -2, 10);

		head = new ModelRenderer(base, 32, 4);
        head.addBox(-4.0F, -4.0F, -8.0F, 8, 8, 8);
		
		neck = new ModelRenderer(base, 0, 0);
		neck.addBox(-3.0F, 3.0F, 3.0F, 6, 6, 6);
		
        body = new ModelRenderer(base, 0, 12);
		body.addBox(-5.0F, 0, -6.0F, 10, 8, 12);

        leg1 = new ModelRenderer(base, 18, 0);
		leg1.addBox(-15.0F, -1.0F, -1.0F, 16, 2, 2);
        leg2 = new ModelRenderer(base, 18, 0);
		leg2.addBox(-1.0F, -1.0F, -1.0F, 16, 2, 2);
        leg3 = new ModelRenderer(base, 18, 0);
		leg3.addBox(-15.0F, -1.0F, -1.0F, 16, 2, 2);
        leg4 = new ModelRenderer(base, 18, 0);
		leg4.addBox(-1.0F, -1.0F, -1.0F, 16, 2, 2);
        leg5 = new ModelRenderer(base, 18, 0);
		leg5.addBox(-15.0F, -1.0F, -1.0F, 16, 2, 2);
        leg6 = new ModelRenderer(base, 18, 0);
		leg6.addBox(-1.0F, -1.0F, -1.0F, 16, 2, 2);
        leg7 = new ModelRenderer(base, 18, 0);
		leg7.addBox(-15.0F, -1.0F, -1.0F, 16, 2, 2);
        leg8 = new ModelRenderer(base, 18, 0);
		leg8.addBox(-1.0F, -1.0F, -1.0F, 16, 2, 2);
		
	}
	
	@Override
	public void renderHead(Entity entity, float legSwing, float prevLegSwing,
			float wingSwing, float yaw, float pitch, float scale,
			int legHeight, int bodyHeight, Vec3 attachmentPoint) {

		this.bindToAttachmentPoint(this.head, attachmentPoint, legHeight, bodyHeight);
		
		bindTexture();
		head.rotateAngleX = pitch / (180F / (float) Math.PI);
		head.rotateAngleY = yaw / (180F / (float) Math.PI);
		head.render(scale);
	}

	@Override
	public void renderLegs(Entity entity, float legSwing, float prevLegSwing,
			float wingSwing, float yaw, float pitch, float scale,
			int bodyHeight, Vec3[] attachmentPoints) {

		bindTexture();

        float var8 = ((float)Math.PI / 4F);
        this.leg1.rotateAngleZ = -var8;
        this.leg2.rotateAngleZ = var8;
        this.leg3.rotateAngleZ = -var8 * 0.74F;
        this.leg4.rotateAngleZ = var8 * 0.74F;
        this.leg5.rotateAngleZ = -var8 * 0.74F;
        this.leg6.rotateAngleZ = var8 * 0.74F;
        this.leg7.rotateAngleZ = -var8;
        this.leg8.rotateAngleZ = var8;
        float var9 = -0.0F;
        float var10 = 0.3926991F;
        this.leg1.rotateAngleY = var10 * 2.0F + var9;
        this.leg2.rotateAngleY = -var10 * 2.0F - var9;
        this.leg3.rotateAngleY = var10 * 1.0F + var9;
        this.leg4.rotateAngleY = -var10 * 1.0F - var9;
        this.leg5.rotateAngleY = -var10 * 1.0F + var9;
        this.leg6.rotateAngleY = var10 * 1.0F - var9;
        this.leg7.rotateAngleY = -var10 * 2.0F + var9;
        this.leg8.rotateAngleY = var10 * 2.0F - var9;
        float var11 = -(MathHelper.cos(legSwing * 0.6662F * 2.0F + 0.0F) * 0.4F) * prevLegSwing;
        float var12 = -(MathHelper.cos(legSwing * 0.6662F * 2.0F + (float)Math.PI) * 0.4F) * prevLegSwing;
        float var13 = -(MathHelper.cos(legSwing * 0.6662F * 2.0F + ((float)Math.PI / 2F)) * 0.4F) * prevLegSwing;
        float var14 = -(MathHelper.cos(legSwing * 0.6662F * 2.0F + ((float)Math.PI * 3F / 2F)) * 0.4F) * prevLegSwing;
        float var15 = Math.abs(MathHelper.sin(legSwing * 0.6662F + 0.0F) * 0.4F) * prevLegSwing;
        float var16 = Math.abs(MathHelper.sin(legSwing * 0.6662F + (float)Math.PI) * 0.4F) * prevLegSwing;
        float var17 = Math.abs(MathHelper.sin(legSwing * 0.6662F + ((float)Math.PI / 2F)) * 0.4F) * prevLegSwing;
        float var18 = Math.abs(MathHelper.sin(legSwing * 0.6662F + ((float)Math.PI * 3F / 2F)) * 0.4F) * prevLegSwing;
        this.leg1.rotateAngleY += var11;
        this.leg2.rotateAngleY += -var11;
        this.leg3.rotateAngleY += var12;
        this.leg4.rotateAngleY += -var12;
        this.leg5.rotateAngleY += var13;
        this.leg6.rotateAngleY += -var13;
        this.leg7.rotateAngleY += var14;
        this.leg8.rotateAngleY += -var14;
        this.leg1.rotateAngleZ += var15;
        this.leg2.rotateAngleZ += -var15;
        this.leg3.rotateAngleZ += var16;
        this.leg4.rotateAngleZ += -var16;
        this.leg5.rotateAngleZ += var17;
        this.leg6.rotateAngleZ += -var17;
        this.leg7.rotateAngleZ += var18;
        this.leg8.rotateAngleZ += -var18;
		bindToAttachmentPoint(leg1, attachmentPoints[0], getLegHeight(), bodyHeight);
		bindToAttachmentPoint(leg2, attachmentPoints[1], getLegHeight(), bodyHeight);
		bindToAttachmentPoint(leg3, attachmentPoints[2], getLegHeight(), bodyHeight);
		bindToAttachmentPoint(leg4, attachmentPoints[3], getLegHeight(), bodyHeight);
		bindToAttachmentPoint(leg5, attachmentPoints[4], getLegHeight(), bodyHeight);
		bindToAttachmentPoint(leg6, attachmentPoints[5], getLegHeight(), bodyHeight);
		bindToAttachmentPoint(leg7, attachmentPoints[6], getLegHeight(), bodyHeight);
		bindToAttachmentPoint(leg8, attachmentPoints[7], getLegHeight(), bodyHeight);

		leg1.render(scale);
		leg2.render(scale);
		leg3.render(scale);
		leg4.render(scale);
		leg5.render(scale);
		leg6.render(scale);
		leg7.render(scale);
		leg8.render(scale);
	}

	@Override
	public void renderBody(Entity entity, float legSwing, float prevLegSwing,
			float wingSwing, float yaw, float pitch, float scale, int legHeight) {
		float h = (float) 24 - legHeight - getBodyHeight();
		body.setRotationPoint(0, h, 6);
		neck.setRotationPoint(0, h - 1, -9);
		bindTexture();
		body.render(scale);
		neck.render(scale);
	}

	private void bindTexture() {
		super.bindTexture("/mob/spider.png");
	}
	
	@Override
	public String getName() {
		return "spider";
	}

	@Override
	public int getLegHeight() {
		return 6;
	}

	@Override
	public int getNumberOfLegs() {
		return 8;
	}
	
	@Override
	public int getBodyHeight() {
		return 8;
	}

}
