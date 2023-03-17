package data;

import io.qameta.allure.Description;
import org.apiguardian.api.API;
import org.junit.jupiter.api.DisplayName;
import org.testng.annotations.DataProvider;

public class DataProviders {

    @DataProvider(name = "voteSubIdData")
    public static Object[][] getSubIdData(){
        //provide a second element in each array that will expalin the data
        String[][] data = {{"my-user-1234"}, {"test"}, {""}, {"ffffffffff"}};
        return data;
    }

    @DataProvider(name="voteIdData")
    public Object[][] getVoteIdData(){
        //provide a second element in each array that will expalin the data
        String existingVoteId = String.valueOf(APIData.GetVoteId());
        String[][] data = {
                {existingVoteId,"200", "SUCCESS"},
                {existingVoteId, "404", "NO_VOTE_FOUND_MATCHING_ID"},
                {"-50", "404","INVALID_ID"},
                {"sdkljfldsjkf", "404","INVALID_ID"}
        };
        return data;
    }

    @DataProvider(name="favouriteIdData")
    public Object[][] getFavouriteIdData(){
        //provide a second element in each array that will expalin the data
        String existingFavouriteId = String.valueOf(APIData.GetFavouriteId());
        String[][] data = {
                {existingFavouriteId,"200", "SUCCESS"},
                {existingFavouriteId, "404", "NO_FAVOURITE_FOUND_MATCHING_ID"},
                {"-50", "404","INVALID_ID"},
                {"sdkljfldsjkf", "404","INVALID_ID"}
        };
        return data;
    }

    @DataProvider(name="voteData")
    public Object[][] getUpVoteData(){
        //provide a second element in each array that will expalin the data
        String[][] data = {
                {"asf100","my-user-1234", "2", "201", "SUCCESS"},
                {"","my-user-1234", "2","400", "is not allowed to be empty"},
                {"false","my-user-1234", "2", "400", "is required"},
                //
                {"asf100","", "2","400", "is not allowed to be empty"},
                {"asf100","false", "2", "400", "is required"},
                {"asf100","sub0023", "2","201", "SUCCESS"},
                //
                {"asf100","sub0023", "","400", "is not allowed to be empty"},
                {"asf100","false", "false","400", "is required"},
                {"asf100","sub0023", "2","201", "SUCCESS"},
                {"asf100","sub0023", "-10","201", "SUCCESS"}
        };
        return data;
    }

    @DataProvider(name="breedData")
    @Description("This data provider will provide the limit and page values for the get breeds request.")
    @DisplayName("Breed data provider")
    public Object[][] getBreedData(){

        String[][] data = {
                {"10", "0", "Testing with valid data"},
                {"10", "100", "Testing with out of range page"},
                {"-1", "0", "Testing with negative limit"},
                {"0", "0", "Testing with zero limit"},
                {"", "0", "Testing with empty limit"},
                {"10", "-1", "Testing with negative page"},
                {"10", "", "Testing with empty page"},
                {"", "", "Testing with empty limit and page"},
        };

        return data;
    }


}
