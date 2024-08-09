package com.nanou.yaraBank.exception.Response;


public class Message {


    public static final String ACCOUNT_SAVE = "le compte a été crée avec success" ;
    public static final String ACCOUNT_CHECKING = "merci de verifier votre nimero de compte" ;
    public static final String READY_EXIST_MAIL ="cet email existe déja" ;
    public static final String READY_EXIST_PHONE ="cet numero de telephone  existe déja" ;
    public static final String BAD_REQUEST = "merci de verifier votre mot de passe et username";
    public static final String LOGIN_SUCCESS="connexion reussie avec success";
    public static final String AGENCY_SUCCESS="agence crée  avec success";
    public static final int OK=1;
    public static final String VOTRE_SUGGESTION_A_ETE_PRIS_EN_COMPTE = "votre suggestion a ete pris en compte";
    public static final String DEVICE="XOF";
    public static final String IDCUSTOMER="000";
    public static final double OVERDRAFT =900000 ;
    public static final String INDICE=  "YB";
    public static final String OPERATIONWAITING= "votre operation est en attente d'activation ";
    public static final String OPERATIONSUCCESS= "votre operation effectuer en avec  success ";


    public static final String HISTORY_EMPTY = "il ya aucune historique de paiement d'abonnément ";
    public static final String SUBSCRIBE_ASK = "il ya aucune demande d'abonnément ";
    public static final String LICENSE_VALID = " votre license vous a été envoyé ";
    public static final String LICENSE_ASKING= " demande license est en cours  de traitement";
    public static final String LICENSE_ASKING_SEND = " demande license enregistrer avec  succes  ";
    public static final String HISTORY_EMPTY_FOR =  "il y a aucune demande d'abonnément disponible pour cet etablissement ";
    public static final String USERNAME_AUTHORISATION = "utilisation a deja  ce authorisation";
    public static final String SEND_CODE = "un nouveau code vous a été envoyé";
    public static final String ACTIVATION_LICENSE = "License d'activation du Compte";
    public static final String NO_SEND_CODE = "le code ne vous a pas été envoyé";
    public static final String CODE_NO = " ce compte a été déja activé  ";
    public static final String AUTHORISATION_SUCCESS_DELETE = "authorisation modifié avec success";
    public static final String AUTHORISATION_NO_EXIT = "cette authorisation n'existe pas ";
    public static final String AUTHORISATION_SUCCESS_ADD = "authorisation ajoutée avec success";
    public static final String PASSWORD = "  le mot de passe est Obligatoire ";
    public static final String USERNAME = "  le nom d'utilisateur est Obligatoire ";
    public static final String USERNAME_SUCCESS_ACTIVATED = "compte activé avec success";
    public static final String USER_INVALID = " votre nom d'utilisateur n 'est pas correcte ";
    public static final String USER_NOT_CONNEXION = " ce compte  est déja  encours  utilisation  ";
    public static final String LICENSE_INVALID = " votre license n 'est plus valide ";
    public static final String Code_ALREADY_EXIP = " cet code a deja  expiré";
    public static final String Code_ALREADY_USED = " cet code a été deja utilisé ";
    public static final String CURRENT_PASSWORD_IS_REQUIRED = "  l'ancien mot de passe  est Obligatoire ";
    public static final String NEW_PASSWORD_IS_REQUIRED = "  le nouveau mot de passe  est Obligatoire ";
    public static final String CONFIRM_PASSWORD_IS_REQUIRED = "  la confirmation  de mot de passe est Obligatoire ";
    public static final String PASSWORD_OLD_IS_BAD = " votre ancien mot de passe est incorrect ";
    public static final String THE_BOTH_PASSWORD_IS_INVALID = " vos deux nouveaux mot de passe  ne sont pas conforme ";
   // public static final String PASSWORD_OLD_AND_CURRENT_PASSWORD_ARE_BAD = " votre ancien mot de passe et nouveau mot de passe sont les memes  ";
    public static final String PASSWORD_IS_VALID = " mot de passe  modifié  avec success ";
    public static final String PASSWORD_CURRENT_IS_BAD = " votre ancien n'a pas été modifié ";
    public static final String GROUP = " vous n'êtes pas dans le groupe authorisé merci de contacter l'administrateur   ";
    public static final String CODE_NO_GENERATED = " vous ne pouvez pas gere encore de code merci de verifier votre email  ";
    public static final String AUTHORISATION_SUCCESS_SAVE = "authorisation enregistré avec success";
    public static final String AUTHORISATION_N_ADD = "le authorisation n'a pas été ajoutée";
    public static final String AUTHORISATION_NO_ADD = "cet authorisation existe deja";
    public static final String SOURCE_PROFILE = "src/main/resources/profiles";
    public static final String FILE_NO_TOK = "le formant du nom de votre fichier est incorrect ";
    public static final String FILE_EXIST = "cet fichier du meme nom  existe deja.";
    public static final String FILE_NOT_OK = "fichier non  charger ";

// les messages de retour pour l'utilisateur
    public static final String EMAIL_NOT_FOUND = " cet email  n'existe pas   ";
    public static final String PASSWORD_NO_SEND = " vous ne pouvez pas reinitialiser votre mot de passe encore veillez utilisé celui qui a été générer  ";
    public static final String PASSWORD_INITIALIZE = " un email contenant votre mot de passe  vous a été envoyé  avec success ";
    public static final String USER_NOT_LOGIN = "  nom d'utilisateur ou mot de passe  incorrect   ";
    public static final String ACCOUNT_LOCKE = "votre compte a été bloqué. Veuillez patienter quelques minutes";
    public static final String PASSWORD_ALREADY_EXPIRE = " cet mot pass n'est plus valable";
    public static final String AUTHORISATION_EMPTY = "il ya aucune authorisation disponible";
    public static final String NAME_ALREADY_EXIT = "ce nom existe deja";

// les messages de retour pour l'etat de stock
    public static final String STOCK_NOT_FOUND = "id état de stock  incorrect";
    public static final String STOCK_SAVE_SUCCESS = "  état de stock crée avec success";
    public static final String STOCK_ALREADY_EXIT = "ce  état de stock existe deja";
    public static final String STOCK_IS_EMPTY = "il y a aucun   état de stock  disponible ";
    public static final String STOCK_EDIT_SUCCESS = " stock  état de modifié avec success";
    public static final String STOCK_DELETE_SUCCESS = " stock  état de  supprimé avec success";

// les messages de retour pour le Shop
    public static final String SHOP_ERROR = " il ya un probleme avec votre boutique ";
    public static final String SHOP_LICENSE_INVALID = " Merci de contacter votre magasin pour activer sa licence ";
    public static final String SHOP_ACCOUNT = " Merci de contacter votre magasin  ";

    // les messages de retour pour le product
    public static final String PRODUCT_NOT_FOUND = "id produit  incorrect";
    public static final String PRODUCT_IS_EMPTY = "il y a aucun  produit  disponible ";
    public static final String PRODUCT_SAVE_SUCCESS = "produit crée avec success";
    public static final String PRODUCT_DELETE_SUCCESS = " produit  supprimée avec success";

// les messages de retour pour la category
    public static final String CATEGORY_IS_EMPTY = "il y a aucune  categorie  disponible ";
    public static final String CATEGORY_NOT_FOUND = "id categorie  incorrect";
    public static final String CATEGORY_EDIT_SUCCESS = " categorie  modifiée avec success";
    public static final String PRODUCT_EDIT_SUCCESS = " produit  modifiée avec success";
    public static final String CATEGORY_SAVE_SUCCESS = "categorie créee avec success";
    public static final String CATEGORY_DELETE_SUCCESS = " categorie  supprimée avec success";

    // les messages de retour pour le Box
    public static final String BOX_EMPTY = "il y a aucun box disponible";
    public static final String BOX_IS_FULL = " le nombre de space de creation pour votre license est atteint  ";
    public static final String BOX_ALREADY_EXIT = "cet box existe deja ";
    public static final String BOX_SIZE = "merci de verifier la taille du box ";
    public static final String BOX_SAVE_SUCCESS = "box enregistré avec success";
    public static final String BOX_DO_NOT_EXIT = "cet box n'existe pas ";
    public static final String BOX_EDIT_SUCCESS = "box modifié avec success";
    public static final String BOX_DELETE_NOT_SUCCESS = "impossible de  supprimer ce box car  c est rattaché a une boutique  ";
    public static final String BOX_DELETE_SUCCESS = "box supprimé avec success  ";

    // les messages de retour pour le price
    public static final String PRICE_NOT_FOUND = "id prix  incorrect";
    public static final String PRICE_IS_EMPTY = "il y a aucun  prix  disponible ";
    public static final String PRICE_EDIT_SUCCESS = " prix  modifié avec success";
    public static final String PRICE_DELETE_SUCCESS = " prix  supprimée avec success";

    // les messages de retour pour la Facture
    public static final String INVOICE_SAVE = " facture enregistré avec success ";
    public static final String INVOICE_IS_EMPTY = "il y a aucune  facture  disponible ";
    public static final String INVOICE_DATE_IS_EMPTY = "il y a aucun montant disponible sur cette periode  ";

    // les messages de retour pour la Commande
    public static final String COMMAND_SAVE = " commande enregistré avec success ";
    public static final String COMMAND_IS_EMPTY = "il y a aucune  commande  disponible ";

    // les messages de retour pour la PROFORMA
    public static final String PROFORMA_SAVE = " proforma enregistré avec success ";
    public static final String PROFORMA_IS_EMPTY = "il y a aucun  proforma  disponible ";



    // les messages de retour pour l'emplacement
    public static final String EMPLACEMENT_SAVE = " l'emplacement enregistré avec success ";
    public static final String EMPLACEMENT_IS_EMPTY = "il y a aucun  emplacement  disponible ";
    public static final String EMPLACEMENT_EDIT_SUCCESS = " emplacement  modifié avec success";
    public static final String EMPLACEMENT_DELETE_SUCCESS = "emplacement supprimé avec success  ";
    public static final String EMPLACEMENT_NOT_FOUND = "id emplacement  incorrect";
    public static final String EMPLACEMENT_ALREADY_EXIT = "cet emplacement existe deja ";
    // les messages de retour pour le item
    public static final String ITEM_IS_EMPTY = "il y a aucun  article  disponible sur cette periode ";


    // les messages de retour pour le Paiement
    public static final String PAIEMENT_DENIED = "Merci de verifier votre montant ";
    public static final String BALANCE_DENIED = "  votre solde est insuffisant ";
    public static final String PAIEMENT_SUCCESS = "paiement effectué  avec success ";
    public static final String CONNEXION_SUCCESS = "connexion réussie  avec success ";

    public static final String NEW_ACTIVATION_CODE = "Nouveau code d'activation ";
    public static final String RESET_PASSWORD ="Réinitialisation de votre mot de passe " ;
    public static final String ACCOUNT_CREATED ="Création du Compte" ;
    public static final double RATE = 0.9;
}
