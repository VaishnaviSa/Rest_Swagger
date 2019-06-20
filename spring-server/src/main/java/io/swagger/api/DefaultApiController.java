package io.swagger.api;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiParam;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-05-31T22:55:27.830Z")

@Controller
public class DefaultApiController implements DefaultApi {

	private static final Logger log = LoggerFactory.getLogger(DefaultApiController.class);
	final int size=10;
	Map<String,String> response = new HashMap<String, String>();
	HashSet<Long> phset=new HashSet<Long>();

	long lastAssigned=1111111111;
	long start=1111111111;
	long end=9999999999L;
	String assigned=new String();
	boolean isValid=true;
	private final ObjectMapper objectMapper;
	private final HttpServletRequest request;

	@org.springframework.beans.factory.annotation.Autowired
	public DefaultApiController(ObjectMapper objectMapper, HttpServletRequest request) {
		this.objectMapper = objectMapper;
		this.request = request;
	}

	//generate fancy phone number
	@RequestMapping(value = "/fancy/{phnum}",
			produces = { "application/json" }, 
			method = RequestMethod.GET)
	ResponseEntity<Map<String, String>> fancyPhnumGet(@ApiParam(value = "input fancy number by user",required=true) @PathVariable("phnum") String phnum){
		int size=phnum.length();
		long phnum1=Long.parseLong(phnum);
		if(size==10 && phnum1>start && phnum1 <=end && !phset.contains(phnum1)) {
			phset.add(phnum1);
			assigned=String.valueOf(phnum1);
			isValid=true;
		} 
		else {

			if(phset.contains(phnum1))lastAssigned++;

			while(phset.contains(lastAssigned)) {
				if(lastAssigned<end) {
					lastAssigned++;
				}
				else {
					isValid=false;
					break;
				}
			}
			if(lastAssigned<=end) phset.add(lastAssigned);
			assigned=String.valueOf(lastAssigned);
		}

		if(isValid) {
			response.clear();
			response.put("success phone number assigned",assigned);
			return ResponseEntity.accepted().body(response);
		}
		else {
			response.clear();
			isValid=true;
			response.put("not ok ","out of range");
			return ResponseEntity.accepted().body(response);
		}
	}
	//get a phone number 
	public ResponseEntity<Map<String, String>> rootGet() {
		while(phset.contains(lastAssigned)) {
			if(lastAssigned<end) {
				lastAssigned++;
			}
			else {
				isValid=false;
				break;
			}
		}
		if(lastAssigned<=end) phset.add(lastAssigned);
		else isValid=false;

		String assigned=String.valueOf(lastAssigned);

		if(isValid) {
			response.clear();
			response.put("success phone number assigned",assigned);
			return ResponseEntity.accepted().body(response);
		}
		else {
			response.clear();
			isValid=true;
			response.put("not ok ","out of range");
			return ResponseEntity.accepted().body(response);
		}
	}
}
