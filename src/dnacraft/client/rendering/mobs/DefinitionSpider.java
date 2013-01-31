package dnacraft.client.rendering.mobs;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
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
		
		headAttachmentPoint = Vec3.createVectorHelper(0.0F, -4.0F, -6.0F);
		
		wingAttachmentPoints = new Vec3[] {
				Vec3.createVectorHelper(-5.0F, -1.0F, 0.0F),
				Vec3.createVectorHelper(5.0F, -1.0F, 0.0F),
		};

		head = new ModelRenderer(base, 32, 4);
        head.addBox(-4.0F, -4.0F, -8.0F, 8, 8, 8);
		
		neck = new ModelRenderer(base, 0, 0);
		neck.addBox(-3.0F, 3.0F, 3.0F, 6, 6, 6);
		
        body = new ModelRenderer(base, 0, 12);
		body.addBox(-5.0F, 0, -6.0F, 10, 8, 12);
		
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

	@Override
	public void renderWings(Entity entity, float legSwing, float prevLegSwing,
			float wingSwing, float yaw, float pitch, float scale,
			int legHeight, int bodyHeight, Vec3[] attachmentPoints) {
		// TODO Auto-generated method stub
		
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
		return 10;
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
