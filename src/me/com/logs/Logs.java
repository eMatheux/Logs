package me.com.logs;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import me.com.logs.listeners.JoinEvent;
import me.com.logs.listeners.PlayerListeners;

public class Logs extends JavaPlugin {

	private static Logs i;
	private Map<String, File> nicks = new HashMap<String, File>();
	private File logs;

	@Override
	public void onLoad() {
	}

	@Override
	public void onEnable() {
		i = this;
		createFolder();
		loadPlayers();
		Bukkit.getPluginManager().registerEvents(new JoinEvent(), this);
		Bukkit.getPluginManager().registerEvents(new PlayerListeners(), this);
	}

	@Override
	public void onDisable() {
	}

	public static Logs getI() {
		return i;
	}

	public Map<String, File> getNick() {
		return nicks;
	}

	public File getFile() {
		return logs;
	}

	private void createFolder() {
		try {
			logs = new File(getDataFolder() + File.separator + "jogadores");
			if (!logs.exists()) {
				logs.mkdirs();
			}
		} catch (Exception e) {
			return;
		}
	}

	private void loadPlayers() {
		File[] files = logs.listFiles();

		for (File file : files) {
			nicks.put(file.getName(), file);
		}
	}

	public void addLine(File file, String[] lines) {
		BufferedWriter bufferedWriter;
		FileWriter writer;
		try {
			writer = new FileWriter(file, true);
			bufferedWriter = new BufferedWriter(writer);
			for (String s : lines) {
				bufferedWriter.write(s);
				bufferedWriter.newLine();
			}
			bufferedWriter.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}