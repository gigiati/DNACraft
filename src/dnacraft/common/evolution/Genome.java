package dnacraft.common.evolution;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.item.Item;

import dnacraft.common.evolution.genome.GenomePorkCooked;
import dnacraft.common.evolution.genome.GenomePorkRaw;
import dnacraft.common.evolution.genome.GenomeSeeds;

public abstract class Genome {
	
	public static Genome seeds = new GenomeSeeds();
	public static Genome porkRaw = new GenomePorkRaw();
	public static Genome porkCooked = new GenomePorkCooked();
	
	private HashMap<String, Double> traits = new HashMap<String, Double>();
	
	public void addTrait(String name, double value) {
		traits.put(name,  value);
	}
	
	public Map.Entry<String,Double> getRandomTrait() {
		
		double totalWeight = 0.0;
		for (Map.Entry<String,Double> entry : traits.entrySet())
		{
		    totalWeight += entry.getValue();
		}
		double random = Math.random() * totalWeight;
		for (Map.Entry<String,Double> entry : traits.entrySet())
		{
			random -= entry.getValue();
			if (random <= 0.0d)
		    {
		        return entry;
		    }
		}
		return null;
	}
}
