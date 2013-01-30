package dnacraft.api;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import dnacraft.client.rendering.Body;
import dnacraft.client.rendering.BodyPart;

public interface IMobDefinition {
	public String getName();
	public BodyPart[] getLegs(ModelBase base);
	public Body getBody(ModelBase base);
	public BodyPart getHead(ModelBase base);
}
