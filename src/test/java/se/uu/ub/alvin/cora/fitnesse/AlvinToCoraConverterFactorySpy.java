package se.uu.ub.alvin.cora.fitnesse;

import se.uu.ub.cora.alvin.tocorastorage.fedora.AlvinFedoraToCoraConverter;
import se.uu.ub.cora.alvin.tocorastorage.fedora.AlvinFedoraToCoraConverterFactory;

public class AlvinToCoraConverterFactorySpy implements AlvinFedoraToCoraConverterFactory {

	public String type;
	public AlvinToCoraFitnesseConverterSpy converterSpy;

	@Override
	public AlvinFedoraToCoraConverter factor(String type) {
		this.type = type;
		converterSpy = new AlvinToCoraFitnesseConverterSpy();
		return converterSpy;
	}

}
