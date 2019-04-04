/*
 * Copyright 2018, 2019 Uppsala University Library
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

import java.lang.reflect.Method;

import se.uu.ub.cora.alvin.mixedstorage.fedora.AlvinFedoraConverterFactory;

public class AlvinFitnesseDependencyProvider {
	private static AlvinFedoraConverterFactory converterFactory;

	public AlvinFitnesseDependencyProvider() {
		// needs a public constructor for fitnesse to work
		super();
	}

	public static synchronized void setConverterFactoryClassName(String converterFactoryClassName) {
		try {
			Class<?>[] cArg = new Class[1];
			cArg[0] = String.class;
			Method constructor = Class.forName(converterFactoryClassName)
					.getMethod("usingFedoraURL", cArg);
			converterFactory = (AlvinFedoraConverterFactory) constructor.invoke(null,
					"someFakeUrlSinceItsNotUsedHereButCodeRefactoringIsNeededElsewhere");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static AlvinFedoraConverterFactory getConverterFactory() {
		return converterFactory;
	}
}
