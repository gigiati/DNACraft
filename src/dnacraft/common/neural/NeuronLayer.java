package dnacraft.common.neural;

public class NeuronLayer {

	Neuron[] neurons;
	int weightsPerNeuron;
	
	public NeuronLayer(int numNeurons, int weightsPerNeuron)
	{
		this.weightsPerNeuron = weightsPerNeuron;
		neurons = new Neuron[numNeurons];
		for (int i = 0; i < numNeurons; i++){
	        neurons[i] = new Neuron(weightsPerNeuron);
	    }
	}
	
	public int getNumWeights()
	{
        return weightsPerNeuron * neurons.length;
    }
	
	public float[] getWeights()
	{
		int i = 0;
		float[] ret = new float[this.getNumWeights()];
        for (int a = 0; a<neurons.length; a++){
        	float[] w = neurons[a].weights;
            for (int b = 0; b < w.length; b++) {
                ret[i] = w[b];
                i++;
            }
        }
        return ret;
	}
	
	public void setWeights(float[] weights)
	{
		int c = 0;
        for (int i = 0; i < neurons.length; i++){
        	float[] tmpw = new float[weightsPerNeuron];
            for (int p = 0; p < weightsPerNeuron; p++) {
                tmpw[p] = weights[c];
                c++;
            }
            neurons[i].weights = tmpw;
        }
	}
	
	public float[] getOutputs(float[] inputs)
	{
		float[] out = new float[neurons.length];
        for (int i = 0; i < neurons.length; i++)
        {
            out[i] = neurons[i].getOutput(inputs);
        }
        return out;
	}
}
