/*
 * cnetwork - a constraint network implementation for Java
 * Copyright (C) 2017 Julian Thome <julian.thome.de@gmail.com>
 *
 * cnetwork is licensed under the EUPL, Version 1.1 or – as soon
 * they will be approved by the European Commission - subsequent versions of the
 * EUPL (the "Licence"); You may not use this work except in compliance with the
 * Licence. You may obtain a copy of the Licence at:
 *
 * https://joinup.ec.europa.eu/sites/default/files/eupl1.1.-licence-en_0.pdf
 *
 * Unless required by applicable law or agreed to in writing, software distributed
 * under the Licence is distributed on an "AS IS" basis, WITHOUT WARRANTIES OR
 * CONDITIONS OF ANY KIND, either express or implied.  See the Licence for the
 * specific language governing permissions and limitations under the Licence.
 */

package com.github.hycos.cnetwork.utils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


public class EscapeUtils {

    private static Character[] sarray = new Character[]{'+', '{', '}', '(',
            ')', '[', ']', '&', '^', '-', '?', '*', '\"', '$', '<', '>', '.',
            '|', '#' ,'~', '\\'};

    private static Set<Character> special = new HashSet<Character>(Arrays.asList(sarray));


    public static String escapeSpecialCharacters(String s) {
        StringBuilder out = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (special.contains(c)) {
                out.append("\\" + c);
            } else {
                out.append(c);
            }
        }
        return out.toString();
    }

    public static String unescapeSpecialCharacters(String s) {
        if(s == null)
            return "";

        StringBuilder out = new StringBuilder();
        char pred = ' ';
        for (char c : s.toCharArray()) {
            if (pred == '\\' && special.contains(c)) {
                out.deleteCharAt(out.length() - 1); // delete NULL
                out.append(c);
            } else {
                out.append(c);
            }
            pred = c;

            System.out.println("PRED " + pred);
            System.out.println("NXT " + c);
        }
        return out.toString();
    }

}
