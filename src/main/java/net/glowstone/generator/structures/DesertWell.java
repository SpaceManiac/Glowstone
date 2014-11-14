package net.glowstone.generator.structures;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.util.Vector;

import net.glowstone.util.BlockStateDelegate;

public class DesertWell extends Structure {

    public DesertWell(Location location, BlockStateDelegate delegate) {
        super(location, delegate);
        setSize(new Vector(5, 6, 5));
    }

    @Override
    public boolean generate() {

        cuboid.offset(new Vector(-2, -2, -2));

        while (getBlockState(new Vector(2, 1, 2)).getType() == Material.AIR && cuboid.getMin().getBlockY() > 0) {
            cuboid.offset(new Vector(0, -1, 0));
        }

        if (getBlockState(new Vector(2, 1, 2)).getType() != Material.SAND) {
            return false;
        }

        for (int x = 0; x < getSize().getBlockX(); x++) {
            for (int z = 0; z < getSize().getBlockZ(); z++) {
                if (getBlockState(new Vector(x, 0, z)).getType() == Material.AIR
                        && getBlockState(new Vector(x, -1, z)).getType() == Material.AIR) {
                    return false;
                }
            }
        }

        fill(new Vector(0, 0, 0), new Vector(4, 2, 4), Material.SANDSTONE);
        fill(new Vector(1, 2, 1), new Vector(3, 2, 3), Material.AIR);
        setBlock(new Vector(2, 2, 0), Material.STEP, 1);
        setBlock(new Vector(0, 2, 2), Material.STEP, 1);
        setBlock(new Vector(4, 2, 2), Material.STEP, 1);
        setBlock(new Vector(2, 2, 4), Material.STEP, 1);
        fill(new Vector(2, 1, 1), new Vector(2, 1, 3), Material.STATIONARY_WATER);
        fill(new Vector(1, 1, 2), new Vector(3, 1, 2), Material.STATIONARY_WATER);

        fill(new Vector(1, 2, 1), new Vector(1, 4, 1), Material.SANDSTONE);
        fill(new Vector(1, 2, 3), new Vector(1, 4, 3), Material.SANDSTONE);
        fill(new Vector(3, 2, 1), new Vector(3, 4, 1), Material.SANDSTONE);
        fill(new Vector(3, 2, 3), new Vector(3, 4, 3), Material.SANDSTONE);

        fill(new Vector(1, 5, 1), new Vector(3, 5, 3), Material.STEP, 1);
        setBlock(new Vector(2, 5, 2), Material.SANDSTONE);

        return true;
    }
}
