/*
API KEY:
lmao
 */
//12025000 users

public class Main {
private static String TOKEN; //osu api token
    public static void main(String[] args) {
        try {
            PeppyApi api = new PeppyApi(TOKEN);
            PeppyUser user = new PeppyUser(api, 6487115);
            System.out.println(user.getPp_rank() + " test");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

