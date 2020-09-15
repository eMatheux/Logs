package me.com.logs.listeners;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Locale;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import me.com.logs.Logs;

public class PlayerListeners implements Listener {

	Locale locale = new Locale("pt", "BR");
	private SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy 'às' HH:mm:ss", locale);
	private GregorianCalendar calendar = new GregorianCalendar();
	private String date = format.format(calendar.getTime());

	@EventHandler
	public void join(PlayerJoinEvent e) {
		if (Logs.getI().getNick().containsKey(e.getPlayer().getName())) {
			File file = Logs.getI().getNick().get(e.getPlayer().getName());
			Logs.getI().addLine(file, new String[] {"[" + date + "] " + e.getPlayer().getName() + ":" + " Entrou no servidor"});
		}
	}

	@EventHandler
	public void quit(PlayerQuitEvent e) {

		if (Logs.getI().getNick().containsKey(e.getPlayer().getName())) {
			File file = Logs.getI().getNick().get(e.getPlayer().getName());
			Logs.getI().addLine(file, new String[] {"[" + date + "] " + e.getPlayer().getName() + ":" + " Saiu do servidor"});
		}
	}

	@EventHandler
	public void sendCommand(PlayerCommandPreprocessEvent e) {

		if (Logs.getI().getNick().containsKey(e.getPlayer().getName())) {
			File file = Logs.getI().getNick().get(e.getPlayer().getName());
			Logs.getI().addLine(file, new String[] {"[" + date + "] " + e.getPlayer().getName() + ":" + " " + e.getMessage()});
		}
	}

	@EventHandler
	public void writeOnChat(AsyncPlayerChatEvent e) {
		if (Logs.getI().getNick().containsKey(e.getPlayer().getName())) {
			File file = Logs.getI().getNick().get(e.getPlayer().getName());
			Logs.getI().addLine(file, new String[] {"[" + date + "] " + e.getPlayer().getName() + ":" + " " + e.getMessage() });
		}
	}

}
