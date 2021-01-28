package control;

import model.Block;
import view.BlockDisplay;

public class BlockPresenter implements Block.Observer{
    
    private final Block block;
    private final BlockDisplay blockDisplay;

    public BlockPresenter(Block block, BlockDisplay blockDisplay) {
        this.block = block;
        this.blockDisplay = blockDisplay;
        this.block.register(this);
        this.blockDisplay.on(new BlockDisplay.Moved() {
            @Override
            public void to(int x, int y) {
                block.movedTo(x / 100 + 1, Block.MAX - y / 100);
            }
        });
        this.refresh();
    }    

    @Override
    public void changed() {
        this.refresh();
    }

    private void refresh() {
        blockDisplay.paintBlock((block.x()-1)*blockDisplay.SIZE, (Block.MAX -  block.y())*blockDisplay.SIZE);
    }
}