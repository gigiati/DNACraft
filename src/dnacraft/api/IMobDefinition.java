package dnacraft.api;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import dnacraft.client.rendering.Body;
import dnacraft.client.rendering.BodyPart;

public interface IMobDefinition {
	public String getName();
	public BodyPart[] getLegs(ModelBase base);
	public float getAdditionalLegHeight();
	public Body getBody(ModelBase base);
	public BodyPart getHead(ModelBase base);
	public void setLegRotations(BodyPart[] parts, Entity entity, float legSwing,
						float maxLegSwing, float wingSwing, float yaw,
						float pitch, float scale);
}
