package tailmaster.model;

/**
 * User: Halil KARAKOSE
 * Date: 14.Oca.2009
 * Time: 11:31:55
 */
public class LogFile {
    private int id;
    private int serverId;
    private String alias;
    private String fileDestination;

    public LogFile(String alias, String fileDestination) {
        this(0, alias, fileDestination);
    }

    public LogFile(int serverId, String alias, String fileDestination) {
        this(0, serverId, alias, fileDestination);
    }

    public LogFile(int id, int serverId, String alias, String fileDestination) {
        this.id = id;
        this.serverId = serverId;
        this.alias = alias;
        this.fileDestination = fileDestination;
    }

    public int getId() {
        return id;
    }

    public int getServerId() {
        return serverId;
    }

    public String getFileDestination() {
        return fileDestination;
    }

    public String getAlias() {
        return alias;
    }
}
