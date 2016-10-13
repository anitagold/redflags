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
package hu.petabyte.redflags.engine.test;

import hu.petabyte.redflags.engine.RedflagsEngineApp;
import hu.petabyte.redflags.engine.gear.indicator.AbstractIndicator;
import hu.petabyte.redflags.engine.gear.indicator.helper.DirectiveHelper;
import hu.petabyte.redflags.engine.gear.indicator.helper.ProfilesHelper;
import hu.petabyte.redflags.engine.gear.indicator.hu.DecisionDateDiffersFromOpeningDateIndicator;
import hu.petabyte.redflags.engine.model.IndicatorResult;
import hu.petabyte.redflags.engine.model.Notice;
import hu.petabyte.redflags.engine.tedintf.TedInterface;
import hu.petabyte.redflags.engine.tedintf.TedInterfaceHolder;
import hu.petabyte.redflags.engine.tedintf.cached.CachedTedInterface;
import hu.petabyte.redflags.engine.test.helper.ProcessedNoticeProvider;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.ConfigFileApplicationContextInitializer;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author Zsolt Jurányi
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = RedflagsEngineApp.class)
@ContextConfiguration(classes = RedflagsEngineApp.class, initializers = ConfigFileApplicationContextInitializer.class)
@ActiveProfiles("test")
public class PPDirectiveTest {

	private static final TedInterface tedintf = new CachedTedInterface(
			"indicator-test-cache");
	private @Autowired TedInterfaceHolder ted;
	private @Autowired ProcessedNoticeProvider processedNoticeProvider;
	private @Autowired ProfilesHelper profiles;

	@Before
	public void _init() {
		ted.set(tedintf);
	}

	private IndicatorResult indicatorResult(String id,
			Class<? extends AbstractIndicator> i) {
		Notice notice;
		try {
			notice = processedNoticeProvider.notice(id);
			IndicatorResult ir = notice.getIndicatorResults().get(
					AbstractIndicator.getIdForIndicatorClass(i));
			System.out.println(DirectiveHelper.getDirective(notice));
			System.out.println(DirectiveHelper
					.isPublicProcurementDirective(notice));
			System.out.println(profiles.isTestProfile());
			return ir;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Test
	public void dddfodShouldIgnorePPNotice() {
		String id = "188381-2016";
		IndicatorResult ir = indicatorResult(id,
				DecisionDateDiffersFromOpeningDateIndicator.class);
		System.out.println(ir);
	}

}
