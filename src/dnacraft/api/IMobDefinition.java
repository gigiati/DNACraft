package dnacraft.api;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.Vec3;
import dnacraft.client.rendering.Body;
import dnacraft.client.rendering.BodyPart;

public interface IMobDefinition {
	public void renderHead(Entity entity, float legSwing,
			float prevLegSwing, float wingSwing, float yaw,
			float pitch, float scale, int legHeight, Vec3 headAttachmentPoint);
	public void renderLegs(Entity entity, float legSwing,
			float prevLegSwing, float wingSwing, float yaw,
			float pitch, float scale, Vec3[] legAttachmentPoints);
	public void renderBody(Entity entity, float legSwing,
			float prevLegSwing, float wingSwing, float yaw,
			float pitch, float scale, int legHeight);
	public Vec3[] getLegAttachmentPoints(int numLegs);
	public Vec3 getHeadAttachmentPoint();
	public String getName();
	public int getLegHeight();
	public float getBodyLength();
	public int getNumberOfLegs();
}
