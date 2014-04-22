/*
 * Copyright 2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package hello;

import java.io.IOException;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.github.fge.jsonpatch.diff.JsonDiff;

/**
 * @author Roy Clarkson
 */
@RestController
@RequestMapping("/")
public class MainController {

	@RequestMapping(value = "/diff", method = RequestMethod.POST, consumes = "application/json", produces = {
			"application/json", "application/json-patch+json" })
	public JsonNode diff(@RequestBody JsonNode data) {
		JsonNode source = data.get("source");
		JsonNode target = data.get("target");
		return JsonDiff.asJson(source, target);
	}

	@RequestMapping(value = "/patch", method = RequestMethod.POST, consumes = "application/json", produces = {
			"application/json", "application/json-patch+json" })
	public JsonNode patch(@RequestBody JsonNode data) throws IOException, JsonPatchException {
		JsonNode source = data.get("source");
		JsonPatch patch = JsonPatch.fromJson(data.get("patch"));
		return patch.apply(source);
	}

}
