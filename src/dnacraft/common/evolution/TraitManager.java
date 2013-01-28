package dnacraft.common.evolution;

import java.util.HashMap;

public class TraitManager extends HashMap<String, Double> {

	public static final String COLOR_RED = "red";
	public static final String COLOR_GREEN = "green";
	public static final String COLOR_YELLOW = "green";
	public static final String COLOR_PINK = "pink";
	public static final String COLOR_WHITE = "white";
	
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
	
}
