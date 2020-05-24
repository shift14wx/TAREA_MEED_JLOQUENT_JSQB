package org.jloquent;

public class env implements DBConfig{
    @Override
    public DatabaseType getDatabaseType() {
        return DatabaseType.POSTGRES;
    }

    @Override
    public String getHostName() {
        return "localhost";
    }

    @Override
    public String getPortNumber() {
        return "5432";
    }

    @Override
    public String getDatabaseName() {
        return "test01";
    }

    @Override
    public String getUsername() {
        return "postgres";
    }

    @Override
    public String getPassword() {
        return "nike";
    }
}
