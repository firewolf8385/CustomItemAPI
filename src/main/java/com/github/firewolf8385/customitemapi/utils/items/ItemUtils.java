package com.github.firewolf8385.customitemapi.utils.items;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.UUID;

public class ItemUtils {

    public static String getStringData(ItemStack item, String keySring) {
        NamespacedKey key = new NamespacedKey(Bukkit.getPluginManager().getPlugin("CustomItemAPI"), keySring);
        PersistentDataContainer container = item.getItemMeta().getPersistentDataContainer();

        if(container.has(key, PersistentDataType.STRING)) {
            return container.get(key, PersistentDataType.STRING);
        }

        return null;
    }

    public static int getIntData(ItemStack item, String keySring) {
        NamespacedKey key = new NamespacedKey(Bukkit.getPluginManager().getPlugin("CustomItemAPI"), keySring);
        PersistentDataContainer container = item.getItemMeta().getPersistentDataContainer();

        if(container.has(key, PersistentDataType.INTEGER)) {
            return container.get(key, PersistentDataType.INTEGER);
        }

        return 0;
    }

    @SuppressWarnings("deprecation")
    public static ItemStack getHeadFromValue(String value) {
        UUID id = UUID.nameUUIDFromBytes(value.getBytes());
        int less = (int) id.getLeastSignificantBits();
        int most = (int) id.getMostSignificantBits();
        return Bukkit.getUnsafe().modifyItemStack(
                new ItemStack(Material.PLAYER_HEAD),
                "{SkullOwner:{Id:[I;" + (less * most) + "," + (less >> 23) + "," + (most / less) + "," + (most * 8731) + "],Properties:{textures:[{Value:\"" + value + "\"}]}}}"
        );
    }

    private static byte[] longToBytes(long x) {
        var buffer = ByteBuffer.allocate(Long.BYTES);
        buffer.putLong(x);
        return buffer.array();
    }

    @SuppressWarnings("deprecation")
    public static ItemStack createCustomHead(String texture) {
        UUID texUUID = UUID.nameUUIDFromBytes(texture.getBytes());
        byte[] bytesA = longToBytes(texUUID.getMostSignificantBits());
        byte[] bytesB = longToBytes(texUUID.getLeastSignificantBits());
        int[] intList = new int[] {
                new BigInteger(Arrays.copyOfRange(bytesA, 0, (bytesA.length/2))).intValue(),
                new BigInteger(Arrays.copyOfRange(bytesA, (bytesA.length/2), bytesA.length)).intValue(),
                new BigInteger(Arrays.copyOfRange(bytesB, 0, (bytesB.length/2))).intValue(),
                new BigInteger(Arrays.copyOfRange(bytesB, (bytesB.length/2), bytesB.length)).intValue()
        };
        return Bukkit.getUnsafe().modifyItemStack(
                new ItemStack(Material.PLAYER_HEAD),
                "{SkullOwner:{" + MessageFormat.format("Id:[I;{0},{1},{2},{3}]", Long.toString(intList[0]), Long.toString(intList[1]), Long.toString(intList[2]), Long.toString(intList[3])) + ",Properties:{textures:[{Value:\"" + texture + "\"}]}}}"
        );
    }
}