package dnacraft.common.evolution;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagDouble;

public class TraitManager extends HashMap<String, Double> {

	public static final String COLOR_RED = "red";
	public static final String COLOR_GREEN = "green";
	public static final String COLOR_YELLOW = "green";
	public static final String COLOR_PINK = "pink";
	public static final String COLOR_WHITE = "white";
	
	public static final String ZOMBIE = "zombie";
	public static final String CREEPER = "creeper";
	public static final String CHICKEN = "chicken";
	public static final String PIG = "pig";
	public static final String SHEEP = "sheep";
	public static final String OCELOT = "ocelot";
	public static final String ENDERMAN = "enderman";
	public static final String SPIDER = "spider";
	
	public static final String DROPS_WHEAT = "wheat";
	public static final String DROPS_SEEDS = "seeds";
	public static final String DROPS_RAW_PORK = "rawpork";
	
	public static final String CONSUMES_BONEMEAL = "consumesbonemeal";
	public static final String NEEDS_LIGHT = "needslight";
	public static final String SIZE_SMALL = "sizesmall";
	
	public static final String OFFSPRING_1 = "offspring1";
	public static final String OFFSPRING_2 = "offspring2";
	
	public static final String HEALTH_VERY_LOW = "verylowhealth";
	public static final String HEALTH_LOW = "lowhealth";
	
	public static final String FOOD = "food";
	
	
	public static TraitManager instance = new TraitManager();
	
	private List<String> bodies = Arrays.asList(new String[] {
			ZOMBIE,
			CREEPER,
			CHICKEN,
			PIG,
			SHEEP,
			SPIDER,
			OCELOT,
			ENDERMAN
	});
	
	public TraitManager() {
		this.put(ZOMBIE, 1.0);
		this.put(CREEPER, 1.0);
		this.put(CHICKEN, 1.0);
		this.put(PIG, 1.0);
		this.put(SHEEP, 1.0);
		this.put(SPIDER, 1.0);
		this.put(OCELOT, 1.0);
		this.put(ENDERMAN, 1.0);
	}
	
	public String getBodyPartFromDNA(NBTTagCompound dna) {
		return getRandomWeightedFromList(dna, bodies);
	}
	
	
	public String getRandomWeightedFromList(NBTTagCompound traits, List<String> list) {
		if (traits != null) {
			double totalWeight = 0.0;
			
			Map<String, Double> weighted = new HashMap<String, Double>();
			Collection col = traits.getTags();
			Iterator it = col.iterator();
			while (it.hasNext()) {
				NBTBase nbt = (NBTBase) it.next();
				if (nbt.getId() == 6) {
					if (list.contains(nbt.getName())) {
						double weightedValue = ((NBTTagDouble) nbt).data * this.get(nbt.getName());
						totalWeight += weightedValue;
						weighted.put(nbt.getName(), weightedValue);
					}
				}
			}
			
			double random = Math.random() * totalWeight;
			for (Map.Entry<String, Double> entry : weighted.entrySet()) {
				random -= entry.getValue();
				if (random <= 0.0d) {
					return entry.getKey();
				}
			}
		}
		int index = new Random().nextInt(list.size());
        return list.get(index);
	}

}
