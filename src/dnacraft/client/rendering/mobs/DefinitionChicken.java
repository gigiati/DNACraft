package dnacraft.client.rendering.mobs;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import dnacraft.api.IMobDefinition;
import dnacraft.client.rendering.Body;
import dnacraft.client.rendering.BodyPart;

public class DefinitionChicken implements IMobDefinition {

	@Override
	public String getName() {
		return "chicken";
	}

	@Override
	public BodyPart[] getLegs(ModelBase base) {
		
		return new BodyPart[] {
			new BodyPart(base, "/mob/chicken.png", 26, 0, 3, 5, 3, -1.0F, 0.0F, -3.0F) {
				
				@Override
				public void setRotation(ModelRenderer renderer, Entity entity, float legSwing,
						float maxLegSwing, float wingSwing, float yaw,
						float pitch, float scale) {
					renderer.rotateAngleX = MathHelper.cos(legSwing * 0.6662F) * 1.4F * maxLegSwing;
				}
			},
			new BodyPart(base, "/mob/chicken.png", 26, 0, 3, 5, 3, -1.0F, 0.0F, -3.0F) {
				@Override
				public void setRotation(ModelRenderer renderer, Entity entity, float legSwing,
						float maxLegSwing, float wingSwing, float yaw,
						float pitch, float scale) {
					renderer.rotateAngleX = MathHelper.cos(legSwing * 0.6662F + (float)Math.PI) * 1.4F * maxLegSwing;
				}
			}
		};
	}

	@Override
	public Body getBody(ModelBase base) {
		return null;
	}

	@Override
	public BodyPart getHead(ModelBase base) {
		// TODO Auto-generated method stub
		return null;
	}

}
