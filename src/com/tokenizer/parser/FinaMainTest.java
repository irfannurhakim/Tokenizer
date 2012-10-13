/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tokenizer.parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.StringTokenizer;

/**
 *
 * @author user
 */
public class FinaMainTest {
    
    public static void main(String[] args) {
        
        String a="UBS? have,organized an important.meeting  }will be held tomorrow for all employees who "
                + ": 1) have accepted offers or 2) intend to accept offers ( to the best of their "
                + "knowledge) but who?have issues that are being!resolved All employees in these 12:33:34 "
                + "not get this message in time Tammie's Schopp mother-in-law  100-20-1233 10am-1pm rono.budi susi,shinta ";
        
        a= a.toLowerCase();
        String [] ax = a.split("\\s+");
        ArrayList<String> as = new ArrayList<String>();
        
        for (String s : ax) {
            
            if(s.matches("\\W") || s.matches("\\s*") )
            {
                System.out.println(s);
                
            }
            else
            {
                //s= removeHeadAndTail(s);
                s= removeApostrope(s);
                s= removeHypenate(s);
                //s= removeSpecialChar(s);
                if (isNeedSplit(s))
                {
                    String [] asd =splitSpecialChar(s);
                    for (String asdx : asd) {
                        if(!asdx.equals(""))
                        {
                        as.add(asdx);
                        }
                    }
                }
                else
                {
                    as.add(s);
                }
                
            }
        
        }
        
        
        
        System.out.println(as);
        
        /*String test2="<div id=\"related-results\">     <h2>         More Results searching for &#8220;<span class=\"search-term\">html tag regex</span>&#8221;         <a href=\"#related-results\" class=\"close\"></a>      </h2>      <div class=\"content\">          <p>             It looks like you found this post via a search engine result.<br />             Here are a few other posts you might find interesting:          </p> 			    <ul class=\"morelist\"> 			        <li class=\"morelistitem\"> 				        <a id=\"ctl07_Links_ctl01_Link\" href=\"/archive/2005/04/22/Matching_HTML_With_Regex.aspx\" target=\"_blank\">Matching HTML With Regular Expressions Redux</a> 				        <a id=\"ctl07_Links_ctl01_EditReadingLink\" href=\"javascript:__doPostBack(&#39;ctl07$Links$ctl01$EditReadingLink&#39;,&#39;&#39;)\"></a> 		            </li> 			        <li class=\"morelistitem\"> 				        <a id=\"ctl07_Links_ctl02_Link\" href=\"/archive/2007/08/13/speed-up-your-pages-and-improve-your-yslow-score-with.aspx\" target=\"_blank\">Speed Up Your Pages And Improve Your YSlow Score With The Coral Content Distribution Network</a> 				        <a id=\"ctl07_Links_ctl02_EditReadingLink\" href=\"javascript:__doPostBack(&#39;ctl07$Links$ctl02$EditReadingLink&#39;,&#39;&#39;)\"></a> 		            </li> 			    </ul> 			    <a href=\"/search.aspx?q=html%20tag%20regex\" id=\"ctl07_Links_ctl03_searchMore\" class=\"more-results\">Click for all Search Results for html tag regex</a> 	</div>";
        //test2.replaceAll("\\t", "");
        test2 =StringParserMethod.removeHTMLTag(test2);
        test2=test2.replaceAll("\\s", "");
        System.out.println(test2);*/
        
        /*String test3 ="X-Origin: CARSON-M X-FileName: mike carson 6-25-02.PST-----Original Appointment-----".toLowerCase();
        String coba[] =test3.split(".*(.pst)", 2);
        //System.out.println(coba[0]);
        System.out.println(coba[1]);*/
        
       
	    
    }
    public static boolean isNeedSplit(String partString)
    {
        return (partString.matches("(\\w*\\W)*\\w*"));
    }
    public static String[] splitSpecialChar(String partString)
    {
        return (partString.split("\\W"));
    }
    public static String removeHeadAndTail(String anyString){
        String result = "";
        
        result =  (anyString.matches("\\W*\\w*\\W*"))?anyString.replaceAll("\\W+", ""):anyString;       
        return result;
        
    }
    
    public static String removeApostrope(String s)
    {
        s=s.replaceAll("'s", "");
        s=s.replaceAll("'", "");
        return s;
    }
    
    public static String removeHypenate(String s)
    {
        /*if(s.matches("(\\d+\\w*-)*\\d+\\w*"))
        {
            return s;
        }*/
        if(s.matches("([a-zA-Z]*-)*[a-zA-Z]*"))
        {
        s=s.replaceAll("-", "");
        }
        return s;
    }
    
    
    public static String removeSpecialChar(String s){
        return s.replaceAll("[,!;:?%=_&#/\\\\^\\+\\*\\{\\}\\$\\|\\<>\\[\\]\\(\\)\"]+", "");
    }
    
    
}
