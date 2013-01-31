package dnacraft.client.rendering.mobs;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.RenderEngine;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import dnacraft.api.IMobDefinition;

public class DefinitionChicken implements IMobDefinition {

	private ModelRenderer head;
	private ModelRenderer bill;
	private ModelRenderer chin;
	
	private ModelRenderer body;
	private ModelRenderer rightLeg;
	private ModelRenderer leftLeg;
    public ModelRenderer rightWing;
    public ModelRenderer leftWing;

	private Vec3[] legAttachmentPoints2 = new Vec3[] {
			Vec3.createVectorHelper(-2.0, 0.0, 1.0),
			Vec3.createVectorHelper(1.0, 0.0, 1.0),
	};
	
	private Vec3[] legAttachmentPoints4 = new Vec3[] {
			Vec3.createVectorHelper(-1.0, 0.0, -2.0),
			Vec3.createVectorHelper(1.0, 0.0, -2.0),
			Vec3.createVectorHelper(-1.0, 0.0, 2.0),
			Vec3.createVectorHelper(1.0, 0.0, 2.0),
	};

	private Vec3[] legAttachmentPoints8 = new Vec3[] {
			Vec3.createVectorHelper(0.0, 3.0F, 0.0),
			Vec3.createVectorHelper(0.0, 3.0F, 0.0),
			Vec3.createVectorHelper(0.0, 3.0F, 0.0),
			Vec3.createVectorHelper(0.0, 3.0F, 0.0),
			Vec3.createVectorHelper(0.0, 3.0F, 0.0),
			Vec3.createVectorHelper(0.0, 3.0F, 0.0),
			Vec3.createVectorHelper(0.0, 3.0F, 0.0),
			Vec3.createVectorHelper(0.0, 3.0F, 0.0),
	};

	private Vec3[] wingAttachmentPoints = new Vec3[] {
			Vec3.createVectorHelper(-3.0F, 6.0F, 0.0F),
			Vec3.createVectorHelper(3.0F, 6.0F, 0.0F),
	};
	
	private Vec3 headAttachmentPoint = Vec3.createVectorHelper(0.0F, 5.0F, -4.0F);
	
	public DefinitionChicken(ModelBase base) {

        this.head = new ModelRenderer(base, 0, 0);
        this.head.addBox(-2.0F, -6.0F, -2.0F, 4, 6, 3, 0.0F);
        this.bill = new ModelRenderer(base, 14, 0);
        this.bill.addBox(-2.0F, -4.0F, -4.0F, 4, 2, 2, 0.0F);
        this.chin = new ModelRenderer(base, 14, 4);
        this.chin.addBox(-1.0F, -2.0F, -3.0F, 2, 2, 2, 0.0F);

        this.rightLeg = new ModelRenderer(base, 26, 0);
        this.rightLeg.addBox(-1.0F, 0.0F, -3.0F, 3, 5, 3);
        this.leftLeg = new ModelRenderer(base, 26, 0);
        this.leftLeg.addBox(-1.0F, 0.0F, -3.0F, 3, 5, 3);
        
        this.body = new ModelRenderer(base, 0, 9);
        this.body.addBox(-3.0F, 0, 0, 6, 8, 6, 0.0F);
        
        this.rightWing = new ModelRenderer(base, 24, 13);
        this.rightWing.addBox(0.0F, 0.0F, -3.0F, 1, 4, 6);
        this.leftWing = new ModelRenderer(base, 24, 13);
        this.leftWing.addBox(-1.0F, 0.0F, -3.0F, 1, 4, 6);
        
	}

	private void bindTexture() {
		RenderEngine renderEngine = Minecraft.getMinecraft().renderEngine;
		renderEngine.bindTexture(renderEngine.getTexture("/mob/chicken.png"));
	}
	
	@Override
	public void renderHead(Entity entity, float legSwing, float prevLegSwing,
			float wingSwing, float yaw, float pitch, float scale,
			int legHeight, Vec3 attachmentPoint) {

		if (attachmentPoint != null) {
			bindToAttachmentPoint(head, attachmentPoint, legHeight);
			bindToAttachmentPoint(chin, attachmentPoint, legHeight);
			bindToAttachmentPoint(bill, attachmentPoint, legHeight);
			
			bindTexture();
			this.head.rotateAngleX = pitch / (180F / (float)Math.PI);
	        this.head.rotateAngleY = yaw / (180F / (float)Math.PI);
	        this.bill.rotateAngleX = this.head.rotateAngleX;
	        this.bill.rotateAngleY = this.head.rotateAngleY;
	        this.chin.rotateAngleX = this.head.rotateAngleX;
	        this.chin.rotateAngleY = this.head.rotateAngleY;
			head.render(scale);
		}
		bill.render(scale);
		chin.render(scale);
	}
	
	private void setLegRotation(ModelRenderer leg, Vec3 point) {
		leg.setRotationPoint((float) point.xCoord,
				(float) (24 - getLegHeight() - point.yCoord),
				(float) point.zCoord);
	}
	
	@Override
	public void renderLegs(Entity entity, float legSwing, float prevLegSwing,
			float wingSwing, float yaw, float pitch, float scale,
			Vec3[] attachmentPoints) {
		
		if (attachmentPoints != null) {
			bindTexture();
			bindToAttachmentPoint(leftLeg, attachmentPoints[0], getLegHeight());
			bindToAttachmentPoint(rightLeg, attachmentPoints[1], getLegHeight());
			
			rightLeg.rotateAngleX = MathHelper.cos(legSwing * 0.6662F) * 1.4F * prevLegSwing;
			leftLeg.rotateAngleX = MathHelper.cos(legSwing * 0.6662F + (float)Math.PI) * 1.4F * prevLegSwing;
			
			leftLeg.render(scale);
			rightLeg.render(scale);
		}
		
	}

	@Override
	public void renderBody(Entity entity, float legSwing, float prevLegSwing,
			float wingSwing, float yaw, float pitch, float scale, int legHeight) {
		body.setRotationPoint(0, (float) 24 - legHeight, -(getBodyLength() / 2));
		body.rotateAngleX = ((float) Math.PI / 2F);
		bindTexture();
		body.render(scale);
	}

	@Override
	public void renderWings(Entity entity, float legSwing, float prevLegSwing,
			float wingSwing, float yaw, float pitch, float scale,
			int legHeight, Vec3[] attachmentPoints) {
		if (attachmentPoints != null) {
			bindTexture();
			bindToAttachmentPoint(this.leftWing, attachmentPoints[0], legHeight);
			bindToAttachmentPoint(this.rightWing, attachmentPoints[1], legHeight);
	
	        rightWing.rotateAngleZ = -wingSwing;
	        leftWing.rotateAngleZ = wingSwing;
			leftWing.render(scale);
			rightWing.render(scale);
		}
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
	public Vec3[] getWingAttachmentPoints() {
		return wingAttachmentPoints;
	}

	@Override
	public String getName() {
		return "chicken";
	}

	@Override
	public int getLegHeight() {
		return 5;
	}

	@Override
	public float getBodyLength() {
		return 8;
	}

	@Override
	public int getNumberOfLegs() {
		return 2;
	}
	
	private void bindToAttachmentPoint(ModelRenderer renderer, Vec3 attachmentPoint, float legHeight) {
		renderer.setRotationPoint(
				(float) attachmentPoint.xCoord,
				(float) (24 - legHeight - attachmentPoint.yCoord),
				(float) attachmentPoint.zCoord
		);
	}
}
