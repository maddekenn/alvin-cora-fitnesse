package se.uu.ub.cora.alvin.fitnesse;

import se.uu.ub.cora.alvin.mixedstorage.fedora.AlvinCoraToFedoraConverter;
import se.uu.ub.cora.alvin.mixedstorage.fedora.AlvinFedoraConverterFactory;
import se.uu.ub.cora.alvin.mixedstorage.fedora.AlvinFedoraToCoraConverter;

public class AlvinToCoraConverterFactorySpy implements AlvinFedoraConverterFactory {

	public String type;
	public AlvinToCoraFitnesseConverterSpy converterSpy;

	public static AlvinFedoraConverterFactory usingFedoraURL(String fedoraURL) {
		return new AlvinToCoraConverterFactorySpy();
	}

	@Override
	public AlvinFedoraToCoraConverter factorToCoraConverter(String type) {
		this.type = type;
		converterSpy = new AlvinToCoraFitnesseConverterSpy();
		return converterSpy;
	}

	@Override
	public AlvinCoraToFedoraConverter factorToFedoraConverter(String type) {
		// TODO Auto-generated method stub
		return null;
	}

}
