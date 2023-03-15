package com.regex;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.util.StringUtils;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import it.units.inginf.male.console.ConsoleRegexTurtle;

@Controller
@EnableAutoConfiguration
@SpringBootApplication
public class AmazingRegexApplication {

	Map<String, Map<String, Integer>> server = new HashMap<String, Map<String,Integer>>();
	String regexVar = "";
	String regexVar1 = RandomStringUtils.random(10, true, false);
	String regexVar2 = RandomStringUtils.random(10, true, false);

	@RequestMapping("/")
	public String index() {
		return "index";
	}
	@RequestMapping("/manual")
	public String manual() {
		return "manual";
	}
	@RequestMapping("/about")
	public String about() {
		return "about";
	}

	@RequestMapping("/regex")
	public String regex(@RequestParam("findChar") String findChar,
			Model model) throws Exception {
		
		// Make random string
		regexVar = regexVar1 + findChar + regexVar2;
		if (StringUtils.isEmpty(regexVar) || StringUtils.isEmpty(findChar)) {
			return "index";
		}

		FirebaseApp firebaseApp = new FBInitialize().connet();
		FirebaseDatabase defaultDatabase = FirebaseDatabase.getInstance(firebaseApp);
		DatabaseReference ref = defaultDatabase.getReference();
		DatabaseReference server1 = ref.child("server/server1");
		server1.setValueAsync(1);

		String key = "key" + new SimpleDateFormat("dd-MM-yyyy-HH-mm-ss").format(new Timestamp(System.currentTimeMillis()));

		Thread thread = new Thread() {
			public void run() {
				String jsonData = createJsonData(regexVar, findChar);

				ConsoleRegexTurtle.getRegex(jsonData, key, defaultDatabase);
				server1.setValueAsync(0);
			}
		};
		thread.start();

		model.addAttribute("regexVar", regexVar);
		model.addAttribute("findChar", findChar);
		model.addAttribute("key", key);

		return "index";
	}

	// @RequestMapping("/matchregex")
	// public String matchregex(@RequestParam(name = "regex") String regex, @RequestParam("str") String str,
	// 		@RequestParam(name = "regexVar") String regexVar, @RequestParam("findChar") String findChar, @RequestParam("key") String key,
	// 		Model model) throws Exception {

	// 	try {
	// 		Pattern pattern = Pattern.compile(regex);
	// 		List<String> result = new ArrayList<String>();
	// 		Matcher m = pattern.matcher(str);
	// 		while (m.find()) {
	// 		    result.add(m.group());
	// 		}

	// 		List<String> resultDuplicates = result.stream()
	// 			     .distinct()
	// 			     .collect(Collectors.toList());

	// 		model.addAttribute("regex", regex);

	// 		if (result.size() != 0 && !StringUtils.isEmpty(regex)) {
	// 			model.addAttribute("result", String.join("\r\n", result));
	// 		}
	// 		if (resultDuplicates.size() != 0 && !StringUtils.isEmpty(regex)) {
	// 			model.addAttribute("resultDuplicates", String.join("\r\n", resultDuplicates));
	// 		}
	// 	} catch (Exception e) {

	// 	}
	// 	model.addAttribute("str", str);
	// 	model.addAttribute("regexVar", regexVar);
	// 	model.addAttribute("findChar", findChar);
	// 	model.addAttribute("key", key);

	// 	return "index";

	// }

	private String createJsonData(String regexVar, String findChar) {
		String regexCharGroup = regexVar.replaceAll("\"", "\'\'") + "\r\n" + String.join("", regexVar.replaceAll("\"", "\'\'").split("\\r\\n"));
		String[] regexCharList = regexCharGroup.split("\\r\\n");
		String[] findCharList = findChar.split("\\r\\n");

		Map<String, List<String>> xxx = new HashMap<String, List<String>>();

		for (String i: regexCharList) {

			List<matchDto> indexes = new ArrayList<matchDto>();

			for (String j: findCharList) {
				int index = 0;
				while (i.indexOf(j, index) != -1) {
					matchDto match = new matchDto();
					match.setStart(i.indexOf(j, index));
					match.setEnd(i.indexOf(j, index) + j.length());
					indexes.add(match);
					index = i.indexOf(j, index) + j.length();
				}
			}


			List<String> p = new ArrayList<String>();
			for (matchDto o: indexes) {
				p.add("{\"start\":" + o.getStart() + ",\"end\":" + o.getEnd() + "}");
			}
			xxx.put(i, p);
		}


		List<String> yyy = new ArrayList<String>();

		for (String key : xxx.keySet()) {
			yyy.add("{\"string\":\"" + key + "\",\"match\":[" + String.join(",",xxx.get(key)) + "]}");
		}

		String str = "{\"examples\":[" + String.join(",",yyy) + "]}";

		return str;
	}

	public static void main(String[] args) {
		SpringApplication.run(AmazingRegexApplication.class, args);
	}

}
