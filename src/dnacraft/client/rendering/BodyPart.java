package dnacraft.client.rendering;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class BodyPart {

	protected String texture;
	protected int textureOffsetX;
	protected int textureOffsetY;
	protected int width;
	protected int height;
	protected int depth;
	protected float rotPointX;
	protected float rotPointY;
	protected float rotPointZ;
	private ModelRenderer renderer;

	public BodyPart(ModelBase base, String texture, int textureOffsetX,
			int textureOffsetY, int width, int height, int depth,
			float rotPointX, float rotPointY, float rotPointZ) {
		this.renderer = new ModelRenderer(base, textureOffsetX, textureOffsetY);
		this.renderer.addBox(rotPointX, rotPointY, rotPointZ, width, height,
				depth);
		this.height = height;
		this.width = width;
		this.depth = depth;
		this.texture = texture;

	}

	public String getTexture() {
		return this.texture;
	}

	public ModelRenderer getRenderer() {
		return this.renderer;
	}

	public int getTextureOffsetX() {
		return textureOffsetX;
	}

	public int getTextureOffsetY() {
		return textureOffsetY;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int getDepth() {
		return depth;
	}

	public float getRotationPointX() {
		return rotPointX;
	}

	public float getRotationPointY() {
		return rotPointY;
	}

	public float getRotationPointZ() {
		return rotPointZ;
	}

	public void setRotation(ModelRenderer renderer, Entity entity,
			float legSwing, float prevLegSwing, float wingSwing, float yaw,
			float pitch, float scale) {

	}
}
