package momijiadd.content.Test;

import arc.graphics.Color;
import mindustry.content.*;
import mindustry.gen.Sounds;
import mindustry.type.*;
import mindustry.world.Block;
import mindustry.world.blocks.production.GenericCrafter;
import mindustry.world.draw.*;
import momijiadd.MultiCrafter;
import momijiadd.content.VeAdd.VeContent;

import static momijiadd.ModLoad.addModName;

/**
 * 基于氘和氚的聚变燃料合成路线.
 *
 * @see Blocks
 * @since 2026-10-02
 */
public class TestFusionFuel{
    public static Liquid brine, lithium, heavyWater, deuterium, tritium;

    public static Block evaporationPlant, neutronActivator, heavyWaterFilter, electrolyticSeparator, fusionRefuellerPro;

    public static void load(boolean veEnabled){

        //Liquid
        //region

        brine = new Liquid("brine", Color.valueOf("f0be64")){{
            heatCapacity = 0.35f;
            viscosity = 0.55f;
            boilPoint = 0.53f;

            effect = StatusEffects.wet;
            gasColor = Color.valueOf("f0be64c4");

            if(veEnabled) databaseTag = "liquid-fluid";
        }};

        lithium = new Liquid("lithium", Color.valueOf("c88c28")){{
            flammability = 0.8f;
            explosiveness = 0.6f;
            temperature = 0.85f;
            heatCapacity = 1f;
            viscosity = 0.4f;
            boilPoint = 1.2f;
            coolant = false;

            effect = StatusEffects.burning;
            gasColor = Color.valueOf("c88c28c4");

            if(veEnabled) databaseTag = "liquid-fluid";
        }};

        // 与普通水略有差异
        heavyWater = new Liquid("heavy-water", Color.valueOf("596a96")){{
            heatCapacity = 0.45f;
            viscosity = 0.6f;
            boilPoint = 0.51f;

            effect = StatusEffects.wet;
            gasColor = Color.grays(0.9f);

            if(veEnabled) databaseTag = "liquid-fluid";
        }};

        deuterium = new Liquid("deuterium", Color.valueOf("f01e1e")){{
            gas = true;
            flammability = 1f;

            if(veEnabled) databaseTag = "gas-fluid";
        }};

        tritium = new Liquid("tritium", Color.valueOf("50fa50")){{
            gas = true;
            flammability = 1f;

            if(veEnabled) databaseTag = "gas-fluid";
        }};

        //endregion

        //Blocks
        //region crafting

        //来源于: https://www.mcmod.cn/item/7674.html
        evaporationPlant = new MultiCrafter("evaporation-plant"){{
            requirements(Category.crafting, ItemStack.with(Items.copper, 500, Items.metaglass, 60, Items.graphite, 80, Items.titanium, 120, Items.silicon, 50, Items.surgeAlloy, 30));
            size = 3;
            maxEfficiency = 2f;
            itemCapacity = 0;
            hideDetails = false;

            drawer = new DrawMulti(
                    new DrawRegion("-bottom"),
                    new DrawLiquidTile(Liquids.water, 7f),
                    new DrawLiquidTile(brine, 7f),
                    new DrawLiquidTile(lithium, 7f),
                    new DrawRegion()
            );

            recipes = new Recipe[]{
                    new Recipe(){{
                        inputLiquid = new LiquidStack(Liquids.water, 500f / 60f);
                        outputLiquid = new LiquidStack(brine, 5f / 60f);
                        inputHeat = 20f;
                        craftTime = 60f;
                    }},
                    new Recipe(){{
                        inputLiquid = new LiquidStack(brine, 50f / 60f);
                        outputLiquid = new LiquidStack(lithium, 5f / 60f);
                        inputHeat = 20f;
                        craftTime = 60f;
                    }}
            };

        }};

        //来源于: https://www.mcmod.cn/item/546149.html
        neutronActivator = new GenericCrafter("neutron-activator"){{
            requirements(Category.crafting, ItemStack.with(Items.lead, 60, Items.metaglass, 20, Items.thorium, 80, Items.silicon, 100, Items.plastanium, 40, Items.phaseFabric, 20));
            size = 3;

            drawer = new DrawMulti(
                    new DrawRegion("-bottom"),
                    new DrawLiquidTile(lithium, 7f),
                    new DrawLiquidTile(tritium, 7f),
                    new DrawRegion()
            );

            itemCapacity = 0;
            consumePower(196f / 60f);
            consumeLiquid(lithium, 1f / 60f);
            outputLiquid = new LiquidStack(tritium, 1f / 60f);
            craftTime = 60f * 2f / 3f;
        }};

        heavyWaterFilter = new GenericCrafter("heavy-water-filter"){{
            requirements(Category.crafting, ItemStack.with(Items.lead, 60, Items.metaglass, 35, Items.titanium, 40, Items.silicon, 60, Items.phaseFabric, 5));
            size = 2;
            floating = true;
            rotate = true;
            rotateDraw = false;
            drawArrow = true;

            drawer = new DrawMulti(
                    new DrawRegion("-bottom1"),
                    new DrawLiquidTile(Liquids.water, 4f),
                    new DrawBubbles(Color.valueOf("7693e3")){{
                        sides = 8;
                        recurrence = 3f;
                        spread = 6;
                        radius = 1.5f;
                        amount = 10;
                    }},
                    new DrawRegion("-bottom2"),
                    new DrawLiquidTile(heavyWater, 6f),
                    new DrawRegion(),
                    new DrawLiquidOutputs());

            hasPower = true;
            itemCapacity = 0;
            liquidCapacity = 200f;
            consumePower(256f / 60f);
            consumeLiquid(Liquids.water, 100f / 60f);
            outputLiquid = new LiquidStack(heavyWater, 1f / 60f);
            liquidOutputDirections = new int[]{0};
            craftTime = 60f;
        }};

        electrolyticSeparator = new GenericCrafter("electrolytic-separator"){{
            requirements(Category.crafting, ItemStack.with(Items.metaglass, 50, Items.graphite, 45, Items.titanium, 50, Items.surgeAlloy, 120));
            size = 2;
            floating = true;
            rotate = true;
            rotateDraw = false;
            drawArrow = true;

            drawer = new DrawMulti(
                    new DrawRegion(){{
                        name = addModName("heavy-water-filter-bottom1");
                    }},
                    new DrawLiquidTile(heavyWater, 4f),
                    new DrawBubbles(Color.valueOf("7693e3")){{
                        sides = 8;
                        recurrence = 3f;
                        spread = 4f;
                        amount = 15;
                    }},
                    new DrawLiquidTile(deuterium, 4f){{
                        padTop = 8f;
                    }},
                    new DrawBubbles(Color.valueOf("7693e3")){{
                        sides = 8;
                        recurrence = 3f;
                        spread = 6;
                        radius = 1.5f;
                        amount = 10;
                    }},
                    new DrawLiquidTile(Liquids.ozone, 4f){{
                        padTop = 8f;
                    }},
                    new DrawRegion(),
                    new DrawLiquidOutputs(),
                    new DrawGlowRegion("-glow"){{
                        color = Color.valueOf("faffd7");
                        alpha = 0.8f;
                    }}
            );

            hasPower = true;
            conductivePower = true;
            itemCapacity = 0;
            liquidCapacity = 60f;
            consumePower(200f / 60f);
            consumeLiquid(heavyWater, 10f / 60f);
            outputLiquids = LiquidStack.with(deuterium, 3f / 60f, Liquids.ozone, 2f / 60f);
            liquidOutputDirections = new int[]{1, 3};
            craftTime = 60f;
        }};

        if(veEnabled){
            fusionRefuellerPro = new GenericCrafter("fusion-refueller-pro"){{
                requirements(Category.crafting, ItemStack.with(Items.lead, 120, Items.silicon, 100, Items.thorium, 70, VeContent.catalyzon, 20, VeContent.fibralt, 40, VeContent.warpNucleus, 5));
                size = 2;

                drawer = new DrawMulti(
                        new DrawRegion("-bottom1"),
                        new DrawLiquidTile(tritium, 3),
                        new DrawRegion("-bottom2"),
                        new DrawLiquidTile(deuterium, 6),
                        new DrawArcSmelt(){{
                            midColor =  Color.valueOf("bf92f9");
                            flameColor = Color.valueOf("9eabf7");
                            flameRad = 0.8f;
                            flameRadiusScl = 2;

                        }},
                        new DrawRegion(),
                        new DrawGlowRegion("-glow"){{
                            alpha = 0.9f;
                            glowScale = 6f;
                            color = Color.valueOf("9eabf7");
                        }}
                );

                consumePower(192f / 60f);
                consumeItem(VeContent.catalyzon, 1);
                consumeLiquids(LiquidStack.with(deuterium, 0.1f / 60f, tritium, 0.1f / 60f));
                outputItem = new ItemStack(VeContent.fusionFuel, 5);
                craftTime = 60f;

                itemCapacity = 50;
                liquidCapacity = 5f;
                ambientSound = Sounds.loopHum;
                ambientSoundVolume = 0.08f;
                craftEffect = Fx.generatespark;
            }};
        }

        //endregion

    }

    public static void loadLast(){
        evaporationPlant.requirements = ItemStack.with(Items.metaglass, 60, Items.silicon, 50, VeContent.aluminium, 500, VeContent.silicide, 50, VeContent.chromium, 40, VeContent.fibralt, 80);
        heavyWaterFilter.requirements = ItemStack.with(Items.lead, 60, Items.metaglass, 35, Items.silicon, 60, VeContent.catalyzon, 20, VeContent.fibralt, 40);
        electrolyticSeparator.requirements = ItemStack.with(Items.metaglass, 50, Items.graphite, 45, VeContent.chromium, 40, Items.surgeAlloy, 120);

        final Planet[] CYCLANT = {VeContent.cyclant, VeContent.phoon, VeContent.thavina};
        evaporationPlant.shownPlanets.addAll(CYCLANT);
        neutronActivator.shownPlanets.addAll(CYCLANT);
        heavyWaterFilter.shownPlanets.addAll(CYCLANT);
        electrolyticSeparator.shownPlanets.addAll(CYCLANT);
        fusionRefuellerPro.shownPlanets.addAll(CYCLANT);

    }
}
