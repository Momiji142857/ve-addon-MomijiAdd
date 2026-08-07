package momijiadd.content.LoadingVe;

import arc.graphics.Color;
import mindustry.content.*;
import mindustry.ctype.UnlockableContent;
import mindustry.entities.effect.ParticleEffect;
import mindustry.gen.Sounds;
import mindustry.type.*;
import mindustry.world.Block;
import mindustry.world.blocks.production.Drill;
import mindustry.world.blocks.production.GenericCrafter;
import mindustry.world.blocks.storage.Unloader;
import mindustry.world.blocks.units.UnitCargoLoader;
import mindustry.world.draw.*;
import mindustry.world.meta.Attribute;
import mindustry.world.meta.BlockGroup;
import momijiadd.type.BatchDumpBridge;
import momijiadd.type.ItemLiquidJunction;
import momijiadd.type.LinkedDrill;
import momijiadd.type.OmniCrafter;

/**
 * 掌管与 Vanilla Expansion 模组联动的方块.
 *
 * @see Blocks
 * @since 2026-08-07
 */
public class VeAddBlocks{
    public static Block

            //region Cyclant
            //production
            powerDrillPro, beamDrillPro,

    //distribution
    railLiquidJunction, isomorphicUnloader,

    //crafting
    cellLaboratoryPro, saltElectrolyzerPro, sandHoter,

    //units
    platformThetaPro, platformLambdaPro,

    //endregion

    //region Maress
    //production
    batteryItemBridge;

    //endregion


    public static void load(){
        //Cyclant
        //region production

        powerDrillPro = new LinkedDrill("power-drill-pro"){{
            requirements(Category.production, ItemStack.with(Items.metaglass, 2, Items.graphite, 20, Items.silicon, 3, VeContent.aluminium, 24));
            dumpTime = 1;
            size = 2;
            tier = 3;
            hasItems = true;
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

        beamDrillPro = new Drill("beam-drill-pro"){{
            requirements(Category.production, ItemStack.with(Items.lead, 60, Items.graphite, 60, Items.metaglass, 30, Items.silicon, 35, VeContent.quartz, 55, VeContent.catalyzon, 10));
            tier = 4;
            drillTime = 108f;
            liquidBoostIntensity = 1.8f;
            warmupSpeed = 0.01f;
            drillMultipliers.put(Items.sporePod, 0f); // -
            rotateSpeed = 7f;
            updateEffect = new ParticleEffect(){{
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

        //endregion
        //region distribution

        railLiquidJunction = new ItemLiquidJunction("rail-liquid-junction"){{
            requirements(Category.distribution, ItemStack.with(Items.graphite, 4, VeContent.aluminium, 14));
            speed = 10f;
            capacity = 5;
            displayedSpeed = 24f; // 60f / 10f * 5 = 30f 实测30帧下稳定25.0
            buildCostMultiplier = 6f;
            squareSprite = false;
            researchCostMultiplier = 0.2f;
            shownPlanets.addAll(VeContent.cyclant, VeContent.phoon, VeContent.thavina);
        }};

        isomorphicUnloader = new Unloader("isomorphic-unloader"){{
            requirements(Category.distribution, ItemStack.with(Items.silicon, 30, VeContent.aluminium, 40));
            speed = 60f / 11f;
            group = BlockGroup.transportation;
            researchCostMultiplier = 0.02f;
            shownPlanets.addAll(VeContent.cyclant, VeContent.phoon, VeContent.thavina);
        }};

        //endregion
        //region crafting

        cellLaboratoryPro = new OmniCrafter("cell-laboratory-pro"){{
            requirements(Category.crafting, ItemStack.with(Items.metaglass, 80, Items.graphite, 200, Items.silicon, 320, VeContent.catalyzon, 50, VeContent.chromium, 400));
            dumpExtraItem = true;
            randomResults = ItemStack.with(VeContent.nitroalkoss, 1, Items.sporePod, 5, VeContent.plantMatter, 5);
            emptyWeight = 49;
            consumeItems(ItemStack.with(Items.sporePod, 1, VeContent.plantMatter, 1));
            consumeLiquid(Liquids.water, 10f / 60f);
            consumePower(200f / 60f);
            shownPlanets.addAll(VeContent.cyclant, VeContent.phoon, VeContent.thavina);
            baseExplosiveness = 1f;
            hasItems = true;
            hasLiquids = true;
            hasPower = true;
            // craftEffect = Fx.shockwave;
            updateEffect = Fx.smeltsmoke;
            liquidCapacity = 120f;
            itemCapacity = 50;
            craftTime = 60f;
            size = 4;
            ambientSound = Sounds.loopBio;
            ambientSoundVolume = 0.2f;
            legacyReadWarmup = true;
            researchCostMultiplier = 0.01f;
            drawer = new DrawMulti(new DrawRegion("-bottom"), new DrawLiquidTile(Liquids.water), new DrawCultivator(){{
                plantColorLight = Color.valueOf("e16049");
                plantColor = Color.valueOf("ba352b");
                bottomColor = Color.valueOf("892018");
            }}, new DrawRegion("-rotator"){{
                rotateSpeed = -3f;
                x = -9f;
                y = 9f;
            }}, new DrawRegion("-rotator"){{
                rotateSpeed = -3f;
                x = 9f;
                y = 9f;
            }}, new DrawRegion("-rotator"){{
                rotateSpeed = -3f;
                x = -9f;
                y = -9f;
            }}, new DrawRegion("-rotator"){{
                rotateSpeed = -3f;
                x = 9f;
                y = -9f;
            }}, new DrawRegion(), new DrawGlowRegion("-glow"){{
                color = Color.valueOf("ff8a67");
                alpha = 0.6f;
            }});
            // hideDetails = false;
        }};

        saltElectrolyzerPro = new GenericCrafter("salt-electrolyzer-pro"){{
            requirements(Category.crafting, ItemStack.with(Items.metaglass, 40, Items.graphite, 30, VeContent.catalyzon, 10, VeContent.chromium, 40));
            size = 2;
            craftTime = 60f;
            consumeItem(VeContent.salt, 3);
            consumeLiquid(Liquids.water, 15f / 60f);
            outputLiquids = LiquidStack.with(VeContent.chlorine, 9f / 60f, Liquids.hydrogen, 9f / 60f, Liquids.water, 0f);
            regionRotated1 = 3;
            liquidOutputDirections = new int[] {1, 3, 0};
            drawer = new DrawMulti(new DrawRegion("-bottom"), new DrawLiquidTile(Liquids.water, 2), new DrawBubbles(Color.valueOf("7693e3")){{
                sides = 8;
                recurrence = 3f;
                spread = 4f;
                amount = 15;
            }}, new DrawLiquidTile(VeContent.chlorine){{
                padBottom = 8f;
                padTop = 0f;
            }}, new DrawLiquidTile(Liquids.hydrogen){{
                padBottom = 0f;
                padTop = 8f;
            }}, new DrawRegion(), new DrawLiquidOutputs(), new DrawGlowRegion("-glow"){{
                color = Color.valueOf("faffd7");
                alpha = 0.8f;
            }});
            group = BlockGroup.liquids;
            outputsLiquid = true;
            liquidCapacity = 50f;
            hasItems = true;
            hasLiquids = true;
            hasPower = true;
            itemCapacity = 30;
            invertFlip = true;
            rotate = true;
            conductivePower = true;
            consumePower(30f / 60f);
            ambientSound = Sounds.loopElectricHum;
            ambientSoundVolume = 0.08f;
            craftEffect = Fx.lightning;
            researchCostMultiplier = 0.02f;
            shownPlanets.addAll(VeContent.cyclant, VeContent.phoon, VeContent.maress, VeContent.thavina);
        }};

        sandHoter = new OmniCrafter("sand-hoter"){{
            requirements(Category.crafting, ItemStack.with(Items.graphite, 100, Items.metaglass, 40, Items.silicon, 40, VeContent.aluminium, 30));
            size = 2;
            consumePower(42f / 60f);
            consumeItems(ItemStack.with(Items.sand, 3, Items.blastCompound, 1));
            outputLiquids = LiquidStack.with(VeContent.lava, 8f / 60f);
            outputsLiquid = true;
            craftTime = 60f / 2f;
            hasLiquids = true;
            hasItems = true;
            hasPower = true;
            liquidCapacity = 80f;
            itemCapacity = 60;
            ambientSound = Sounds.loopSmelter;
            ambientSoundVolume = 0.08f;
            drawer = new DrawMulti(new DrawRegion(), new DrawGlowRegion("-glow"){{
                alpha = 0.9f;
                color = Color.valueOf("ffa665");
                glowScale = 5f;
            }}, new DrawFlame(Color.valueOf("ffb6a5")));
            attribute = Attribute.heat;
            boostScale = 0.5f;
            maxBoost = 3f;
            minEfficiency = -1f;
            shownPlanets.addAll(VeContent.cyclant, VeContent.phoon, VeContent.maress, VeContent.thavina);
        }};

        //endregion
        //region units

        platformThetaPro = new UnitCargoLoader("platform-theta-pro"){{
            requirements(Category.units, ItemStack.with(Items.lead, 160, Items.silicon, 100, VeContent.aluminium, 200));
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
            researchCostMultiplier = 0.02f;
            shownPlanets.addAll(VeContent.cyclant, VeContent.phoon, VeContent.maress, VeContent.thavina);
        }};

        platformLambdaPro = new UnitCargoLoader("platform-lambda-pro"){{
            requirements(Category.units, ItemStack.with(Items.lead, 200, Items.graphite, 50, Items.silicon, 200, VeContent.aluminium, 200, VeContent.silicide, 25));
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
            researchCostMultiplier = 0.02f;
            shownPlanets.addAll(VeContent.cyclant, VeContent.phoon, VeContent.maress, VeContent.thavina);
        }};

        //endregion

        //Maress
        //region units

        batteryItemBridge = new BatchDumpBridge("battery-item-bridge"){{
            requirements(Category.distribution, ItemStack.with(Items.lead, 10, Items.silicon, 2, VeContent.quartz, 2, VeContent.ferrum, 10));
            range = 6;
            transportTime = 1f;
            fadeIn = false;
            itemCapacity = 30;
            moveArrows = true;
            pulse = true;
            arrowSpacing = 4f;
            buildCostMultiplier = 3f;
            bridgeWidth = 8f;
            arrowPeriod = 1f;
            arrowTimeScl = 2f;
            researchCostMultiplier = 0.1f;
            hasPower = true;
            squareSprite = false;
            consumePowerBuffered(200f);
            canOverdrive = true;
            conductivePower = true;
            outputsPower = true;
            shownPlanets.addAll(VeContent.maress, VeContent.sitrullus, VeContent.thavina);
        }};

        //endregion
    }

    public static void loadLast(){
        ((UnitCargoLoader) platformThetaPro).unitType = VeContent.thetaTether;
        ((UnitCargoLoader) platformLambdaPro).unitType = VeContent.lambdaTether;

        //add to shownPlanets
        final Planet[] CYCLANT = {VeContent.cyclant, VeContent.phoon, VeContent.thavina};
        addToShownPlanets(CYCLANT,
                          powerDrillPro, beamDrillPro,
                          railLiquidJunction, isomorphicUnloader,
                          cellLaboratoryPro, saltElectrolyzerPro, sandHoter,
                          platformThetaPro, platformLambdaPro
        );

        final Planet[] MARESS = {VeContent.maress, VeContent.thavina};
        addToShownPlanets(MARESS, batteryItemBridge);
    }

    /**
     * 将一个星球添加到多个内容的 shownPlanets 列表.
     *
     * @param planet   要添加的星球.
     * @param contents 可变参数，要添加星球的 UnlockableContent.
     * @param <T>      内容类型，必须是 UnlockableContent 的子类.
     * @throws NullPointerException 如果 planet 或 contents 中的元素为 null.
     * @see UnlockableContent#shownPlanets
     */
    @SafeVarargs
    public static <T extends UnlockableContent> void addToShownPlanets(Planet planet, T... contents){
        for(T content : contents){
            content.shownPlanets.add(planet);
        }
    }

    /**
     * 将多个星球添加到多个内容的 shownPlanets 列表.
     *
     * @param planets  要添加的星球数组.
     * @param contents 可变参数，要添加星球的 UnlockableContent.
     * @param <T>      内容类型，必须是 UnlockableContent 的子类.
     * @throws NullPointerException 如果 planets 或 contents 中的元素为 null.
     * @see UnlockableContent#shownPlanets
     */
    @SafeVarargs
    public static <T extends UnlockableContent> void addToShownPlanets(Planet[] planets, T... contents){
        for(T content : contents){
            content.shownPlanets.addAll(planets);
        }
    }

}
