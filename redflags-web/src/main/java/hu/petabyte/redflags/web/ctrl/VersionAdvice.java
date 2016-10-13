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
package hu.petabyte.redflags.web.ctrl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * @author Zsolt Jurányi
 */
@ControllerAdvice
public class VersionAdvice {

	@Value("${build.title}")
	private String appTitle;

	@Value("${build.time}")
	private String built;

	@Value("${build.version}")
	private String version;

	@ModelAttribute("appTitle")
	public String getAppTitle() {
		return appTitle;
	}

	@ModelAttribute("built")
	public String getBuilt() {
		return built;
	}

	@ModelAttribute("version")
	public String getVersion() {
		return version;
	}
}
