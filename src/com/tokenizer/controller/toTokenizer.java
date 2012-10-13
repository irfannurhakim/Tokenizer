/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tokenizer.controller;

import java.util.HashMap;

/**
 *
 * @author user
 */
public class toTokenizer {

    public static HashMap<String, Integer> getListTo(String to) {
        HashMap<String, Integer> termList = new HashMap<String, Integer>();
        String[] terms = to.split(",|(\\s)+|<|> ");
        for (int i = 0; i < terms.length; i++) {
            String key = terms[i];
            if (!key.matches("")) {
                Integer freq = (Integer) termList.get(key);
                if (freq == null) {
                    freq = new Integer(1);
                } else {
                    int value = freq.intValue();
                    freq = new Integer(value + 1);
                }
                termList.put(key, freq);
            }
        }
        //System.out.println(termList);
        return termList;
    }
    /*
     * public static void main(String[] args) { String sample =
     * "laurel.adams@enron.com, cyndie.balfour-flanagan@enron.com, "+
     * "\nedward.baughman@enron.com, georgia.blanchard@enron.com, "+
     * "\nfred.boas@enron.com, kathryn.bussell@enron.com, "+
     * "\nmike.croucher@enron.com, cheryl.dawes@enron.com, "+
     * "\nmargaret.dhont@enron.com, e..dickson@enron.com, "+
     * "\nromeo.d'souza@enron.com, michael.eiben@enron.com, "+
     * "\ncounterparty.enron@enron.com, group.enron@enron.com, "+
     * "\nj..farmer@enron.com, genia.fitzgerald@enron.com, l..gay@enron.com, "+
     * "\nscotty.gilbert@enron.com, r..guillen@enron.com, "+
     * "\nsanjeev.gupta@enron.com, m..hall@enron.com, todd.hall@enron.com, "+
     * "\ndavid.hardy@enron.com, m..harmon@enron.com, peggy.hedstrom@enron.com,
     * "+ "\nf..herod@enron.com, liz.hillman@enron.com,
     * nathan.hlavaty@enron.com, "+ "\nmarcie.holtman@enron.com,
     * jim.homco@enron.com, cindy.horn@enron.com, "+ "\nrahil.jafry@enron.com,
     * tana.jones@enron.com, l..kelly@enron.com, "+ "\ntroy.klussmann@enron.com,
     * c..knightstep@enron.com, "+ "\nvictor.lamadrid@enron.com,
     * jenny.latham@enron.com, "+ "\npaula.lee@enron.com, lisa.lees@enron.com,
     * elsie.lew@enron.com, "+ "\nh..lewis@enron.com, kori.loibl@enron.com,
     * donna.lowry@enron.com, "+ "\nf..lytle@enron.com, chris.mallory@enron.com,
     * "+ "\ntom.mcfatridge@enron.com, richard.mckeel@enron.com, "+
     * "\nerrol.mclaughlin@enron.com, bruce.mills@enron.com, "+
     * "\nscott.mills@enron.com, tom.moran@enron.com, l..nowlan@enron.com, "+
     * "\nkaren.o'day@enron.com, scott.palmer@enron.com, "+
     * "\nstephanie.panus@enron.com, k..patton@enron.com, "+
     * "\ndebra.perlingiere@enron.com, sarah.pernul@enron.com, "+
     * "\nrichard.pinion@enron.com, phillip.platter@enron.com, "+
     * "\ns..pollan@enron.com, d..powell@enron.com, joan.quick@enron.com, "+
     * "\nm..thurston@enron.com, stacey.richardson@enron.com, "+
     * "\njeff.richter@enron.com, oscar.rivera@enron.com, "+
     * "\nbernice.rodriguez@enron.com, samuel.round@enron.com, "+
     * "\ndianne.seib@enron.com, stephanie.sever@enron.com, "+
     * "\nlynn.shivers@enron.com, geoff.storey@enron.com, "+
     * "\njohn.suttle@enron.com, diel.terry@enron.com, d..thorne@enron.com, "+
     * "\nsusan.trevino@enron.com, darren.vanek@enron.com, "+
     * "\ndaniel.vargas@enron.com, ellen.wallumrod@enron.com, "+ "\news
     * <.watson@enron.com>, patricia.weatherspoon@enron.com, "+
     * "\nw..white@enron.com, credit <.williams@enron.com>, "+
     * "\nd..winfree@enron.com, christa.winfrey@enron.com, "+
     * "\njeremy.wong@enron.com, rita.wynne@enron.com";
     *
     * //String sample2 = "<.watson@enron.com>"; //sample2.replaceAll("<", "");
     * //System.out.println(sample2); getListTo(sample);
    }
     */
}
