package dnacraft.common.entity;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.world.World;

public class EntityMutant extends EntityAnimal  {

	public EntityMutant(World par1World) {
		super(par1World);
		// TODO Auto-generated constructor stub
	}

	@Override
	public EntityAgeable createChild(EntityAgeable var1) {
		return null;
	}

	@Override
	public int getMaxHealth() {
		return 30;
	}


}
