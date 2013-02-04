package dnacraft.common.evolution;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;

import dnacraft.common.evolution.materials.DNAPorkRaw;
import dnacraft.common.item.metas.MetaDNA;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagIntArray;

public class DNA extends HashMap<String, Genome> {

	public static DNA porkRaw = new DNAPorkRaw();

	private static Random rnd = new Random();

	public DNA() {

	}

	public void addGenome(Genome genome) {
		put(genome.getType(), genome);
	}

	public int getMostActive(String key) {
		return 0;
	}

	public int getHeadType() {
		return 0;
	}

	public NBTTagCompound toNBT() {
		NBTTagCompound compound = new NBTTagCompound();
		Genome genome;
		for (Map.Entry<String, Genome> entry : this.entrySet()) {
			genome = entry.getValue();
			compound.setCompoundTag(genome.getType(), genome.toNBT());
		}
		return compound;
	}

	public static DNA fromNBT(NBTTagCompound compound) {
		DNA dna = new DNA();
		Collection col = compound.getTags();
		Iterator it = col.iterator();
		while (it.hasNext()) {
			NBTBase nbt = (NBTBase) it.next();
			dna.put(nbt.getName(), Genome.fromNBT(((NBTTagCompound) nbt)));
		}
		return dna;
	}

	public static DNA merge(DNA dna1, DNA dna2) {

		DNA newDNA = new DNA();

		Set<String> unique = new HashSet<String>();
		unique.addAll(dna1.keySet());
		unique.addAll(dna2.keySet());

		for (String key : unique) {
			Genome child = new Genome(key);
			Genome father = dna1.get(key);
			Genome mother = dna2.get(key);
			if (mother != null && father != null) {
				child = mother.mergeWith(father);
			} else if (mother != null) {
				child = mother.clone();
			} else {
				child = father.clone();
			}
			newDNA.put(key, child);
		}
		return null;
	}

	public DNA clone() {
		DNA newDNA = new DNA();
		for (Entry<String, Genome> entry : entrySet()) {
			newDNA.put(entry.getKey(), entry.getValue().clone());
		}
		return newDNA;
	}

	public static DNA mergeFragment(DNA stackDNA, DNA fragment) {

		if (stackDNA.size() <= 0) {
			return fragment.clone();
		}

		int item = rnd.nextInt(stackDNA.size());
		int i = 0;
		DNA newDNA = new DNA();
		for (Entry<String, Genome> entry : stackDNA.entrySet()) {
			Genome newGenome = entry.getValue().clone();
			if (i == item) {
				int rndTrait = rnd.nextInt(40);
				Genome fragmentGenome = fragment.get(entry.getKey());
				newGenome.set(rndTrait, fragmentGenome.get(rndTrait).clone());
			}
			newDNA.put(entry.getKey(), newGenome);
			i = i + 1;
		}
		
		return newDNA;
	}
}
