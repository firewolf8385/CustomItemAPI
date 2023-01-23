package com.github.firewolf8385.customitemapi.enchantments;

import com.github.firewolf8385.customitemapi.utils.chat.ChatUtils;
import org.bukkit.event.player.PlayerInteractEvent;

public class TestEnchantment extends CustomEnchantment {

    public TestEnchantment() {
        super("test", "Best Tester");
    }

    public void onInteract(PlayerInteractEvent event) {
        ChatUtils.chat(event.getPlayer(), "&b&lTest Message");
    }
}