package mx.agarcia.ea.jda.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Sol4IT Mexico 2019 - Todos los derechos reservados
 * @category Almacenes Garcia / Ceteris- JDA API
 */
public class ConfigEntries {

    private String id;
    private boolean list;

    private HashMap<String, ConfigEntry> configMap = new HashMap();
    private List<ConfigEntry> configList = new ArrayList();

    public void addConfigEntry(ConfigEntry pConfig) {
        if (isList()) {
            this.configList.add(pConfig);
        } else {
            this.configMap.put(pConfig.getId(), pConfig);
        }
    }


    public ConfigEntry getEntry(String pId) {

        Iterator itKey = this.configMap
                             .keySet()
                             .iterator();
        String keyNext;
        /*System.out.println("      ***** <" + pId + ">getEntry CONFIGS  *****");
        while (itKey.hasNext()) {
            keyNext = (String) itKey.next();
            System.out.println("      Tiene esta llave -----------> " + keyNext);
        }*/


        return (ConfigEntry) this.configMap.get(pId);
    }

    public List<ConfigEntry> getEntries() {
        return this.configList;
    }

    public boolean containEntry(String pId) {
        boolean contained = false;
        if (!isList()) {
            contained = this.configMap.containsKey(pId);
        } else {
            Iterator<ConfigEntry> itKeys = this.configList.iterator();
            while (itKeys.hasNext()) {
                if ((pId != null) && (pId.equals(((ConfigEntry) itKeys.next()).getId()))) {
                    contained = true;
                    break;
                }
            }
        }
        return contained;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isList() {
        return this.list;
    }

    public void setList(boolean list) {
        this.list = list;
    }
}
