package momijiadd.content.LoadingVe;

import arc.struct.Seq;
import mindustry.content.TechTree;
import mindustry.ctype.UnlockableContent;
import mindustry.game.Objectives;
import momijiadd.content.AddTechTree;

import static mindustry.content.TechTree.node;

/**
 * 将VE联动内容添加到科技树.
 *
 * @see VeAddBlocks
 * @see AddTechTree
 * @since 2026-08-07
 */
public class VeAddTechTree{

    public static void load(){
        //region Cyclant

        cyclantAddAfter(VeContent.powerDrill,
                        node(VeAddBlocks.powerDrillPro));

        cyclantAddAfter(VeContent.beamDrill,
                        node(VeAddBlocks.beamDrillPro));

        cyclantAddAfter(VeContent.fluidJunction,
                        node(VeAddBlocks.railLiquidJunction, Seq.with(new Objectives.Research(VeContent.railJunction)), () -> {}));

        cyclantAddAfter(VeContent.railUnloader,
                        node(VeAddBlocks.isomorphicUnloader));

        cyclantAddAfter(VeContent.cellLaboratory,
                        node(VeAddBlocks.cellLaboratoryPro));

        cyclantAddAfter(VeContent.saltElectrolyzer,
                        node(VeAddBlocks.saltElectrolyzerPro));

        cyclantAddAfter(VeContent.blaster,
                        node(VeAddBlocks.sandHoter, Seq.with(new Objectives.Research(VeContent.lavaCooler)), () -> {}));

        cyclantAddAfter(VeContent.isomorphicCoreShard,
                        node(VeAddBlocks.platformThetaPro, () ->
                                node(VeAddBlocks.platformLambdaPro)));

        //endregion
        //region Maress

        maressAddAfter(VeContent.ferricRail,
                       node(VeAddBlocks.batteryItemBridge));

        //endregion
    }

    /** 在 Cyclant 科技树中添加节点. */
    private static void cyclantAddAfter(UnlockableContent c, TechTree.TechNode newNode){
        AddTechTree.addAfter(VeContent.coreNucleusRoot, c, newNode);
    }

    /** 在 Maress 科技树中添加节点. */
    private static void maressAddAfter(UnlockableContent c, TechTree.TechNode newNode){
        AddTechTree.addAfter(VeContent.coreSingularityRoot, c, newNode);
    }

}
