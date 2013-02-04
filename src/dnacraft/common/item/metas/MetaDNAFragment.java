package dnacraft.common.item.metas;

import java.util.HashMap;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import dnacraft.DNACraft;
import dnacraft.api.IMeta;
import dnacraft.common.evolution.DNA;

public class MetaDNAFragment implements IMeta {

	private DNA dna = null;
	private int id;
	private String name;
	
	public static HashMap<Object, MetaDNAFragment> fragmentsForItems = new HashMap<Object, MetaDNAFragment>();
	

	public MetaDNAFragment(int id, String name, DNA dna, Object...objs) {
		this.dna = dna;
		this.id = id;
		this.name = name;
		for (Object obj : objs) {
			fragmentsForItems.put(obj, this);
		}
	}
	
	@Override
	public int getIconIndex() {
		return this.id;
	}

	@Override
	public String getItemNameIS() {
		return this.name;
	}

	@Override
	public int getId() {
		return this.id;
	}

	@Override
	public ItemStack newItemStack(int count) {
		return new ItemStack(DNACraft.Items.itemGeneric, count, this.getId());
	}

	@Override
	public ItemStack newItemStack() {
		return newItemStack(1);
	}
	
	public DNA getDNA() {
		return dna;
	}

	@Override
	public boolean onItemUse(ItemStack itemStack, EntityPlayer player,
			World world, int x, int y, int z, int side, float par8, float par9,
			float par10) {
		return false;
	}
	
}
