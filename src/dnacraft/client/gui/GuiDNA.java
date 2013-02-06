package dnacraft.client.gui;

import java.util.Map.Entry;

import org.lwjgl.opengl.GL11;

import dnacraft.common.evolution.DNA;
import dnacraft.common.evolution.Gene;
import dnacraft.common.evolution.Genome;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.StringTranslate;

public class GuiDNA extends GuiScreen {
	
	private NBTTagCompound nbt;
	private DNA dna;
	
	public GuiDNA(ItemStack stack) {
		if (stack != null && stack.hasTagCompound()) {
			nbt = stack.getTagCompound();
			dna = DNA.fromNBT(nbt.getCompoundTag("traits"));
		}
	}
	
    public void drawScreen(int par1, int par2, float par3)
    {
        StringTranslate var4 = StringTranslate.getInstance();
        this.drawDefaultBackground();
		int texture = this.mc.renderEngine
				.getTexture("/dnacraft/resources/gfx/gui-dna.png");
		this.mc.renderEngine.bindTexture(texture);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		int var5 = (this.width - 176) / 2;
		int var6 = (this.height - 166) / 2;
		this.drawTexturedModalRect(var5, var6, 0, 0, 176, 166);
		drawDNA(var5, var6);
        super.drawScreen(par1, par2, par3);
    }
    
    private void drawDNA(int x, int y) {
    	int tx;
    	int ty;
    	int offsety = 10;
    	for (Entry<String, Genome> entry : dna.entrySet()) {
        	int offsetx = 8;
        	for (Gene gene : entry.getValue()) {
        		tx = x + offsetx;
        		ty = y + offsety;
        		int col = 0xFF000000 + ((gene.getTrait() * 1000000000) % 0xFFFFFF);
        		if (gene.isActive()) {
        			drawRect(tx, ty, tx+3, ty+3, col );
        		}else {
        			drawRect(tx, ty+3, tx+3, ty+6, col  );
        		}
            	offsetx += 4;
        	}
        	offsety += 10;
    		
    	}
    }
}
