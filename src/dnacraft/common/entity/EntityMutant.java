package dnacraft.common.entity;

import java.io.DataOutput;
import java.io.IOException;
import java.util.HashMap;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;

import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import dnacraft.common.evolution.TraitManager;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntityMutant extends EntityAnimal implements
		IEntityAdditionalSpawnData {

	public HashMap<String, Double> genome = new HashMap<String, Double>();

	public float field_70886_e = 0.0F;
	public float destPos = 0.0F;
	public float field_70884_g;
	public float field_70888_h;
	public float field_70889_i = 1.0F;

	public String head = "creeper";
	private String legs = "creeper";
	private String body = "creeper";
	private String arms = "creeper";
	private String wings = "creeper";
	private String tail = "creeper";
	
	public NBTTagCompound dna = null;

	public EntityMutant(World world) {
		super(world);
	}
	
	public void setDNAFromItemStack(ItemStack stack) {
		if (stack.hasTagCompound()) {
			this.dna = stack.getTagCompound().getCompoundTag("traits");
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

	@Override
	public EntityAgeable createChild(EntityAgeable var1) {
		return null;
	}

	@Override
	public void initCreature() {
	}

	@Override
	public int getMaxHealth() {
		return 30;
	}

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();
		this.handleWings();

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
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound tag) {
		super.readEntityFromNBT(tag);
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
