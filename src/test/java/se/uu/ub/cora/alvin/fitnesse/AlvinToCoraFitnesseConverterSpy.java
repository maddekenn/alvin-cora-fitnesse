package se.uu.ub.cora.alvin.fitnesse;

import se.uu.ub.cora.alvin.mixedstorage.fedora.AlvinFedoraToCoraConverter;
import se.uu.ub.cora.bookkeeper.data.DataGroup;

public class AlvinToCoraFitnesseConverterSpy implements AlvinFedoraToCoraConverter {

	public String xml;

	@Override
	public DataGroup fromXML(String xml) {
		this.xml = xml;
		return DataGroup.withNameInData("DataGroupReturnedFromSpy");
	}

}
