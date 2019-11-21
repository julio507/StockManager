package com.yard.stockmanager.useful;

public class Current
{
    private static int user;

    /**
     * @return the user
     */
    public static int getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public static void setUser(int user) {
        Current.user = user;
    }
}