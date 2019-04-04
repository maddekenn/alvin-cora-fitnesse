package se.uu.ub.alvin.cora.fitnesse;

import se.uu.ub.cora.alvin.mixedstorage.fedora.AlvinCoraToFedoraConverter;
import se.uu.ub.cora.alvin.mixedstorage.fedora.AlvinFedoraConverterFactory;
import se.uu.ub.cora.alvin.mixedstorage.fedora.AlvinFedoraToCoraConverter;

public class AlvinToCoraConverterThrowsExceptionFactorySpy implements AlvinFedoraConverterFactory {

	public String type;
	public AlvinToCoraFitnesseConverterSpy converterSpy;

	public static AlvinFedoraConverterFactory usingFedoraURL(String fedoraURL) {
		return new AlvinToCoraConverterThrowsExceptionFactorySpy();
	}

	@Override
	public AlvinFedoraToCoraConverter factorToCoraConverter(String type) {
		throw new RuntimeException();
	}

	@Override
	public AlvinCoraToFedoraConverter factorToFedoraConverter(String type) {
		// TODO Auto-generated method stub
		return null;
	}

}
