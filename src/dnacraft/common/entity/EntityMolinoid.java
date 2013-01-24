package dnacraft.common.entity;

import net.minecraft.entity.monster.EntityGolem;
import net.minecraft.world.World;


public class EntityMolinoid extends EntityGolem {

	public EntityMolinoid(World world) {
		super(world);
        this.texture = "/mob/snowman.png";
        this.setSize(0.4F, 1.8F);
	}

	@Override
	public int getMaxHealth() {
		return 4;
	}

}
