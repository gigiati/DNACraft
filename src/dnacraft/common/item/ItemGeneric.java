package dnacraft.common.item;

import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import dnacraft.api.IMeta;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemGeneric extends Item {

	private HashMap<Integer, IMeta> metaitems = new HashMap<Integer, IMeta>();

	public ItemGeneric(int i) {
		super(i);
		setHasSubtypes(true);
		setMaxDamage(0);
		setMaxStackSize(64);
		setCreativeTab(CreativeTabs.tabMisc);
		setTextureFile("/dnacraft/resources/gfx/items_generic.png");
	}

	@Override
	public int getIconFromDamage(int i) {
		IMeta meta = getMeta(i);
		if (meta != null) {
			return meta.getIconIndex();
		}
		return 0;
	}
	
	@Override
    public String getItemNameIS(ItemStack stack)
    {
		IMeta meta = getMeta(stack.getItemDamage());
		if (meta != null) {
			return meta.getItemNameIS();
		}
        return "";
    }

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(int id, CreativeTabs tab, List subItems) {
		for (Entry<Integer, IMeta> entry : metaitems.entrySet()) {
			subItems.add(new ItemStack(id, 1, entry.getKey()));
		}
	}

	public void addMeta(IMeta meta) {
		metaitems.put(meta.getId(), meta);
	}

	public IMeta getMeta(int id) {
		return metaitems.get(id);
	}
	
	public IMeta getMeta(ItemStack itemStack) {
		return getMeta(itemStack.getItemDamage());
	}

}
