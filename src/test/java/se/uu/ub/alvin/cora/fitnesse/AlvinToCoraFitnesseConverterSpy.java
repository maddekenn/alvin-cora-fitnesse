package se.uu.ub.alvin.cora.fitnesse;

import se.uu.ub.cora.alvin.tocorastorage.AlvinToCoraConverter;
import se.uu.ub.cora.bookkeeper.data.DataGroup;

public class AlvinToCoraFitnesseConverterSpy implements AlvinToCoraConverter {

	public String xml;

	@Override
	public DataGroup fromXML(String xml) {
		this.xml = xml;
		return DataGroup.withNameInData("DataGroupReturnedFromSpy");
	}

}
