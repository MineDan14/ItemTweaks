package at.FG.itemtwaks

import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.{Item, ItemStack}

class myCreativeTab(Name:String,I:Item) extends CreativeTabs(Name){

  override def getTabIconItem = new ItemStack((I))
}
