Wie loze dingen vindt bij het nalezen van de controllers e.d. mag die hier zetten zodat we ze later kunnen fixen:

  * warehouseItemType heeft een factory method voor WarehouseItem
  * we hebben gekozen om de de maximale hoeveelheid in het warehouse te managen met een map van Class<?extends WarehouseItemType> naar Integer., de reden dat we dit gedaan hebben is om er zeker van te zijn dat de hashcode & de .equals uniek zijn per WarehouseItemType.
  * 
