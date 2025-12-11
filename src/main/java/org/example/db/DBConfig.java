package org.example.db;

public class DBConfig {
    public static final String HOST = "mysql-311d68c2-joegiunta3-1d69.j.aivencloud.com";
    public static final int PORT = 25644;
    public static final String DB_NAME = "defaultdb";
    public static final String USER = "avnadmin";

    public static final String PASSWORD =
            System.getProperty("AIVEN_MYSQL_PASSWORD", System.getenv("AIVEN_MYSQL_PASSWORD"));

    public static final String URL =
            "jdbc:mysql://" + HOST + ":" + PORT + "/" + DB_NAME
                    + "?sslMode=REQUIRED"
                    + "&useUnicode=true"
                    + "&characterEncoding=utf8"
                    + "&serverTimezone=UTC";
}
