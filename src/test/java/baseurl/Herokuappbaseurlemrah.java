package baseurl;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class Herokuappbaseurlemrah {

    protected RequestSpecification spec;

    @Before
    public void setupbaseurl(){
        spec = new RequestSpecBuilder().setBaseUri("https://restful-booker.herokuapp.com").build();

    }

}
