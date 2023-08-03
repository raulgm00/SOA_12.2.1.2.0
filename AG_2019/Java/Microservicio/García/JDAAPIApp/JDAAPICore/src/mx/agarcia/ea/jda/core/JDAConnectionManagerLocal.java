package mx.agarcia.ea.jda.core;

import java.sql.Connection;

import javax.ejb.Local;

@Local
public interface JDAConnectionManagerLocal {
    public Connection getConnection();
    
}
