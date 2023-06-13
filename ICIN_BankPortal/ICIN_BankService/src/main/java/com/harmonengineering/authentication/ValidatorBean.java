/****************************
 *  ValidatorBean.java
 */
package com.harmonengineering.authentication;

import java.io.IOException;
import java.io.StringReader;


import static java.lang.String.valueOf;
import static java.lang.System.out;

public class ValidatorBean
{
    public boolean isValidText( String s )
    {
        return  (s != null && !s.equals("")) ;
    }
    public boolean isValidNumber( String s )
    {
        return isValidInteger(s) ||
                isValidLong(s)||
                isValidDouble(s);
    }
    public boolean isValidInteger( String s )
    {
        boolean return_value = true ;
        if ( isValidText( s )) {
            try {
                int n = Integer.parseInt(s);
            } catch (NumberFormatException e)
            {
                return_value = false;
            }
        } else return_value = false ;
        return return_value ;
    }
    public boolean isValidLong( String s )
    {
        boolean return_value = true ;
        if ( isValidText( s )) {
            try {
                Long n = Long.parseLong(s) ;
            } catch (NumberFormatException e)
            {
                return_value = false;
            }
        } else return_value = false ;
        return return_value ;
    }
    public boolean isValidDouble( String s )
    {
        boolean return_value = true ;
        if (s != null && !s.equals("")) {
            try {
                Double n = Double.parseDouble(s);
            } catch (NumberFormatException e)
            {
                return_value = false;
            }
        } else return_value = false ;
        return return_value ;
    }
    public boolean isValidDecimal(double doubleNumber, int wholePartLength, int decimalLength )
            throws IOException
    {
        String str = valueOf(doubleNumber) ;
        System.out.println(doubleNumber + " -> " + str ); ;
        char[] bytes = new char[1024] ; int length ;
        StringReader strReader = new StringReader( str ) ;
        length = strReader.read(bytes) ;
        int i ;
        for ( i = 0 ; i < length ; ++i )
        {
            if ( bytes[i] == '.' )
                break ;
            out.print(bytes[i]);
        }
        int wpart = i ;
        int dpart = length-(i+1) ;
        System.out.println( "wpart> " + wpart + " dpart> " + dpart ) ;
        return ( (wpart <= (wholePartLength-decimalLength)) && (dpart <= decimalLength) ) ;
    }
    public String toString() {
        return "ValidatorBean"+this.hashCode();
    }
}