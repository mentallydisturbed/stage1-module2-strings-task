package com.epam.mjc;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class StringSplitter {

    /**
     * Splits given string applying all delimeters to it. Keeps order of result substrings as in source string.
     *
     * @param source source string
     * @param delimiters collection of delimiter strings
     * @return List of substrings
     */
    public List<String> splitByDelimiters(String source, Collection<String> delimiters) {
        List<String> result = new ArrayList<>();
        result.add(source);
        Iterator<String> it = delimiters.iterator();
        while(it.hasNext()){
            String delim = it.next();
            List<String> newResult = new ArrayList<>();
            for (Iterator<String> jt = result.iterator(); jt.hasNext();) {
                String str = jt.next();
                String[] elems = str.split(delim);
                for (String pos:
                     elems) {
                    if(pos.length() > 0)
                        newResult.add(pos);
                }

            }
            result = newResult;
        }
        return result;
    }
}
