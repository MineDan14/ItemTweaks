package at.FG.itemtwaks.API;

import net.minecraft.item.Item;

public class it {

  /* public static void testfunction(String s){
       jsAPI$.MODULE$.testfunction(s);
   }*/

   public static void setItemCreativeTab(String ID,String Ct){
       jsAPI.setItemCreativeTab(ID,Ct);
   }

   public static void newCreativeTab(String Name,String Item){
       jsAPI.newCreativeTab(Name,Item);
   }

}