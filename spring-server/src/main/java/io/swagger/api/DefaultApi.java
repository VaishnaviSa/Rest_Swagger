/**
 * NOTE: This class is auto generated by the swagger code generator program (2.4.5).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package io.swagger.api;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-05-31T22:55:27.830Z")

@Api(value = "default", description = "the default API")
public interface DefaultApi {

    @ApiOperation(value = "Get phone number", nickname = "rootGet", notes = "", tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Success response") })
    @RequestMapping(value = "/get",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<Map<String, String>> rootGet();

}


