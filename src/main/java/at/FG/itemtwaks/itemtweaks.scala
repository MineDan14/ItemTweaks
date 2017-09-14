package at.FG.itemtwaks

import java.io.{File, FileReader, FilenameFilter, Reader}
import javax.script.ScriptEngineManager

import at.FG.itemtwaks.API.jsAPI
import net.minecraft.init.Blocks
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.Mod.EventHandler
import net.minecraftforge.fml.common.event.FMLInitializationEvent
import net.minecraftforge.fml.common.event._
import net.minecraftforge.fml.common.registry.{ForgeRegistries, GameRegistry}
import at.FG.itemtwaks.items.testitem
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.{Item, ItemStack}
import net.minecraftforge.common.config.Configuration
import at.FG.itemtwaks.lists



@Mod(modid = "itemtweaks", version = "1.0", modLanguage = "scala")
object MyMod {
  val MODID="itemtweaks";
  //val config = new Configuration(new File("./config/mymod.config"))

  @EventHandler
  def preInit(event: FMLPreInitializationEvent): Unit = {

  }

  @EventHandler
  def postInit(event: FMLPostInitializationEvent): Unit = {

    at.FG.itemtwaks.lists.init()    // initializing all needed lists ( items, Creativetabs, ...

    var engine = new ScriptEngineManager().getEngineByName("nashorn") // creating a new nashorn engine to interptet th scripts
    engine.eval("var jsAPI = Java.type('at.FG.itemtwaks.API.it');")       // loading the Itemtweaks api into the compiler

    val folder = new File("./scripts/it");
    val list_of_files = folder.listFiles(new FilenameFilter {         ////creating a filter for js files
      override def accept(dir: File, name: String): Boolean = {
        return name.contains(".js")
      }
    })

    System.out.println(list_of_files.length+" Files found");
    list_of_files.foreach(U=>{
      System.out.println("Executed"+U.getName)
      engine.eval(new FileReader(U))
    } )

  }

  @EventHandler
  def beforeServerStart(event: FMLServerAboutToStartEvent): Unit = {

  }

  @EventHandler
  def init(event: FMLInitializationEvent) {
//    System.out.println("DIRT BLOCK >> " + Blocks.DIRT.getUnlocalizedName)
  }
}

