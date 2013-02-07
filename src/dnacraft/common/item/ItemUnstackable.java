package dnacraft.common.item;


public class ItemUnstackable extends ItemGeneric {

	public ItemUnstackable(int i) {
		super(i);
		setMaxStackSize(1);
		setTextureFile("/dnacraft/resources/gfx/items_unstackable.png");
	}

}
