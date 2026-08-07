package momijiadd.content.LoadingVe;

import arc.util.Log;
import mindustry.Vars;
import mindustry.ctype.ContentType;
import mindustry.ctype.MappableContent;
import mindustry.type.*;
import mindustry.world.Block;

/**
 * 读取VE本体的内容.
 *
 * @since 2026-08-07
 */
public class VeContent{

    public static Item
            //Cyclant
            aluminium, quartz, catalyzon, silicide, salt, plantMatter, chromium, nitroalkoss,

    //Maress
    ferrum;

    public static Liquid
            //Cyclant
            lava, chlorine;

    public static Block
            //Cyclant
            //production
            powerDrill, beamDrill,

    //distribution
    railJunction, railUnloader,

    //liquid
    fluidJunction,

    //crafting
    cellLaboratory, saltElectrolyzer, blaster, lavaCooler,

    //storage
    isomorphicCoreShard,

    //Maress
    //production
    ferricRail,

    //TechTree
    coreNucleusRoot, coreSingularityRoot;

    public static UnitType
            //Cyclant
            thetaTether, lambdaTether;

    public static Planet cyclant, phoon, maress, sitrullus, thavina;


    public static void load(){
        //region Items
        //Cyclant
        aluminium = VeLoadItems("aluminium");
        quartz = VeLoadItems("quartz");
        catalyzon = VeLoadItems("catalyzon");
        silicide = VeLoadItems("silicide");
        salt = VeLoadItems("salt");
        plantMatter = VeLoadItems("plant-matter");
        chromium = VeLoadItems("chromium");
        nitroalkoss = VeLoadItems("nitroalkoss");
        ferrum = VeLoadItems("ferrum");

        //endregion
        //region Liquids
        //Cyclant
        lava = VeLoadLiquids("lava");
        chlorine = VeLoadLiquids("chlorine");

        //endregion
        //region Blocks

        //Cyclant
        //production
        powerDrill = VeLoadBlocks("power-drill");
        beamDrill = VeLoadBlocks("beam-drill");

        //distribution
        railJunction = VeLoadBlocks("rail-junction");
        railUnloader = VeLoadBlocks("rail-unloader");

        //liquid
        fluidJunction = VeLoadBlocks("fluid-junction");

        //crafting
        cellLaboratory = VeLoadBlocks("cell-laboratory");
        saltElectrolyzer = VeLoadBlocks("salt-electrolyzer");
        blaster = VeLoadBlocks("blaster");
        lavaCooler = VeLoadBlocks("lava-cooler");

        //storage
        isomorphicCoreShard = VeLoadBlocks("isomorphic-core-shard");

        //Maress
        //production
        ferricRail = VeLoadBlocks("ferric-rail");

        //endregion
        //region TechTree

        coreNucleusRoot = VeLoadBlocks("core-nucleus-root");
        coreSingularityRoot = VeLoadBlocks("core-singularity-root");

        //endregion
    }

    public static void loadLast(){
        //region UnitType
        //Cyclant
        thetaTether = VeLoadUnits("theta-tether");
        lambdaTether = VeLoadUnits("lambda-tether");

        //endregion
        //region Planets

        cyclant = VeLoadPlanets("cyclant");
        phoon = VeLoadPlanets("phoon");
        maress = VeLoadPlanets("maress");
        sitrullus = VeLoadPlanets("sitrullus");
        thavina = VeLoadPlanets("thavina");

        //endregion
    }


    /** 便捷的读取VE本体的内容, 同时在未找到内容时给出警告 */
    public static <T extends MappableContent> T VeLoad(ContentType type, String name){
        T ret = Vars.content.getByName(type, "ve-" + name);
        if(ret == null){
            Log.err("VeContent: Can't find ve-" + name + "type: " + type);
            return null;
        }
        return ret;
    }

    /** 读取各个类型 */
    public static <T extends MappableContent> T VeLoadItems(String name){
        return VeLoad(ContentType.item, name);
    }

    public static <T extends MappableContent> T VeLoadLiquids(String name){
        return VeLoad(ContentType.liquid, name);
    }

    public static <T extends MappableContent> T VeLoadUnits(String name){
        return VeLoad(ContentType.unit, name);
    }

    public static <T extends MappableContent> T VeLoadBlocks(String name){
        return VeLoad(ContentType.block, name);
    }

    public static <T extends MappableContent> T VeLoadPlanets(String name){
        return VeLoad(ContentType.planet, name);
    }

}
