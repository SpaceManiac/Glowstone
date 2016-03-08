package net.glowstone.block.itemtype;

import net.glowstone.block.GlowBlock;
import net.glowstone.entity.GlowLivingEntity;
import org.bukkit.Material;

public class ItemAxe extends ItemTool {

    public ItemAxe(Material material) {
        super(material.getMaxDurability());
    }

    @Override
    public short calculateBreakDamage(GlowBlock target) {
        return 1;
    }
    
    @Override
    protected short calculateAttackDamage(GlowLivingEntity target) {
        return 2;
    }
}
