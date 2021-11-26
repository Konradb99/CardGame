package com.example.karcianka.database

import com.example.karcianka.R

class All
{
    companion object{
        //lokacje
        var biblioteka = Location("biblioteka", "")

        var cek = Location("cek","Centrum Energetyczno Kaloryczne")
        var chemia = Location("chemia", "Wydzial Chemiczny")

        var elektryk = Location("eletryk", "Wydzial Elektryczny")

        var firewall = Location("firewall", "Ściana ognia",
                                "Ufff... ależ tu jest gorąco...", draw = R.drawable.firewall)

        var gig = Location("gig", "Wydzial Gorniczy")

        var hala = Location("hala", "Hala sportowa")

        var karta_morska = Location("statek", "Statek na Kłodnicy",
                                    "Strasznie tu sie chwieje. I śmierdzi.", draw = R.drawable.karta_morska)
        var klodnica = Location("klodnica", "Klodnica.", "Fuuuuj....")

        var labirynt = Location("labirynt", "Labirynt",
                                "Milion korytarzy i milion zaułków... czy tam są schody?!", draw = R.drawable.labirynt)
        var ms = Location("ms", "Krag Rytualu", "Yyyyy... to twoj wydzial?")

        var mt = Location("mt", "Wydzial Muzyki i Tanca")

        var mrowisko = Location("mrowisko", "Mrowisko",
                                "Opis mrowiska.", draw = R.drawable.mrowisko)
        var narnia = Location("narnia", "Narnia",
                                "", draw = R.drawable.narnia)

        var park = Location("park", "Park")

        var wieza = Location("wieza", "Wieza Magow")


        var wszystkielokacje = mutableListOf<Location>(biblioteka, cek, chemia, elektryk,
            firewall,gig,hala, karta_morska,klodnica,labirynt,ms,mt,mrowisko, narnia, park, wieza)


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