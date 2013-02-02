package dnacraft.client.rendering.mobs;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import dnacraft.api.IMobDefinition;
import dnacraft.client.model.ModelMutant;

public class DefinitionOcelot extends BaseDefinition implements IMobDefinition {

	private ModelRenderer tail1;
	private ModelRenderer tail2;
	private ModelRenderer head;
	private ModelRenderer body;
	
	private ModelRenderer leg1;
	private ModelRenderer leg2;
	private ModelRenderer leg3;
	private ModelRenderer leg4;
	
	public DefinitionOcelot(ModelMutant base) {

		legAttachmentPoints2 = new Vec3[] {
				Vec3.createVectorHelper(3.0, -6.0F, 0.0),
				Vec3.createVectorHelper(-3.0, -6.0F, 0.0),
		};
		legAttachmentPoints4 = new Vec3[] {
				Vec3.createVectorHelper(1.0, -6.0F, 7.0),
				Vec3.createVectorHelper(-1.0, -6.0F, 7.0),
				Vec3.createVectorHelper(1.0, -6.0F, -5.0),
				Vec3.createVectorHelper(-1.0, -6.0F, -5.0),
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
		
		headAttachmentPoint = Vec3.createVectorHelper(0.0F, -1.0F, -8.0F);
		
		wingAttachmentPoints = new Vec3[] {
				Vec3.createVectorHelper(-2.0F, 0.0F, 0.0F),
				Vec3.createVectorHelper(2.0F, 0.0F, 0.0F),
		};
		
		armAttachmentPoints = new Vec3[] {
				Vec3.createVectorHelper(-2.0F, 0.0F, 0.0F),
				Vec3.createVectorHelper(2.0F, 0.0F, 0.0F),
		};
		
		tailAttachmentPoint = Vec3.createVectorHelper(0, -1, 8);
        base.setTextureOffset("head.main", 0, 0);
        base.setTextureOffset("head.nose", 0, 24);
        base.setTextureOffset("head.ear1", 0, 10);
        base.setTextureOffset("head.ear2", 6, 10);
        head = new ModelRenderer(base, "head");
        head.addBox("main", -2.5F, -2.0F, -3.0F, 5, 4, 5);
        head.addBox("nose", -1.5F, 0.0F, -4.0F, 3, 2, 2);
        head.addBox("ear1", -2.0F, -3.0F, 0.0F, 1, 1, 2);
        head.addBox("ear2", 1.0F, -3.0F, 0.0F, 1, 1, 2);
        
        tail1 = new ModelRenderer(base, 0, 15);
        tail1.addBox(-0.5F, 0.0F, 0.0F, 1, 8, 1);
        tail1.rotateAngleX = 0.9F;
        tail2 = new ModelRenderer(base, 4, 15);
        tail2.addBox(-0.5F, 0.0F, 0.0F, 1, 8, 1);
	
        body = new ModelRenderer(base, 20, 0);
        body.addBox(-2, -8, -6, 4, 16, 6);
        
        this.leg1 = new ModelRenderer(base, 8, 13);
        this.leg1.addBox(-1.0F, 0.0F, -1.0F, 2, 6, 2);
        
        this.leg2 = new ModelRenderer(base, 8, 13);
        this.leg2.addBox(-1.0F, 0.0F, -1.0F, 2, 6, 2);
        
        this.leg3 = new ModelRenderer(base, 8, 13);
        this.leg3.addBox(-1.0F, 0.0F, -0.0F, 2, 6, 2);
        
        this.leg4 = new ModelRenderer(base, 8, 13);
        this.leg4.addBox(-1.0F, 0.0F, -0.0F, 2, 6, 2);
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
		
		bindToAttachmentPoint(leg1, attachmentPoints[0], getLegHeight(), bodyHeight);
		bindToAttachmentPoint(leg2, attachmentPoints[1], getLegHeight(), bodyHeight);
		bindToAttachmentPoint(leg3, attachmentPoints[2], getLegHeight(), bodyHeight);
		bindToAttachmentPoint(leg4, attachmentPoints[3], getLegHeight(), bodyHeight);

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
	public void renderTail(Entity entity, float legSwing, float prevLegSwing,
			float wingSwing, float yaw, float pitch, float scale,
			int legHeight, int bodyHeight, Vec3 attachmentPoint) {
		tail2.setRotationPoint(
				(float) attachmentPoint.xCoord,
				(float) (24 - legHeight - bodyHeight - attachmentPoint.yCoord) + 5,
				(float) attachmentPoint.zCoord + 6
		);
		bindToAttachmentPoint(tail1, attachmentPoint, legHeight, bodyHeight);
		bindTexture();

        this.tail2.rotateAngleX = 1.7278761F + ((float)Math.PI / 4F) * MathHelper.cos(legSwing) * prevLegSwing;
		tail1.render(scale);
		tail2.render(scale);
		
	}
	
	@Override
	public String getName() {
		return "ocelot";
	}

	@Override
	public int getLegHeight() {
		return 6;
	}
	
	public int getBodyHeight() {
		return 6;
	}

	@Override
	public int getNumberOfLegs() {
		return 4;
	}

	private void bindTexture() {
		super.bindTexture("/mob/ozelot.png");
	}
	
}
