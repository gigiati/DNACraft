package dnacraft.common.neural;

import java.util.Arrays;

public class NeuralNetwork {

	protected int numInputs = 0;
	protected int numHidden = 0;
	NeuronLayer inputLayer;
	NeuronLayer[] hiddenLayers;
	NeuronLayer outputLayer;
	
	public NeuralNetwork (int numInputs, int numOutputs, int numHidden)
	{
		this.numInputs = numInputs;
		this.numHidden = numHidden;

		int numPerHidden = numInputs;
		int inputWeightSize = numInputs;
		
		inputLayer = new NeuronLayer(numInputs, numInputs + 1);
		hiddenLayers = new NeuronLayer[numHidden];
		
		int lastLayerSize = numInputs;
		
		for (int i = 0; i < numHidden; i++)
		{
			hiddenLayers[i] = new NeuronLayer(numPerHidden, lastLayerSize + 1);
			lastLayerSize = numPerHidden;
		}

		outputLayer = new NeuronLayer(numOutputs, lastLayerSize + 1);

	}
	
	public float[] getOutputs(float[] inputs)
	{
		float[] lastOutputs = inputLayer.getOutputs(inputs);
		for (int i=0; i<hiddenLayers.length; i++)
		{
			lastOutputs = hiddenLayers[i].getOutputs(lastOutputs);
		}
		return outputLayer.getOutputs(lastOutputs);
	}
	
	public float[] getWeights()
	{
		int numW = inputLayer.getNumWeights() + outputLayer.getNumWeights();
		for (int a = 0; a < hiddenLayers.length; a++)
		{
			numW += hiddenLayers[a].getNumWeights();
		}
		float[] ret = new float[numW];
		int i = 0;
		for (float weight : inputLayer.getWeights())
		{
			ret[i] = weight;
			i++;
		}
		for (int a = 0; a < hiddenLayers.length; a++)
		{
			for (float weight : hiddenLayers[a].getWeights())
			{
				ret[i] = weight;
				i++;
			}
		}
		for (float weight : outputLayer.getWeights())
		{
			ret[i] = weight;
			i++;
		}
		return ret;
	}
	
	public void setWeights(float[] weights)
	{
		int offset = inputLayer.getNumWeights();
		inputLayer.setWeights(Arrays.copyOfRange(weights, 0, offset));
		
		for (NeuronLayer layer : hiddenLayers)
		{
			layer.setWeights(Arrays.copyOfRange(weights, offset, offset + layer.getNumWeights()));
			offset += layer.getNumWeights();
		}
		
		outputLayer.setWeights(Arrays.copyOfRange(weights, offset, offset + outputLayer.getNumWeights()));
	}
	
}
