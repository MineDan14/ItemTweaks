package at.FG.itemtweaks

import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.Item
import net.minecraftforge.fml.common.registry.ForgeRegistries

package object lists {

  //var allItems :List[Item]= null
  var allItems:Map[String,Item]=null        // map to store al items and blocks
  var allCreativeTabs : Map[String,CreativeTabs]=null   // map to store all creativeTabs

  def init() {      //called to initialize the lists

    val itemss = ForgeRegistries.ITEMS.getValues    // load all registered items to itemss
    allItems=Map(itemss.get(0).getRegistryName.toString->itemss.get(0))   //define allItems with the first item
    var ib = 1
    while (ib < itemss.size()) {      //loop through all items
      allItems=allItems.+(itemss.get(ib).getRegistryName.toString->itemss.get(ib))    //add items to allitems
      ib += 1
    }


    val blocks = ForgeRegistries.BLOCKS.getValues // load all registred blocks into
    ib = 0
    while (ib < blocks.size()) {      //loop through all blocks
      this.allItems+(Item.getItemFromBlock(blocks.get(ib)).getRegistryName.toString->Item.getItemFromBlock(blocks.get(ib)))     //adding the correspondong item for each block
      ib += 1
    }


    allItems.foreach(U=>System.out.println("S:"+U._1+" "+U._2.getRegistryName))       // log all items

    allCreativeTabs=Map(CreativeTabs.BREWING.getTabLabel->CreativeTabs.BREWING)       // define allCreativeTabs with one (brewing)

    allItems.foreach(U=> {    // looping through all items
      val s = U._2
      var exist=false
      allCreativeTabs.foreach(C=> if(C._2==s.getCreativeTab) exist=true)  // if CreativeTab exists set exsist to true
      if(!exist && s.getCreativeTab!=null) allCreativeTabs = allCreativeTabs+(s.getCreativeTab.getTabLabel->s.getCreativeTab) // if exist is false and an crative tab is set add it to the map
    })

    allCreativeTabs.foreach(C => System.out.println("Ct:" +C._1+ C._2.getTabLabel))   //log all creativetabs
  }

  def getItemByID(ID:String):Item = {   //returns an item from an ID (minecraft:iron_axe
    return allItems(ID)
  }

  def getCreativeTabByLable(Lb:String): CreativeTabs ={   //returns an creative tab from its lable
    return allCreativeTabs(Lb)
  }

  def newCreativeTab(Name:String,Id:String): Unit ={      //creates a new Creative tab
    var s:CreativeTabs=new myCreativeTab(Name,lists.getItemByID(Id))
    allCreativeTabs=allCreativeTabs+(s.getTabLabel->s)

    System.out.println("Ct:" +s.getTabLabel)   //log all creativetabs
  }

}
