package ve.addon.momiji.content;

import arc.graphics.Color;
import arc.util.Log;
import mindustry.Vars;
import mindustry.content.Items;
import mindustry.content.Liquids;
import mindustry.ctype.ContentType;
import mindustry.ctype.MappableContent;
import mindustry.type.*;
import mindustry.world.Block;
import mindustry.world.blocks.production.AttributeCrafter;
import mindustry.world.blocks.production.GenericCrafter;
import mindustry.world.blocks.units.UnitCargoLoader;
import mindustry.world.draw.*;

public class VeContent {

    public static Item aluminium, quartz, catalyzon, silicide, salt, chromium;

    public static Liquid lava, chlorine;

    public static UnitType thetaTether, lambdaTether;

    public static Block coreNucleusRoot,
            powerDrill, beamDrill,
            railJunction, fluidJunction, railUnloader,
            saltElectrolyzer, blaster, lavaCooler,
            platformTheta, platformLambda;

    public static Planet cyclant, phoon, maress, thavina;

    public static void load() {
        // Items
        aluminium = VeLoad(ContentType.item, "aluminium");
        quartz = VeLoad(ContentType.item, "quartz");
        catalyzon = VeLoad(ContentType.item, "catalyzon");
        silicide = VeLoad(ContentType.item, "silicide");
        salt = VeLoad(ContentType.item, "salt");
        chromium = VeLoad(ContentType.item, "chromium");

        // Liquids
        lava = VeLoad(ContentType.liquid, "lava");
        chlorine = VeLoad(ContentType.liquid, "chlorine");

        // UnitType
        thetaTether = VeLoad(ContentType.unit, "theta-tether");
        lambdaTether = VeLoad(ContentType.unit, "lambda-tether");

        // Blocks
        railJunction = VeLoad(ContentType.block, "rail-junction");

        coreNucleusRoot = VeLoad(ContentType.block, "core-nucleus-root");
        powerDrill = VeLoad(ContentType.block, "power-drill");
        beamDrill = VeLoad(ContentType.block, "beam-drill");
        fluidJunction = VeLoad(ContentType.block, "fluid-junction");
        railUnloader = VeLoad(ContentType.block, "rail-unloader");
        saltElectrolyzer = VeLoad(ContentType.block, "salt-electrolyzer");
        blaster = VeLoad(ContentType.block, "blaster");
        lavaCooler = VeLoad(ContentType.block, "lava-cooler");
        platformTheta = VeLoad(ContentType.block, "platform-theta");
        platformLambda = VeLoad(ContentType.block, "platform-lambda");

        // Planets
        cyclant = VeLoad(ContentType.planet, "cyclant");
        phoon = VeLoad(ContentType.planet, "phoon");
        maress = VeLoad(ContentType.planet, "maress");
        thavina = VeLoad(ContentType.planet, "thavina");


        AddBlocks.railLiquidJunction.requirements = ItemStack.with(Items.graphite, 4, aluminium, 14);

        AddBlocks.isomorphicUnloader.requirements = ItemStack.with(Items.silicon, 30, aluminium, 40);

        AddBlocks.powerDrillPro.requirements = ItemStack.with(Items.metaglass, 2, Items.graphite, 20, Items.silicon, 3, aluminium, 24);

        AddBlocks.beamDrillPro.requirements = ItemStack.with(Items.lead, 60, Items.graphite, 60, Items.metaglass, 30,  Items.silicon, 35, quartz, 55, catalyzon, 10);

        GenericCrafter saltElectrolyzerPro = (GenericCrafter) AddBlocks.saltElectrolyzerPro;
        saltElectrolyzerPro.requirements = ItemStack.with(Items.graphite, 30, Items.metaglass, 40, catalyzon, 10, chromium, 40);
        saltElectrolyzerPro.consumeItem(salt, 3);
        saltElectrolyzerPro.outputLiquids = LiquidStack.with(Liquids.water, 0f, chlorine, 9f / 60f, Liquids.hydrogen, 9f/60f);
        saltElectrolyzerPro.drawer = new DrawMulti(
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
        saltElectrolyzerPro.shownPlanets.addAll(cyclant, phoon, maress, thavina);

        AttributeCrafter sandHoter = (AttributeCrafter) AddBlocks.sandHoter;
        sandHoter.requirements = ItemStack.with(Items.graphite, 100, Items.metaglass, 40, Items.silicon, 40, aluminium, 30);
        sandHoter.outputLiquids = LiquidStack.with(lava, 8f / 60f);

        UnitCargoLoader platformThetaPro = (UnitCargoLoader) AddBlocks.platformThetaPro;
        platformThetaPro.requirements = ItemStack.with(Items.lead, 160, Items.silicon, 100, aluminium, 200);
        platformThetaPro.unitType = thetaTether;
        platformThetaPro.shownPlanets.addAll(cyclant, phoon, maress, thavina);

        UnitCargoLoader platformLambdaPro = (UnitCargoLoader) AddBlocks.platformLambdaPro;
        platformLambdaPro.requirements = ItemStack.with(Items.lead, 200, Items.graphite, 50, Items.silicon, 200, aluminium, 200, silicide, 25);
        platformLambdaPro.unitType = lambdaTether;
        platformLambdaPro.shownPlanets.addAll(cyclant, phoon, maress, thavina);
    }

    public static <T extends MappableContent> T VeLoad(ContentType type, String name) {
        T ret = Vars.content.getByName(type, "ve-" + name);
        if (ret == null) {
            Log.err("VeContent: Can't find ve-" + name);
            return null;
        }
        return ret;
    }
}
