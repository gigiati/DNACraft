package dnacraft.client.rendering.mobs;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import dnacraft.api.IMobDefinition;
import dnacraft.client.rendering.Body;
import dnacraft.client.rendering.BodyPart;

public class DefinitionSpider implements IMobDefinition {

	@Override
	public String getName() {
		return "spider";
	}

	@Override
	public BodyPart[] getLegs(ModelBase base) {
		return new BodyPart[] {
			new BodyPart(base, "/mob/spider.png", 18, 0, 16, 2, 2, -15.0F, -1.0F, -1.0F),
			new BodyPart(base, "/mob/spider.png", 18, 0, 16, 2, 2, -1.0F, -1.0F, -1.0F),
			new BodyPart(base, "/mob/spider.png", 18, 0, 16, 2, 2, -15.0F, -1.0F, -1.0F),
			new BodyPart(base, "/mob/spider.png", 18, 0, 16, 2, 2, -1.0F, -1.0F, -1.0F),
			new BodyPart(base, "/mob/spider.png", 18, 0, 16, 2, 2, -15.0F, -1.0F, -1.0F),
			new BodyPart(base, "/mob/spider.png", 18, 0, 16, 2, 2, -1.0F, -1.0F, -1.0F),
			new BodyPart(base, "/mob/spider.png", 18, 0, 16, 2, 2, -15.0F, -1.0F, -1.0F),
			new BodyPart(base, "/mob/spider.png", 18, 0, 16, 2, 2, -1.0F, -1.0F, -1.0F)
		
		};

	}

	@Override
	public Body getBody(ModelBase base) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BodyPart getHead(ModelBase base) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public float getAdditionalLegHeight() {
		return 6.0F;
	}

	@Override
	public void setLegRotations(BodyPart[] parts, Entity entity,
			float legSwing, float maxLegSwing, float wingSwing, float yaw,
			float pitch, float scale) {
		ModelRenderer spiderLeg1 = parts[0].getRenderer();
		ModelRenderer spiderLeg2 = parts[1].getRenderer();
		ModelRenderer spiderLeg3 = parts[2].getRenderer();
		ModelRenderer spiderLeg4 = parts[3].getRenderer();
		ModelRenderer spiderLeg5 = parts[4].getRenderer();
		ModelRenderer spiderLeg6 = parts[5].getRenderer();
		ModelRenderer spiderLeg7 = parts[6].getRenderer();
		ModelRenderer spiderLeg8 = parts[7].getRenderer();

        float var8 = ((float)Math.PI / 4F);
        spiderLeg1.rotateAngleZ = -var8;
        spiderLeg2.rotateAngleZ = var8;
        spiderLeg3.rotateAngleZ = -var8 * 0.74F;
        spiderLeg4.rotateAngleZ = var8 * 0.74F;
        spiderLeg5.rotateAngleZ = -var8 * 0.74F;
        spiderLeg6.rotateAngleZ = var8 * 0.74F;
        spiderLeg7.rotateAngleZ = -var8;
        spiderLeg8.rotateAngleZ = var8;
        float var9 = -0.0F;
        float var10 = 0.3926991F;
        spiderLeg1.rotateAngleY = var10 * 2.0F + var9;
        spiderLeg2.rotateAngleY = -var10 * 2.0F - var9;
        spiderLeg3.rotateAngleY = var10 * 1.0F + var9;
        spiderLeg4.rotateAngleY = -var10 * 1.0F - var9;
        spiderLeg5.rotateAngleY = -var10 * 1.0F + var9;
        spiderLeg6.rotateAngleY = var10 * 1.0F - var9;
        spiderLeg7.rotateAngleY = -var10 * 2.0F + var9;
        spiderLeg8.rotateAngleY = var10 * 2.0F - var9;
        float var11 = -(MathHelper.cos(legSwing * 0.6662F * 2.0F + 0.0F) * 0.4F) * maxLegSwing;
        float var12 = -(MathHelper.cos(legSwing * 0.6662F * 2.0F + (float)Math.PI) * 0.4F) * maxLegSwing;
        float var13 = -(MathHelper.cos(legSwing * 0.6662F * 2.0F + ((float)Math.PI / 2F)) * 0.4F) * maxLegSwing;
        float var14 = -(MathHelper.cos(legSwing * 0.6662F * 2.0F + ((float)Math.PI * 3F / 2F)) * 0.4F) * maxLegSwing;
        float var15 = Math.abs(MathHelper.sin(legSwing * 0.6662F + 0.0F) * 0.4F) * maxLegSwing;
        float var16 = Math.abs(MathHelper.sin(legSwing * 0.6662F + (float)Math.PI) * 0.4F) * maxLegSwing;
        float var17 = Math.abs(MathHelper.sin(legSwing * 0.6662F + ((float)Math.PI / 2F)) * 0.4F) * maxLegSwing;
        float var18 = Math.abs(MathHelper.sin(legSwing * 0.6662F + ((float)Math.PI * 3F / 2F)) * 0.4F) * maxLegSwing;
        spiderLeg1.rotateAngleY += var11;
        spiderLeg2.rotateAngleY += -var11;
        spiderLeg3.rotateAngleY += var12;
        spiderLeg4.rotateAngleY += -var12;
        spiderLeg5.rotateAngleY += var13;
        spiderLeg6.rotateAngleY += -var13;
        spiderLeg7.rotateAngleY += var14;
        spiderLeg8.rotateAngleY += -var14;
        spiderLeg1.rotateAngleZ += var15;
        spiderLeg2.rotateAngleZ += -var15;
        spiderLeg3.rotateAngleZ += var16;
        spiderLeg4.rotateAngleZ += -var16;
        spiderLeg5.rotateAngleZ += var17;
        spiderLeg6.rotateAngleZ += -var17;
        spiderLeg7.rotateAngleZ += var18;
        spiderLeg8.rotateAngleZ += -var18;
		
	}

}
