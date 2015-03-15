## WareHouse ##

Doel: <br>
Zorgen dat alle <a href='WareHouseItem.md'>WareHouseItem</a>s op 1 plaats kunnen bijgehouden worden, per warehouse item type moet er een limiet kunnen opgelegd worden. De plaats waar we deze info bijhouden lijkt me best in warehouse zelf.<br>
<br>
Nodig:<br>

''Stockmanagement''<br>
Voor stock management zie de respectievelijke paginas per <a href='WarehouseItemType.md'>WarehouseItemType</a>, en zie de paginas <a href='StockProvider.md'>StockProvider</a> en <a href='OrderStrategy.md'>OrderStrategy</a>.<br>
<br>
1: WAREHOUSEITEMTYPE is dit een methode van warehouseitem?</br>
<pre><code>WareHouseItem i = new SleepingTablet();<br>
<br>
//Option one<br>
<br>
i.getClass();//  Voordeel dat het altijd uniek is.<br>
<br>
i.getType(); // &lt; geeft een int terug of iets van het type ItemType<br>
             // &lt;voordeel dat het meer vrijheid geeft aan de itemtypes maar dit <br>
             // dit lijkt nogal loos om daarvoor de inheritance tree te copieren<br>
             // Mischien een string?<br>
<br>
</code></pre>

Enum vs getType()<br>
<br>
Enum:<br>
+ Simpel.<br>
- Moeilijk te onderhouden -> niet te voorspellen wat er gebeurt bij uitbreiding.<br><br>
getType():<br>
+ Niet werken met getClass(). Waarom niet? -> Weet niet precies het Class werkt.<br>
- Moet uniek zijn.<br>

reflection vs factory vs types vs zoals het nu is.