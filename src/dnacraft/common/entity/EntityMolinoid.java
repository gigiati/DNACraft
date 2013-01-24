package dnacraft.common.entity;

import dnacraft.common.Genome;
import dnacraft.common.GenomeManager;
import dnacraft.common.entity.ai.EntityAISelectiveMate;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntityMolinoid extends EntityAnimal {

	private Genome genome;

	private int maxHealth = 20;

	public enum LoveStatus {
		CHILLING_OUT, ON_HEAT, IN_LOVE
	};

	private int heatCycleFrequency;
	private int heatDuration;
	private LoveStatus currentLoveStatus = LoveStatus.CHILLING_OUT;

	private int loveCycleTimeRemaining = 0;
	
	private boolean male = false;

	public EntityMolinoid(World world) {
		this(world, new Genome());
	}

	public EntityMolinoid(World world, Genome newGenome) {
		super(world);
		
		setGenome(newGenome);
        
		this.texture = "/mob/pig.png";
        this.setSize(0.9F, 0.9F);
		float speed = 0.23F;

        this.getNavigator().setAvoidsWater(true);
        
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new EntityAIPanic(this, 0.38F));
        this.tasks.addTask(2, new EntityAISelectiveMate(this, speed));
        this.tasks.addTask(3, new EntityAIWander(this, speed));
        this.tasks.addTask(4, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        this.tasks.addTask(5, new EntityAILookIdle(this));
        
	}

	@Override
    protected boolean isAIEnabled()
    {
        return true;
    }

	@Override
    protected void updateAITasks()
    {
        super.updateAITasks();
    }
    
	public void setGenome(Genome g) {

		// keep a reference to our genome
		genome = g;

		// set some properties based on the new genome
		maxHealth = 1 + (int) (40 * genome.get(Genome.MAX_HEALTH));
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


		if (!this.isMale()) {
			
			loveCycleTimeRemaining = this.getLoveCycleTimeRemaining();
			
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
	
			if (!this.worldObj.isRemote) {
				this.setLoveCycleTimeRemaining(loveCycleTimeRemaining);
			}
		}
		super.onLivingUpdate();
		
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		if (!this.isMale()) {
			this.dataWatcher.addObject(15, new Integer(loveCycleTimeRemaining));
		}
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
	
	public LoveStatus getLoveStatus() {
		return this.currentLoveStatus;
	}
	
	public boolean isMale() {
		return male;
	}
	
	@Override
    public boolean canMateWith(EntityAnimal otherMob)
    {
		EntityMolinoid otherMolinoid = (EntityMolinoid) otherMob;
		
		// males dont wanna mate with males!
		if (this.isMale()) return false;
		
		// not mating unless im on heat!
		if (this.getLoveStatus() != LoveStatus.ON_HEAT) return false;

		return isAttractedTo(otherMolinoid);
    }
	
	private boolean isAttractedTo(EntityMolinoid otherMob) {
		return true;
	}

}
