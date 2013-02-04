package dnacraft.common.evolution;

import java.util.Collection;
import java.util.Iterator;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagIntArray;
import net.minecraft.nbt.NBTTagList;

public class DNAManager {

	public static final int GENOME_LEG_ID = 0;
	public static final int NUMBER_OF_GENOMES = 1;

	public static int[] createNewGenome(int type, int size) {
		int[] result = new int[size * 4];
		int j = 0;
		for (int i = 0; i < size; i++, j++) {
			result[j] = type;
			result[j] = Math.random() < 0.5 ? 0 : 1;
			result[j] = type;
			result[j] = Math.random() < 0.5 ? 0 : 1;
		}
		return result;
	}

	public static NBTTagCompound DNAToNBT(int[][] dna) {
		NBTTagCompound tags = new NBTTagCompound();
		for (int i = 0; i < dna.length; i++) {
			if (dna[i] != null) {
				tags.setIntArray(Integer.toString(i), dna[i]);
			}
		}
		return tags;
	}
	
	public static int[][] NBTToDNA(NBTTagCompound compound)
	{
		int[][] dna = new int[NUMBER_OF_GENOMES][];
		Collection col = compound.getTags();
		Iterator it = col.iterator();
		while (it.hasNext()) {
			NBTBase nbt = (NBTBase) it.next();
			if (nbt.getId() == 11) {
				int gene = Integer.parseInt(nbt.getName());
				dna[gene] = ((NBTTagIntArray) nbt).intArray;
			}
		}
		return dna;
	}
	
	public static int[][] SpliceDNA(int[][] one, int[][] two) {
	    int[][] newGenome = new int[][]{};
	    int j = 0;
	    for (int i = 0; i < one.length; i++, j+=4) {
	    	newGenome[i] = one[i].clone();
	    	if (Math.random() < 0.5) {
		    	newGenome[i][j] = two[i][j];
		    	newGenome[i][j+1] = two[i][j+1];
	    	}
	    	if (Math.random() < 0.5) {
		    	newGenome[i][j+2] = two[i][j+2];
		    	newGenome[i][j+3] = two[i][j+3];
	    	}
	    }
	    return newGenome;
	}
}
