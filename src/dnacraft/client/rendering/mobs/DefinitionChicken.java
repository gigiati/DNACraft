package dnacraft.client.rendering.mobs;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import dnacraft.api.IMobDefinition;
import dnacraft.common.evolution.Trait;

public class DefinitionChicken extends BaseDefinition implements IMobDefinition {

	private ModelRenderer head;
	private ModelRenderer bill;
	private ModelRenderer chin;
	
	private ModelRenderer body;
	private ModelRenderer rightLeg;
	private ModelRenderer leftLeg;
    public ModelRenderer rightWing;
    public ModelRenderer leftWing;

	
	public DefinitionChicken(ModelBase base) {
		
		legAttachmentPoints2 = new Vec3[] {
				Vec3.createVectorHelper(-2.0, -6.0F, 1.0),
				Vec3.createVectorHelper(1.0, -6.0F, 1.0),
		};

		legAttachmentPoints4 = new Vec3[] {
				Vec3.createVectorHelper(-1.0, -6.0, -2.0),
				Vec3.createVectorHelper(1.0, -6.0, -2.0),
				Vec3.createVectorHelper(-1.0, -6.0, 2.0),
				Vec3.createVectorHelper(1.0, -6.0, 2.0),
		};
		
		legAttachmentPoints8 = new Vec3[] {
				Vec3.createVectorHelper(0.0, -3.0F, 0.0),
				Vec3.createVectorHelper(0.0, -3.0F, 0.0),
				Vec3.createVectorHelper(0.0, -3.0F, 0.0),
				Vec3.createVectorHelper(0.0, -3.0F, 0.0),
				Vec3.createVectorHelper(0.0, -3.0F, 0.0),
				Vec3.createVectorHelper(0.0, -3.0F, 0.0),
				Vec3.createVectorHelper(0.0, -3.0F, 0.0),
				Vec3.createVectorHelper(0.0, -3.0F, 0.0),
		};
		
		
		wingAttachmentPoints = new Vec3[] {
				Vec3.createVectorHelper(-3.0F, 0.0F, 0.0F),
				Vec3.createVectorHelper(3.0F, 0.0F, 0.0F),
		};
		
		armAttachmentPoints = new Vec3[] {
				Vec3.createVectorHelper(-3.0F, -2.0F, 0.0F),
				Vec3.createVectorHelper(3.0F, -2.0F, 0.0F),
		};
		
		headAttachmentPoint = Vec3.createVectorHelper(0.0F, -2.0F, -4.0F);
		
		tailAttachmentPoint = Vec3.createVectorHelper(0, -1, 4);
		
        this.head = new ModelRenderer(base, 0, 0);
        this.head.addBox(-2.0F, -6.0F, -2.0F, 4, 6, 3);
        this.bill = new ModelRenderer(base, 14, 0);
        this.bill.addBox(-2.0F, -4.0F, -4.0F, 4, 2, 2);
        this.chin = new ModelRenderer(base, 14, 4);
        this.chin.addBox(-1.0F, -2.0F, -3.0F, 2, 2, 2);

        this.rightLeg = new ModelRenderer(base, 26, 0);
        this.rightLeg.addBox(-1.0F, 0.0F, -3.0F, 3, 5, 3);
        this.leftLeg = new ModelRenderer(base, 26, 0);
        this.leftLeg.addBox(-1.0F, 0.0F, -3.0F, 3, 5, 3);
        
        this.body = new ModelRenderer(base, 0, 9);
        this.body.addBox(-3.0F, -4.0F, -6.0F, 6, 8, 6);
        
        this.rightWing = new ModelRenderer(base, 24, 13);
        this.rightWing.addBox(0.0F, 0.0F, -3.0F, 1, 4, 6);
        this.leftWing = new ModelRenderer(base, 24, 13);
        this.leftWing.addBox(-1.0F, 0.0F, -3.0F, 1, 4, 6);
        
	}

	private void bindTexture() {
		super.bindTexture("/mob/chicken.png");
	}
	
	@Override
	public void renderHead(Entity entity, float legSwing, float prevLegSwing,
			float wingSwing, float yaw, float pitch, float scale,
			int legHeight, int bodyHeight, Vec3 attachmentPoint) {

		bindToAttachmentPoint(head, attachmentPoint, legHeight, bodyHeight);
		bindToAttachmentPoint(chin, attachmentPoint, legHeight, bodyHeight);
		bindToAttachmentPoint(bill, attachmentPoint, legHeight, bodyHeight);
		
		bindTexture();
		this.head.rotateAngleX = pitch / (180F / (float)Math.PI);
        this.head.rotateAngleY = yaw / (180F / (float)Math.PI);
        this.bill.rotateAngleX = this.head.rotateAngleX;
        this.bill.rotateAngleY = this.head.rotateAngleY;
        this.chin.rotateAngleX = this.head.rotateAngleX;
        this.chin.rotateAngleY = this.head.rotateAngleY;
		head.render(scale);
		bill.render(scale);
		chin.render(scale);
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
		
		leftLeg.render(scale);
		rightLeg.render(scale);
		
	}

	@Override
	public void renderBody(Entity entity, float legSwing, float prevLegSwing,
			float wingSwing, float yaw, float pitch, float scale, int legHeight) {
		body.setRotationPoint(0, (float) 24 - legHeight - getBodyHeight(), 0);
		body.rotateAngleX = ((float) Math.PI / 2F);
		bindTexture();
		body.render(scale);
	}

	@Override
	public void renderWings(Entity entity, float legSwing, float prevLegSwing,
			float wingSwing, float yaw, float pitch, float scale,
			int legHeight, int bodyHeight, Vec3[] attachmentPoints) {
		bindTexture();
		bindToAttachmentPoint(this.leftWing, attachmentPoints[0], legHeight, bodyHeight);
		bindToAttachmentPoint(this.rightWing, attachmentPoints[1], legHeight, bodyHeight);

        rightWing.rotateAngleZ = -wingSwing;
        leftWing.rotateAngleZ = wingSwing;
		leftWing.render(scale);
		rightWing.render(scale);
	}

	@Override
	public int getTrait() {
		return Trait.ANIMAL_CHICKEN;
	}

	@Override
	public int getLegHeight() {
		return 5;
	}

	@Override
	public int getNumberOfLegs() {
		return 2;
	}
	
	@Override
	public int getBodyHeight() {
		return 6;
	}
}
