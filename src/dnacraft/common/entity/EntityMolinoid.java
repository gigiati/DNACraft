package dnacraft.common.entity;

import dnacraft.common.Genome;
import net.minecraft.entity.monster.EntityGolem;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntityMolinoid extends EntityGolem {

	private Genome genome;
	
	private int maxHealth = 20;

	public EntityMolinoid(World world) {
		super(world);
		this.texture = "/mob/pig.png";
		this.setSize(0.4F, 1.8F);
		setGenome(new Genome());
	}

	private void setGenome(Genome g) {
		
		// keep a reference to our genome
		genome = g;
		
		// set some properties based on the new genome
		maxHealth = (int)(20 * genome.get(Genome.MAX_HEALTH));
		this.health = maxHealth;
	}

	@Override
	public int getMaxHealth() {
        return maxHealth;
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound tagCompound) {
		super.writeEntityToNBT(tagCompound);
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound tagCompound) {
		super.readEntityFromNBT(tagCompound);
	}

}
