package dnacraft.common;

import java.util.HashMap;
import java.util.Map;

public class Tally extends HashMap<Integer, Integer> {
	
	public Map.Entry<Integer, Integer> largest() {
		Map.Entry<Integer, Integer> maxEntry = null;
		for (Map.Entry<Integer, Integer> entry : entrySet())
		{
		    if (maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) > 0)
		    {
		        maxEntry = entry;
		    }
		}
		return maxEntry;
	}

	public Map.Entry<Integer, Integer> randomWeighted() {
		return randomWeighted(1);
	}
	public Map.Entry<Integer, Integer> randomWeighted(double pow) {
		double totalWeight = 0;
		for (Map.Entry<Integer, Integer> entry : entrySet())
		{
			totalWeight += Math.pow(entry.getValue(), pow);
		}
		int randomIndex = -1;
		double random = Math.random() * totalWeight;
		for (Map.Entry<Integer, Integer> entry : entrySet())
		{
			random -= Math.pow(entry.getValue(), pow);
		    if (random <= 0.0d)
		    {
		        return entry;
		    }
		}
		return null;
	}

	public void increment(int key) {
		int val = 0;
		if (containsKey(key)) {
			val = get(key);
		}
		put(key, val + 1);
	}
}
