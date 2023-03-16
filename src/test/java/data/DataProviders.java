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


}
