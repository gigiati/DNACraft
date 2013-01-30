package dnacraft.client.rendering.mobs;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
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
			new BodyPart(base, "/mob/pig.png", 28, 8, 10, 16, 8, 0.0F, 11.0F, 2.0F) {
				
				@Override
				public void setRotation(ModelRenderer renderer, Entity entity, float legSwing,
						float prevLegSwing, float wingSwing, float yaw,
						float pitch, float scale) {
					renderer.rotateAngleX = ((float)Math.PI / 2F);
				}
			},
			new BodyPart(base, "/mob/pig.png", 0, 16, 4, 6, 4, -2.0F, 0.0F, -2.0F) {
				@Override
				public void setRotation(ModelRenderer renderer, Entity entity, float legSwing,
						float prevLegSwing, float wingSwing, float yaw,
						float pitch, float scale) {
					// logic here
				}
			},
			new BodyPart(base, "/mob/pig.png", 0, 16, 4, 6, 4, -2.0F, 0.0F, -2.0F) {
				@Override
				public void setRotation(ModelRenderer renderer, Entity entity, float legSwing,
						float prevLegSwing, float wingSwing, float yaw,
						float pitch, float scale) {
					// logic here
				}
			},
			new BodyPart(base, "/mob/pig.png", 0, 16, 4, 6, 4, -2.0F, 0.0F, -2.0F) {
				@Override
				public void setRotation(ModelRenderer renderer, Entity entity, float legSwing,
						float prevLegSwing, float wingSwing, float yaw,
						float pitch, float scale) {
					// logic here
				}
			}
		};
	}
	
	public Body getBody(ModelBase base) {

		Body pigBody = new Body(base, "/mob/pig.png", 28, 8, 10, 16, 8, 0, -8.0F, 0) {

			@Override
			public void setRotation(ModelRenderer renderer, Entity entity, float legSwing,
					float prevLegSwing, float wingSwing, float yaw,
					float pitch, float scale) {
				//renderer.rotateAngleX = ((float)Math.PI / 2F);
			}
		};
		
		pigBody.setLegAttachmentPoints(new Vec3[] {
				Vec3.createVectorHelper(0.0, 0.0, 0.0),
				Vec3.createVectorHelper(0.0, 0.0, 0.0)
		});
		
		pigBody.setLegAttachmentPoints(new Vec3[] {
				Vec3.createVectorHelper(0.0, 0.0, 0.0),
				Vec3.createVectorHelper(0.0, 0.0, 0.0),
				Vec3.createVectorHelper(0.0, 0.0, 0.0),
				Vec3.createVectorHelper(0.0, 0.0, 0.0)
		});
		
		pigBody.setLegAttachmentPoints(new Vec3[] {
				Vec3.createVectorHelper(0.0, 0.0, 0.0),
				Vec3.createVectorHelper(0.0, 0.0, 0.0),
				Vec3.createVectorHelper(0.0, 0.0, 0.0),
				Vec3.createVectorHelper(0.0, 0.0, 0.0),
				Vec3.createVectorHelper(0.0, 0.0, 0.0),
				Vec3.createVectorHelper(0.0, 0.0, 0.0),
				Vec3.createVectorHelper(0.0, 0.0, 0.0),
				Vec3.createVectorHelper(0.0, 0.0, 0.0)
		});
		
		return pigBody;
	}

	@Override
	public String getName() {
		return "pig";
	}
}
