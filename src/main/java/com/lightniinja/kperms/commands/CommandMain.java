package com.lightniinja.kperms.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.lightniinja.kperms.KPermsPlugin;

public class CommandMain implements CommandExecutor {
	
	private CommandAddGroup commandAddGroup;
	private CommandAddPermission commandAddPermission;
	private CommandGroupAdd commandGroupAdd;
	private CommandGroupInfo commandGroupInfo;
	private CommandGroupPAdd commandGroupPAdd;
	private CommandGroupPRemove commandGroupPRemove;
	private CommandGroupRemove commandGroupRemove;
	private CommandHelp commandHelp;
	private CommandReload commandReload;
	private CommandRemoveGroup commandRemoveGroup;
	private CommandRemovePermission commandRemovePermission;
	private CommandSetGroup commandSetGroup;
	private CommandUnknown commandUnknown;
	private CommandUserInfo commandUserInfo;
	
	public CommandMain(KPermsPlugin plugin) {
		commandAddGroup = new CommandAddGroup(plugin);
		commandAddPermission = new CommandAddPermission(plugin);
		commandGroupAdd = new CommandGroupAdd(plugin);
		commandGroupInfo = new CommandGroupInfo(plugin);
		commandGroupPAdd = new CommandGroupPAdd(plugin);
		commandGroupPRemove = new CommandGroupPRemove(plugin);
		commandGroupRemove = new CommandGroupRemove(plugin);
		commandHelp = new CommandHelp(plugin);
		commandReload = new CommandReload(plugin);
		commandRemoveGroup = new CommandRemoveGroup(plugin);
		commandRemovePermission = new CommandRemovePermission(plugin);
		commandSetGroup = new CommandSetGroup(plugin);
		commandUnknown = new CommandUnknown(plugin);
		commandUserInfo = new CommandUserInfo(plugin);
	}
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(args.length == 0) {
			commandHelp.execute(sender, command, label, args);
		} else if(args.length == 1) {
			if(args[0].equalsIgnoreCase("help")) {
				commandHelp.execute(sender, command, label, args);
			} else if(args[0].equalsIgnoreCase("reload")) {
				commandReload.execute(sender, command, label, args);
			} else {
				commandUnknown.execute(sender, command, label, args);
			}
		} else if(args.length == 2) {
			if(args[0].equalsIgnoreCase("user")) {
				commandUserInfo.execute(sender, command, label, args);
			} else if (args[0].equalsIgnoreCase("group")) {
				commandGroupInfo.execute(sender, command, label, args);
			} else {
				commandUnknown.execute(sender, command, label, args);
			}
		} else if(args.length == 3) {
			if(args[0].equalsIgnoreCase("group")) {
				if(args[2].equalsIgnoreCase("add")) {
					commandGroupAdd.execute(sender, command, label, args);
				} else if(args[2].equalsIgnoreCase("remove")) {
					commandGroupRemove.execute(sender, command, label, args);
				} else {
					commandUnknown.execute(sender, command, label, args);
				}
			} else {
				commandUnknown.execute(sender, command, label, args);
			}
		}else if(args.length == 5) {
			if(args[0].equalsIgnoreCase("user")) {
				if(args[2].equalsIgnoreCase("permission")) {
					if(args[3].equalsIgnoreCase("add")) {
						commandAddPermission.execute(sender, command, label, args);
					} else if(args[3].equalsIgnoreCase("remove")) {
						commandRemovePermission.execute(sender, command, label, args);
					} else {
						commandUnknown.execute(sender, command, label, args);
					}
				} else if(args[2].equalsIgnoreCase("group")) {
					if(args[3].equalsIgnoreCase("set")) {
						commandSetGroup.execute(sender, command, label, args);
					} else if(args[3].equalsIgnoreCase("add")) {
						commandAddGroup.execute(sender, command, label, args);
					} else if(args[3].equalsIgnoreCase("remove")) {
						commandRemoveGroup.execute(sender, command, label, args);
					} else {
						commandUnknown.execute(sender, command, label, args);
					}
				} else {
					commandUnknown.execute(sender, command, label, args);
				}
			} else if(args[0].equalsIgnoreCase("group")) {
				if (args[2].equalsIgnoreCase("permission")) {
					if(args[3].equalsIgnoreCase("add")) {
						commandGroupPAdd.execute(sender, command, label, args);
					} else if(args[3].equalsIgnoreCase("remove")) {
						commandGroupPRemove.execute(sender, command, label, args);
					} else {
						commandUnknown.execute(sender, command, label, args);
					}
				} else {
					commandUnknown.execute(sender, command, label, args);
				}
			} else {
				commandUnknown.execute(sender, command, label, args);
			}
		} else {
			commandUnknown.execute(sender, command, label, args);
		}
		return true;
	}
}
