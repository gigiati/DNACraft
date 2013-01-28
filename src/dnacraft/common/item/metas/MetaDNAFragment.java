package dnacraft.common.item.metas;

import java.util.HashMap;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import dnacraft.DNACraft;
import dnacraft.api.IMeta;
import dnacraft.common.evolution.Genome;
import dnacraft.common.evolution.TraitManager;

public class MetaDNAFragment implements IMeta {

	private Genome genome = null;
	private int id;
	private String name;
	
	public static HashMap<Item, MetaDNAFragment> fragmentsForItems = new HashMap<Item, MetaDNAFragment>();
	
	public MetaDNAFragment(int id, String name, Item item, Genome genome) {
		this.genome = genome;
		this.id = id;
		this.name = name;
		fragmentsForItems.put(item, this);
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
	
	public Genome getGenome() {
		return genome;
	}
}
