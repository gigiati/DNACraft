package dnacraft.client.rendering.mobs;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
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
	
	public BodyPart[] getLegs(ModelBase base) {

		return new BodyPart[] {
			new BodyPart(base, "/mob/pig.png", 0, 16, 4, 6, 4, -2.0F, 0.0F, -2.0F) {
				
				@Override
				public void setRotation(ModelRenderer renderer, Entity entity, float legSwing,
						float maxLegSwing, float wingSwing, float yaw,
						float pitch, float scale) {
					renderer.rotateAngleX = MathHelper.cos(legSwing * 0.6662F) * 1.4F * maxLegSwing;
				}
			},
			new BodyPart(base, "/mob/pig.png", 0, 16, 4, 6, 4, -2.0F, 0.0F, -2.0F) {
				@Override
				public void setRotation(ModelRenderer renderer, Entity entity, float legSwing,
						float maxLegSwing, float wingSwing, float yaw,
						float pitch, float scale) {
					renderer.rotateAngleX = MathHelper.cos(legSwing * 0.6662F + (float)Math.PI) * 1.4F * maxLegSwing;
				}
			},
			new BodyPart(base, "/mob/pig.png", 0, 16, 4, 6, 4, -2.0F, 0.0F, -2.0F) {
				@Override
				public void setRotation(ModelRenderer renderer, Entity entity, float legSwing,
						float maxLegSwing, float wingSwing, float yaw,
						float pitch, float scale) {
					renderer.rotateAngleX = MathHelper.cos(legSwing * 0.6662F + (float)Math.PI) * 1.4F * maxLegSwing;
				}
			},
			new BodyPart(base, "/mob/pig.png", 0, 16, 4, 6, 4, -2.0F, 0.0F, -2.0F) {
				@Override
				public void setRotation(ModelRenderer renderer, Entity entity, float legSwing,
						float maxLegSwing, float wingSwing, float yaw,
						float pitch, float scale) {
					renderer.rotateAngleX = MathHelper.cos(legSwing * 0.6662F) * 1.4F * maxLegSwing;
				}
			}
		};
	}
	
	public Body getBody(ModelBase base) {

		Body pigBody = new Body(base, "/mob/pig.png", 28, 8, 10, 16, 8, -5.0F, 0, 0) {

			@Override
			public void setRotation(ModelRenderer renderer, Entity entity, float legSwing,
					float prevLegSwing, float wingSwing, float yaw,
					float pitch, float scale) {
				renderer.rotateAngleX = ((float)Math.PI / 2F);
			}
		};
		
		pigBody.setLegAttachmentPoints(new Vec3[] {
				Vec3.createVectorHelper(3.0, 0.0, 0.0),
				Vec3.createVectorHelper(-3.0, 0.0, 0.0)
		});
		
		pigBody.setLegAttachmentPoints(new Vec3[] {
				Vec3.createVectorHelper(3.0, 0.0, 7.0),
				Vec3.createVectorHelper(-3.0, 0.0, 7.0),
				Vec3.createVectorHelper(3.0, 0.0, -5.0),
				Vec3.createVectorHelper(-3.0, 0.0, -5.0)
		});
		
		pigBody.setLegAttachmentPoints(new Vec3[] {
				Vec3.createVectorHelper(0.0, 3.0F, 0.0),
				Vec3.createVectorHelper(0.0, 3.0F, 0.0),
				Vec3.createVectorHelper(0.0, 3.0F, 0.0),
				Vec3.createVectorHelper(0.0, 3.0F, 0.0),
				Vec3.createVectorHelper(0.0, 3.0F, 0.0),
				Vec3.createVectorHelper(0.0, 3.0F, 0.0),
				Vec3.createVectorHelper(0.0, 3.0F, 0.0),
				Vec3.createVectorHelper(0.0, 3.0F, 0.0),
		});
		
		pigBody.setHeadAttachmentPoint(Vec3.createVectorHelper(0.0F, 6.0F, -6.0F));
		
		return pigBody;
	}

	@Override
	public String getName() {
		return "pig";
	}

	@Override
	public BodyPart getHead(ModelBase base) {
		return new BodyPart(base, "/mob/pig.png", 0, 0, 8, 8, 8, -4.0F, -4.0F, -8.0F) {
			@Override
			public void setRotation(ModelRenderer renderer, Entity entity, float legSwing,
					float maxLegSwing, float wingSwing, float yaw,
					float pitch, float scale) {
		        renderer.rotateAngleX = pitch / (180F / (float)Math.PI);
		        renderer.rotateAngleY = yaw / (180F / (float)Math.PI);
			}
		};
	}

	@Override
	public float getAdditionalLegHeight() {
		return 0;
	}
}
