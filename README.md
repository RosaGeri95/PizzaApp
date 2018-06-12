# PizzaApp

A projektet a "Mobil- és webes szoftverek" című tárgy keretében készítettem el.

Az alkalmazás egy (fiktív) Pizzéria mobilos (Androidos) felülete, ami segítségével pizzát lehet rendelni. Vannak előre összeállított ételek, illetve magunk is megválogathatjuk a paramétereket, például a pizza méretét és hogy milyen feltét legyen rajta (például sonka, szalámi, gomba, kukorica, stb...). Az így összerakott pizzákat el is lehet menteni a kedvencek közé, így legközelebb már könnyebben be lehet tenni őket a kosárba.

# Föbb funkciók
* A megrendelt tételeket egy kosárban jelenti meg RecyclerView segítségével.
* A kosárba kétféleképpen lehet pizzát betenni: A kedvencek (és az előre összeállítottak) nézetről egy kattintásra, ezek egy adatbázisban vannak eltárolva. A másik lehetőség, ha magunk rakjuk össze, ezt egy DialogFragment segítségével lehet megtenni.
* A kosárból törölni is lehet tételeket, illetve az áruk árát mindig kiírja és összesíti is. Ha megvagyunk, el lehet kezdeni a rendelést.
