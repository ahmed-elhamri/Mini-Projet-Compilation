package main;

public class Test {
    public static void main(String[] args) {
//        String[] tests = {
//            "le chat mange",
//            "la souris mange le fromage",
//            "aujourd'hui je mange une orange",
//            "il court vite",
//            "nous marchons lentement"
//        };
//        
        String[] tests = {
            "le chat mange",
            "une souris mange le fromage",
            "chaque matin, le téléphone sonne à 6 heures",
            "les chiens voient un chat rouge",
            "l'enfant lit un livre ancien",
            "nous aimons les ordinateurs intelligents",
            "demain, je mange une pomme",
            "elle voit un chien joli dans le parc",
            "aujourd'hui, les enfants jouent dans le jardin",
            "fromage",
            "le fromage", 
            "une souris mange", 
            "Je charge mon téléphone", 
            "Chaque matin, le téléphone sonne à 6 heures"
        };

        for (String test : tests) {
            TokenManager tm = new TokenManager(test);
            Parseur parseur = new Parseur(tm);

            try {
                parseur.parse();
                System.out.println("Entrée : \"" + test + "\" => valide");
            } catch (RuntimeException e) {
                System.out.println("Entrée : \"" + test + "\" => invalide (" + e.getMessage() + ")");
            }
        }
    }
}
