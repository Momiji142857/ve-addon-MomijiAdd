package ve.addon.momiji;

import arc.Events;
import arc.util.Log;
import mindustry.game.EventType;
import ve.addon.momiji.content.AddBlocks;
import ve.addon.momiji.content.AddTechTree;
import ve.addon.momiji.content.VeContent;

public class ModLoad extends mindustry.mod.Mod {

    public ModLoad(){
        Log.info("Loaded VE Addon: MomijiAdd constructor.");
        Events.on(EventType.ContentInitEvent.class, e -> {
            VeContent.load();
            AddTechTree.load();
        });
    }

    @Override
    public void loadContent() {
        AddBlocks.load();
    }

}
