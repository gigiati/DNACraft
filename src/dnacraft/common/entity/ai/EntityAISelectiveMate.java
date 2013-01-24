package dnacraft.common.entity.ai;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

import dnacraft.common.entity.EntityMolinoid;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.world.World;

public class EntityAISelectiveMate extends EntityAIBase {

    private EntityMolinoid theAnimal;
    World theWorld;
    private EntityMolinoid targetMate;

    int spawnBabyDelay = 0;

    float moveSpeed;

    public EntityAISelectiveMate(EntityAnimal par1EntityAnimal, float par2)
    {
        this.theAnimal = (EntityMolinoid)par1EntityAnimal;
        this.theWorld = par1EntityAnimal.worldObj;
        this.moveSpeed = par2;
        this.setMutexBits(3);
    }

    public boolean shouldExecute()
    {
        if (this.theAnimal.isMale())
        {
            this.targetMate = this.getNearbyMate();
            return this.targetMate != null;
        }
        return false;
    }

    public boolean continueExecuting()
    {
        return this.targetMate.isEntityAlive() && this.targetMate.isInLove() && this.spawnBabyDelay < 60;
    }

    public void resetTask()
    {
        this.targetMate = null;
        this.spawnBabyDelay = 0;
    }

    public void updateTask()
    {
        this.theAnimal.getLookHelper().setLookPositionWithEntity(this.targetMate, 10.0F, (float)this.theAnimal.getVerticalFaceSpeed());
        this.theAnimal.getNavigator().tryMoveToEntityLiving(this.targetMate, this.moveSpeed);
        ++this.spawnBabyDelay;

        if (this.spawnBabyDelay == 60)
        {
            this.spawnBaby();
        }
    }

    private EntityMolinoid getNearbyMate()
    {
        float var1 = 8.0F;
        List var2 = this.theWorld.getEntitiesWithinAABB(this.theAnimal.getClass(), this.theAnimal.boundingBox.expand((double)var1, (double)var1, (double)var1));
        Iterator var3 = var2.iterator();
        EntityMolinoid var4;

        do
        {
            if (!var3.hasNext())
            {
                return null;
            }

            var4 = (EntityMolinoid)var3.next();
        }
        while (!var4.canMateWith(this.theAnimal));

        return var4;
    }

    private void spawnBaby()
    {
        EntityAgeable var1 = this.theAnimal.createChild(this.targetMate);

        if (var1 != null)
        {
            this.theAnimal.setGrowingAge(6000);
            this.targetMate.setGrowingAge(6000);
            this.theAnimal.resetInLove();
            this.targetMate.resetInLove();
            var1.setGrowingAge(-24000);
            var1.setLocationAndAngles(this.theAnimal.posX, this.theAnimal.posY, this.theAnimal.posZ, 0.0F, 0.0F);
            this.theWorld.spawnEntityInWorld(var1);
            Random var2 = this.theAnimal.getRNG();

            for (int var3 = 0; var3 < 7; ++var3)
            {
                double var4 = var2.nextGaussian() * 0.02D;
                double var6 = var2.nextGaussian() * 0.02D;
                double var8 = var2.nextGaussian() * 0.02D;
                this.theWorld.spawnParticle("heart", this.theAnimal.posX + (double)(var2.nextFloat() * this.theAnimal.width * 2.0F) - (double)this.theAnimal.width, this.theAnimal.posY + 0.5D + (double)(var2.nextFloat() * this.theAnimal.height), this.theAnimal.posZ + (double)(var2.nextFloat() * this.theAnimal.width * 2.0F) - (double)this.theAnimal.width, var4, var6, var8);
            }

            this.theWorld.spawnEntityInWorld(new EntityXPOrb(this.theWorld, this.theAnimal.posX, this.theAnimal.posY, this.theAnimal.posZ, var2.nextInt(7) + 1));
        }
    }
}
