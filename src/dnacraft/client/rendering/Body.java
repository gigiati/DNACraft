package dnacraft.client.rendering;

import java.util.HashMap;

import net.minecraft.client.model.ModelBase;
import net.minecraft.util.Vec3;

public class Body extends BodyPart {

	protected Vec3 headAttachmentPoint;
	protected HashMap<Integer, Vec3[]> legAttachmentPoints = new HashMap<Integer, Vec3[]>();
	protected Vec3[] wingAttachmentPoints;
	protected Vec3[] armAttachmentPoints;

	public Body(ModelBase base, String texture, int textureOffsetX,
			int textureOffsetY, int width, int height, int depth,
			float rotPointX, float rotPointY, float rotPointZ) {
		super(base, texture, textureOffsetX, textureOffsetY, width, height,
				depth, rotPointX, rotPointY, rotPointZ);
	}

	public void setLegAttachmentPoints(Vec3[] points) {
		legAttachmentPoints.put(points.length, points);
	}

	public Vec3[] getLegAttachmentPoints(int forNumLegs) {
		return legAttachmentPoints.get(forNumLegs);
	}
	
	public void setHeadAttachmentPoint(Vec3 point) {
		headAttachmentPoint = point;
	}
	
	public Vec3 getHeadAttachmentPoint() {
		return headAttachmentPoint;
	}

}
