package se.uu.ub.alvin.cora.fitnesse;

import se.uu.ub.cora.alvin.tocorastorage.AlvinToCoraConverter;
import se.uu.ub.cora.alvin.tocorastorage.AlvinToCoraConverterFactory;

public class AlvinToCoraConverterFactorySpy implements AlvinToCoraConverterFactory {

	public String type;
	public AlvinToCoraFitnesseConverterSpy converterSpy;

	@Override
	public AlvinToCoraConverter factor(String type) {
		this.type = type;
		converterSpy = new AlvinToCoraFitnesseConverterSpy();
		return converterSpy;
	}

}
