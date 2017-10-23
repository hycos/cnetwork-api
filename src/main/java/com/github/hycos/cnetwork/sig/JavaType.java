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

/**
 * This file is part of the Joana IFC project. It is developed at the
 * Programming Paradigms Group of the Karlsruhe Institute of Technology.
 *
 * For further details on licensing please read the information at
 * http://joana.ipd.kit.edu or contact the authors.
 */
package com.github.hycos.cnetwork.sig;

import java.util.LinkedList;
import java.util.List;


public class JavaType {

    /** Format descriptor */
    public static enum Format {
        /** for human-readable format */
        HR,

        /** for bytecode format */
        BC;
    }

    private final Format format;
    private final JavaPackage pack;
    private final String baseType;
    private final int arrDim;

    private final String bcStringWTS;
    private final String bcStringWOTS;
    private final int hashCode;

    private static final String[] bcBaseTypes = { "V", "Z", "C", "B", "C", "S",
            "I", "J", "F", "D" };
    private static final String[] hrBaseTypes = { "void", "boolean", "char",
            "byte", "char", "short", "int", "long", "float", "double" };

    private JavaType(Format format, String baseType, int arrDim) {
        this.format = format;
        this.arrDim = arrDim;
        switch (format) {
            case HR:
                if (baseType.contains(".")) {
                    int ptIndex = baseType.lastIndexOf('.');
                    this.pack = new JavaPackage(baseType.substring(0, ptIndex));
                } else {
                    this.pack = JavaPackage.DEFAULT;
                }
                break;
            case BC:
                for (int i = 0; i < hrBaseTypes.length; i++) {
                    assert !baseType.equals(hrBaseTypes[i]);
                }
                if (baseType.contains("/")) {
                    int slIndex = baseType.lastIndexOf("/");
                    this.pack = new JavaPackage(baseType.substring(1, slIndex).replaceAll("/", "."));
                } else {
                    this.pack = JavaPackage.DEFAULT;
                }

                if (!isPrimitiveBC(baseType) && !baseType.endsWith(";")) {
                    baseType += ";";
                }

                break;
            default:
                throw new IllegalStateException();
        }
        this.baseType = baseType;
        this.bcStringWOTS = mkBCChars(false);
        this.bcStringWTS = mkBCChars(true);
        this.hashCode = this.bcStringWTS.hashCode();
    }

    private boolean isPrimitiveBC(String baseType2) {
        for (String bcBaseType : bcBaseTypes) {
            if (bcBaseType.equals(baseType2)) {
                return true;
            }
        }

        return false;
    }

    public JavaPackage getPackage() {
        return pack;
    }

    public String toBCString() {
        return toBCString(true);
    }

    public String toBCString(final boolean trailingsemicolon) {
        if (trailingsemicolon) {
            return bcStringWTS;
        } else {
            return bcStringWOTS;
        }
    }

    private String mkBCChars(final boolean trailingsemicolon) {
        StringBuilder sbBC = new StringBuilder("");
        for (int i = 0; i < arrDim; i++) {
            sbBC.append('[');
        }
        if (format == Format.HR) {
            if ("void".equals(baseType)) {
                sbBC.append('V');
            } else if ("boolean".equals(baseType)) {
                sbBC.append('Z');
            } else if ("char".equals(baseType)) {
                sbBC.append('C');
            } else if ("byte".equals(baseType)) {
                sbBC.append('B');
            } else if ("short".equals(baseType)) {
                sbBC.append('S');
            } else if ("int".equals(baseType)) {
                sbBC.append('I');
            } else if ("long".equals(baseType)) {
                sbBC.append('J');
            } else if ("float".equals(baseType)) {
                sbBC.append('F');
            } else if ("double".equals(baseType)) {
                sbBC.append('D');
            } else {
                sbBC.append('L');
                sbBC.append(baseType.replaceAll("\\.", "/"));
                if (trailingsemicolon) sbBC.append(';');
            }
        } else {
            sbBC.append(baseType);
        }
        return sbBC.toString();
    }

    public String toHRString() {
        StringBuilder sbHR = new StringBuilder("");
        if (format == Format.BC) {
            switch (baseType.charAt(0)) {
                case 'V':
                    sbHR.append("void");
                    break;
                case 'Z':
                    sbHR.append("boolean");
                    break;
                case 'C':
                    sbHR.append("char");
                    break;
                case 'B':
                    sbHR.append("byte");
                    break;
                case 'S':
                    sbHR.append("short");
                    break;
                case 'I':
                    sbHR.append("int");
                    break;
                case 'J':
                    sbHR.append("long");
                    break;
                case 'F':
                    sbHR.append("float");
                    break;
                case 'D':
                    sbHR.append("double");
                    break;
                case 'L':
                    sbHR.append(baseType.replace("/", ".").replace(";", "")
                            .substring(1));
                    break;
            }
        } else {
            sbHR.append(baseType);
        }
        for (int i = 0; i < arrDim; i++)
            sbHR.append("[]");

        return sbHR.toString();
    }

    public String toString() {
        return toBCString();
    }

    public String toHRStringShort() {
        String hr = toHRString();
        int offset = hr.lastIndexOf('.');
        if (offset >= 0) {
            return hr.substring(offset + 1);
        } else {
            return hr;
        }
    }


    public static JavaType parseSingleTypeFromString(String s, Format f) {
        int arrDim;
        String baseType;
        switch (f) {
            case BC:
                if (s.contains("[")) {
                    arrDim = s.lastIndexOf('[') + 1;
                    baseType = s.substring(arrDim);
                } else {
                    arrDim = 0;
                    baseType = s;
                }
                break;
            case HR:
                if (s.contains("[")) {
                    arrDim = (s.length() - s.indexOf('[')) / 2;
                    baseType = s.substring(0, s.indexOf('['));
                } else {
                    arrDim = 0;
                    baseType = s;
                }
                break;
            default:
                throw new IllegalStateException();
        }

        return new JavaType(f, baseType, arrDim);
    }


    public static JavaType parseSingleTypeFromString(String s) {
        if (s.startsWith("[") || (s.startsWith("L") && s.contains("/"))) {
            /**
             * If s starts with "[", then it denotes an array type in bytecode format (Class names must not contain "[").
             * If it starts with L and contains "/", it describes some reference type in some package - in bytecode notation.
             * If s does not start with "[" and does not contain "L" or "/", then it is definitely not in bytecode format
             * (Since we assume, that s does not represent a primitive type).
             */
            return parseSingleTypeFromString(s, Format.BC);
        } else if (s.endsWith("[]") || (s.contains("."))) {
            /**
             * If s ends with with "[]", then it denotes an array type in human-readable format (Class names must not contain "[" or "]").
             * If it contains ".", it describes some reference type in some package - in human-readable format (class names must not contain "."
             * and the package-separator character in bytecode format is "/").
             * In every other case, s is definitely not in human-readable format.
             */
            return parseSingleTypeFromString(s, Format.HR);

        } else {
            /**
             * s does not start with "[", does not end with "[]", and contains neither "/" nor "."
             * Either s is some malformed junk or a class in the default package. In the former case,
             * no format is applicable, in the latter case, no format can be excluded.
             */
            return null;
        }
    }


    public static List<JavaType> parseListOfTypesFromString(String s, Format f) {
        List<JavaType> ret = new LinkedList<JavaType>();
        switch (f) {
            case BC:
                StringBuilder rest = new StringBuilder(s);
                while (rest.length() > 0) {
                    int curLen = rest.length();
                    JavaType next = parseSingleBCTypeFromStringPrefix(rest);
                    if (next == null || rest.length()==curLen) {
                        return null;
                    } else {
                        ret.add(next);
                    }
                }
                break;
            case HR:
                String[] singleTypes = s.split("\\s*,\\s*");
                for (String singleType : singleTypes) {
                    ret.add(parseSingleTypeFromString(singleType, f));
                }
                break;
        }

        return ret;
    }

    private static JavaType parseSingleBCTypeFromStringPrefix(StringBuilder s) {
        int arrDim = 0;
        String baseType;
        int cutoff;
        if (s.charAt(0) == '[') {
            do {
                arrDim++;
            } while (s.charAt(arrDim)=='[');
        }

        if (s.charAt(arrDim) == 'L') {
            if (s.indexOf(";") >= 0) {
                baseType = s.substring(arrDim, s.indexOf(";") + 1);
            } else {
                baseType = s.toString().substring(arrDim) + ";";
            }
            cutoff = arrDim + baseType.length();
        } else {
            baseType = s.substring(arrDim, arrDim + 1);
            cutoff = arrDim + 1;
        }

        JavaType ret = new JavaType(Format.BC, baseType, arrDim);
        s.delete(0, cutoff);
        return ret;
    }


    @Override
    public int hashCode() {
        return hashCode;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof JavaType)) {
            return false;
        }
        JavaType other = (JavaType) obj;
        return bcStringWTS.equals(other.bcStringWTS);
    }

    public boolean isString() {
        return this.toBCString().equals("Ljava/lang/String;");
    }

    public boolean isBoolean() {
        return this.toBCString().equals("Z");
    }

    public boolean isNumeric() {
        return this.toBCString().equals("I");
    }


}