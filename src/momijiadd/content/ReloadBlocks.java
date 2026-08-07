package momijiadd.content;

import arc.graphics.Color;
import mindustry.content.Items;
import mindustry.content.Liquids;
import mindustry.type.ItemStack;
import mindustry.type.LiquidStack;
import mindustry.world.blocks.production.GenericCrafter;
import mindustry.world.blocks.units.UnitCargoLoader;
import mindustry.world.draw.*;
import momijiadd.type.BatchDumpBridge;
import momijiadd.type.OmniCrafter;

public class ReloadBlocks {
    public static void load() {
        // Cyclant
        AddBlocks.railLiquidJunction.requirements = ItemStack.with(Items.graphite, 4, VeContent.aluminium, 14);
        AddBlocks.railLiquidJunction.shownPlanets.addAll(VeContent.cyclant, VeContent.phoon, VeContent.thavina);

        AddBlocks.isomorphicUnloader.requirements = ItemStack.with(Items.silicon, 30, VeContent.aluminium, 40);
        AddBlocks.isomorphicUnloader.shownPlanets.addAll(VeContent.cyclant, VeContent.phoon, VeContent.thavina);

        AddBlocks.powerDrillPro.requirements = ItemStack.with(Items.metaglass, 2, Items.graphite, 20, Items.silicon, 3, VeContent.aluminium, 24);

        AddBlocks.beamDrillPro.requirements = ItemStack.with(Items.lead, 60, Items.graphite, 60, Items.metaglass, 30, Items.silicon, 35, VeContent.quartz, 55, VeContent.catalyzon, 10);

        OmniCrafter cellLaboratoryPro = (OmniCrafter) AddBlocks.cellLaboratoryPro;
        cellLaboratoryPro.requirements = ItemStack.with(Items.metaglass, 80, Items.graphite, 200, Items.silicon, 320, VeContent.catalyzon, 50, VeContent.chromium, 400);
        cellLaboratoryPro.randomResults = ItemStack.with(VeContent.nitroalkoss, 1, Items.sporePod, 5, VeContent.plantMatter, 5);
        cellLaboratoryPro.emptyWeight = 49;
        cellLaboratoryPro.consumeItems(ItemStack.with(Items.sporePod, 1, VeContent.plantMatter, 1));
        cellLaboratoryPro.consumeLiquid(Liquids.water, 10f / 60f);
        cellLaboratoryPro.shownPlanets.addAll(VeContent.cyclant, VeContent.phoon, VeContent.thavina);
        cellLaboratoryPro.init();

        GenericCrafter saltElectrolyzerPro = (GenericCrafter) AddBlocks.saltElectrolyzerPro;
        saltElectrolyzerPro.requirements = ItemStack.with(Items.metaglass, 40, Items.graphite, 30, VeContent.catalyzon, 10, VeContent.chromium, 40);
        saltElectrolyzerPro.consumeItem(VeContent.salt, 3);
        saltElectrolyzerPro.consumeLiquid(Liquids.water, 15f / 60f);
        saltElectrolyzerPro.outputLiquids = LiquidStack.with(VeContent.chlorine, 9f / 60f, Liquids.hydrogen, 9f / 60f, Liquids.water, 0f);
        saltElectrolyzerPro.regionRotated1 = 3;
        saltElectrolyzerPro.liquidOutputDirections = new int[]{1, 3, 0};
        saltElectrolyzerPro.drawer = new DrawMulti(
                new DrawRegion("-bottom"),
                new DrawLiquidTile(Liquids.water, 2),
                new DrawBubbles(Color.valueOf("7693e3")) {{
                    sides = 8;
                    recurrence = 3f;
                    spread = 4f;
                    amount = 15;
                }},
                new DrawLiquidTile(VeContent.chlorine) {{
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
        saltElectrolyzerPro.shownPlanets.addAll(VeContent.cyclant, VeContent.phoon, VeContent.maress, VeContent.thavina);
        saltElectrolyzerPro.init();

        OmniCrafter sandHoter = (OmniCrafter) AddBlocks.sandHoter;
        sandHoter.requirements = ItemStack.with(Items.graphite, 100, Items.metaglass, 40, Items.silicon, 40, VeContent.aluminium, 30);
        sandHoter.outputLiquids = LiquidStack.with(VeContent.lava, 8f / 60f);
        sandHoter.shownPlanets.addAll(VeContent.cyclant, VeContent.phoon, VeContent.maress, VeContent.thavina);
        sandHoter.init();

        UnitCargoLoader platformThetaPro = (UnitCargoLoader) AddBlocks.platformThetaPro;
        platformThetaPro.requirements = ItemStack.with(Items.lead, 160, Items.silicon, 100, VeContent.aluminium, 200);
        platformThetaPro.unitType = VeContent.thetaTether;
        platformThetaPro.shownPlanets.addAll(VeContent.cyclant, VeContent.phoon, VeContent.maress, VeContent.thavina);
        platformThetaPro.init();

        UnitCargoLoader platformLambdaPro = (UnitCargoLoader) AddBlocks.platformLambdaPro;
        platformLambdaPro.requirements = ItemStack.with(Items.lead, 200, Items.graphite, 50, Items.silicon, 200, VeContent.aluminium, 200, VeContent.silicide, 25);
        platformLambdaPro.unitType = VeContent.lambdaTether;
        platformLambdaPro.shownPlanets.addAll(VeContent.cyclant, VeContent.phoon, VeContent.maress, VeContent.thavina);
        platformLambdaPro.init();

        // Maress
        BatchDumpBridge batteryItemBridge = (BatchDumpBridge) AddBlocks.batteryItemBridge;
        batteryItemBridge.requirements = ItemStack.with(Items.lead, 10, Items.silicon, 2, VeContent.quartz, 2, VeContent.ferrum, 10);
        batteryItemBridge.shownPlanets.addAll(VeContent.maress, VeContent.sitrullus, VeContent.thavina);
        batteryItemBridge.init();
    }
}
