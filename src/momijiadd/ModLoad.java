package momijiadd;

import arc.Events;
import arc.util.Log;
import mindustry.game.EventType;
import mindustry.mod.Mod;
import momijiadd.content.AddBlocks;
import momijiadd.content.AddTechTree;
import momijiadd.content.VeContent;
import momijiadd.content.ReloadBlocks;

public class ModLoad extends Mod {

    public ModLoad() {
        Log.info("Loaded VE Addon: MomijiAdd constructor.");
        Events.on(EventType.ContentInitEvent.class, e -> {
            VeContent.load();
            AddTechTree.load();
            ReloadBlocks.load();
        });
    }

    @Override
    public void loadContent() {
        AddBlocks.load();
    }

}
