package com.example.karcianka.GameEntity

import com.example.karcianka.R

class All
{
    companion object{
        //lokacje
        var blankloc = Location("blankloc", "Pusta lokacja",
            "Ciemno wszędzie, głucho wszędzie.", draw = R.drawable.przygody)

        var biblioteka = Location("biblioteka", "Biblioteka",
            description="Legenda głosi że ktoś przyszedł tu z własnej woli", draw=R.drawable.biblioteka)

        var samouczek = Location("samouczek", "Samouczek", "Kliknij w butelkę aby wejść do budynku", draw =R.drawable.solaris)

        var cek = Location("cek","Centrum Energetyczno-Kaloryczne", description="Opis CEK", draw=R.drawable.cek)

        var chemia = Location("chemia", "Pracownia Alchemiczna", description="Opis Chemii", draw=R.drawable.chemia)

        var elektryk = Location("eletryk", "Klasztor Boga Błyskawic", description="Opis Elektryczny", draw=R.drawable.elektryk)

        var firewall = Location("firewall", "Ściana ognia",
            "Ufff... ależ tu jest gorąco...", draw = R.drawable.firewall)

        var gig = Location("gig", "Główny Instytut Górnictwa", description="Opis GiG", draw=R.drawable.gig)

        var hala = Location("hala", "Arena olimpijska", description="Opis Hali Sportowej", draw=R.drawable.hala)

        //var karta_morska = Location("statek", "Statek na Kłodnicy",  "Strasznie tu sie chwieje. I śmierdzi.", draw = R.drawable.klodnica)

        var klodnica = Location("klodnica", "Kłodnica.", "Fuuuuj....", draw=R.drawable.klodnica)

        var labirynt = Location("labirynt", "Labirynt",
            "Milion korytarzy i milion zaułków...", draw = R.drawable.labirynt)

        var ministerstwo = Location("ministerstwo", "Ministerstwo Sledzia i Wódki",
            "Dawno tu nie byliśmy... (tak naprawdę to ledwo wczoraj)", draw = R.drawable.ministerstwo)

        var ms = Location("ms", "Krąg Rytualny", "Yyyyy... to twoj wydzial?", draw=R.drawable.ms)

        var mt = Location("mt", "Królestwo Muzyki i Tanca", description="Każdy tańczy i śpiewa", draw=R.drawable.mt)

        var mrowisko = Location("mrowisko", "Mrowisko",
            "Opis mrowiska.", draw = R.drawable.mrowisko)

        var narnia = Location("narnia", "Narnia",
            "Nic nie trzeba mówić, wszyscy to miejsce znają.", draw = R.drawable.narnia)

        var park = Location("park", "Park", description="Drzewa wszędzie, dziki wszędzie", draw=R.drawable.park)

        var wieza = Location("wieza", "Wieża Magów", description="Co tu mówić", draw=R.drawable.wieza)

        var solaris = Location("solaris", "Solaris",
            "Stowarzyszenie osób lubiących alkohol, rozrywki i studia", draw=R.drawable.solaris)

        var wnetrzeMinisterstwo = Location("wnetrze", "Ministerstwo Śledzia i Wódki",
            "Dawno tu nie byliśmy... (tak naprawdę to ledwo wczoraj)", draw=R.drawable.wnetrzeministerstwa)

        var wszystkielokacje = mutableListOf<Location>(biblioteka, cek, chemia, elektryk,
            firewall,gig,hala,klodnica,labirynt,ms,mt,mrowisko, narnia, park, wieza, solaris)


        //npce
        var smok = NPC(
            "smok", "Ognisty smok",
            "To chyba.... prawdziwy smok. I zieje ogniem!!!", draw = R.drawable.dragon
        )
        var kapciomag = NPC(
            "kapciomag", "Wielki Mag",
            "Wyglada jak młody Gandalf ubrany na fioletowo.", draw = R.drawable.kapciomag
        )
        var kapcio = NPC(
            "kapcio", "???",
            "Jakiś obszarpany gość???", draw = R.drawable.kapcio
        )
        var kapciomagrozmazany = NPC(
            "kapcio", "???",
            "Coś... ktoś... mi się rozmazuje przed oczami...", draw = R.drawable.kapciomagrozmazany
        )
        var witula = NPC(
            "witua", "Rybak",
            "Chyba go znam tego czlowieka!", draw = R.drawable.witua
        )
        var kolega = NPC(
            "kolega", "Kolega",
            "Znam go z widzenia. Chyba. Ale na wódkę zawsze zaprasza.", draw = R.drawable.kolega
        )

        //itemy
        var losos = Item("losos", "Łosoś kłodnicki",
            "Wygląda jak zwykły łosoś, ale czy kłodnicki na pewno jest bezpieczny?", draw = R.drawable.losos)
        var utm = Item("utm", "UTM", "", draw = R.drawable.utm)
        var shot1 = Item("shot1", "Niebieski szocik", "To chyba sie nazywało kamikadze.", draw = R.drawable.szot1)

        var shot2 = Item("shot2", "Pomarańczowy szocik", "Pomarańcza jak nic.", draw = R.drawable.szot2)

        var shot3 = Item("shot3", "Żółty szocik",
            "To pewnie będzie... kwaśne. Jak cytryna. A może to słodki banan?", draw = R.drawable.szot3)

        var shot4 = Item("shot4", "Różowy szocik", "Nie mam pojęcia, z czego to... Nie wiem, czy chcę wiedzieć.", draw = R.drawable.szot4)

        var shot5 = Item("shot5", "Zielony szocik", "Kaktus???", draw = R.drawable.szot5)

        var shots = mutableListOf(shot1, shot2, shot3,shot4, shot5)

        var memeEnding = Location("meme", "", "", draw=R.drawable.przygody)

        var all = mutableListOf(biblioteka, cek, chemia, elektryk,
            firewall,gig,hala,klodnica,labirynt,ms,mt,mrowisko, narnia,
            park, wieza, solaris, smok, kapciomag, kapcio, kapciomagrozmazany,
        witula, losos, utm, kolega, shot1, shot2, shot3, shot4, shot5, ministerstwo, wnetrzeMinisterstwo, memeEnding)




    }

}