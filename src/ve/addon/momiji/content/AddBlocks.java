package ve.addon.momiji.content;

import arc.graphics.Color;
import mindustry.content.Fx;
import mindustry.content.Items;
import mindustry.content.Liquids;
import mindustry.content.UnitTypes;
import mindustry.entities.effect.ParticleEffect;
import mindustry.gen.Sounds;
import mindustry.type.*;
import mindustry.world.Block;
import mindustry.world.blocks.production.AttributeCrafter;
import mindustry.world.blocks.production.Drill;
import mindustry.world.blocks.production.GenericCrafter;
import mindustry.world.blocks.storage.Unloader;
import mindustry.world.blocks.units.UnitCargoLoader;
import mindustry.world.draw.*;
import mindustry.world.meta.BlockGroup;
import ve.addon.momiji.AddType.ItemLiquidJunction;
import ve.addon.momiji.AddType.LinkedDrill;

import static mindustry.type.ItemStack.with;

public class AddBlocks {

    public static Block itemLiquidJunction,
            powerDrillPro, beamDrillPro,
            railLiquidJunction, isomorphicUnloader,
            saltElectrolyzerPro, sandHoter,
            platformThetaPro, platformLambdaPro;

    public static void load() {
        itemLiquidJunction = new ItemLiquidJunction("item-liquid-junction") {{
            requirements(Category.distribution, ItemStack.with(Items.copper, 3, Items.metaglass, 8, Items.graphite, 4));
            speed = 26f;
            capacity = 6;
            buildCostMultiplier = 6f;
            researchCostMultiplier = 0.2f;
        }};

        powerDrillPro = new LinkedDrill("power-drill-pro") {{
            requirements(Category.production, ItemStack.with(Items.metaglass, 2, Items.graphite, 20, Items.silicon, 3));
            dumpTime = 1;
            size = 2;
            tier = 3;
            hasPower = true;
            conductivePower = true;
            rotateSpeed = 5f;
            liquidBoostIntensity = 1.8f;
            warmupSpeed = 0.03f;
            drillMultipliers.put(Items.sporePod, 0f);
            drillTime = 320f;
            itemCapacity = 10; // -
            consumeLiquid(Liquids.water, 1.5f / 60f).boost();
            consumePower(10f / 60f);
            researchCostMultiplier = 0.02f;
        }};

        beamDrillPro = new Drill("beam-drill-pro") {{
            requirements(Category.production, ItemStack.with(Items.lead, 60, Items.graphite, 60, Items.metaglass, 30, Items.silicon, 35));
            tier = 4;
            drillTime = 108f;
            liquidBoostIntensity = 1.8f;
            warmupSpeed = 0.01f;
            drillMultipliers.put(Items.sporePod, 0f); // -
            rotateSpeed = 7f;
            updateEffect = new ParticleEffect() {{
                colorFrom = Color.valueOf("ffffff");
                colorTo = Color.valueOf("ffdaa9");
                particles = 4;
                length = 20f; // -
                baseLength = 0f; // -
                line = true;
                strokeFrom = 3f;
                strokeTo = 0f; // -
                lenFrom = 10f;
                lenTo = 0f;
                lifetime = 10f;
            }};
            drawRim = true;
            heatColor = Color.valueOf("ffdaa9");
            size = 3;
            itemCapacity = 30;
            hasPower = true;
            consumeLiquid(Liquids.water, 5f / 60f).boost();
            consumePower(102f / 60f);
            ambientSound = Sounds.loopMineBeam;
            ambientSoundVolume = 0.02f;
            researchCostMultiplier = 0.02f;
        }};

        railLiquidJunction = new ItemLiquidJunction("rail-liquid-junction") {{
            requirements(Category.distribution, ItemStack.with(Items.graphite, 4));
            speed = 10f;
            capacity = 5;
            displayedSpeed = 24f; // 60f / 10f * 5 = 30f 实测30帧下稳定25.0
            buildCostMultiplier = 6f;
            squareSprite = false;
            researchCostMultiplier = 0.2f;
        }};

        isomorphicUnloader = new Unloader("isomorphic-unloader") {{
            requirements(Category.distribution, with(Items.silicon, 30));
            speed = 60f / 11f;
            group = BlockGroup.transportation;
            researchCostMultiplier = 0.02f;
        }};

        saltElectrolyzerPro = new GenericCrafter("salt-electrolyzer-pro") {{
            requirements(Category.crafting, ItemStack.with(Items.graphite, 30, Items.metaglass, 40));
            size = 2;
            craftTime = 60f;
            group = BlockGroup.liquids;
            liquidCapacity = 50f;
            hasItems = true;
            hasLiquids = true;
            hasPower = true;
            itemCapacity = 30;
            invertFlip = true;
            rotate = true;
            regionRotated1 = 3;
            liquidOutputDirections = new int[] {0, 1, 3};
            conductivePower = true;
            consumeLiquid(Liquids.water, 15f / 60f);
            consumePower(30f / 60f);
            ambientSound = Sounds.loopElectricHum;
            ambientSoundVolume = 0.08f;
            craftEffect = Fx.lightning;
            researchCostMultiplier = 0.02f;
        }};

        sandHoter = new AttributeCrafter("sand-hoter") {{
            requirements(Category.crafting, ItemStack.with(Items.graphite, 100, Items.metaglass, 40, Items.silicon, 40));
            size = 2;
            consumePower(42f / 60f);
            consumeItems(ItemStack.with(Items.sand, 3, Items.blastCompound, 1));
            craftTime = 60f / 2f;
            hasLiquids = true;
            hasItems = true;
            hasPower = true;
            liquidCapacity = 80f;
            itemCapacity = 60;
            outputsLiquid = true;
            ambientSound = Sounds.loopSmelter;
            ambientSoundVolume = 0.08f;
            drawer = new DrawMulti(
                    new DrawRegion(),
                    new DrawGlowRegion("-glow") {{
                        alpha = 0.9f;
                        color = Color.valueOf("ffa665");
                        glowScale = 5f;
                    }},
                    new DrawFlame(Color.valueOf("ffb6a5"))
            );

            boostScale = 0.5f;
            maxBoost = 3f;
            minEfficiency = -1f;
        }};

        platformThetaPro = new UnitCargoLoader("platform-theta-pro") {{
            requirements(Category.units, ItemStack.with(Items.lead, 160, Items.silicon, 100));
            size = 2;
            buildTime = 900f;
            unitType = UnitTypes.alpha;
            consumePower(90f / 60f);
            itemCapacity = 0;
            polySides = 4;
            polyRadius = 5f;
            polyStroke = 1f;
            acceptsItems = false;
            solid = false;
            underBullets = true;
            shownPlanets.addAll(VeContent.cyclant, VeContent.phoon, VeContent.maress, VeContent.thavina);
            researchCostMultiplier = 0.02f;
        }};

        platformLambdaPro = new UnitCargoLoader("platform-lambda-pro") {{
            requirements(Category.units, ItemStack.with(Items.lead, 200, Items.graphite, 50, Items.silicon, 200));
            size = 2;
            buildTime = 1200f;
            unitType = UnitTypes.alpha;
            consumePower(105f / 60f);
            consumeLiquid(Liquids.water, 8f / 60f);
            itemCapacity = 0;
            hasLiquids = true;
            liquidCapacity = 20f;
            polySides = 4;
            polyRadius = 5f;
            polyStroke = 1f;
            acceptsItems = false;
            solid = false;
            underBullets = true;
            shownPlanets.addAll(VeContent.cyclant, VeContent.phoon, VeContent.maress, VeContent.thavina);
            researchCostMultiplier = 0.02f;
        }};
    }
}
