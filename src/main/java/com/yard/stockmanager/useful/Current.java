package com.yard.stockmanager.useful;

import com.yard.stockmanager.persistence.entity.Funcionario;

public class Current
{
    private static Funcionario user;

    /**
     * @return the user
     */
    public static Funcionario getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public static void setUser(Funcionario user) {
        Current.user = user;
    }
}