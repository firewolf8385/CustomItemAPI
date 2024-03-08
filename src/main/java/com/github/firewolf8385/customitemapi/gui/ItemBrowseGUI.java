package com.github.firewolf8385.customitemapi.gui;

import com.github.firewolf8385.customitemapi.CustomItemAPIPlugin;
import com.github.firewolf8385.customitemapi.addon.Addon;
import com.github.firewolf8385.customitemapi.items.CustomItem;
import com.github.firewolf8385.customitemapi.utils.gui.CustomGUI;
import com.github.firewolf8385.customitemapi.utils.items.ItemBuilder;
import com.github.firewolf8385.customitemapi.utils.items.SkullBuilder;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.List;

public class ItemBrowseGUI extends CustomGUI {

    public ItemBrowseGUI(int page) {
        super(54, "Item Browser");

        int[] fillers = {0,1,2,3,4,5,6,7,8,45,46,47,48,49,50,51,52,53};
        for(int i : fillers) {
            setItem(i, new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE).setDisplayName(" ").build());
        }

        int i = 0;
        for(Addon addon : CustomItemAPIPlugin.getAddons()) {
            setItem(i + 9, addon.getIcon(), (p, a) -> new ItemBrowseGUI(addon, 1).open(p));
            i++;
        }

        if(page == 1) {
            setItem(38, new SkullBuilder("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZjg0ZjU5NzEzMWJiZTI1ZGMwNThhZjg4OGNiMjk4MzFmNzk1OTliYzY3Yzk1YzgwMjkyNWNlNGFmYmEzMzJmYyJ9fX0=").setDisplayName("&cNo more pages!").build());
        }
        else {
            setItem(38, new SkullBuilder("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzU0YWFhYjM5NzY0NjQxZmY4NjI3OTMyZDJmZTFhNGNjY2VkOTY1Mzc1MDhkNGFiYzZjZDVmYmJiODc5MTMifX19").setDisplayName("&aPage " + (page - 1)).build(), (p,a) -> {
                new ItemBrowseGUI(page - 1).open(p);
            });
        }

        if(CustomItemAPIPlugin.getAddons().size() > (page * 21)) {
            setItem(42, new SkullBuilder("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjFkMGY4MmEyYTRjZGQ4NWY3OWY0ZDlkOTc5OGY5YzNhNWJjY2JlOWM3ZjJlMjdjNWZjODM2NjUxYThmM2Y0NSJ9fX0=").setDisplayName("&aPage " + (page + 1)).build(), (p,a) -> {
                new ItemBrowseGUI(page + 1).open(p);
            });
        }
        else {
            setItem(42, new SkullBuilder("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZmNmZTg4NDVhOGQ1ZTYzNWZiODc3MjhjY2M5Mzg5NWQ0MmI0ZmMyZTZhNTNmMWJhNzhjODQ1MjI1ODIyIn19fQ==").setDisplayName("&cNo more pages!").build());
        }

        setItem(40, new ItemBuilder(Material.NETHER_STAR).setDisplayName("&aAll Items").build(), (p,a) -> new ItemBrowseGUI(1, true).open(p));
    }

    public ItemBrowseGUI(Addon addon, int page) {
        super(54, "Item Browser - " + addon.getPlugin().getName());

        int[] fillers = {1,2,3,4,5,6,7,8,45,46,47,48,49,50,51,52,53};
        for(int i : fillers) {
            setItem(i, new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE).setDisplayName(" ").build());
        }

        List<CustomItem> items = new ArrayList<>(addon.getItems());
        int amount = 0;
        for(int i = ((page - 1) * 27); i <= items.size(); i++) {
            if(amount >= 27 || i >= items.size()) {
                break;
            }

            setItem(amount + 9, items.get(i).toItemStack());
            amount++;
        }

        if(page == 1) {
            setItem(38, new SkullBuilder("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZjg0ZjU5NzEzMWJiZTI1ZGMwNThhZjg4OGNiMjk4MzFmNzk1OTliYzY3Yzk1YzgwMjkyNWNlNGFmYmEzMzJmYyJ9fX0=").setDisplayName("&cNo more pages!").build());
        }
        else {
            setItem(38, new SkullBuilder("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzU0YWFhYjM5NzY0NjQxZmY4NjI3OTMyZDJmZTFhNGNjY2VkOTY1Mzc1MDhkNGFiYzZjZDVmYmJiODc5MTMifX19").setDisplayName("&aPage " + (page - 1)).build(), (p,a) -> {
                new ItemBrowseGUI(page - 1, true).open(p);
            });
        }

        if(items.size() > (page * 27)) {
            setItem(42, new SkullBuilder("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjFkMGY4MmEyYTRjZGQ4NWY3OWY0ZDlkOTc5OGY5YzNhNWJjY2JlOWM3ZjJlMjdjNWZjODM2NjUxYThmM2Y0NSJ9fX0=").setDisplayName("&aPage " + (page + 1)).build(), (p,a) -> {
                new ItemBrowseGUI(page + 1, true).open(p);
            });
        }
        else {
            setItem(42, new SkullBuilder("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZmNmZTg4NDVhOGQ1ZTYzNWZiODc3MjhjY2M5Mzg5NWQ0MmI0ZmMyZTZhNTNmMWJhNzhjODQ1MjI1ODIyIn19fQ==").setDisplayName("&cNo more pages!").build());
        }

        setItem(0, new SkullBuilder("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZjg0ZjU5NzEzMWJiZTI1ZGMwNThhZjg4OGNiMjk4MzFmNzk1OTliYzY3Yzk1YzgwMjkyNWNlNGFmYmEzMzJmYyJ9fX0=").setDisplayName("&cBack").build(), (p,a) -> {
            new ItemBrowseGUI(1).open(p);
        });
    }

    public ItemBrowseGUI(int page, boolean showAll) {
        super(54, "Item Browser - All Items");

        int[] fillers = {1,2,3,4,5,6,7,8,45,46,47,48,49,50,51,52,53};
        for(int i : fillers) {
            setItem(i, new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE).setDisplayName(" ").build());
        }

        List<CustomItem> items = new ArrayList<>(CustomItemAPIPlugin.getCustomItems().values());

        int amount = 0;
        for(int i = ((page - 1) * 27); i <= items.size(); i++) {
            if(amount >= 27 || i >= items.size()) {
                break;
            }

            setItem(amount + 9, items.get(i).toItemStack());
            amount++;
        }

        if(page == 1) {
            setItem(38, new SkullBuilder("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZjg0ZjU5NzEzMWJiZTI1ZGMwNThhZjg4OGNiMjk4MzFmNzk1OTliYzY3Yzk1YzgwMjkyNWNlNGFmYmEzMzJmYyJ9fX0=").setDisplayName("&cNo more pages!").build());
        }
        else {
            setItem(38, new SkullBuilder("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzU0YWFhYjM5NzY0NjQxZmY4NjI3OTMyZDJmZTFhNGNjY2VkOTY1Mzc1MDhkNGFiYzZjZDVmYmJiODc5MTMifX19").setDisplayName("&aPage " + (page - 1)).build(), (p,a) -> {
                new ItemBrowseGUI(page - 1, true).open(p);
            });
        }

        if(items.size() > (page * 27)) {
            setItem(42, new SkullBuilder("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjFkMGY4MmEyYTRjZGQ4NWY3OWY0ZDlkOTc5OGY5YzNhNWJjY2JlOWM3ZjJlMjdjNWZjODM2NjUxYThmM2Y0NSJ9fX0=").setDisplayName("&aPage " + (page + 1)).build(), (p,a) -> {
                new ItemBrowseGUI(page + 1, true).open(p);
            });
        }
        else {
            setItem(42, new SkullBuilder("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZmNmZTg4NDVhOGQ1ZTYzNWZiODc3MjhjY2M5Mzg5NWQ0MmI0ZmMyZTZhNTNmMWJhNzhjODQ1MjI1ODIyIn19fQ==").setDisplayName("&cNo more pages!").build());
        }

        setItem(0, new SkullBuilder("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZjg0ZjU5NzEzMWJiZTI1ZGMwNThhZjg4OGNiMjk4MzFmNzk1OTliYzY3Yzk1YzgwMjkyNWNlNGFmYmEzMzJmYyJ9fX0=").setDisplayName("&cBack").build(), (p,a) -> {
            new ItemBrowseGUI(1).open(p);
        });
    }
}