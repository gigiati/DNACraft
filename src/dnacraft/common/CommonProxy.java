package dnacraft.common;

import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import dnacraft.DNACraft;
import dnacraft.common.entity.EntityMolinoid;

public class CommonProxy {
	
	public void init() {
		
		EntityRegistry.registerGlobalEntityID(EntityMolinoid.class, "Molinoid", EntityRegistry.findGlobalUniqueEntityId(), 24, 30 );
		EntityRegistry.registerModEntity(EntityMolinoid.class, "Molinoid", 6, DNACraft.instance, 128, 1, true);
		LanguageRegistry.instance().addStringLocalization("entity.molinoid.name", "en_US", "Molinoid Egg");;


	}
	
	public void registerRenderInformation()
	{
		
	}
}
