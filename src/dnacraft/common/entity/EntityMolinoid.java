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

	private int heatCycleFrequency;
	private int heatDuration;

	private int loveCycleTimeRemaining = 0;
	
	public static final int ME_NO_LOVE = 0;
	public static final int ON_HEAT = 1;
	public static final int IN_LOVE = 2;
	
	public EntityMolinoid(World world) {
		super(world);
		
		this.texture = "/mob/pig.png";
		this.setSize(0.9F, 0.9F);
		float speed = 0.23F;

		this.getNavigator().setAvoidsWater(true);

		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(1, new EntityAIPanic(this, 0.38F));
		this.tasks.addTask(2, new EntityAISelectiveMate(this, speed));
		this.tasks.addTask(3, new EntityAIWander(this, speed));
		this.tasks.addTask(4, new EntityAIWatchClosest(this,
				EntityPlayer.class, 6.0F));
		this.tasks.addTask(5, new EntityAILookIdle(this));
		
		this.genome = new Genome();
		
	}

	@Override
	protected boolean isAIEnabled() {
		return true;
	}

	@Override
	protected void updateAITasks() {
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
		System.out.println("maxHealth = "+ maxHealth);
		return maxHealth == 0 ? 20 : maxHealth;
	}

	@Override
	public void onLivingUpdate() {
		if (!this.worldObj.isRemote && !this.isMale()) {
			
			int currentLoveStatus = this.getLoveCycleState();
		
			if (loveCycleTimeRemaining <= 0) {
				if (currentLoveStatus == ME_NO_LOVE) {
					this.setLoveCycleState(ON_HEAT);
					loveCycleTimeRemaining = heatDuration;
				} else if (currentLoveStatus == ON_HEAT) {
					this.setLoveCycleState(ME_NO_LOVE);
					loveCycleTimeRemaining = heatCycleFrequency;
				}
			} else {
				loveCycleTimeRemaining--;
			}
		}
		
		super.onLivingUpdate();

	}

	@Override
	protected void entityInit() {
		super.entityInit();
		this.dataWatcher.addObject(15, new Byte((byte)0));
        this.dataWatcher.addObject(16, new Byte((byte)0));
        if (!this.worldObj.isRemote) {
        	this.setMale(Math.random() < 0.5);
        }
	}

	public int getLoveCycleState() {
		return this.dataWatcher.getWatchableObjectByte(15);
	}

	public void setLoveCycleState(int state) {
		this.dataWatcher.updateObject(15, Byte.valueOf((byte)state));
	}
	
    public boolean isMale()
    {
		return (this.dataWatcher.getWatchableObjectByte(16) & 16) != 0;
    }

    public void setMale(boolean par1)
    {
        byte var2 = this.dataWatcher.getWatchableObjectByte(16);

        if (par1)
        {
            this.dataWatcher.updateObject(16, Byte.valueOf((byte)(var2 | 16)));
        }
        else
        {
            this.dataWatcher.updateObject(16, Byte.valueOf((byte)(var2 & -17)));
        }
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
		EntityMolinoid baby = new EntityMolinoid(parent.worldObj);
		Genome newGenome = GenomeManager.mutate(this.getGenome(),
				parent.getGenome());
		baby.setGenome(newGenome);
		return baby;
	}

	public Genome getGenome() {
		return genome;
	}


	@Override
	public boolean canMateWith(EntityAnimal otherMob) {
		
		if (otherMob == this) return false;
		
		EntityMolinoid otherMolinoid = (EntityMolinoid) otherMob;

		// males dont wanna mate with males!
		if (this.isMale())
			return false;
		
		// not mating unless im on heat!
		if (this.getLoveCycleState() != ON_HEAT)
			return false;


		return isAttractedTo(otherMolinoid);
	}

	private boolean isAttractedTo(EntityMolinoid otherMob) {
		return true;
	}

}
