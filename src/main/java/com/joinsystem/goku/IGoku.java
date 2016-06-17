package com.joinsystem.goku;

import com.joinsystem.goku.common.constants.Constants;
import com.joinsystem.goku.remote.ISimpleComputeRemote;
import com.joinsystem.goku.remote.SimpleComputeRemote;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

/**
 * <p>Date : 16/6/15</p>
 * <p>Time : 下午4:55</p>
 *
 * @author jerry
 */
public class IGoku {
    private static final Logger LOG = LoggerFactory.getLogger("Goku RMI Main");
    private static int remote_port = Constants.DEFAULT_RMI_PORT;
    private static String server_host = Constants.DEFAULT_SERVER_HOST;



    public static void main(String[] args) {

        try {
            ISimpleComputeRemote simpleComputeRemote = new SimpleComputeRemote();
            LocateRegistry.createRegistry(remote_port);
            Naming.bind("rmi://" + server_host + ":" + remote_port + Constants.DEFAULT_SIMPLE_SERVICE_PATH, simpleComputeRemote);

            LOG.info("SimpleCompute Service Started.");
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (AlreadyBoundException e) {
            e.printStackTrace();
        }
    }

    public int getRemote_port() {
        return remote_port;
    }

    public void setRemote_port(int remote_port) {
        this.remote_port = remote_port;
    }

    public String getServer_host() {
        return server_host;
    }

    public void setServer_host(String server_host) {
        this.server_host = server_host;
    }
}
