package dnacraft.common.entity;

import java.io.DataOutput;
import java.io.IOException;
import java.util.HashMap;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;

import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import dnacraft.common.entity.ai.EntityAIMutantSwell;
import dnacraft.common.evolution.TraitManager;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentThorns;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAICreeperSwell;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIOwnerHurtByTarget;
import net.minecraft.entity.ai.EntityAIOwnerHurtTarget;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITargetNonTamed;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityMutant extends EntityAnimal implements
		IEntityAdditionalSpawnData {

	public HashMap<String, Double> genome = new HashMap<String, Double>();

	/* wings stuff */
	public float field_70886_e = 0.0F;
	public float destPos = 0.0F;
	public float field_70884_g;
	public float field_70888_h;
	public float field_70889_i = 1.0F;

	/* model/generics stuff */
	public String head;
	private String legs;
	private String body;
	private String arms;
	private String wings;
	private String tail;
	public NBTTagCompound dna = null;
	
	/* creeper stuff */
    private int timeSinceIgnited;
    private int fuseTime = 30;
    private int lastActiveTime;
    private int explosionRadius = 3;

	public EntityMutant(World world) {
		super(world);
        this.setSize(0.9F, 1.3F);
        this.getNavigator().setAvoidsWater(true);
        int i = 0;
        this.moveSpeed = 0.3F;
        
        this.tasks.addTask(i++, new EntityAISwimming(this));
        //this.tasks.addTask(i++, new EntityAIMutantSwell(this));
        //this.tasks.addTask(i++, new EntityAIAttackOnCollide(this, EntityLiving.class, this.moveSpeed, false));
        this.tasks.addTask(i++, new EntityAIPanic(this, 0.38F));
        this.tasks.addTask(i++, new EntityAIWander(this, this.moveSpeed));
        //this.tasks.addTask(i++, new EntityAILeapAtTarget(this, 0.3F));
        this.tasks.addTask(i++, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        this.tasks.addTask(i++, new EntityAILookIdle(this));

        //this.targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityLiving.class, 16.0F, 0, true));
        //this.targetTasks.addTask(2, new EntityAIHurtByTarget(this, true));
	}
	
	public void setDNAFromTagCompound(NBTTagCompound tagCompound) {
		if (tagCompound != null && tagCompound.hasKey("traits")) {
			this.dna = tagCompound.getCompoundTag("traits");
			System.out.println("We have DNA");
		}else {
		System.out.println("We have no DNA");
		}
		this.head = TraitManager.instance.getBodyPartFromDNA(this.dna);
        this.body = TraitManager.instance.getBodyPartFromDNA(this.dna);
        this.legs = TraitManager.instance.getBodyPartFromDNA(this.dna);
        this.tail = TraitManager.instance.getBodyPartFromDNA(this.dna);
        this.wings = TraitManager.instance.getBodyPartFromDNA(this.dna);
        this.arms = TraitManager.instance.getBodyPartFromDNA(this.dna);
        if (this.arms != this.body) {
        	this.arms = "pig";
        }
	}
	
	public void setDNAFromItemStack(ItemStack stack) {
		NBTTagCompound dna = null;
		if (stack.hasTagCompound()) {
			dna = stack.getTagCompound();
		}
		setDNAFromTagCompound(dna);
        
	}

	@Override
	public EntityAgeable createChild(EntityAgeable var1) {
		return null;
	}

    public boolean isAIEnabled()
    {
        return true;
    }
    
    public boolean attackEntityAsMob(Entity par1Entity)
    {
        int var2 = 2;

        if (this.isPotionActive(Potion.damageBoost))
        {
            var2 += 3 << this.getActivePotionEffect(Potion.damageBoost).getAmplifier();
        }

        if (this.isPotionActive(Potion.weakness))
        {
            var2 -= 2 << this.getActivePotionEffect(Potion.weakness).getAmplifier();
        }

        int var3 = 0;

        if (par1Entity instanceof EntityLiving)
        {
            var2 += EnchantmentHelper.getEnchantmentModifierLiving(this, (EntityLiving)par1Entity);
            var3 += EnchantmentHelper.getKnockbackModifier(this, (EntityLiving)par1Entity);
        }

        boolean var4 = par1Entity.attackEntityFrom(DamageSource.causeMobDamage(this), var2);

        if (var4)
        {
            if (var3 > 0)
            {
                par1Entity.addVelocity((double)(-MathHelper.sin(this.rotationYaw * (float)Math.PI / 180.0F) * (float)var3 * 0.5F), 0.1D, (double)(MathHelper.cos(this.rotationYaw * (float)Math.PI / 180.0F) * (float)var3 * 0.5F));
                this.motionX *= 0.6D;
                this.motionZ *= 0.6D;
            }

            int var5 = EnchantmentHelper.getFireAspectModifier(this);

            if (var5 > 0)
            {
                par1Entity.setFire(var5 * 4);
            }

            if (par1Entity instanceof EntityLiving)
            {
                EnchantmentThorns.func_92044_a(this, (EntityLiving)par1Entity, this.rand);
            }
        }

        return var4;
    }
    
	@Override
	public void initCreature() {
	}

	@Override
	public int getMaxHealth() {
		return 30;
	}
	
	@Override
	public void onUpdate() {
		this.handleCreeperBehaviour();
		super.onUpdate();
	}

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();
		this.handleWings();
	}

	
	
    /**
     * Returns the current state of creeper, -1 is idle, 1 is 'in fuse'
     */
    public int getCreeperState()
    {
        return this.dataWatcher.getWatchableObjectByte(16);
    }

    /**
     * Returns true if the creeper is powered by a lightning bolt.
     */
    public boolean getPowered()
    {
        return this.dataWatcher.getWatchableObjectByte(17) == 1;
    }
    /**
     * Sets the state of creeper, -1 to idle and 1 to be 'in fuse'
     */
    public void setCreeperState(int par1)
    {
        this.dataWatcher.updateObject(16, Byte.valueOf((byte)par1));
    }
    private void handleCreeperBehaviour() {
    	if (this.isEntityAlive())
        {
            this.lastActiveTime = this.timeSinceIgnited;
            int var1 = this.getCreeperState();

            if (var1 > 0 && this.timeSinceIgnited == 0)
            {
                this.playSound("random.fuse", 1.0F, 0.5F);
            }

            this.timeSinceIgnited += var1;

            if (this.timeSinceIgnited < 0)
            {
                this.timeSinceIgnited = 0;
            }

            if (this.timeSinceIgnited >= this.fuseTime)
            {
                this.timeSinceIgnited = this.fuseTime;

                if (!this.worldObj.isRemote)
                {
                    boolean var2 = this.worldObj.getGameRules().getGameRuleBooleanValue("mobGriefing");

                    if (this.getPowered())
                    {
                        this.worldObj.createExplosion(this, this.posX, this.posY, this.posZ, (float)(this.explosionRadius * 2), var2);
                    }
                    else
                    {
                        this.worldObj.createExplosion(this, this.posX, this.posY, this.posZ, (float)this.explosionRadius, var2);
                    }

                    this.setDead();
                }
            }
        }
	}

	protected void entityInit()
    {
        super.entityInit();
        this.dataWatcher.addObject(16, Byte.valueOf((byte) - 1));
        this.dataWatcher.addObject(17, Byte.valueOf((byte)0));
    }
    
	public void handleWings() {
		this.field_70888_h = this.field_70886_e;
		this.field_70884_g = this.destPos;
		this.destPos = (float) ((double) this.destPos + (double) (this.onGround ? -1
				: 4) * 0.3D);

		if (this.destPos < 0.0F) {
			this.destPos = 0.0F;
		}

		if (this.destPos > 1.0F) {
			this.destPos = 1.0F;
		}

		if (!this.onGround && this.field_70889_i < 1.0F) {
			this.field_70889_i = 1.0F;
		}

		this.field_70889_i = (float) ((double) this.field_70889_i * 0.9D);

		if (!this.onGround && this.motionY < 0.0D) {
			this.motionY *= 0.6D;
		}

		this.field_70886_e += this.field_70889_i * 2.0F;
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound tag) {
		super.writeEntityToNBT(tag);
		if (this.dna != null) {
			tag.setCompoundTag("traits", this.dna);
		}
		tag.setString("head", this.head);
		tag.setString("body", this.body);
		tag.setString("wings", this.wings);
		tag.setString("arms", this.arms);
		tag.setString("tail", this.tail);
		tag.setString("legs", this.legs);


        if (this.dataWatcher.getWatchableObjectByte(17) == 1)
        {
            tag.setBoolean("powered", true);
        }

        tag.setShort("Fuse", (short)this.fuseTime);
        tag.setByte("ExplosionRadius", (byte)this.explosionRadius);
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound tag) {
		super.readEntityFromNBT(tag);
		if (tag.hasKey("traits")) {
			dna = tag.getCompoundTag("traits");
		}
		this.head = tag.getString("head");
		this.body = tag.getString("body");
		this.wings = tag.getString("wings");
		this.arms = tag.getString("arms");
		this.tail = tag.getString("tail");
		this.legs = tag.getString("legs");
		
		
		/*
		 * CREEPER behaviour
		 */
		this.dataWatcher.updateObject(17, Byte.valueOf((byte)(tag.getBoolean("powered") ? 1 : 0)));

        if (tag.hasKey("Fuse"))
	        this.fuseTime = tag.getShort("Fuse");
        
        if (tag.hasKey("ExplosionRadius"))
        	this.explosionRadius = tag.getByte("ExplosionRadius");
        
	}

	public void writeSpawnData(ByteArrayDataOutput data) {
		try {
			writeStreamData(data);
		} catch (IOException e) {
		}
	}

	private void writeStreamData(DataOutput data) throws IOException {
		data.writeUTF(this.head);
		data.writeUTF(this.body);
		data.writeUTF(this.wings);
		data.writeUTF(this.arms);
		data.writeUTF(this.legs);
		data.writeUTF(this.tail);
	}

	@Override
	public void readSpawnData(ByteArrayDataInput data) {
		try
	    {
	      this.head = data.readUTF();
	      this.body = data.readUTF();
	      this.wings = data.readUTF();
	      this.arms = data.readUTF();
	      this.legs = data.readUTF();
	      this.tail = data.readUTF();
	    }catch(Exception e) {
	    }
	}

	public String getBodyModel() {
		return this.body;
	}
	public String getArmsModel() {
		return this.arms;
	}
	public String getHeadModel() {
		return this.head;
	}
	public String getLegsModel() {
		return this.legs;
	}
	public String getWingsModel() {
		return this.wings;
	}
	public String getTailModel() {
		return this.tail;
	}

}
