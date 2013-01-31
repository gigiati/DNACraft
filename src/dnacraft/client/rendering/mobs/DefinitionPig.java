package dnacraft.client.rendering.mobs;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.RenderEngine;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import cpw.mods.fml.client.registry.RenderingRegistry;
import dnacraft.api.IMobDefinition;
import dnacraft.client.model.ModelMutant;
import dnacraft.client.renderer.entity.RenderMutant;
import dnacraft.client.rendering.Body;
import dnacraft.client.rendering.BodyPart;
import dnacraft.common.entity.EntityMutant;

public class DefinitionPig implements IMobDefinition {

	private ModelRenderer leg1;
	private ModelRenderer leg2;
	private ModelRenderer leg3;
	private ModelRenderer leg4;

	private ModelRenderer body;

	private ModelRenderer head;

	private Vec3[] legAttachmentPoints2 = new Vec3[] {
			Vec3.createVectorHelper(3.0, 0.0, 0.0),
			Vec3.createVectorHelper(-3.0, 0.0, 0.0), };
	private Vec3[] legAttachmentPoints4 = new Vec3[] {
			Vec3.createVectorHelper(3.0, 0.0, 7.0),
			Vec3.createVectorHelper(-3.0, 0.0, 7.0),
			Vec3.createVectorHelper(3.0, 0.0, -5.0),
			Vec3.createVectorHelper(-3.0, 0.0, -5.0), };

	private Vec3[] legAttachmentPoints8 = new Vec3[] {
			Vec3.createVectorHelper(0.0, 3.0F, 0.0),
			Vec3.createVectorHelper(0.0, 3.0F, 0.0),
			Vec3.createVectorHelper(0.0, 3.0F, 0.0),
			Vec3.createVectorHelper(0.0, 3.0F, 0.0),
			Vec3.createVectorHelper(0.0, 3.0F, 0.0),
			Vec3.createVectorHelper(0.0, 3.0F, 0.0),
			Vec3.createVectorHelper(0.0, 3.0F, 0.0),
			Vec3.createVectorHelper(0.0, 3.0F, 0.0), };

	private Vec3 headAttachmentPoint = Vec3.createVectorHelper(0.0F, 6.0F,
			-6.0F);

	public DefinitionPig(ModelBase base) {
		leg1 = new ModelRenderer(base, 0, 16);
		leg1.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4);
		leg2 = new ModelRenderer(base, 0, 16);
		leg2.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4);
		leg3 = new ModelRenderer(base, 0, 16);
		leg3.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4);
		leg4 = new ModelRenderer(base, 0, 16);
		leg4.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4);

		body = new ModelRenderer(base, 28, 8);
		body.addBox(-5.0F, 0, 0, 10, 16, 8);

		head = new ModelRenderer(base, 0, 0);
		head.addBox(-4.0F, -4.0F, -8.0F, 8, 8, 8);
	}

	private void bindTexture() {
		RenderEngine renderEngine = Minecraft.getMinecraft().renderEngine;
		renderEngine.bindTexture(renderEngine.getTexture("/mob/pig.png"));
	}

	private void setLegRotation(ModelRenderer leg, Vec3 point) {
		leg.setRotationPoint((float) point.xCoord,
				(float) (24 - getLegHeight() - point.yCoord),
				(float) point.zCoord);
	}

	@Override
	public void renderLegs(Entity entity, float legSwing, float prevLegSwing,
			float wingSwing, float yaw, float pitch, float scale,
			Vec3[] legAttachmentPoints) {

		setLegRotation(leg1, legAttachmentPoints[0]);
		setLegRotation(leg2, legAttachmentPoints[1]);
		setLegRotation(leg3, legAttachmentPoints[2]);
		setLegRotation(leg4, legAttachmentPoints[3]);

		this.leg1.rotateAngleX = MathHelper.cos(legSwing * 0.6662F) * 1.4F
				* prevLegSwing;
		this.leg2.rotateAngleX = MathHelper.cos(legSwing * 0.6662F
				+ (float) Math.PI)
				* 1.4F * prevLegSwing;
		this.leg3.rotateAngleX = MathHelper.cos(legSwing * 0.6662F
				+ (float) Math.PI)
				* 1.4F * prevLegSwing;
		this.leg4.rotateAngleX = MathHelper.cos(legSwing * 0.6662F) * 1.4F
				* prevLegSwing;

		leg1.render(scale);
		leg2.render(scale);
		leg3.render(scale);
		leg4.render(scale);
	}

	@Override
	public void renderBody(Entity entity, float legSwing, float prevLegSwing,
			float wingSwing, float yaw, float pitch, float scale, int legHeight) {
		body.setRotationPoint(0, (float) 24 - legHeight, -(getBodyLength() / 2));
		bindTexture();
		body.rotateAngleX = ((float) Math.PI / 2F);
		body.render(scale);

	}

	@Override
	public void renderHead(Entity entity, float legSwing, float prevLegSwing,
			float wingSwing, float yaw, float pitch, float scale,
			int legHeight, Vec3 headAttachmentPoint) {
		head.setRotationPoint((float) headAttachmentPoint.xCoord,
				(float) (24 - legHeight - headAttachmentPoint.yCoord),
				(float) headAttachmentPoint.zCoord);
		bindTexture();
		head.rotateAngleX = pitch / (180F / (float) Math.PI);
		head.rotateAngleY = yaw / (180F / (float) Math.PI);
		head.render(scale);
	}

	@Override
	public Vec3[] getLegAttachmentPoints(int numLegs) {
		switch (numLegs) {
		case 2:
			return this.legAttachmentPoints2;
		case 4:
			return this.legAttachmentPoints4;
		case 8:
			return this.legAttachmentPoints8;
		}
		return null;
	}

	@Override
	public Vec3 getHeadAttachmentPoint() {
		return this.headAttachmentPoint;
	}

	@Override
	public String getName() {
		return "pig";
	}

	@Override
	public int getLegHeight() {
		return 6;
	}

	@Override
	public float getBodyLength() {
		return 16;
	}

	@Override
	public int getNumberOfLegs() {
		return 4;
	}

}
