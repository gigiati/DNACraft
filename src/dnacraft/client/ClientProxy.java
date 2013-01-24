package dnacraft.client;

import net.minecraft.client.model.ModelCow;
import cpw.mods.fml.client.registry.RenderingRegistry;
import dnacraft.client.model.ModelMolinoid;
import dnacraft.client.renderer.entity.RenderMolinoid;
import dnacraft.common.CommonProxy;
import dnacraft.common.entity.EntityMolinoid;

public class ClientProxy extends CommonProxy {
	@Override
	public void registerRenderInformation() {
		RenderingRegistry.registerEntityRenderingHandler(EntityMolinoid.class,
				new RenderMolinoid(new ModelMolinoid(), 0.7F));
	}
}
