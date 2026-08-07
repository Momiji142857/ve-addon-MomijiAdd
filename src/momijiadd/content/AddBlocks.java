package momijiadd.content;

import mindustry.content.Blocks;
import mindustry.content.Items;
import mindustry.content.UnitTypes;
import mindustry.type.Category;
import mindustry.type.ItemStack;
import mindustry.world.Block;
import mindustry.world.blocks.storage.CoreBlock;
import mindustry.world.meta.BuildVisibility;
import momijiadd.type.ItemLiquidJunction;

import static mindustry.type.ItemStack.with;

/**
 * 模组本身的非联动方块.
 *
 * @see Blocks
 * @since 2026-08-07
 */
public class AddBlocks{
    public static Block

            //distribution
            itemLiquidJunction,

    // sandbox
    pogCore;

    public static void load(){
        //region distribution

        itemLiquidJunction = new ItemLiquidJunction("item-liquid-junction"){{
            requirements(Category.distribution, ItemStack.with(Items.copper, 3, Items.metaglass, 8, Items.graphite, 4));
            speed = 26f;
            capacity = 6;
            buildCostMultiplier = 3f;
            researchCostMultiplier = 0.2f;
        }};

        //endregion
        //region sandbox

        pogCore = new CoreBlock("pog-core"){{
            requirements(Category.effect, BuildVisibility.sandboxOnly, with());
            health = Integer.MAX_VALUE;
            armor = Integer.MAX_VALUE;
            size = 3;
            itemCapacity = 40000;
            unitCapModifier = 40;
            unitType = UnitTypes.emanate;
            alwaysUnlocked = true;
        }};

        //endregion
    }
}
