package com.nanou.yaraBank.exception.Response;


public class Security {
    public static final String CODE_INVALID = " votre code n 'est pas correcte ";
    public static final Integer VALIDATION = 24;
    public static final Integer MONTH = 32;

    public static final Integer N = 2;
    public static final Integer N1 = 3;
    public static final Integer NO = 0;
    public static final Integer D = 10;
    public static final Integer T = 3;
    public static final Integer TATTENTIF = 2;
    public static final Integer TWO = 2;
    public static final Integer SIX = 6;

    public static final String EMAIL_INVALID = " votre email n 'est pas correcte ";
    public static final String SHOP_SUCCESS_SAVE = "Boutique enrégistrée avec success";
    public static final String SPACE_SUCCESS_SAVE = "espace de boutique enrégistré avec success";
    public static final String EMAIL_ALREADY_EXIST = " cet email  existe déja ";
    public static final String USERNAME_ALREADY_EXIST = " cet username  existe déja ";

    public static final String SHOP_ALREADY_EXIST = " ce nom  de  boutique  existe déja ";

    public static final String PHONE_ALREADY_EXIST = " ce numéro de téléphone existe deja ";
    public static final String SHOP_REQUIRED = "  la boutique est Obligatoire ";

    public static final String DURATION_INVALID = "  la durée est obligatoire ";







    public static final Integer OK = 1;
    public static final String LOGOUT = " déconnecté avec success   ";
    public static final String ENCRIPTION_KEY = "3404a6b8a0bc685ac5ddb21c8aca6be5b5ca38baa4f1638df36b131e831202f6";
    public static final String REFRESHTOKEN_NOT_FOUND = "le token a expiré ";
    public static final String TOKEN_NOT_FOUND = " cet token est incorrecte    ";
    public static final String TOKEN_EXPIRE = " cet token n'est plus  valide  ";
    public static final String USER_NOT_FOUND = " cet nom d'utilisateur n'existe pas   ";
    public static final String FORMAT_CODE ="%09d" ;
    public static final String CHEICK_OPERATION =" echec de l'operation merci de verifier le solde de votre compte" ;
    public static final String EMPTY_OPERATION =" il y a aucune l'operation disponible " ;
    public static final String EMPTY_ACCOUNT =" il y a aucun compte disponible " ;
    public static final String EMPTY_MESSAGE =" il y a aucune suggestion disponible " ;
    public static final String CHEICK_ACCOUNT ="merci de verifier le solde de votre compte" ;
    static final String ACCESS_NOT_ACCESS = "Access refusé";
    public static final String PHONE_EXIT = "Ce numéro de téléphone existe déjà";
    public static final String RETURNING = "retour de l'utilisateur trouvé par nom d'utilisateur :";
    public static final String ACCOUNT_LOCKED = "Vôtre compte est en attente de  validation. Veuillez contacter l'administrateur";
    public static final String METHOD_IS_NOT_ALLOWED = "Cette méthode de demande n'est pas autorisée sur ce point de terminaison. Veuillez envoyer une requête '%s'";
    public static final String BAD_URL = "Il n'existe aucun mappage pour cette URL";
    public static final String[] PUBLIC_URLS = {"/activation","/ws","createCustomer","\"accountActivationExist/{idAccount}", "/activation/license", "/signIn",  "/verification","/forgetPassword","/changePassword","/box/all","/refreshToken","/verification/tfa","createCustomerExist/{idAccount}/{accountType}","createEntreprise","createEntrepriseExist/{idAccount}/{accountType}","suggestion"};

    public static final String INTERNAL_SERVER_ERROR_MSG = "Une erreur s'est produite lors du traitement de la demande";
    public static final String EMAIL_SERVER_ERROR_MSG = "Une erreur s'est produite lors de l'envoie  du mail";
    public static final String INCORRECT_CREDENTIALS = "Nom d'utilisateur ou mot de passe incorrect. Veuillez réessayer";
    public static final String ACCOUNT_DISABLED = "Votre compte a été désactivé. S'il s'agit d'une erreur, veuillez contacter l'administrateur";
    public static final String ERROR_PROCESSING_FILE = "Une erreur s'est produite lors du traitement merci de verifier vos informations ou votre Connexion internet";
    public static final String NOT_ENOUGH_PERMISSION = "Vous n'avez pas assez d'autorisation";
    public static final String CHECK_FIELD = "Merci de verifier vos champs";
    public static final String ERROR_PATH = "/error";
    public static final String ISSUE = "NANOUROUKOU";
    public static final String LABEL = "E-STOCK";
    public static final Integer DIGITAL = 6;
    public static final Integer PERIOD = 30;

    public static final String ACCESS_DENIED_MESSAGE = "Vous n'avez pas la permission d'accéder à cette page";

    public static final String URL = "la page non trouvé pour cet URL";


}
