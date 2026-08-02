package momijiadd.content;

import arc.util.Log;
import mindustry.Vars;
import mindustry.ctype.ContentType;
import mindustry.ctype.MappableContent;
import mindustry.type.*;
import mindustry.world.Block;

/** 读取VE本体的内容 */
public class VeContent {

    public static Item
            aluminium, quartz, catalyzon, silicide, salt, plantMatter, chromium, nitroalkoss,
            ferrum;

    public static Liquid lava, chlorine;

    public static UnitType thetaTether, lambdaTether;

    public static Block
            coreNucleusRoot,
            powerDrill, beamDrill,
            railJunction, fluidJunction, railUnloader,
            cellLaboratory, saltElectrolyzer, blaster, lavaCooler,
            isomorphicCoreShard,

            coreSingularityRoot, ferricRail

                    ;

    public static Planet cyclant, phoon, maress, sitrullus, thavina;

    public static void load() {
        // Items
        aluminium = VeLoadItems("aluminium");
        quartz = VeLoadItems("quartz");
        catalyzon = VeLoadItems("catalyzon");
        silicide = VeLoadItems("silicide");
        salt = VeLoadItems("salt");
        plantMatter = VeLoadItems("plant-matter");
        chromium = VeLoadItems("chromium");
        nitroalkoss = VeLoadItems("nitroalkoss");
        ferrum = VeLoadItems("ferrum");

        // Liquids
        lava = VeLoadLiquids("lava");
        chlorine = VeLoadLiquids("chlorine");

        // UnitType
        thetaTether = VeLoadUnits("theta-tether");
        lambdaTether = VeLoadUnits("lambda-tether");

        // Blocks
        railJunction = VeLoadBlocks("rail-junction");

        coreNucleusRoot = VeLoadBlocks("core-nucleus-root");
        isomorphicCoreShard = VeLoadBlocks("isomorphic-core-shard");
        powerDrill = VeLoadBlocks("power-drill");
        beamDrill = VeLoadBlocks("beam-drill");
        fluidJunction = VeLoadBlocks("fluid-junction");
        railUnloader = VeLoadBlocks("rail-unloader");
        cellLaboratory = VeLoadBlocks("cell-laboratory");
        saltElectrolyzer = VeLoadBlocks("salt-electrolyzer");
        blaster = VeLoadBlocks("blaster");
        lavaCooler = VeLoadBlocks("lava-cooler");

        coreSingularityRoot = VeLoadBlocks("core-singularity-root");
        ferricRail = VeLoadBlocks("ferric-rail");

        // Planets
        cyclant = VeLoadPlanets("cyclant");
        phoon = VeLoadPlanets("phoon");
        maress = VeLoadPlanets("maress");
        sitrullus = VeLoadPlanets( "sitrullus");
        thavina = VeLoadPlanets("thavina");

    }


    /** 便捷的读取VE本体的内容, 同时在未找到内容时给出警告 */
    public static <T extends MappableContent> T VeLoad(ContentType type, String name) {
        T ret = Vars.content.getByName(type, "ve-" + name);
        if (ret == null) {
            Log.err("VeContent: Can't find ve-" + name);
            return null;
        }
        return ret;
    }

    /** 读取各个类型 */
    public static <T extends MappableContent> T VeLoadItems(String name) {
        return  VeLoad(ContentType.item, name);
    }

    public static <T extends MappableContent> T VeLoadLiquids(String name) {
        return  VeLoad(ContentType.liquid, name);
    }

    public static <T extends MappableContent> T VeLoadUnits(String name) {
        return  VeLoad(ContentType.unit, name);
    }

    public static <T extends MappableContent> T VeLoadBlocks(String name) {
        return  VeLoad(ContentType.block, name);
    }

    public static <T extends MappableContent> T VeLoadPlanets(String name) {
        return  VeLoad(ContentType.planet, name);
    }

}
