package com.apiautomation.tests;
import com.apiautomation.base.*;import com.apiautomation.utils.TestDataFactory;import org.testng.annotations.Test;import static org.hamcrest.Matchers.*;
public class LoginTests extends BaseTest {@Test public void validLoginReturnsJwt()throws Exception{String e=TestDataFactory.email(),p=TestDataFactory.password();try{auth.register("Login User",e,p).then().statusCode(201);var r=auth.login(e,p);r.then().statusCode(200).body("accessToken",not(emptyOrNullString())).body("tokenType",equalTo("Bearer"));assertTime(r,TestConfig.loginThreshold());}finally{db.cleanup(e);}}
 @Test public void incorrectAndUnknownCredentialsReturn401(){auth.login("missing@example.test","wrong-pass").then().statusCode(401);}
 @Test public void missingLoginFieldsRejected(){auth.loginRaw(java.util.Map.of()).then().statusCode(400);}
}
