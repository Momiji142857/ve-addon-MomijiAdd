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
import momijiadd.content.LoadingVe.VeAddBlocks;
import momijiadd.content.LoadingVe.VeAddTechTree;
import momijiadd.content.LoadingVe.VeContent;

public class ModLoad extends Mod{

    public ModLoad(){
        Log.info("Loaded VE Addon: MomijiAdd constructor.");
    }

    public static String AddModName(String add){
        return "ve-addon-momiji" + "-" + add;
    }

    @Override
    public void loadContent(){
        AddBlocks.load();
        AddTechTree.load();

        Mods.LoadedMod LoadedVe = Vars.mods.getMod("ve");
        if(LoadedVe != null && LoadedVe.enabled() == Version.enabled){
            Log.info("[VE Addon: MomijiAdd] VE detected, loading VE-related content.");
            VeContent.load();
            VeAddBlocks.load();
        }else{
            Log.info("[VE Addon: MomijiAdd] VE not found, skipping VE-related content.");
        }

        Events.on(EventType.ModContentLoadEvent.class, e -> {
            if(LoadedVe != null && LoadedVe.enabled() == Version.enabled){
                VeContent.loadLast();
                VeAddBlocks.loadLast();
                VeAddTechTree.load();
            }
        });
    }

}
