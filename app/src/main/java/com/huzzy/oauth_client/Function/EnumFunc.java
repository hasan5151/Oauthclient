package com.huzzy.oauth_client.Function;

public class EnumFunc {
    public static String toString(Enum... scope) {
        StringBuilder scopes = new StringBuilder();
        for (int i = 0; i <= scope.length - 1; i++) {
            if (i == scope.length - 1)
                scopes.append(scope[i]);
            else
                scopes.append(scope[i]).append(" ");
        }
        return scopes.toString();
    }
}
