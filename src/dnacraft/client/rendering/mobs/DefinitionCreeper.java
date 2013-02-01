package dnacraft.client.rendering.mobs;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import dnacraft.api.IMobDefinition;

public class DefinitionCreeper extends BaseDefinition implements IMobDefinition {

	private ModelRenderer body;
	private ModelRenderer leg1;
	private ModelRenderer leg2;
	private ModelRenderer leg3;
	private ModelRenderer leg4;
	private ModelRenderer head;
	
	
	public DefinitionCreeper(ModelBase base) {
		

		legAttachmentPoints2 = new Vec3[] {
				Vec3.createVectorHelper(2.0F, -12.0F, 0.0F),
				Vec3.createVectorHelper(-2.0F, -12.0F, 0.0F),
		};
		
		legAttachmentPoints4 = new Vec3[] {
				Vec3.createVectorHelper(2.0F, -12.0F, 4.0F),
				Vec3.createVectorHelper(-2.0F, -12.0F, 4.0F),
				Vec3.createVectorHelper(2.0F, -12.0F, -4.0F),
				Vec3.createVectorHelper(-2.0F, -12.0F, -4.0F),
		};

		legAttachmentPoints8 = new Vec3[] {
				Vec3.createVectorHelper(0.0, -9.0F, 0.0),
				Vec3.createVectorHelper(0.0, -9.0F, 0.0),
				Vec3.createVectorHelper(0.0, -9.0F, 0.0),
				Vec3.createVectorHelper(0.0, -9.0F, 0.0),
				Vec3.createVectorHelper(0.0, -9.0F, 0.0),
				Vec3.createVectorHelper(0.0, -9.0F, 0.0),
				Vec3.createVectorHelper(0.0, -9.0F, 0.0),
				Vec3.createVectorHelper(0.0, -9.0F, 0.0),
		};

		headAttachmentPoint = Vec3.createVectorHelper(0, 0, 0);
		
		wingAttachmentPoints = new Vec3[] {
				Vec3.createVectorHelper(-4.0F, -1.0F, 0.0F),
				Vec3.createVectorHelper(4.0F, -1.0F, 0.0F),
		};

		tailAttachmentPoint = Vec3.createVectorHelper(0, -9, 3);
		
        this.head = new ModelRenderer(base, 0, 0);
        this.head.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8);
        
        this.body = new ModelRenderer(base, 16, 16);
        this.body.addBox(-4.0F, 0, -2.0F, 8, 12, 4);
        
        this.leg1 = new ModelRenderer(base, 0, 16);
        this.leg1.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4);
        
        this.leg2 = new ModelRenderer(base, 0, 16);
        this.leg2.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4);
        
        this.leg3 = new ModelRenderer(base, 0, 16);
        this.leg3.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4);
        
        this.leg4 = new ModelRenderer(base, 0, 16);
        this.leg4.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4);
		
	}
	
	@Override
	public void renderHead(Entity entity, float legSwing, float prevLegSwing,
			float wingSwing, float yaw, float pitch, float scale,
			int legHeight, int bodyHeight, Vec3 attachmentPoint) {
		bindToAttachmentPoint(head, attachmentPoint, legHeight, bodyHeight);
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

        leg1.rotateAngleX = MathHelper.cos(legSwing * 0.6662F) * 1.4F * prevLegSwing;
        leg2.rotateAngleX = MathHelper.cos(legSwing * 0.6662F + (float)Math.PI) * 1.4F * prevLegSwing;
        leg3.rotateAngleX = MathHelper.cos(legSwing * 0.6662F + (float)Math.PI) * 1.4F * prevLegSwing;
        leg4.rotateAngleX = MathHelper.cos(legSwing * 0.6662F) * 1.4F * prevLegSwing;
		leg1.render(scale);
		leg2.render(scale);
		leg3.render(scale);
		leg4.render(scale);
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
		return "creeper";
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
		return 12;
	}
	
	private void bindTexture() {
		super.bindTexture("/mob/creeper.png");
	}

	@Override
	public void renderTail(Entity entity, float legSwing, float prevLegSwing,
			float wingSwing, float yaw, float pitch, float scale,
			int legHeight, int bodyHeight, Vec3 attachmentPoint) {
		// TODO Auto-generated method stub
		
	}
}
