package dk.ucn.datamatiker.mwe.movechair.Helpers;

import dk.ucn.datamatiker.mwe.movechair.Models.UserModel;

public final class UserHelper {

    private static UserModel user;

    public static UserModel getUser() {
        return user;
    }

    public static void setUser(UserModel user) {
        UserHelper.user = user;
    }
}
