/*
 * Copyright 2018 Uppsala University Library
 *
 * This file is part of Cora.
 *
 *     Cora is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     Cora is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with Cora.  If not, see <http://www.gnu.org/licenses/>.
 */
package se.uu.ub.cora.alvin.fitnesse;

import org.apache.commons.text.StringEscapeUtils;

import se.uu.ub.cora.alvin.mixedstorage.fedora.AlvinFedoraConverterFactory;
import se.uu.ub.cora.alvin.mixedstorage.fedora.AlvinFedoraToCoraConverter;
import se.uu.ub.cora.bookkeeper.data.DataGroup;
import se.uu.ub.cora.bookkeeper.data.converter.DataToJsonConverter;
import se.uu.ub.cora.bookkeeper.data.converter.DataToJsonConverterFactoryImp;
import se.uu.ub.cora.json.builder.JsonBuilderFactory;
import se.uu.ub.cora.json.builder.org.OrgJsonBuilderFactoryAdapter;

public class AlvinToCoraFormatFixture {

	private String type;
	private String xml;

	public void setAlvinXML(String alvinXML) {
		this.xml = removeExtraFormatingAddedByFitnesse(alvinXML);
	}

	private String removeExtraFormatingAddedByFitnesse(String alvinXML) {
		String tempXml = alvinXML.replaceAll("<pre>", "").replaceAll("</pre>", "");
		return StringEscapeUtils.unescapeHtml4(tempXml);
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getJson() {
		try {
			return tryToConvertXMLToJson();
		} catch (Exception e) {
			return "can not convert xml:" + e;
		}
	}

	private String tryToConvertXMLToJson() {
		DataGroup fromXML = convertXMLToDataGroup();
		return convertDataGroupToJson(fromXML);
	}

	private DataGroup convertXMLToDataGroup() {
		AlvinFedoraToCoraConverter converter = createConverterForCurrentType();
		return converter.fromXML(xml);
	}

	private AlvinFedoraToCoraConverter createConverterForCurrentType() {
		AlvinFedoraConverterFactory converterFactory = AlvinFitnesseDependencyProvider
				.getConverterFactory();
		return converterFactory.factorToCoraConverter(type);
	}

	private String convertDataGroupToJson(DataGroup fromXML) {
		DataToJsonConverter dataToJsonConverter = createDataGroupToJsonConverter(fromXML);
		return dataToJsonConverter.toJsonCompactFormat();
	}

	private DataToJsonConverter createDataGroupToJsonConverter(DataGroup dataGroup) {
		DataToJsonConverterFactoryImp dataToJsonConverterFactory = new DataToJsonConverterFactoryImp();
		JsonBuilderFactory factory = new OrgJsonBuilderFactoryAdapter();
		return dataToJsonConverterFactory.createForDataElement(factory, dataGroup);
	}

	public String getAlvinXML() {
		return xml;
	}

}
