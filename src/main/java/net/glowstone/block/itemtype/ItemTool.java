package net.glowstone.block.itemtype;

import net.glowstone.EventFactory;
import net.glowstone.block.GlowBlock;
import net.glowstone.entity.GlowPlayer;
import org.bukkit.GameMode;
import org.bukkit.block.BlockFace;
import org.bukkit.event.player.PlayerItemBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

public abstract class ItemTool extends ItemType {

    private final int maxDurability;

    public ItemTool(int maxDurability) {
        setMaxStackSize(1);
        this.maxDurability = maxDurability;
    }

    @Override
    public void rightClickBlock(GlowPlayer player, GlowBlock target, BlockFace face, ItemStack holding, Vector clickedLoc) {
        if (onToolRightClick(player, holding, target, face, clickedLoc)) {
            damageTool(player, holding, 1);
        }
    }

    protected void damageTool(GlowPlayer player, ItemStack holding, int damage) {
        if (player.getGameMode() == GameMode.CREATIVE || damage == 0) {
            return;
        }

        holding.setDurability((short) (holding.getDurability() + damage));
        if (holding.getDurability() == maxDurability + damage) {
            EventFactory.callEvent(new PlayerItemBreakEvent(player, holding));
            player.getInventory().remove(holding);
        }
    }

    /**
     * Called when a player used (right clicked with) the tool.
     * @param player The player using the tool
     * @param tool The tool
     * @param target The block right clicked with the tool
     * @param face The clicked BlockFace
     * @param clickedLoc The click location on the block
     * @return true if the tool's durability should be decreased, false otherwise
     */
    protected boolean onToolRightClick(GlowPlayer player, ItemStack tool, GlowBlock target, BlockFace face, Vector clickedLoc) {
        // to be overridden in subclasses
        return false;
    }
    
    /**
     * Called when a player break a block.
     * @param player The player using the tool
     * @param tool The tool
     * @param target The block right clicked with the tool
     * @param face The clicked BlockFace
     * @param clickedLoc The click location on the block
     */
    @Override
    public void onBreakBlock(GlowPlayer player, GlowBlock target, ItemStack holding) {
        damageTool(player, holding, calculateBreakDamage(target));
    }

    public int calculateBreakDamage(GlowBlock target) {
        return 1;
    }
}
