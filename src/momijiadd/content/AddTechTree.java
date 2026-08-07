package momijiadd.content;

import arc.struct.Seq;
import arc.util.Log;
import arc.util.Nullable;
import mindustry.content.Blocks;
import mindustry.content.TechTree;
import mindustry.ctype.UnlockableContent;
import mindustry.game.Objectives;

import static mindustry.content.TechTree.node;

/**
 * 将非联动内容添加到科技树.
 *
 * @see AddBlocks
 * @since 2026-08-07
 */
public class AddTechTree{
    public static void load(){
        //region Cyclant

        serpuloAddAfter(Blocks.junction,
                        node(AddBlocks.itemLiquidJunction, Seq.with(new Objectives.Research(Blocks.liquidJunction)), () -> {}));

        //endregion
    }


    /**
     * 在指定解锁内容后添加科技树节点.
     *
     * @param c       父节点对应的解锁内容.
     * @param newNode 要添加的子节点.
     * @throws IllegalArgumentException 父节点不存在或新节点为空.
     */
    public static void addAfter(UnlockableContent c, TechTree.TechNode newNode){
        if(c == null){
            Log.err("[VeAddTechTree] addAfter: 父节点内容不能为空: ", c);
            return;
        }
        if(newNode == null){
            Log.err("[VeAddTechTree] addAfter: 新节点不能为空: ", newNode);
            return;
        }

        TechTree.TechNode parent = TechTree.all.find(t -> t.content == c);
        if(parent == null){
            Log.err("[VeAddTechTree] addAfter: 未找到父节点, 预期解锁内容: " + c);
            return;
        }

        parent.children.add(newNode);
        newNode.parent = parent;
    }

    /**
     * 在指定根节点的子树中, 将新节点添加为指定内容的子节点
     *
     * @param r       根节点对应的解锁内容.
     * @param c       父节点对应的解锁内容 (必须在根节点子树中).
     * @param newNode 要添加的子节点.
     * @throws IllegalArgumentException 根内容或父内容为空、根节点不存在、父节点不存在或新节点为空.
     */
    public static void addAfter(UnlockableContent r, UnlockableContent c, TechTree.TechNode newNode){
        if(r == null){
            Log.err("[VeAddTechTree] addAfter: 根节点内容不能为空: ", r);
            return;
        }
        if(c == null){
            Log.err("[VeAddTechTree] addAfter: 父节点内容不能为空: ", c);
            return;
        }
        if(newNode == null){
            Log.err("[VeAddTechTree] addAfter: 新节点不能为空: ", newNode);
            return;
        }

        // 从根列表中直接查找
        TechTree.TechNode root = TechTree.roots.find(t -> t.content == r);
        if(root == null){
            Log.err("[VeAddTechTree] addAfter: 未找到根节点, 预期解锁内容: " + r);
            return;
        }

        TechTree.TechNode parent = findNode(root, c);
        if(parent == null){
            Log.err("[VeAddTechTree] addAfter: 未找到父节点, 预期解锁内容: " + c + " (在根节点 " + r + " 下)");
            return;
        }

        parent.children.add(newNode);
        newNode.parent = parent;
    }

    /**
     * 在指定根节点的子树中查找匹配内容的节点 (辅助函数).
     *
     * @param root    搜索起始根节点.
     * @param content 要匹配的解锁内容.
     * @return 找到的节点, 未找到返回 null.
     */
    @Nullable
    public static TechTree.TechNode findNode(TechTree.TechNode root, UnlockableContent content){
        if(root.content == content) return root;
        for(TechTree.TechNode child : root.children){
            TechTree.TechNode found = findNode(child, content);
            if(found != null) return found;
        }
        return null;
    }

    /** 在 Serpulo 科技树中添加节点 */
    private static void serpuloAddAfter(UnlockableContent c, TechTree.TechNode newNode){
        addAfter(Blocks.coreShard, c, newNode);
    }

}
