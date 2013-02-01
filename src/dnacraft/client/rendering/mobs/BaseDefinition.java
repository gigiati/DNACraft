package dnacraft.client.rendering.mobs;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.RenderEngine;
import net.minecraft.util.Vec3;

public abstract class BaseDefinition {

	protected Vec3[] legAttachmentPoints2;
	protected Vec3[] legAttachmentPoints4;
	protected Vec3[] legAttachmentPoints8;
	protected Vec3 headAttachmentPoint;
	protected Vec3[] wingAttachmentPoints;
	protected Vec3 tailAttachmentPoint;
	
	protected void bindTexture(String texture) {
		RenderEngine renderEngine = Minecraft.getMinecraft().renderEngine;
		renderEngine.bindTexture(renderEngine.getTexture(texture));
	}
	
	public int getBodyHeight() {
		return 0;
	}
	
	public Vec3[] getLegAttachmentPoints(int numLegs) {
		switch (numLegs) {
		case 2:
			return this.legAttachmentPoints2;
		case 4:
			return this.legAttachmentPoints4;
		case 8:
			return this.legAttachmentPoints8;
		}
		return null;
	}
	
	public Vec3[] getWingAttachmentPoints() {
		return this.wingAttachmentPoints;
	}

	public Vec3 getHeadAttachmentPoint() {
		return this.headAttachmentPoint;
	}
	
	public Vec3 getTailAttachmentPoint() {
		return this.tailAttachmentPoint;
	}
	
	protected void bindToAttachmentPoint(ModelRenderer renderer, Vec3 attachmentPoint, int legHeight, int bodyHeight) {
		renderer.setRotationPoint(
				(float) attachmentPoint.xCoord,
				(float) (24 - legHeight - bodyHeight - attachmentPoint.yCoord),
				(float) attachmentPoint.zCoord
		);
	}
}
