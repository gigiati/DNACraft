package dnacraft.common;

import java.util.ArrayList;

public class Genome extends ArrayList<Double> {
	
	public final static int SIZE 				= 0;
	public final static int WEIGHT 				= 1;
	public final static int COLOR 				= 2;
	
	public final static int MAX_HEALTH 			= 3;
	public final static int REGEN_RATE 			= 4;
	public final static int AGGRESSION 			= 5;
	
	public final static int DROP_YIELD 			= 6;
	public final static int DROP_VARIATION 		= 7;
	
	public final static int HEAT_TOLERANCE 		= 8;
	public final static int COLD_TOLERANCE 		= 9;
	
	public final static int SOCIABILITY 		= 10;
	public final static int MUTABILITY 			= 11;
	public final static int LIFE_EXPECTANCY 	= 12;
	public final static int ATTRACTIVENESS 		= 13;
	
	public final static int EXCRETION_RATE 		= 14;
	
	public final static int HEAT_FREQUENCY		= 15;
	public final static int HEAT_LENGTH			= 16;
	public final static int BIRTH_FAILURE		= 17;
	public final static int CONCPETION_FAILURE	= 18;
	public final static int GESTATION_PERIOD	= 19;
	public final static int MATURITY			= 20;
	
	public Genome() {
		super();
		for (int i = 0; i <= 20; i++) {
			this.add(i, 0.0);
		}
	}
	

}
