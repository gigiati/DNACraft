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
			new BodyPart(base, "/mob/spider.png", 18, 0, 16, 2, 2, -15.0F, -1.0F, -1.0F) {

				@Override
				public void setRotation(ModelRenderer renderer, Entity entity,
						float legSwing, float maxLegSwing, float wingSwing,
						float yaw, float pitch, float scale) {

			        float var10 = 0.3926991F;
			        float var11 = -(MathHelper.cos(legSwing * 0.6662F * 2.0F + 0.0F) * 0.4F) * maxLegSwing;
			        float var15 = Math.abs(MathHelper.sin(legSwing * 0.6662F + 0.0F) * 0.4F) * maxLegSwing;
					renderer.rotateAngleZ = -((float)Math.PI / 4F);
					renderer.rotateAngleY = var10 * 2.0F;
					renderer.rotateAngleY += var11;
					renderer.rotateAngleY += var15;
				}
			},
			new BodyPart(base, "/mob/spider.png", 18, 0, 16, 2, 2, -1.0F, -1.0F, -1.0F) {

				@Override
				public void setRotation(ModelRenderer renderer, Entity entity,
						float legSwing, float maxLegSwing, float wingSwing,
						float yaw, float pitch, float scale) {

			        float var10 = 0.3926991F;
			        float var11 = -(MathHelper.cos(legSwing * 0.6662F * 2.0F + 0.0F) * 0.4F) * maxLegSwing;
			        float var15 = Math.abs(MathHelper.sin(legSwing * 0.6662F + 0.0F) * 0.4F) * maxLegSwing;
					renderer.rotateAngleZ = ((float)Math.PI / 4F);
					renderer.rotateAngleY = -var10 * 2.0F;
					renderer.rotateAngleY += -var11;
					renderer.rotateAngleY += -var15;
				}
			},
			new BodyPart(base, "/mob/spider.png", 18, 0, 16, 2, 2, -15.0F, -1.0F, -1.0F) {

				@Override
				public void setRotation(ModelRenderer renderer, Entity entity,
						float legSwing, float maxLegSwing, float wingSwing,
						float yaw, float pitch, float scale) {

			        float var10 = 0.3926991F;
			        float var12 = -(MathHelper.cos(legSwing * 0.6662F * 2.0F + (float)Math.PI) * 0.4F) * maxLegSwing;
			        float var16 = Math.abs(MathHelper.sin(legSwing * 0.6662F + (float)Math.PI) * 0.4F) * maxLegSwing;
			        renderer.rotateAngleZ = -((float)Math.PI / 4F) * 0.74F;
					renderer.rotateAngleY = var10;
					renderer.rotateAngleY += var12;
					renderer.rotateAngleY += var16;
				}
			},
			new BodyPart(base, "/mob/spider.png", 18, 0, 16, 2, 2, -1.0F, -1.0F, -1.0F) {

				@Override
				public void setRotation(ModelRenderer renderer, Entity entity,
						float legSwing, float maxLegSwing, float wingSwing,
						float yaw, float pitch, float scale) {

			        float var10 = 0.3926991F;
			        float var12 = -(MathHelper.cos(legSwing * 0.6662F * 2.0F + (float)Math.PI) * 0.4F) * maxLegSwing;
			        float var16 = Math.abs(MathHelper.sin(legSwing * 0.6662F + (float)Math.PI) * 0.4F) * maxLegSwing;
			        renderer.rotateAngleZ = ((float)Math.PI / 4F) * 0.74F;
					renderer.rotateAngleY = -var10;
					renderer.rotateAngleY += -var12;
					renderer.rotateAngleY += -var16;
					
				}
			},
			new BodyPart(base, "/mob/spider.png", 18, 0, 16, 2, 2, -15.0F, -1.0F, -1.0F) {

				@Override
				public void setRotation(ModelRenderer renderer, Entity entity,
						float legSwing, float maxLegSwing, float wingSwing,
						float yaw, float pitch, float scale) {

			        float var10 = 0.3926991F;
			        float var13 = -(MathHelper.cos(legSwing * 0.6662F * 2.0F + ((float)Math.PI / 2F)) * 0.4F) * maxLegSwing;
			        float var17 = Math.abs(MathHelper.sin(legSwing * 0.6662F + ((float)Math.PI / 2F)) * 0.4F) * maxLegSwing;
			        renderer.rotateAngleZ = -((float)Math.PI / 4F) * 0.74F;
					renderer.rotateAngleY = -var10;
					renderer.rotateAngleY += var13;
					renderer.rotateAngleY += var17;
				}
			},
			new BodyPart(base, "/mob/spider.png", 18, 0, 16, 2, 2, -1.0F, -1.0F, -1.0F) {

				@Override
				public void setRotation(ModelRenderer renderer, Entity entity,
						float legSwing, float maxLegSwing, float wingSwing,
						float yaw, float pitch, float scale) {
			        float var10 = 0.3926991F;
			        float var13 = -(MathHelper.cos(legSwing * 0.6662F * 2.0F + ((float)Math.PI / 2F)) * 0.4F) * maxLegSwing;
			        float var17 = Math.abs(MathHelper.sin(legSwing * 0.6662F + ((float)Math.PI / 2F)) * 0.4F) * maxLegSwing;
			        renderer.rotateAngleZ = ((float)Math.PI / 4F) * 0.74F;
					renderer.rotateAngleY = var10;
					renderer.rotateAngleY += -var13;
					renderer.rotateAngleY += -var17;
				}
			},
			new BodyPart(base, "/mob/spider.png", 18, 0, 16, 2, 2, -15.0F, -1.0F, -1.0F) {

				@Override
				public void setRotation(ModelRenderer renderer, Entity entity,
						float legSwing, float maxLegSwing, float wingSwing,
						float yaw, float pitch, float scale) {
			        float var10 = 0.3926991F;
			        float var14 = -(MathHelper.cos(legSwing * 0.6662F * 2.0F + ((float)Math.PI * 3F / 2F)) * 0.4F) * maxLegSwing;
			        float var18 = Math.abs(MathHelper.sin(legSwing * 0.6662F + ((float)Math.PI * 3F / 2F)) * 0.4F) * maxLegSwing;
			        renderer.rotateAngleZ = -((float)Math.PI / 4F);
					renderer.rotateAngleY = -var10 * 2.0F;
					renderer.rotateAngleY += var14;
					renderer.rotateAngleY += var18;
				}
			},
			new BodyPart(base, "/mob/spider.png", 18, 0, 16, 2, 2, -1.0F, -1.0F, -1.0F) {

				@Override
				public void setRotation(ModelRenderer renderer, Entity entity,
						float legSwing, float maxLegSwing, float wingSwing,
						float yaw, float pitch, float scale) {
			        float var10 = 0.3926991F;
			        float var14 = -(MathHelper.cos(legSwing * 0.6662F * 2.0F + ((float)Math.PI * 3F / 2F)) * 0.4F) * maxLegSwing;
			        float var18 = Math.abs(MathHelper.sin(legSwing * 0.6662F + ((float)Math.PI * 3F / 2F)) * 0.4F) * maxLegSwing;
			        renderer.rotateAngleZ = ((float)Math.PI / 4F);
					renderer.rotateAngleY = var10 * 2.0F;
					renderer.rotateAngleY += -var14;
					renderer.rotateAngleY += -var18;
				}
			}
		
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

}
