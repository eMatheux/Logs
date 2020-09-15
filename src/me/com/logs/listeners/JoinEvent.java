package me.com.logs.listeners;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Locale;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import me.com.logs.Logs;

public class JoinEvent implements Listener {

	Locale locale = new Locale("pt", "BR");
	private SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy 'às' HH:mm:ss", locale);

	@EventHandler
	public void join(PlayerJoinEvent e) {
		GregorianCalendar calendar = new GregorianCalendar();
		String date = format.format(calendar.getTime());
		if (!Logs.getI().getNick().containsKey(e.getPlayer().getName())) {
			try {
				File file = new File("plugins/Logs/jogadores/" + e.getPlayer().getName() + ".txt");
				Logs.getI().getNick().put(e.getPlayer().getName(), file);
				if (!file.exists()) {
					file.createNewFile();
					file.setWritable(true);
					file.setReadable(true);
					Logs.getI().addLine(file, new String[] { "[" + date + "]" + " Log criado com sucesso." });
					Logs.getI().addLine(file, new String[] { "[" + date + "]" + " " + e.getPlayer().getName()
							+ " Entrou no servidor pela primeira vez." });
				}
			} catch (IOException e2) {
				e2.printStackTrace();
			}
		}
	}
}
