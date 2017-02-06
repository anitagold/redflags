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

import hu.petabyte.redflags.web.svc.OrganizationsSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Zsolt Jurányi
 */
@RestController
public class AutocompleteCtrl {

	@Autowired
	private OrganizationsSvc organizations;

	@RequestMapping("/ac/organizations")
	@ResponseBody
	public List<String> search(@RequestParam String q) {
		List<String> names = new ArrayList<String>();
		List<Map<String, Object>> r = organizations.query(20, 0, q);
		for (Map<String, Object> m : r) names.add((String) m.get("name"));
		return names;
	}

}
