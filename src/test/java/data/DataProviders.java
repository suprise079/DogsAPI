package data;

import org.apiguardian.api.API;
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


}
