package at.FG.itemtweaks.API

import at.FG.itemtweaks.lists
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.Item

object jsAPI {
  def testfunction(s:String): Unit ={
    System.out.println(s)
  }

  def getItemFromID(ID:String):Item = {
    return lists.getItemByID(ID)
  }

  def setItemCreativeTab(ID:String,Ct:String): Unit ={
    getItemFromID(ID).setCreativeTab(lists.getCreativeTabByLable(Ct))
    System.out.println("ITP:"+getItemFromID(ID).getRegistryName)
  }

  def newCreativeTab(Name:String,Item:String): Unit ={
    lists.newCreativeTab(Name,Item)
  }

}
