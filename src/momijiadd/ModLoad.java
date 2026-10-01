package momijiadd;

import arc.Events;
import arc.util.Log;
import mindustry.Vars;
import mindustry.core.Version;
import mindustry.game.EventType;
import mindustry.mod.Mod;
import mindustry.mod.Mods;
import momijiadd.content.AddBlocks;
import momijiadd.content.AddTechTree;
import momijiadd.content.VeAdd.VeAddBlocks;
import momijiadd.content.VeAdd.VeAddTechTree;
import momijiadd.content.VeAdd.VeContent;
import momijiadd.content.Test.TestFusionFuel;

public class ModLoad extends Mod{

    //是否启用基于氘和氚的聚变燃料合成路线.
    public boolean test_fusionFuel = false;

    public ModLoad(){
        Log.info("Loaded VE Addon: MomijiAdd constructor.");
    }

    public static String addModName(String add){
        return "ve-addon-momiji" + "-" + add;
    }

    @Override
    public void loadContent(){
        //Part 1: 检测联动模组启用情况.
        Mods.LoadedMod ve = Vars.mods.getMod("ve");
        boolean veEnabled = ve != null && ve.enabled() == Version.enabled;


        //Part 2: 加载模组内容.
        // AddLiquids.load();
        AddBlocks.load();
        AddTechTree.load();

        //加载各个联动内容.
        if(veEnabled){
            Log.info("[VE Addon: MomijiAdd] VE detected, loading VE-related content.");
            VeContent.load();
            VeAddBlocks.load();
        }else Log.info("[VE Addon: MomijiAdd] VE not found, skipping VE-related content.");

        //测试内容.
        if(test_fusionFuel) TestFusionFuel.load(veEnabled);


        //Part 3: 在模组内容加载完成但尚未初始化时, 加载剩余联动内容.
        Events.on(EventType.ModContentLoadEvent.class, e -> {
            if(veEnabled){
                VeContent.loadLast();
                VeAddBlocks.loadLast();
                VeAddTechTree.load();

                if(test_fusionFuel) TestFusionFuel.loadLast();
            }
        });
    }

}
