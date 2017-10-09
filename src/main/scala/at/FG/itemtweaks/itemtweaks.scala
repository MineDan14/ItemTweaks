package at.FG.itemtweaks

import java.io.{File, FileReader, FilenameFilter}
import javax.script.ScriptEngineManager

import at.FG.itemtweaks.items.BaseItem
import net.minecraftforge.fml.common.Mod.EventHandler
import net.minecraftforge.fml.common.event.{FMLInitializationEvent, FMLPostInitializationEvent, FMLPreInitializationEvent, FMLServerAboutToStartEvent}
import net.minecraftforge.fml.common.{Mod, SidedProxy}
import at.FG.itemtweaks.proxy.CommonProxy
//import at.FG.itemtweaks.render.ItemRendererRegister
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.Item
import net.minecraftforge.event.RegistryEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder
import net.minecraftforge.fml.common.registry.{ForgeRegistries, GameRegistry}
import net.minecraftforge.registries.IForgeRegistry

@Mod(modid = "itemtweaks", version = "1.0", modLanguage = "scala")
@Mod.EventHandler
object itemtweaks{
  val MODID="Itemtweaks"
  //val config = new Configuration(new File("./config/mymod.config"))


  var testitem:Item = new BaseItem("testitem")

  @SidedProxy(
    clientSide = "at.FG.itemtweaks.proxy.ClientProxy",
    serverSide = "at.FG.itemtweaks.proxy.ServerProxy"
  )
  var proxy:CommonProxy = null


  @EventHandler
  def preInit(event: FMLPreInitializationEvent): Unit = {
    System.out.println("Itemtweaks PreInit")
  }

  @EventHandler
  def postInit(event: FMLPostInitializationEvent): Unit = {
    System.out.println("Itemtweaks PostInit")

    at.FG.itemtweaks.lists.init()    // initializing all needed lists ( items, Creativetabs, ...
    System.out.println("Registred:"+ ForgeRegistries.ITEMS.containsValue(testitem))
    var engine = new ScriptEngineManager(null).getEngineByName("nashorn") // creating a new nashorn engine to interptet th scripts
    engine.eval("var jsAPI = Java.type('at.FG.itemtweaks.API.jsAPI');")       // loading the Itemtweaks api into the compiler
    System.out.println("Nashorn init done")
    val folder = new File("./scripts/it")
    val list_of_files = folder.listFiles(new FilenameFilter {         ////creating a filter for js files
      override def accept(dir: File, name: String): Boolean = {
        return true
        name.contains(".js")
      }
    })
    if(list_of_files!=null) {
      System.out.println(list_of_files.length + " Files found")
      list_of_files.foreach(U => {
        System.out.println("Executed" + U.getName)
        engine.eval(new FileReader(U))
      })
    }
  }

  @EventHandler
  def init(event: FMLInitializationEvent) {
    System.out.println("Itemtweaks Init")
//    System.out.println("DIRT BLOCK >> " + Blocks.DIRT.getUnlocalizedName)
    System.out.println(testitem)
    //ItemRendererRegister.registerItemRenderer()

  }

    @SubscribeEvent
    def registerItems(event :RegistryEvent.Register[Item]) {
      System.out.println("registering:")
      val registry:IForgeRegistry[Item]=event.getRegistry
      testitem.setCreativeTab(CreativeTabs.REDSTONE)
      testitem.setRegistryName(itemtweaks.MODID+":"+testitem.getUnlocalizedName())
      testitem.setMaxStackSize(64)
      registry.register(testitem)
    }
}