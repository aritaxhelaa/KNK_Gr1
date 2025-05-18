package utils;

import models.User;
import utils.SessionSearchData;

public class SessionManager {
    private static User currentUser;

    public static void setCurrentUser(User user) {
        currentUser = user;
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void clearSession() {
        currentUser = null;
    }

    public static boolean isLoggedIn() {
        return currentUser != null;
    }
    private static utils.SessionSearchData searchData;

    public static void setSearchData(utils.SessionSearchData data) {
        searchData = data;
    }

    public static utils.SessionSearchData getSearchData() {
        return searchData;
    }

}
