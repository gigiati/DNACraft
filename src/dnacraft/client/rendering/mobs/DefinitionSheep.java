package dnacraft.client.rendering.mobs;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import dnacraft.api.IMobDefinition;
import dnacraft.common.evolution.Trait;

public class DefinitionSheep extends BaseDefinition implements IMobDefinition {

	private ModelRenderer head;
	private ModelRenderer head2;
	private ModelRenderer body;
	private ModelRenderer body2;
	private ModelRenderer leg1;
	private ModelRenderer leg2;
	private ModelRenderer leg3;
	private ModelRenderer leg4;
	
	private ModelRenderer leg1_2;
	private ModelRenderer leg2_2;
	private ModelRenderer leg3_2;
	private ModelRenderer leg4_2;

	public DefinitionSheep(ModelBase base) {

		legAttachmentPoints2 = new Vec3[] {
				Vec3.createVectorHelper(3.0, -6.0F, 0.0),
				Vec3.createVectorHelper(-3.0, -6.0F, 0.0),
		};
		legAttachmentPoints4 = new Vec3[] {
				Vec3.createVectorHelper(3.0, -6.0F, 7.0),
				Vec3.createVectorHelper(-3.0, -6.0F, 7.0),
				Vec3.createVectorHelper(3.0, -6.0F, -5.0),
				Vec3.createVectorHelper(-3.0, -6.0F, -5.0),
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
		
		headAttachmentPoint = Vec3.createVectorHelper(0.0F, -1.0F, -7.0F);
		
		wingAttachmentPoints = new Vec3[] {
				Vec3.createVectorHelper(-5.0F, -1.0F, 0.0F),
				Vec3.createVectorHelper(5.0F, -1.0F, 0.0F),
		};
		
		armAttachmentPoints = new Vec3[] {
				Vec3.createVectorHelper(-5.0F, -1.0F, 0.0F),
				Vec3.createVectorHelper(5.0F, -1.0F, 0.0F),
		};
		
		tailAttachmentPoint = Vec3.createVectorHelper(0, -1, 8);
		this.head = new ModelRenderer(base, 0, 0);
		this.head.addBox(-3.0F, -4.0F, -6.0F, 6, 6, 8, 0.0F);
        
		this.head2 = new ModelRenderer(base, 0, 0);
        this.head2.addBox(-3.0F, -4.0F, -4.0F, 6, 6, 6, 0.6F);

        this.body = new ModelRenderer(base, 28, 8);
        this.body.addBox(-4.0F, -8.0F, -6.0F, 8, 16, 6);

        this.body2 = new ModelRenderer(base, 28, 8);
        this.body2.addBox(-4.0F, -8.0F, -6.0F, 8, 16, 6, 1.75F);

        this.leg1 = new ModelRenderer(base, 0, 16);
        this.leg1.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4);
        this.leg2 = new ModelRenderer(base, 0, 16);
        this.leg2.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4);
        this.leg3 = new ModelRenderer(base, 0, 16);
        this.leg3.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4);
        this.leg4 = new ModelRenderer(base, 0, 16);
        this.leg4.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4);
        
        this.leg1_2 = new ModelRenderer(base, 0, 16);
        this.leg1_2.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4, 0.5F);
        this.leg2_2 = new ModelRenderer(base, 0, 16);
        this.leg2_2.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4, 0.5F);
        this.leg3_2 = new ModelRenderer(base, 0, 16);
        this.leg3_2.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4, 0.5F);
        this.leg4_2 = new ModelRenderer(base, 0, 16);
        this.leg4_2.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4, 0.5F);
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
		super.bindTexture("/mob/sheep_fur.png");
		this.bindToAttachmentPoint(this.head2, attachmentPoint, legHeight, bodyHeight);
		head2.rotateAngleX = head.rotateAngleX;
		head2.rotateAngleY = head.rotateAngleY;
		head2.render(scale);
	}

	@Override
	public void renderLegs(Entity entity, float legSwing, float prevLegSwing,
			float wingSwing, float yaw, float pitch, float scale,
			int bodyHeight, Vec3[] attachmentPoints) {

		bindTexture();
		
		bindToAttachmentPoint(leg1, attachmentPoints[0], getLegHeight(), bodyHeight);
		bindToAttachmentPoint(leg2, attachmentPoints[1], getLegHeight(), bodyHeight);
		bindToAttachmentPoint(leg3, attachmentPoints[2], getLegHeight(), bodyHeight);
		bindToAttachmentPoint(leg4, attachmentPoints[3], getLegHeight(), bodyHeight);

		this.leg1_2.rotationPointX = leg1.rotationPointX;
		this.leg1_2.rotationPointY = leg1.rotationPointY;
		this.leg1_2.rotationPointZ = leg1.rotationPointZ;
		
		this.leg2_2.rotationPointX = leg2.rotationPointX;
		this.leg2_2.rotationPointY = leg2.rotationPointY;
		this.leg2_2.rotationPointZ = leg2.rotationPointZ;
		
		this.leg3_2.rotationPointX = leg3.rotationPointX;
		this.leg3_2.rotationPointY = leg3.rotationPointY;
		this.leg3_2.rotationPointZ = leg3.rotationPointZ;

		this.leg4_2.rotationPointX = leg4.rotationPointX;
		this.leg4_2.rotationPointY = leg4.rotationPointY;
		this.leg4_2.rotationPointZ = leg4.rotationPointZ;

		this.leg1.rotateAngleX = MathHelper.cos(legSwing * 0.6662F) * 1.4F * prevLegSwing;
		this.leg2.rotateAngleX = MathHelper.cos(legSwing * 0.6662F + (float) Math.PI) * 1.4F * prevLegSwing;
		this.leg3.rotateAngleX = MathHelper.cos(legSwing * 0.6662F + (float) Math.PI) * 1.4F * prevLegSwing;
		this.leg4.rotateAngleX = MathHelper.cos(legSwing * 0.6662F) * 1.4F * prevLegSwing;
		this.leg1_2.rotateAngleX = this.leg1.rotateAngleX;
		this.leg2_2.rotateAngleX = this.leg2.rotateAngleX;
		this.leg3_2.rotateAngleX = this.leg3.rotateAngleX;
		this.leg4_2.rotateAngleX = this.leg4.rotateAngleX;
		leg1.render(scale);
		leg2.render(scale);
		leg3.render(scale);
		leg4.render(scale);
		super.bindTexture("/mob/sheep_fur.png");
		leg1_2.render(scale);
		leg2_2.render(scale);
		leg3_2.render(scale);
		leg4_2.render(scale);
	}

	@Override
	public void renderBody(Entity entity, float legSwing, float prevLegSwing,
			float wingSwing, float yaw, float pitch, float scale, int legHeight) {
		body.setRotationPoint(0, (float) 24 - legHeight - getBodyHeight(), 1.0F);
		bindTexture();
		body.rotateAngleX = ((float) Math.PI / 2F);
		body.render(scale);
		super.bindTexture("/mob/sheep_fur.png");
		body2.rotationPointX = body.rotationPointX;
		body2.rotationPointY = body.rotationPointY;
		body2.rotationPointZ = body.rotationPointZ;
		body2.rotateAngleX = body.rotateAngleX;
		body2.render(scale);
	}

	@Override
	public int getTrait() {
		return Trait.ANIMAL_SHEEP;
	}

	@Override
	public int getLegHeight() {
		return 12;
	}

	@Override
	public int getNumberOfLegs() {
		return 4;
	}
	
	public int getBodyHeight() {
		return 6;
	}

	private void bindTexture() {
		super.bindTexture("/mob/sheep.png");
	}

}
