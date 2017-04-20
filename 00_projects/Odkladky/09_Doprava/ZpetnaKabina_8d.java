/*******************************************************************************
 * Instance tridy ObousmernaKabina predstavuji kabinky pohybujici se po linkach.
 *
 * Zmeny oproti tride ZpetnaKabina_8c:
 * - Trida nyni dedi od tridy Kabina_8c
 * - Konstruktor vola rodicovsky konstuktor ciste, protoze vsechny nebezpecne 
 *   operace provadi tovarni meotda v okamziku, kdy se provadet smi. 
 *
 * @author  Rudolf Pecinovsky
 * @version 1.00,  29.02.2004
 */
public class ZpetnaKabina_8d extends Kabina_8c
{
//== KONSTANTNI ATRIBUTY TRIDY =================================================
//== PROMENNE ATRIBUTY TRIDY ===================================================
//== KONSTANTNI ATRIBUTY INSTANCI ==============================================
//== PROMENNE ATRIBUTY INSTANCI ================================================
    
    Ctverec ctverec;
    
    
//== PRISTUPOVE METODY VLASTNOSTI TRIDY ========================================
//== OSTATNI METODY TRIDY ======================================================

//##############################################################################
//== KONSTRUKTORY A METODY VRACEJICI INSTANCE VLASTNI TRIDY ====================

    /***************************************************************************
     * Vytvori kruhovou kabinu, ktera se bude pohybovat zadanou rychlosti
     * po zadane lince.
     * 
     * @param linka    Linka, po ktere se kabina pohybuje.
     */
    protected ZpetnaKabina_8d(Linka linka)
    {
        super( linka );
        ctverec = new Ctverec( kabina.getX(), kabina.getY(), PRUMER, 
                              Barva.FIALOVA );
    }


    /***************************************************************************
     * Vytvori kruhovou kabinu, ktera se bude pohybovat zadanou rychlosti
     * po zadane lince.
     * 
     * @param linka    Linka, po ktere se kabina pohybuje.
     */    
    public static ZpetnaKabina_8d getInstance( Linka linka )
    {
        AP.nekresli();
            ZpetnaKabina_8d kabina = new ZpetnaKabina_8d( linka );
        AP.vratKresli();
        kabina.presunuto();
        return kabina;
    }



//== PRISTUPOVE METODY VLASTNOSTI INSTANCI =====================================

    /***************************************************************************
     * Nastavi novou pozici instance.
     *
     * @param x   Nova x-ova pozice instance
     * @param y   Nova y-ova pozice instance
     */
    public void setPozice( int x, int y )
    {
        AP.nekresli();
            super  .setPozice( x,         y         );
            ctverec.setPozice( x-POLOMER, y-POLOMER );
        AP.vratKresli();
    }
    
    

//== PREKRYTE METODY IMPLEMENTOVANYCH ROZHRANI =================================

    /***************************************************************************
     * Za pomoci dodaneho kreslitka vykresli obraz sve instance
     * na animacni platno.
     *  
     * @param kreslitko   Kreslitko, kterym se instance nakresli na platno.     
     */
    public void nakresli( Kreslitko kreslitko )
    {
        ctverec.nakresli( kreslitko );
        super  .nakresli( kreslitko );
    }

//== PREKRYTE METODY IMPLEMENTOVANYCH ROZHRANI =================================

    /***************************************************************************
     * Metoda vyzadovana rozhranim IMultiposuvny: definuje akci, 
     * ktera se ma provest v okamziku, 
     * kdy je objekt doveden do pozadovane cilove pozicie.
     * 
     * @param mp   Multipresouvac, ktery kabinku dovedl do cile.
     *             Ten bude pravdepodobne pozadan, aby kabinku dovezl zase dal
     *             a v pripade dorazeni do cilove stanice
     */
    public void presunuto()
    {
        //Zastavka, kam jsme dorazili, musi znat sveho predchudce
        dalsi = dalsi.getPredchozi();
        
        P.cekej( linka.getCekani() );
        
        //Nechame kabinu presunout na dalsi zastavku
        Multipresouvac mp = Multipresouvac.getInstance();
        mp.presun( this,  dalsi.getPozice(),  linka.getRychlost() );
    }



//== PREKRYTE ABSTRAKTNI METODY RODICOVSKE TRIDY ===============================
//== PREKRYTE KONKRETNI METODY RODICOVSKE TRIDY ================================
//== NOVE ZAVEDENE METODY INSTANCI =============================================
//== SOUKROME A POMOCNE METODY TRIDY ===========================================
//== SOUKROME A POMOCNE METODY INSTANCI ========================================
//== VNORENE A VNITRNI TRIDY ===================================================
//== TESTY A METODA MAIN =======================================================
}
