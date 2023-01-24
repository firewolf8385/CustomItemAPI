package com.github.firewolf8385.customitemapi.gui;

import com.github.firewolf8385.customitemapi.CustomItemAPI;
import com.github.firewolf8385.customitemapi.addon.Addon;
import com.github.firewolf8385.customitemapi.utils.gui.CustomGUI;
import com.github.firewolf8385.customitemapi.utils.items.ItemBuilder;
import com.github.firewolf8385.customitemapi.utils.items.SkullBuilder;
import org.bukkit.Material;

public class AddonBrowseGUI extends CustomGUI {

    public AddonBrowseGUI(int page) {
        super(54, "Addon Browser");

        int[] fillers = {0,1,2,3,4,5,6,7,8,45,46,47,48,49,50,51,52,53};
        for(int i : fillers) {
            setItem(i, new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE).setDisplayName(" ").build());
        }

        int i = 0;
        for(Addon addon : CustomItemAPI.getAddons()) {
            setItem(i + 9, addon.getIcon(), (p, a) -> new AddonBrowseGUI(addon).open(p));
            i++;
        }

        if(page == 1) {
            setItem(38, new SkullBuilder("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZjg0ZjU5NzEzMWJiZTI1ZGMwNThhZjg4OGNiMjk4MzFmNzk1OTliYzY3Yzk1YzgwMjkyNWNlNGFmYmEzMzJmYyJ9fX0=").setDisplayName("&cNo more pages!").build());
        }
        else {
            setItem(38, new SkullBuilder("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzU0YWFhYjM5NzY0NjQxZmY4NjI3OTMyZDJmZTFhNGNjY2VkOTY1Mzc1MDhkNGFiYzZjZDVmYmJiODc5MTMifX19").setDisplayName("&aPage " + (page - 1)).build(), (p,a) -> {
                new AddonBrowseGUI(page - 1).open(p);
            });
        }

        if(CustomItemAPI.getAddons().size() > (page * 21)) {
            setItem(42, new SkullBuilder("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjFkMGY4MmEyYTRjZGQ4NWY3OWY0ZDlkOTc5OGY5YzNhNWJjY2JlOWM3ZjJlMjdjNWZjODM2NjUxYThmM2Y0NSJ9fX0=").setDisplayName("&aPage " + (page + 1)).build(), (p,a) -> {
                new AddonBrowseGUI(page + 1).open(p);
            });
        }
        else {
            setItem(42, new SkullBuilder("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZmNmZTg4NDVhOGQ1ZTYzNWZiODc3MjhjY2M5Mzg5NWQ0MmI0ZmMyZTZhNTNmMWJhNzhjODQ1MjI1ODIyIn19fQ==").setDisplayName("&cNo more pages!").build());
        }
    }

    public AddonBrowseGUI(Addon addon) {
        super(54, addon.getPlugin().getName());

        int[] fillers = {0,1,2,3,4,5,6,7,8,45,46,47,48,49,50,51,52,53};
        for(int i : fillers) {
            setItem(i, new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE).setDisplayName(" ").build());
        }

        ItemBuilder items = new ItemBuilder(Material.GOLDEN_SWORD).setDisplayName("&aView Items");
        ItemBuilder enchantments = new ItemBuilder(Material.ENCHANTED_BOOK).setDisplayName("&aView Enchantments");

        setItem(13, addon.getIcon());
        setItem(29, items.build(), (p,a) -> new ItemBrowseGUI(addon, 1).open(p));
        setItem(33, enchantments.build());
    }
}
