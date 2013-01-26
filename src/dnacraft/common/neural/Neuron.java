package dnacraft.common.neural;

public class Neuron {

	protected float[] weights;
	
	public Neuron(int numberOfWeights)
	{
		this.weights = new float[numberOfWeights];
		for (int i = 0; i < numberOfWeights; i += 1) {
	        this.weights[i] = (float)((Math.random() * 2) - 1);
	    }
	}
	
	public float getOutput(float[] inputs)
	{
		int numInputs = inputs.length;
		float total = 0;
		int i = 0;
		float threshold = 0;
		for (i = 0; i < numInputs; i += 1) {
		        total += inputs[i] * this.weights[i];
		}
		threshold = this.weights[i];
		return (float)(1 / (1 + Math.exp((-total) / threshold)));
	}
}
