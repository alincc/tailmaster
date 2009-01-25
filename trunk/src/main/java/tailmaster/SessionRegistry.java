package tailmaster;

import com.sshtools.j2ssh.session.SessionChannelClient;
import com.sshtools.j2ssh.SshClient;

import java.util.*;
import java.io.IOException;

/**
 * User: Halil KARAKOSE
 * Date: Jan 22, 2009
 * Time: 9:06:07 PM
 */
public class SessionRegistry {
    private static HashMap<Long, SessionChannelClient> sshChannelMap = new HashMap<Long, SessionChannelClient>();
    private static HashMap<Long, SshClient> connectionMap = new HashMap<Long, SshClient>();

    public static void put(long connectionId, SessionChannelClient ssh) {
        sshChannelMap.put(connectionId, ssh);
    }

    public static HashMap<Long, SessionChannelClient> getSshChannelMap() {
        return sshChannelMap;
    }

    public static void put(long connectionId, SshClient sshClient) {
        connectionMap.put(connectionId, sshClient);
    }

    public static HashMap<Long, SshClient> getConnectionMap() {
        return connectionMap;
    }

    public static void disconnect(long connectionId) {
        SessionChannelClient sessionChannel = sshChannelMap.get(connectionId);
        SshClient connection = connectionMap.get(connectionId);
        try {
            sessionChannel.close();
            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void disconnect() {
        Set<Long> connectionIdSet = sshChannelMap.keySet();
        for (Long connectionId : connectionIdSet) {
            SessionChannelClient sessionChannel = sshChannelMap.get(connectionId);
            SshClient client = connectionMap.get(connectionId);
            try {
                sessionChannel.close();
                client.disconnect();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }
}