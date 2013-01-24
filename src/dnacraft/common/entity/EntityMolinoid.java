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
	private int heatDuration;
	private LoveStatus currentLoveStatus = LoveStatus.CHILLING_OUT;

	private int loveCycleTimeRemaining = 0;

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
		heatDuration = 200;

		loveCycleTimeRemaining = heatCycleFrequency;

	}

	@Override
	public int getMaxHealth() {
		return maxHealth;
	}

	@Override
	public void onLivingUpdate() {

		loveCycleTimeRemaining = this.getLoveCycleTimeRemaining();
		if (this.worldObj.isRemote) {
			System.out.println(loveCycleTimeRemaining);
		}
		if (loveCycleTimeRemaining <= 0) {
			if (currentLoveStatus == LoveStatus.CHILLING_OUT) {
				currentLoveStatus = LoveStatus.ON_HEAT;
				loveCycleTimeRemaining = heatDuration;
				onHeat();
			} else if (currentLoveStatus == LoveStatus.ON_HEAT) {
				currentLoveStatus = LoveStatus.CHILLING_OUT;
				loveCycleTimeRemaining = heatCycleFrequency;
				onChillout();
			}
		} else {
			loveCycleTimeRemaining--;
		}

		this.setLoveCycleTimeRemaining(loveCycleTimeRemaining);

		super.onLivingUpdate();
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		this.dataWatcher.addObject(15, new Integer(loveCycleTimeRemaining));
	}

	public int getLoveCycleTimeRemaining() {
		return this.dataWatcher.getWatchableObjectInt(15);
	}

	public void setLoveCycleTimeRemaining(int time) {
		this.dataWatcher.updateObject(15, Integer.valueOf(time));
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
