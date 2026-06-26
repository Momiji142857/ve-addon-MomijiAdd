package ve.addon.momiji;

import arc.util.Log;
import ve.addon.momiji.content.AddBlocks;
import ve.addon.momiji.content.AddTechTree;

public class ModLoad extends mindustry.mod.Mod {

    public ModLoad(){
        Log.info("Loaded VE Addon: MomijiAdd constructor.");
    }

    @Override
    public void loadContent(){
        AddBlocks.load();
        AddTechTree.load();
    }

}
