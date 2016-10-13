/*
   Copyright 2014-2016 PetaByte Research Ltd.

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 */
package hu.petabyte.redflags.engine.gear.indicator.helper;

import hu.petabyte.redflags.engine.model.Notice;

/**
 *
 * @author Zsolt Jurányi
 *
 */
public class DirectiveHelper {

	// TODO in the future: we should introduce Directive enum and parse it

	private static String CLASSICAL = "2004/18/";
	private static String PUBLIC_PROCUREMENT = "2014/24/";

	public static String getDirective(Notice notice) {
		return (null == notice || null == notice.getData() || null == notice
				.getData().getDirective()) ? "" : notice.getData()
						.getDirective();
	}

	public static boolean isClassicalDirective(Notice notice) {
		return getDirective(notice).contains(CLASSICAL);
	}

	public static boolean isPublicProcurementDirective(Notice notice) {
		return getDirective(notice).contains(PUBLIC_PROCUREMENT);
	}
}
