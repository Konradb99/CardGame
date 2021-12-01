package com.example.karcianka.database

import com.example.karcianka.R

class All
{
    companion object{
        //lokacje
        var blankloc = Location("bkankloc", "Pusta lokacja",
            "Ciemno wszędzie, głucho wszędzie.", draw = R.drawable.blank)
        var biblioteka = Location("biblioteka", "Biblioteka", description="Legenda głosi że ktoś przyszedł tu z własnej woli", draw=R.drawable.biblioteka)

        var cek = Location("cek","Centrum Energetyczno Kaloryczne", description="Opis CEK", draw=R.drawable.cek)
        var chemia = Location("chemia", "Pracownia Alchemiczna", description="Opis Chemii", draw=R.drawable.chemia)

        var elektryk = Location("eletryk", "Klasztor Boga Błyskawic", description="Opis Elektryczny", draw=R.drawable.elektryk)

        var firewall = Location("firewall", "Ściana ognia",
            "Ufff... ależ tu jest gorąco...", draw = R.drawable.firewall)

        var gig = Location("gig", "Główny Instytut Górnictwa", description="Opis GiG", draw=R.drawable.gig)

        var hala = Location("hala", "Arena olimpijska", description="Opis Hali Sportowej", draw=R.drawable.hala)

        //var karta_morska = Location("statek", "Statek na Kłodnicy",  "Strasznie tu sie chwieje. I śmierdzi.", draw = R.drawable.klodnica)
        var klodnica = Location("klodnica", "Kłodnica.", "Fuuuuj....", draw=R.drawable.klodnica)

        var labirynt = Location("labirynt", "Labirynt",
            "Milion korytarzy i milion zaułków... czy tam są schody?!", draw = R.drawable.labirynt)
        var ms = Location("ms", "Krąg Rytualny", "Yyyyy... to twoj wydzial?", draw=R.drawable.ms)

        var mt = Location("mt", "Królestwo Muzyki i Tanca", description="Każdy tańczy i śpiewa", draw=R.drawable.mt)

        var mrowisko = Location("mrowisko", "Mrowisko",
            "Opis mrowiska.", draw = R.drawable.mrowisko)
        var narnia = Location("narnia", "Narnia",
            "Nic nie trzeba mówić", draw = R.drawable.narnia)

        var park = Location("park", "Park", description="Drzewa wszędzie, dziki wszędzie", draw=R.drawable.park)

        var wieza = Location("wieza", "Wieża Magów", description="Co tu mówić", draw=R.drawable.wieza)

        var solaris = Location("solaris", "Solaris", "Stowarzyszenie osób lubiących alkohol, rozrywki i seks", draw=R.drawable.solaris)

        var wszystkielokacje = mutableListOf<Location>(biblioteka, cek, chemia, elektryk,
            firewall,gig,hala,klodnica,labirynt,ms,mt,mrowisko, narnia, park, wieza, solaris)   


            //npce
            var smok = NPC(
                "smok", "Ognisty smok",
                "To chyba.... prawdziwy smok. I zieje ogniem!!!", draw = R.drawable.dragon
            )
            var kapciomag = NPC(
                "kapciomag", "Wielki Mag",
                "Wyglada jak mlody Gandalf ubrany na fioletowo.", draw = R.drawable.kapciomag
            )
            var kapcio = NPC(
                "kapcio", "???",
                "Jakiś obszarpany gość???", draw = R.drawable.kapcio
            )
            var kapciomagrozmazany = NPC(
                "kapcio", "???",
                "Coś mi się rozmazuje przed oczami...", draw = R.drawable.kapciomagrozmazany
            )
            var witula = NPC(
                "witua", "Witula",
                "Chyba znasz tego czlowieka!", draw = R.drawable.witua
            )

        //itemy
        var losos = Item("losos", "Łosoś kłodnicki",
                        "Wygląda jak zwykły łosoś, ale czy kłodnicki na pewno jest bezpieczny?", draw = R.drawable.losos)
        var utm = Item("utm", "UTM", "", draw = R.drawable.utm)


    }

}