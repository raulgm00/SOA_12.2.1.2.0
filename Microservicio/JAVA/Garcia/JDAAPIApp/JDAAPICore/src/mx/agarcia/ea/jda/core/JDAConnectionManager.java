package mx.agarcia.ea.jda.core;

import java.sql.Connection;

import javax.ejb.Remote;

@Remote
public interface JDAConnectionManager {
    public Connection getConnection();
}
