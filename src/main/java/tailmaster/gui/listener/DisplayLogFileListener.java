package tailmaster.gui.listener;

import tailmaster.model.LogFile;
import tailmaster.model.Server;
import tailmaster.model.LocationType;
import tailmaster.dao.ServerDao;
import tailmaster.gui.TailMasterFrame;
import tailmaster.gui.LogDisplayPanel;
import tailmaster.gui.CloseButtonTabbedPane;
import tailmaster.RemoteTailCommand;
import tailmaster.TailExecutor;
import tailmaster.LocalTailCommand;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * User: Halil KARAKOSE
 * Date: Jan 18, 2009
 * Time: 6:24:04 PM
 */
public class DisplayLogFileListener implements ActionListener {
	private LogFile logFile;

	public DisplayLogFileListener(LogFile logFile) {
		this.logFile = logFile;
	}

	public void actionPerformed(ActionEvent e) {
		Server server = ServerDao.getInstance().findById(logFile.getServerId());
		TailMasterFrame gui = TailMasterFrame.getInstance();
		CloseButtonTabbedPane tabbedPane = gui.getTabbedPane();

		LogDisplayPanel slcmJbossTab = new LogDisplayPanel();
		tabbedPane.addTab(logFile.getAlias(), null, slcmJbossTab, server.getHostname());
		if (LocationType.REMOTE.getLocationTypeId() == logFile.getLocationType()) {
			RemoteTailCommand command = new RemoteTailCommand(server, logFile, slcmJbossTab.getLogTextArea());
			TailExecutor tailExecutor = new TailExecutor(command);
			tailExecutor.start();
		} else {
			LocalTailCommand localTailCommand = new LocalTailCommand(logFile, slcmJbossTab.getLogTextArea());
			TailExecutor tailExecutor = new TailExecutor(localTailCommand);
			tailExecutor.start();
			System.out.println("start tail command here");

		}
	}
}