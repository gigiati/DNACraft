package dnacraft.common.entity;

import dnacraft.common.Genome;
import dnacraft.common.GenomeManager;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntityMolinoid extends EntityAnimal {

	private Genome genome;

	private int maxHealth = 20;

	private enum LoveStatus {
		CHILLING_OUT, ON_HEAT, IN_LOVE
	};

	private int heatCycleFrequency;
	private int heatCycleLength;
	private LoveStatus currentLoveStatus = LoveStatus.CHILLING_OUT;

	private int timeLeftUntilHeat = 0;
	private int timeLeftOnHeat = 0;

	public EntityMolinoid(World world) {
		super(world);
		this.texture = "/mob/pig.png";
		this.setSize(0.4F, 1.8F);
		setGenome(new Genome());
	}

	public EntityMolinoid(World world, Genome newGenome) {
		super(world);
		setGenome(newGenome);
	}

	public void setGenome(Genome g) {

		// keep a reference to our genome
		genome = g;

		// set some properties based on the new genome
		maxHealth = 1 + (int) (20 * genome.get(Genome.MAX_HEALTH));
		this.health = maxHealth;

		heatCycleFrequency = 100 + (int) (1000 * genome
				.get(Genome.HEAT_FREQUENCY));
		heatCycleLength = 200;
		timeLeftUntilHeat = heatCycleFrequency;

	}

	@Override
	public int getMaxHealth() {
		return maxHealth;
	}

	@Override
	public void onLivingUpdate() {

		if (!this.worldObj.isRemote) {
			if (currentLoveStatus == LoveStatus.CHILLING_OUT) {
				if (timeLeftUntilHeat <= 0) {
					currentLoveStatus = LoveStatus.ON_HEAT;
					timeLeftOnHeat = heatCycleLength;
					onHeat();
				} else {
					timeLeftUntilHeat--;
				}
			} else if (currentLoveStatus == LoveStatus.ON_HEAT) {
				// randomly look for targets. if we find one, change to IN_LOVE
				if (timeLeftOnHeat <= 0) {
					currentLoveStatus = LoveStatus.CHILLING_OUT;
					timeLeftUntilHeat = heatCycleFrequency;
					onChillout();
				} else {
					timeLeftOnHeat--;
				}
			}
		}

		super.onLivingUpdate();
	}

	private void onHeat() {
		System.out.println("On heat!");
	}

	private void onChillout() {
		System.out.println("Chilling out!");
	}

	private void onInLove() {

	}

	@Override
	public void writeEntityToNBT(NBTTagCompound tagCompound) {
		super.writeEntityToNBT(tagCompound);
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound tagCompound) {
		super.readEntityFromNBT(tagCompound);
	}

	public EntityAgeable createChild(EntityAgeable parent) {
		return this.spawnBabyAnimal((EntityMolinoid) parent);
	}

	public EntityAgeable spawnBabyAnimal(EntityMolinoid parent) {
		Genome newGenome = GenomeManager.mutate(this.getGenome(),
				parent.getGenome());
		return new EntityMolinoid(parent.worldObj, newGenome);
	}

	public Genome getGenome() {
		return genome;
	}

}
