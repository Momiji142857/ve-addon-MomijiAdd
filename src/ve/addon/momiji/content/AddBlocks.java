package ve.addon.momiji.content;

import arc.graphics.Color;
import mindustry.Vars;
import mindustry.content.Fx;
import mindustry.content.Items;
import mindustry.content.Liquids;
import mindustry.ctype.ContentType;
import mindustry.entities.effect.ParticleEffect;
import mindustry.gen.Sounds;
import mindustry.type.*;
import mindustry.world.Block;
import mindustry.world.blocks.production.Drill;
import mindustry.world.blocks.production.GenericCrafter;
import mindustry.world.draw.*;
import mindustry.world.meta.BlockGroup;
import ve.addon.momiji.AddType.ItemLiquidJunction;

public class AddBlocks {

    public static Block itemLiquidJunction, railLiquidJunction, beamDrillPro, saltElectrolyzerPro;

    static Item aluminium = Vars.content.getByName(ContentType.item, "ve-aluminium");
    static Item quartz = Vars.content.getByName(ContentType.item, "ve-quartz");
    static Item catalyzon = Vars.content.getByName(ContentType.item, "ve-catalyzon");
    static Item salt = Vars.content.getByName(ContentType.item, "ve-salt");
    static Item chromium = Vars.content.getByName(ContentType.item, "ve-chromium");
    static Liquid chlorine = Vars.content.getByName(ContentType.liquid, "ve-chlorine");
    static Planet cyclant = Vars.content.getByName(ContentType.planet, "ve-cyclant");
    static Planet phoon = Vars.content.getByName(ContentType.planet, "ve-phoon");
    static Planet maress = Vars.content.getByName(ContentType.planet, "ve-maress");
    static Planet thavina = Vars.content.getByName(ContentType.planet, "ve-thavina");

    public static void load() {
        itemLiquidJunction = new ItemLiquidJunction("item-liquid-junction") {{
            requirements(Category.distribution, ItemStack.with(Items.copper, 3, Items.metaglass, 8, Items.graphite, 4));
            speed = 26f;
            capacity = 6;
            buildCostMultiplier = 6f;
            solid = false;
            researchCostMultiplier = 0.02f;
        }};

        railLiquidJunction =  new ItemLiquidJunction("rail-liquid-junction") {{
            requirements(Category.distribution, ItemStack.with(Items.metaglass, 8, Items.graphite, 4, aluminium, 2));
            speed = 10f;
            capacity = 5;
            displayedSpeed = 24f; // 60f / 10f * 5 = 30f 实测30帧下稳定25.0
            buildCostMultiplier = 6f;
            squareSprite = false;
            solid = false;
            researchCostMultiplier = 0.02f;
        }};

        beamDrillPro = new Drill("beam-drill-pro") {{
            requirements(Category.production, ItemStack.with(Items.lead, 60, Items.graphite, 60, Items.metaglass, 30,  Items.silicon, 35, quartz, 55, catalyzon, 10));
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

        saltElectrolyzerPro = new GenericCrafter("salt-electrolyzer-pro") {{
            requirements(Category.crafting, ItemStack.with(Items.graphite, 30, Items.metaglass, 40, catalyzon, 10, chromium, 40));
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
            consumeItem(salt, 3);
            consumeLiquid(Liquids.water, 15f / 60f);
            consumePower(30f / 60f);
            ambientSound = Sounds.loopElectricHum;
            ambientSoundVolume = 0.08f;
            craftEffect = Fx.lightning;
            outputLiquids = LiquidStack.with(Liquids.water, 0f, chlorine, 9f / 60f, Liquids.hydrogen, 9f/60f);
            drawer = new DrawMulti(
                    new DrawRegion("-bottom"),
                    new DrawLiquidTile(Liquids.water, 2),
                    new DrawBubbles(Color.valueOf("7693e3")) {{
                        sides = 8;
                        recurrence = 3f;
                        spread = 4f;
                        amount = 15;
                    }},
                    new DrawLiquidTile(chlorine) {{
                        padBottom = 8f;
                        padTop = 0f;
                    }},
                    new DrawLiquidTile(Liquids.hydrogen) {{
                        padBottom = 0f;
                        padTop = 8f;
                    }},
                    new DrawRegion(),
                    new DrawLiquidOutputs(),
                    new DrawGlowRegion("-glow") {{
                        color = Color.valueOf("faffd7");
                        alpha = 0.8f;
                    }}
            );
            researchCostMultiplier = 0.02f;
            shownPlanets.addAll(cyclant, phoon, maress, thavina);
        }};

    }
}
