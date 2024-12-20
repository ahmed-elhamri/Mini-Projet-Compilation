package main;

import java.util.Arrays;
import java.util.List;

public class Parseur {

    private String tokenActuel;
    private TokenManager gestionnaireDeTokens;
    private boolean estPluriel; 

    public Parseur(TokenManager gestionnaireDeTokens) {
        this.gestionnaireDeTokens = gestionnaireDeTokens;
        avancer();
    }

    public void avancer() {
        tokenActuel = gestionnaireDeTokens.suivant().toLowerCase(); 
    }

    public void consommer(String attendu) {
        if (tokenActuel.equals(attendu)) {
            avancer();
        } else {
            throw new RuntimeException("Erreur : attendu '" + attendu + "' mais trouvé '" + tokenActuel + "'");
        }
    }

    public void parsePhrase() {
        if (estAdverbeCirc(tokenActuel)) {
            parseAdverbeCirc(); 
        }
        parseSujet();
        parseVerbe();
        if (!tokenActuel.isEmpty() && !estVirgule(tokenActuel)) { 
            parseComplement();
        }
    }

    public void parseSujet() {
        if (estPronom(tokenActuel)) {
            consommer(tokenActuel);
        } else {
            if (estArticle(tokenActuel) || estDeterminant(tokenActuel) || tokenActuel.equals("l'")) {
                parseArticle();
                parseNom();
            } else if (estNom(tokenActuel)) {
                parseNom();
            } else if (estAdverbeCirc(tokenActuel)) {
                parseAdverbeCirc();
                parseNom(); 
            } else {
                throw new RuntimeException("Erreur : sujet invalide '" + tokenActuel + "'");
            }
            if (estAdjectif(tokenActuel)) {
                parseAdjectif();
            }
        }
    }

    public void parseVerbe() {
        if (estVerbe(tokenActuel)) {
            consommer(tokenActuel);
        } else {
            throw new RuntimeException("Erreur : verbe invalide '" + tokenActuel + "'");
        }
    }

    public void parseComplement() {
        if (estComplementCirc(tokenActuel)) {
            while (estComplementCirc(tokenActuel)) {
                consommer(tokenActuel);
            }
        } else {
            if (estArticle(tokenActuel) || estDeterminant(tokenActuel)) {
                parseArticle();
                parseNom();
            } else if (estNom(tokenActuel)) {
                parseNom();
            } else {
                return;
            }
            if (estAdjectif(tokenActuel)) {
                parseAdjectif();
            }
        }
    }

    public void parseArticle() {
        if (estArticle(tokenActuel) || estDeterminant(tokenActuel) || tokenActuel.equals("l'")) {
            estPluriel = tokenActuel.equals("les") || tokenActuel.equals("des");
            consommer(tokenActuel);
        } else {
            throw new RuntimeException("Erreur : article invalide '" + tokenActuel + "'");
        }
    }

    public void parseNom() {
        if (estNom(tokenActuel)) {
            consommer(tokenActuel);
        } else {
            throw new RuntimeException("Erreur : nom invalide '" + tokenActuel + "'");
        }
    }

    public void parseAdjectif() {
        if (estAdjectif(tokenActuel)) {
            consommer(tokenActuel);
        } else {
            throw new RuntimeException("Erreur : adjectif invalide '" + tokenActuel + "'");
        }
    }

    public void parseAdverbeCirc() {
        if (estAdverbeCirc(tokenActuel)) {
            consommer(tokenActuel);
        } else {
            throw new RuntimeException("Erreur : adverbe circonstanciel invalide '" + tokenActuel + "'");
        }
    }

    private boolean estArticle(String token) {
        return List.of("le", "la", "les", "une", "un", "des", "de", "du", "au", "aux").contains(token);
    }

    private boolean estNom(String token) {
        return List.of("souris", "fromage", "fleur", "soleil", "maison", "oiseau", "montagne", "rivière", "arbre", "table", "fenêtre", "voiture", "plantes", "amis", "orange", "voitures").contains(token);
    }

    private boolean estAdjectif(String token) {
        return List.of("belle", "nouveau", "gros", "fort", "haut", "rapide", "sage", "heureux", "clair", "douce", "difficile", "facile", "sympa", "moche").contains(token);
    }

    private boolean estVerbe(String token) {
        return List.of("mange", "mangent", "parle", "parlent", "regarde", "regardent", "écoute", "écoutent", "marche", "marchons", "court", "courent", "dort", "dorment", "écrit", "écrivent").contains(token);
    }

    private boolean estPronom(String token) {
        return List.of("je", "tu", "il", "elle", "on", "nous", "vous", "ils", "elles").contains(token);
    }

    private boolean estDeterminant(String token) {
        return List.of("mon", "ton", "son", "notre", "votre", "leur", "leurs", "ma", "ta", "sa").contains(token);
    }

    private boolean estComplementCirc(String token) {
        return List.of("chaque", "matin", "soir", "à", "6", "heures", "hier", "aujourd'hui", "demain", "lundi", "mardi", "samedi", "dimance", "janvier", "fevirier", "mars", "decembre", "toujours").contains(token);
    }

    private boolean estAdverbeCirc(String token) {
        return List.of("aujourd'hui", "demain", "hier", "chaque matin", "toujours", "jamais", "maintenant", "loin", "partout").contains(token);
    }

    private boolean estVirgule(String token) {
        return token.equals(",");
    }

    public void parse() {
        parsePhrase();
        if (!tokenActuel.isEmpty()) {
            throw new RuntimeException("Erreur : entrée restante '" + tokenActuel + "'");
        }
    }
}
