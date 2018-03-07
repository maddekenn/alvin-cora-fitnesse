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
package se.uu.ub.alvin.cora.fitnesse;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AlvinToCoraFormatFixtureTest {
	private AlvinToCoraFormatFixture fixture;
	private AlvinToCoraConverterFactorySpy converterFactorySpy;

	@BeforeMethod
	public void beforeMethod() {
		AlvinFitnesseDependencyProvider.setConverterFactoryClassName(
				"se.uu.ub.alvin.cora.fitnesse.AlvinToCoraConverterFactorySpy");
		converterFactorySpy = (AlvinToCoraConverterFactorySpy) AlvinFitnesseDependencyProvider
				.getConverterFactory();
		fixture = new AlvinToCoraFormatFixture();
	}

	@Test
	public void testNoConverterErrorClassname() throws Exception {
		AlvinFitnesseDependencyProvider.setConverterFactoryClassName(
				"se.uu.ub.alvin.cora.fitnesse.AlvinToCoraConverterThrowsExceptionFactorySpy");
		assertEquals(fixture.getJson(), "can not read xml");
	}

	@Test
	public void testNothingAsTypeAndXML() throws Exception {
		fixture.getJson();
		assertEquals(converterFactorySpy.type, null);
		assertEquals(converterFactorySpy.converterSpy.xml, null);
	}

	@Test
	public void testTypeIsUsedWhenFactoringConverter() throws Exception {
		fixture.setType("someType");
		fixture.getJson();
		assertEquals(converterFactorySpy.type, "someType");
	}

	@Test
	public void testXMLIsSentToConverter() throws Exception {
		String alvinXML = "some xml";
		fixture.setAlvinXML(alvinXML);
		fixture.getJson();
		assertEquals(converterFactorySpy.converterSpy.xml, "some xml");
	}

	@Test
	public void testReturnedTextIsFromConverter() throws Exception {
		String returnedJson = fixture.getJson();
		assertEquals(returnedJson, "{\"name\": \"DataGroupReturnedFromSpy\"}");
	}

}
