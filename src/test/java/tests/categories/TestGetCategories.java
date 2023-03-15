package tests.categories;

import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import utils.HTTPConfig;

public class TestGetCategories {

    RequestSpecification requestSpec;

    @BeforeClass
    public void initialiseObjects(){
        requestSpec = HTTPConfig.getCategoriesRequestSpec();
    }
}
